const crypto = require('crypto');
const mysql = require("../database.js");

const login = function () {};

login.prototype.loginUser = (req, res, callback) => {    
    function hashPassword(password, salt) {
        salt = salt || crypto.randomBytes(128).toString('base64');

        let hash = crypto.pbkdf2Sync(password, salt, 10000, 512, 'sha512');
        return { "hash": hash.toString('hex'), "salt":  salt };
    }

    let response = {
        "exito": false,
        "error": "",
        "data": null
    };    
    
    let userData = req.body;
    const query = `SELECT usuario.*, 
                          directorio.id_directorio 
                     FROM usuario, 
                          directorio 
                    WHERE directorio.id_directorio_padre IS NULL 
                      AND directorio.id_usuario = usuario.id_usuario 
                      AND usuario.email = '${userData.email}'`;
          
	global.SQLpool.getConnection((err, connection) => {
        connection.query(query, (err, rows, fields) => {
            if (err) {
                console.log(`Error al ejecutar SELECT en MySQL!\n${err}`);
                response.error = "Error al buscar usuario en la base de datos.";
            }
            
            connection.release();

            if (rows.length > 0) {
                userData.contrasena = hashPassword(userData.contrasena, rows[0].salt);   
                if (userData.contrasena.hash === rows[0].contrasena) {
                    response.exito = true;
                    response.error = "";
                    response.data = rows[0];
                } else {
                    response.error = "Contrasena incorrecta.";
                }
            } else {
                response.error = "Correo no encontrado.";
            }
    
            callback(response);
        });
	});
}

module.exports = new login();