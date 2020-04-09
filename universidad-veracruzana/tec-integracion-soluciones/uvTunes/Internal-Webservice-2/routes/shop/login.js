const mysql = require('mysql');
const crypto = require('crypto');

const configData = require('../../config.json');
const connectionData = configData.mysqlConnection;

module.exports = function login(request) {    
    const connection = mysql.createConnection(connectionData);

    function hashPassword(password, salt) {
        var hash = crypto.pbkdf2Sync(password, salt, 10000, 512, 'sha512');
        return hash.toString('hex');
    }
        
    return new Promise((exito, falla) => connection.connect((err) => {
        let data = {
            "exito" : false,
            "error" : "Erro en el servidor"
        }

        if (err) {
            console.log("Error al conectar al MySQL -> " + err);
            exito(data);
        }
        
        let sql = `SELECT * 
                       FROM cliente 
                      WHERE email = '${request.email}'`;
                        
        connection.query(sql, function (err, result) {
            if (err) {
                console.log("Error al intentar hacer login -> " + err);
                exito(data);
            }
            
            data.error = "Correo no encontrado";
            
            if (result.length > 0) {
                let password = hashPassword(request.contrasena, result[0].salt);            
                data.error = "Combinación inválida";

                if (password === result[0].contrasena) {
                    data.error = "Cuenta no activada"

                    if (result[0].activado) {                        
                        data.error = "Su cuenta está deshabilitada.\nContacte el administrador"

                        if (result[0].habilitado) {
                            data.exito = true;
                            data.error = null;
                            data.registro = result[0];

                            let fechan = data.registro["fecha_nacimiento"];
                            data.registro["fecha_nacimiento"] = ("0" + fechan.getDate()).slice(-2) + "/" + ("0" + (fechan.getMonth() + 1)).slice(-2) + "/" + fechan.getFullYear();

                            // Quita los campos inecesarios
                            ["contrasena", "salt", "activado", "habilitado", "token"].forEach(function (key) {
                                delete data.registro[key];
                            })
                            
                            // Guarda los datos en la sesión
                            // request.session.store = data.registro;
                        }
                    } 
                }
            }
            
            exito(data);
        });
    }));
};