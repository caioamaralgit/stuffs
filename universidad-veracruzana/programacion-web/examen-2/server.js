const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');

const connectionData = {
    host     : '127.0.0.1',
    user     : 'uvtunes',
    password : 'DJdmqeMlyn!',
    database : 'tinderuv'
};

const jsonParser = bodyParser.json();
const urlencodedParser = bodyParser.urlencoded({ extended: false });

const app = express();

app.use(express.static(__dirname + '/public'));

app.get('/action/lista-usuarios', urlencodedParser, (request, response) => {
    let responseObj = {
        "exito": false,
        "error": ""
    };    

    let connection = mysql.createConnection(connectionData);  
    
    connection.connect();
    connection.query(`SELECT * FROM usuario`, (err, rows, fields) => {
        if (err) {
            console.log(`Error al ejecutar SELECT en MySQL!\n${err}`);
            responseObj.error = "Error al buscar usuario en la base de datos.";
        } else {
            let usuarios = [];
            
            rows.forEach((usuario) => {
                delete usuario.contrasena;
                usuarios.push(usuario);
            });
    
            responseObj.exito = true;
            responseObj.usuarios = usuarios;
        }
        
        response.json(responseObj);
    });    
    
    connection.end();
});

app.post('/action/login', urlencodedParser,  (request, response) => {
    let responseObj = {
        "exito": false,
        "error": ""
    };    
    
    let userData = request.body;
    let connection = mysql.createConnection(connectionData);  

    connection.connect();
    connection.query(`SELECT * FROM usuario WHERE usuario = '${userData.usuario}' AND contrasena = '${userData.contrasena}'`, (err, rows, fields) => {
        if (err) {
            console.log(`Error al ejecutar SELECT en MySQL!\n${err}`);
            responseObj.error = "Error al buscar usuario en la base de datos.";
        }
        
        if (rows.length > 0) {
            userData.contrasena = hashPassword(userData.contrasena, rows[0].salt);   
            if (userData.contrasena.hash === rows[0].contrasena) responseObj.exito = true;
            else responseObj.error = "Contrasena incorrecta.";
        } else {
            responseObj.error = "Email no encontrado.";
        }

        response.json(responseObj);
    });    
    
    connection.end();
});

app.post('/action/registrar', urlencodedParser,  (request, response) => {
    let userData = request.body;
    userData.genero = userData.genero === 'masculino' ? 'M' : 'F';
    
    let connection = mysql.createConnection(connectionData);  

    connection.connect();
    connection.query(`INSERT INTO usuario SET ?`, userData, (err, rows, fields) => {
        if (err) {
            console.log(`Error al ejecutar INSERT en MySQL!\n${err}`);
            response.redirect('/registro.html?error=true');
        } else {
            response.redirect('/login.html');
        }
    });    
    
    connection.end();
});

app.listen(3000, function () {
  console.log('Servidor ejecutando en el puerto 3000!');
});