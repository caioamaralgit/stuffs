const crypto = require('crypto');
const mysql = require("../database.js");

const register = function () {};

register.prototype.registerUser = (req, res, callback) => {    
    function hashPassword(password, salt) {
        salt = salt || crypto.randomBytes(128).toString('base64');

        let hash = crypto.pbkdf2Sync(password, salt, 10000, 512, 'sha512');
        return { "hash": hash.toString('hex'), "salt":  salt };
    }

    let response = {
        "exito": false,
        "error": ""
    };    

    let userData = req.body;
    let password = hashPassword(userData.contrasena);
    
    userData.contrasena = password.hash;
    userData.salt = password.salt;
    userData.token = crypto.randomBytes(16).toString('hex');
          
	global.SQLpool.getConnection((err, connection) => {
        connection.query(`INSERT INTO usuario SET ?`, userData, (err, result) => {
            if (err) {
                console.log(`Error al ejecutar INSERT en MySQL!\n${err}`);
                response.error = "Error al inserir en la base de datos.";
            } else {
                response.exito = true;
                response.user = result.insertId;
            }
            
            connection.release();
            callback(response);
        });
	});
}

module.exports = new register();