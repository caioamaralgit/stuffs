const mysql = require('mysql');
const needle = require('needle');
const nodemailer = require('nodemailer');

const routes = require('../Routes');
const configData = require('../../config.json');
const connectionData = configData.mysqlConnection;

module.exports = function buy(datos) {     
    const connection = mysql.createConnection(connectionData);

    function getDateNow() { // Creado por "Toskan" en https://stackoverflow.com/questions/926332/how-to-get-formatted-date-time-like-2009-05-29-215557-using-javascript
        var data = new Date();    
        data = data.getFullYear() + "-" + ('0' + (data.getMonth() + 1)).slice(-2) + "-" + ('0' + data.getDate()).slice(-2) + " " + ('0' + data.getHours()).slice(-2) + ":" + ('0' + data.getMinutes()).slice(-2) + ":" + ('0' + data.getSeconds()).slice(-2);
    
        return data;
    }

    function prepararCampos(datos) { // Trata los campos antes de enviarlos para inserción
        datos.fecha_registro = datos.fecha_finalizacion = getDateNow();
        datos.status = "F";        

        datos.articulos.forEach((item) => {
            if (item[1] === "F") {
                datos.status = "P";
                datos.fecha_finalizacion = null;
            }
        });        

        return {
            "email": datos.email,
            "pedido": {
                "id_cliente": datos.id_cliente,
                "monto": datos.monto,
                "status": datos.status,
                "fecha_registro": datos.fecha_registro,
                "fecha_finalizacion": datos.fecha_finalizacion
            },
            "articulos": datos.articulos,
            "envio": datos.envio,
            "facturar": datos.facturar
        };
    }

    function solicitarFactura(ticket) {
        const newCon = mysql.createConnection(connectionData);
        newCon.connect((err) => {
            if (!err) {
                newCon.query(`SELECT p.*, email FROM pedido p, cliente WHERE cliente.id_cliente = p.id_cliente AND p.ticket = ${ticket}`, (err, compra) => {
                    if (!err) {
                        newCon.query(`SELECT * FROM v_articulo_pedido WHERE ticket = ${ticket}`, (err, articulo_pedido) => {
                            newCon.end();

                            let conceptos = [];

                            articulo_pedido.forEach((item) => {
                                conceptos.push({
                                    "concepto": item.nombre,
                                    "costo": item.precio,
                                    "total": compra[0].monto
                                });
                            });

                            let datosFactura = {
                                "rfc": "U2BU5T10",
                                "nombre": "uvTunes Musica y Video",
                                "conceptos": conceptos,
                                "fecha": compra[0].fecha_registro
                            }

                            needle.post(routes.getHacienda("facturar"), datosFactura, function(error, response) {
                                if (!error && response.statusCode == 200)
                                  console.log(response.body);
                            });
                        });
                    }
                });
            }
        });
    }

    function solicitarEnvio(datosEnvio, email, ticket) {
        needle.post("http://148.226.81.33:8084/PaqueteriaSW/api/paqueteria/envio", datosEnvio, (err, response) => {
            if (!err && response.body) {
                console.log(response.body);
                const newCon = mysql.createConnection(connectionData);
                newCon.connect((err) => {
                    if (!err) {
                        newCon.query(`UPDATE pedido 
                                         SET codigo_rastreo = '${response.body.idr}'
                                       WHERE ticket = ${ticket}`, (err) => {
                            if (!err) generateMail(ticket);
                            newCon.end();
                        });
                    }     
                });           
            }
        });
    }

    function sendMail(html, email) {
        const mailOptions = {
            from: 'uvTunes <no-reply@uvtunes.com.mx>',
            to: email,
            subject: 'Nueva compra',
            html: html
        };
        
        nodemailer.createTransport(configData.mailConfig).sendMail(mailOptions);
    }
    
    function generateMail(ticket) {
        const newCon = mysql.createConnection(connectionData);
        newCon.connect((err) => {
            if (!err) {
                newCon.query(`SELECT p.*, email FROM pedido p, cliente WHERE cliente.id_cliente = p.id_cliente AND p.ticket = ${ticket}`, (err, compra) => {
                    if (!err) {
                        newCon.query(`SELECT * FROM v_articulo_pedido WHERE ticket = ${ticket}`, (err, articulo_pedido) => {
                            newCon.end();

                            let articulos = "";
                    
                            articulo_pedido.forEach((item) => {
                                articulos += `<tr>
                                                  <td>${item.cantidad}</td>
                                                  <td>${item.nombre}</td>
                                                  <td>${item.version === "D" ? "Digital" : "Físico"}</td>
                                                  <td>$${item.precio}</td>
                                              </tr>`;
                            });
                    
                            sendMail(`<html>
                                        <body style="text-align: center;">
                                            <div>
                                                Una nueva compra fue registrada: <br /><br />
                                                <div style="display: table; margin: 0px auto; text-align: left;">
                                                    <b>Ticket:</b> #${compra[0].ticket} <br />
                                                    <b>Fecha:</b> ${compra[0].fecha_registro} <br />
                                                    ${compra[0].codigo_rastreo ? `<b>Código de Rastreo:</b> ${compra[0].codigo_rastreo}<br />` : ""}
                                                    <b>Total:</b> $${compra[0].monto} <br /><br />
                                                    <b>Articulos:</b> <br /><br />
                                                    <table>
                                                        <thead>
                                                            <th>Cantidad</th>
                                                            <th>Nombre</th>
                                                            <th>Version</th>
                                                            <th>Precio</th>
                                                        </thead>
                                                        <tbody>
                                                            ${articulos}
                                                        </tbody>
                                                    </table><br /><br />
                                                </div>
                                                Gracias por comprar en la uvTunes! <br /><br />
                                            </div>
                                        </body>
                                    </html`, compra[0].email);
                        });
                    }
                });
            }
        });
    }

    datos = prepararCampos(datos);

    let response = {
        "exito": false,
        "error": "Error en la base de datos."
    };

    return new Promise((exito) => connection.connect((err) => {
        if (err) {
            console.log("Error al conectar al MySQL -> " + err);
            exito(response);
        }
                        
        let query = connection.query('INSERT INTO pedido SET ?', datos.pedido, function (err, result) {
            if (err) {
                console.log("Error al registrar pedido -> " + err);
                response.error = "Falla al generar pedido.";
                exito(response);
            }

            const ticket = result.insertId;

            datos.articulos.forEach((item, index) => {
                datos.articulos[index].push(ticket);
            });
            
            connection.query(`INSERT INTO articulo_pedido (id_producto, version, cantidad, precio, ticket) VALUES ?`, [datos.articulos], function (err, result) {
                if (err) {
                    console.log("Error al registrar articulos del pedido -> " + err);
                    response.error = "Falla al registrar articulos.";
                    exito(response);
                }
                
                connection.end();

                if (datos.pedido.status === "P") solicitarEnvio(datos.envio, datos.email, ticket);
                else generateMail(ticket);

                if (datos.facturar) solicitarFactura(ticket);

                response.exito = true;
                response.error = "";

                exito(response);
            });
        });
    }));
};