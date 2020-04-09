const mysql = require("../database.js");
const requ = require('request');
const routes = require('../../config/routes.json');

const directories = function () {};

directories.prototype.newFiles = (request, callback) => {    
    let response = {
        "exito": false,
        "error": ""
    };    

    let query = `INSERT INTO archivo (id_usuario, id_directorio, portada, archivo, nombre, resumen) VALUES ?`;
    let files = [];

    if (typeof request.body.archivos === 'string' || request.body.archivos instanceof String) {
        request.body.archivos = [ request.body.archivos ];
    }

    request.body.archivos.forEach((file, index) => {
        files.push([ request.userId, 
                     request.body.id_directorio, 
                     `${file}.png`, 
                     `${file}.pdf`, 
                     (request.body.archivos.length > 1 ? `${request.body.nombre}-${index}` : request.body.nombre), 
                     request.body.resumen]);
    });
          
	global.SQLpool.getConnection((err, connection) => {
        if (!err) {        
            connection.query(query, [files], (err) => {
                if (err) {
                    console.log(`Error al ejecutar INSERT de directorio en MySQL!\n${err}`);
                    response.error = "Error al inserir en la base de datos.";
                } else {
                    response.exito = true;
                }
                
                connection.release();
                if (callback) callback(response);
            });
        }
	});
}

directories.prototype.moveFile = (request, callback) => {    
    let response = {
        "exito": false,
        "error": ""
    };    
    
	global.SQLpool.getConnection((err, connection) => {
        if (!err) console.log("Error en la conexión del MySQL.");

        const values = [
            request.body.id_directorio,
            request.body.id_archivo,
            request.userId
        ]
        
        connection.query(`UPDATE archivo SET id_directorio = ? WHERE id_archivo = ? AND id_usuario = ? `, values, (err, result) => {
            if (err) {
                console.log(`Error al buscar archivo en MySQL!\n${err}`);
                response.error = "Error al buscar el archivo.";
            } else {                
                const options = {
                    uri: `${routes["file-server"]}/move`,
                    headers: {
                      'Authorization': request.headers["authorization"]
                    },
                    method: 'POST',
                    json: true,
                    body: { 'fileName' : request.body.nombre, 'fileDir' : request.body.directorio, 'fileOldDir' : request.body.directorio_anterior }
                }
                console.log(options);
    
                requ(options, (error, resp, body) => {
                    if (error || !body.exito) {
                        if (error) console.log(`Error al comunicarse con el segundo servidor!\n\n${error}`)
                        else console.log(body.error);
                    }
                    
                    response.exito = body.exito;
                    
                    if (callback) callback(response);
                });
            }
            
            connection.release();
        });
    });
};

directories.prototype.openFile = (request, callback) => {    
    let response = {
        "exito": false,
        "error": ""
    };    
    
	global.SQLpool.getConnection((err, connection) => {
        if (!err) console.log("Error en la conexión del MySQL.");
        
        connection.query(`SELECT * FROM archivo WHERE id_archivo = ? AND id_usuario = ? `, [ request.query.id_archivo, request.userId ], (err, result) => {
            if (err) {
                console.log(`Error al buscar archivo en MySQL!\n${err}`);
                response.error = "Error al buscar el archivo.";
            } else {
                if (result.length > 0) {
                    response.exito = true;
                    response.archivo = {
                        nombre: result[0].nombre,
                        resumen: result[0].resumen
                    }
                } else {
                    response.error = "Archivo indisponible."
                }
            }
            
            connection.release();
            if (callback) callback(response);
        });
    });
};

module.exports = new directories();