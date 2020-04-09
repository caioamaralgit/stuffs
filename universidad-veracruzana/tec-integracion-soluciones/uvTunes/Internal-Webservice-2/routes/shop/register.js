const crypto = require('crypto');
const mysql = require('mysql');
const nodemailer = require('nodemailer');
const TokenGenerator = require('uuid-token-generator');

const configData = require('../../config.json');
const connectionData = configData.mysqlConnection;

module.exports = function register(data) {     
    const connection = mysql.createConnection(connectionData);

    function getDateNow() { // Creado por "Toskan" en https://stackoverflow.com/questions/926332/how-to-get-formatted-date-time-like-2009-05-29-215557-using-javascript
        var data = new Date();    
        data = data.getFullYear() + "-" + ('0' + (data.getMonth() + 1)).slice(-2) + "-" + ('0' + data.getDate()).slice(-2) + " " + ('0' + data.getHours()).slice(-2) + ":" + ('0' + data.getMinutes()).slice(-2) + ":" + ('0' + data.getSeconds()).slice(-2);
    
        return data;
    }
    
    function generateToken() {
        const tokgen = new TokenGenerator();
        let token = tokgen.generate();

        return token.substr(0, 10);
    }

    function hashPassword(password) {
        var salt = crypto.randomBytes(128).toString('base64');
        var hash = crypto.pbkdf2Sync(password, salt, 10000, 512, 'sha512');
        return { "password" : hash.toString('hex'), "salt" : salt };
    }

    function prepararCampos(datos) { // Trata los campos antes de enviarlos para inserción
        Object.keys(datos).map(function (key) {
            if (key === "contrasena") {
                const password = hashPassword(datos[key].trim() === "" ? "1234" : datos[key]);

                datos[key] = password.password;
                datos["salt"] = password.salt;
            } else if (key === "fecha_nacimiento") {
                datos[key] += " 00:00:00";
            } else if (datos[key].trim() === "") {
                datos[key] = null;
            }

            const token = generateToken();
            datos["token"] = token;
            datos["fecha_registro"] = getDateNow();
        });

        return datos;
    }
    
    function generateMail(token) {
        return `<html>
                    <body style="text-align: center;">
                        <img src="https://uvtunes-client.herokuapp.com/static/media/Logo.43a58311.png" alt="uvTunes" width="230" /><br /><br />
                        <div>
                            Gracias por registrarte en uvTunes! <br /><br />
                            Para activar su cuenta <a href="https://uvtunes-client.herokuapp.com/?token=` + token + `" target="_blank">pulse aqui</a>. Caso no funcione, use este link: <br />
                            <div style="background-color: #EFEFEF; display: table; font-family: Consolas, Verdana; font-size: 0.8em; margin: 10px auto; padding: 10px;">https://uvtunes-client.herokuapp.com/?token=` + token + `</d>
                        </div>
                    </body>
                </html>`;
    }

    let datos = prepararCampos(data);

    return new Promise((exito) => connection.connect((err) => {
        if (err) {
            console.log("Error al conectar al MySQL -> " + err);
            exito(false);
        }
                        
        let query = connection.query('INSERT INTO cliente SET ?', datos, function (err, result) {
            if (err) {
                console.log("Error al registrar usuario -> " + err);
                exito(false);
            }

            connection.end();
          
            const mailOptions = {
                from: 'uvTunes <no-reply@uvtunes.com.mx>',
                to: datos.email.replace(/'/g, ''),
                subject: 'Activate your account',
                html: generateMail(datos.token.replace(/'/g, ''))
            };

            const transporter = nodemailer.createTransport(configData.mailConfig);

            transporter.sendMail(mailOptions, (err) => {
                if (err) {
                    console.log("Error al enviar código de activacion -> " + err);
                    //exito(false);
                }

                exito(true);
            });
        });
    }));
};