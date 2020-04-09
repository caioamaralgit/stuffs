const crypto = require('crypto');
const mysql = require("../database.js");

const reset = function () {};

reset.prototype.requestReset = (req, res, callback) => {    
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
        connection.query(`INSERT INTO usuario SET ?`, userData, (err) => {
            if (err) {
                console.log(`Error al ejecutar INSERT en MySQL!\n${err}`);
                response.error = "Error al inserir en la base de datos.";
            } else {
                response.exito = true;
            }
            
            connection.release();
            callback(response);
        });
	});
}

reset.prototype.reset = (req, res, callback) => {
}

reset.prototype.verifyToken = (req, res, callback) => {
}

module.exports = new reset();