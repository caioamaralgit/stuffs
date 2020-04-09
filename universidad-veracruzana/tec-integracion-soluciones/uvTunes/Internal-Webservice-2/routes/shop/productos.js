var mysql = require('mysql');

var dataConnection = require('../../config.json');
var connectionData = dataConnection.mysqlConnection;

module.exports = function productos(router) {
    const connection = mysql.createConnection(connectionData);
    
    return new Promise((exito, falla) => connection.connect((err) => {
        if (err) {
            console.log("Error al conectar al MySQL -> " + err);
            exito([]);
        }

        connection.query(`SELECT proveedor.nombre AS proveedor, 
                                 productora.nombre AS productora, 
                                 artistas.artistas,
                                 producto.*
                            FROM producto,
                                 proveedor,
                                 productora,
                                 (SELECT id_producto, 
                                         GROUP_CONCAT(nombre SEPARATOR ' / ') AS artistas
                                    FROM artista, 
                                         artista_producto 
                                   WHERE artista.id_artista = artista_producto.id_artista 
                                GROUP BY id_producto) artistas
                           WHERE producto.habilitado = 1
                             AND producto.nombre != ''
                             AND producto.id_productora = proveedor.id_proveedor 
                             AND producto.id_productora = productora.id_productora 
                             AND producto.id_producto = artistas.id_producto
                        ORDER BY producto.id_producto`, function (error, results) {
            if (error) console.log("Error al buscar productos -> " + err);
            exito(results);
        });
    }));    
};