const express = require('express');
const session = require('express-session');
const mysql = require('mysql');
const bodyParser = require('body-parser');

const connectionData = {
    host     : '127.0.0.1',
    user     : 'root',
    password : 'sarkhanvol',
    database : 'cumpleanos'
};

const jsonParser = bodyParser.json();
const urlencodedParser = bodyParser.urlencoded({ extended: false });

const app = express();

app.use(session({ secret: 'ordinario uv', cookie: { maxAge: 60000 }}));

app.get('/lista.html', (request, response) => {
    if (request.session.id_usuario === undefined) response.redirect('/login.html');
    else response.sendFile(`${__dirname}/public/lista.html`);
});

app.get('/login.html', (request, response) => {
    if (request.session.id_usuario !== undefined)  response.redirect('/lista.html');
    else response.sendFile(`${__dirname}/public/login.html`);
});

app.get('/registro.html', (request, response) => {
    if (request.session.id_usuario !== undefined) response.redirect('/lista.html');
    else response.sendFile(`${__dirname}/public/registro.html`);
});

app.get('/action/lista-usuarios', urlencodedParser, (request, response) => {
    let responseObj = {
        "exito": false,
        "error": ""
    };    

    let connection = mysql.createConnection(connectionData);  
    
    connection.connect();
    connection.query(`SELECT * FROM usuario WHERE id_usuario != ${request.session.id_usuario}`, (err, rows, fields) => {
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

app.get('/action/verificar-felicitaciones', urlencodedParser, (request, response) => {
    let responseObj = {
        "exito": false,
        "error": ""
    };    

    let connection = mysql.createConnection(connectionData);  
    
    connection.connect();
    connection.query(`SELECT nombre, fecha, id_notificacion 
                        FROM usuario, felicitaciones 
                       WHERE usuario.id_usuario = felicitaciones.id_usuario 
                         AND felicitaciones.visualizado = 0
                         AND felicitaciones.id_usuario_destino = ${request.session.id_usuario}`, (err, rows, fields) => {
        if (err) {
            console.log(`Error al ejecutar SELECT en MySQL!\n${err}`);
            responseObj.error = "Error al buscar felicitaciones en la base de datos.";
            connection.end();
        } else {
            let felicitaciones = [];
            let notificaciones = [];
            
            rows.forEach((felicitacion) => {
                felicitaciones.push(felicitacion);
                notificaciones.push(felicitacion.id_notificacion);
            });
    
            responseObj.exito = true;
            responseObj.felicitaciones = felicitaciones;

            if (notificaciones.length > 0) connection.query(`UPDATE felicitaciones SET visualizado = 1 WHERE id_notificacion IN (${notificaciones.join(",")})`);
            connection.end();
        }
        
        response.json(responseObj);
    });    
});

app.post('/action/felicitar-usuario', urlencodedParser, (request, response) => {
    let responseObj = { "exito": false };    

    let connection = mysql.createConnection(connectionData);  
    
    connection.connect();
    connection.query(`INSERT INTO felicitaciones (id_usuario, id_usuario_destino) VALUES (${request.session.id_usuario}, ${request.body.id_usuario})`, (err) => {
        if (!err) responseObj.exito = true;
        
        connection.end();
        response.json(responseObj);
    });
});

app.post('/action/registrar', urlencodedParser,  (request, response) => {
    let userData = request.body;    
    let connection = mysql.createConnection(connectionData);  

    for (let key in userData) {
        // Caso algun campo pasado sea vacio
        if (userData[key] === "") {            
            response.redirect('/registro.html?error=1');
            return;
        }
    }
    connection.connect();    
    connection.query(`SELECT * FROM usuario WHERE usuario = '${userData.usuario}'`, (err, rows, fields) => {
        if (rows.length > 0) {
            response.redirect('/registro.html?error=2');
            connection.end();
        } else {        
            connection.query(`INSERT INTO usuario SET ?`, userData, (err, rows, fields) => {
                if (err) {
                    console.log(`Error al ejecutar INSERT en MySQL!\n${err}`);
                    response.redirect('/registro.html?error=3');
                } else {
                    response.redirect('/login.html');
                }
            });       
            connection.end();
        }
    });    
});

app.post('/action/login', urlencodedParser,  (request, response) => {    
    let userData = request.body;
    let connection = mysql.createConnection(connectionData);  

    for (let key in userData) {
        // Caso algun campo pasado sea vacio
        if (userData[key] === "") {            
            response.redirect('/login.html?error=1');
            return;
        }
    }
console.log(`SELECT * FROM usuario WHERE usuario = '${userData.usuario}'`);
    connection.connect();
    connection.query(`SELECT * FROM usuario WHERE usuario = '${userData.usuario}'`, (err, rows, fields) => {
        if (err) {
            console.log(`Error al ejecutar SELECT en MySQL!\n${err}`);
            response.redirect('/login.html?error=4');
        }
        
        if (rows.length > 0) {
            if (rows[0].contrasena === userData.contrasena) {
                request.session.id_usuario = rows[0].id_usuario;
                response.redirect('/lista.html');
            } else {
                response.redirect('/login.html?error=3');
            }
        } else {
            response.redirect('/login.html?error=2');
        }
    });    
    
    connection.end();
});

app.post('/action/logout', (request, response) => {
    request.session.destroy();
    response.json({ exito : true });
});

app.use(express.static(__dirname + '/public'));

app.get('/', (request, response) => {
    if (request.session.id_usuario !== undefined) response.sendFile(`${__dirname}/public/lista.html`);
    else response.sendFile(`${__dirname}/public/login.html`);
});

app.listen(3000, () => console.log('Servidor escuchando el puerto 3000!'));