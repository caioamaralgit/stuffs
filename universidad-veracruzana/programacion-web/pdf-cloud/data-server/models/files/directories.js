const mysql = require("../database.js");
const request = require('request');
const routes = require('../../config/routes.json');

const directories = function () {};

directories.prototype.createDirectory = (data, callback) => {    
    let response = {
        "exito": false,
        "error": ""
    };    

    const values = {
        id_directorio_padre: (data.body !== undefined ? data.body.id_directorio : null),
        id_usuario: (data.body !== undefined ? data.userId : data.id_usuario),
        nombre: (data.body !== undefined ? data.body.nombre : data.nombre)
    }
          
	global.SQLpool.getConnection((err, connection) => {
        connection.query(`INSERT INTO directorio SET ?`, values, (err) => {
            if (err) {
                console.log(`Error al ejecutar INSERT de directorio en MySQL!\n${err}`);
                response.error = "Error al inserir en la base de datos.";
            } else {
                const options = {
                    uri: `${routes["file-server"]}/${(data.body !== undefined ? "folder" : "auth/register")}`,
                    headers: {
                      'Authorization': (data.body !== undefined ? data.headers["authorization"] : null)
                    },
                    method: 'POST',
                    json: true,
                    body: { 'folderName' : data.body.nombre, 'folderDir' : data.body.directorio }
                }
    
                request(options, (error, resp, body) => {
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
}

directories.prototype.showDirectories = (user, callback) => {
    let response = {
        "exito": false,
        "error": ""
    };    
          
	global.SQLpool.getConnection((err, connection) => {
        connection.query(`SELECT * FROM directorio WHERE id_usuario = ${user} AND id_directorio_padre IS NOT NULL ORDER BY id_directorio_padre, nombre`, (err, results) => {
            if (err) {
                console.log(`Error al buscar directorio en MySQL!\n${err}`);
                response.error = "Error al inserir en la base de datos.";
            } else {
                response.exito = true;
                response.directorios = [];
                
                let allDirectories = { };

                results.forEach((directorio) => {
                    // Caso no exista el directorio padre en la variable allDirectories, lo crea con el atributo "subdirectorios"
                    if (allDirectories[directorio.id_directorio_padre] === undefined) allDirectories[directorio.id_directorio_padre] = { "subdirectorios" : [] }

                    /* Caso no exista este directorio en la variable allDirectories, la crea. Caso ya exista significa que un hijo la creo, 
                    * pero solamente con los datos de los subdirectorios. En los dos casos guarda los datos del directorio en allDirectories. */
                    if (allDirectories[directorio.id_directorio] === undefined) {
                        directorio.subdirectorios = [];
                        allDirectories[directorio.id_directorio] = directorio;
                    } else {
                        Object.assign(allDirectories[directorio.id_directorio], directorio);
                    }

                    // Eso crea una referencia al objecto directorio. Siempre que otro directorio cambiarlo, cambiará también ese abajo.
                    allDirectories[directorio.id_directorio_padre]["subdirectorios"].push(directorio);
                });

                if (Object.keys(allDirectories).length > 0) response.directorios = allDirectories[Object.keys(allDirectories)[0]]["subdirectorios"];
            }
            
            connection.release();
            callback(response);
        });
	});
}

directories.prototype.openDirectory = (request, callback) => {
    let response = {
        "exito": false,
        "error": ""
    };    
          
	global.SQLpool.getConnection((err, connection) => {
        connection.query(`SELECT * FROM directorio WHERE id_usuario = ${request.userId} AND id_directorio = ${request.query.id_directorio}`, (err, results) => {
            if (err) {
                console.log(`Error al buscar directorios en MySQL!\n${err}`);
                response.error = "Error al buscar directorio.";
            } else {
                response.nombre = (results[0].id_directorio_padre === null ? "raiz" : results[0].nombre);

                connection.query(`SELECT * FROM directorios_directorio WHERE id_usuario = ${request.userId} AND id_directorio_padre = ${request.query.id_directorio} ORDER BY nombre`, (err, results) => {
                    response.elementos = [];
        
                    if (err) {
                        console.log(`Error al buscar directorios en MySQL!\n${err}`);
                        response.error = "Error al inserir en la base de datos.";
                    } else {
                        response.exito = true;
        
                        results.forEach((directorio) => {
                            response.elementos.push({ 
                                "tipo": "directorio",
                                "id": directorio.id_directorio,
                                "nombre": directorio.nombre,
                                "detalles": null
                            })
                        });
        
                        connection.query(`SELECT * FROM archivos_directorio WHERE id_usuario = ${request.userId} AND id_directorio = ${request.query.id_directorio} ORDER BY ${request.query.orden}`, (err, results) => {
                            if (err) {
                                console.log(`Error al buscar directorios en MySQL!\n${err}`);
                                response.error = "Error al inserir en la base de datos.";
                                response.exito = false;
                            } else {
                                results.forEach((archivo) => {
                                    response.elementos.push({ 
                                        "tipo": "archivo",
                                        "id": archivo.id_archivo,
                                        "nombre": archivo.nombre,
                                        "detalles": {
                                            "portada": archivo.portada,
                                            "archivo": archivo.archivo,
                                            "resumen": archivo.fecha_creacion,
                                            "favorito": archivo.favorito
                                        }
                                    })
                                }); 
                            }
        
                            connection.release();
                            callback(response);
                        });
                    }            
                });
            }
        });
    });
}

module.exports = new directories();