const mysql = require('mysql');

const configData = require('../../config.json');
const connectionData = configData.mysqlConnection;

module.exports = function verifyEmail(request) {    
    const connection = mysql.createConnection(connectionData);
        
    return new Promise((exito, falla) => connection.connect((err) => {
        let data = {
            "exito" : false,
            "error" : "Erro en el servidor"
        }

        if (err) {
            console.log("Error al conectar al MySQL -> " + err);
            exito(data);
        }
        
        const sql = `SELECT * 
                       FROM cliente 
                      WHERE email = '` + request.email + `'`;
                        
        connection.query(sql, function (err, result) {
            if (err) {
                console.log("Error al intentar hacer login -> " + err);
                exito(data);
            }
            
            data.exito = true;
            data.error = null;
            data.free = (result.length > 0 ? false : true);

            exito(data);
        });
    }));
};