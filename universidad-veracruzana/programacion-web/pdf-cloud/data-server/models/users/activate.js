const mysql = require("../database.js");

const activate = function () {};

activate.prototype.activateUser = (req, res, callback) => {
    let response = {
        "exito": false,
        "error": ""
    };    
    
    let userData = req.body;
    const query = `UPDATE usuario SET activado = 1, token = null WHERE token = '${userData.token}'`;
    
	global.SQLpool.getConnection((err, connection) => {
        connection.query(query, (err, rows, fields) => {
            if (err) {
                console.log(`Error al ejecutar SELECT en MySQL!\n${err}`);
                response.error = "Error al buscar usuario en la base de datos.";
            }
            
            connection.release();

            if (rows.affectedRows > 0) {
                response.exito = true;
                response.error = "";
            } else {
                response.error = "Token no encontrado.";
            }
    
            callback(response);
        });
	});
}

module.exports = new activate();