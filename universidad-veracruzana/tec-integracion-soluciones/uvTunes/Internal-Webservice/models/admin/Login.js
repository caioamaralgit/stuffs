'use strict';

const dataConnection = require('../../config/config.json');
const connectionData = dataConnection.mysqlConnection;
const mysql = require('mysql');

module.exports =  async function Login(request) {    
    const connection = mysql.createConnection(connectionData);    
    await connection.connect();

    async function returnResults(query, subquery = "", con = connection) {        
        let result;k

        await new Promise((e) =>
        con.query(query, (err, rows, fields) => {
            console.log(query); 

            let tableData = {
                "columns" : [],
                "rows" : { }
            };

            fields.forEach((fieldObj) => {
                tableData["columns"].push(fieldObj.name);
            });
            
            
            rows.forEach(async (rowObject) => {
                let row_id = rowObject[Object.keys(rowObject)[0]];
                
                tableData.rows[row_id] = rowObject;
                
                if (subquery !== "") 
                    tableData.rows[row_id][subquery.field] = await returnResults(subquery.query.replace('?', row_id));                
            });

            e(tableData);
        })
    ).then((e) => { result = e });
        
        return result;
    }

    let response = {
        "exito": false,
        "error": "Falla en el servidor",
        "data": {}
    };

    let data = {};
    
    switch (request.page) {
        case "pedidos": 
            var results = await returnResults(`SELECT * FROM pedido ORDER BY ticket DESC`, { "field" : "articulos", "query" : "SELECT * FROM v_articulo_pedido WHERE ticket = ?"});
            data["pedidos"] = results;

            response.exito = true;
            response.error = "";
            response.data = data;
            break;
        case "productos": 
            var results = await returnResults(`SELECT * FROM producto`);
            data["productos"] = results;

            response.exito = true;
            response.error = "";
            response.data = data;
            break;
        case "clientes": 
            var results = await returnResults(`SELECT * FROM cliente`);
            data["clientes"] = results;

            response.exito = true;
            response.error = "";
            response.data = data;
            break;
        case "proveedores": 
            var results = await returnResults(`SELECT * FROM proveedor`);
            data["proveedores"] = results;

            response.exito = true;
            response.error = "";
            response.data = data;
            break;            
        case "administradores": 
            var results = await returnResults(`SELECT * FROM administrador`);
            data["administradores"] = results;

            response.exito = true;
            response.error = "";
            response.data = data;
            break;
    }
    
    return response;
};