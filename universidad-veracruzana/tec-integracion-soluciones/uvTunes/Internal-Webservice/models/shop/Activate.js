'use strict';

const mysql = require('mysql');

const configData = require('../../config/config.json');
const connectionData = configData.mysqlConnection;

module.exports = function Activate(param) {    
    const connection = mysql.createConnection(connectionData);

    return new Promise((exito) => connection.connect((err) => {
        let data = {
            "exito" : false,
            "error" : "Erro en el servidor"
        }

        if (err) {
            console.log("Error al conectar al MySQL -> " + err);
            exito(data);
        }
        
        const sql = `UPDATE cliente 
                        SET activado = true
                      WHERE token = '` + param.token + `'`;
                        
        connection.query(sql, function (err, result) {
            if (err) {
                console.log("Error al intentar activar cuenta -> " + err);
                exito(data);
            }
                        
            if (result.affectedRows > 0) data.exito = true,
            data.error = null
            
            exito(data);
        });
    }));
};
