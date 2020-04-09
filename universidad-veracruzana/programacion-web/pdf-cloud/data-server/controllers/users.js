const express = require('express');
const router = express.Router();
const routes = require('../config/routes.json');

// Modelos
const activate = require('../models/users/activate.js');
const login = require('../models/users/login.js');
const register = require('../models/users/register.js');
const reset = require('../models/users/reset.js');
const directories = require('../models/files/directories.js');

// Autenticación
const jwt = require('jsonwebtoken');
const config = require('../config/token.json');
const token = require('./token');
const request = require('request');

router.get('/token', token, (req, res) => res.sendStatus(200));

router.post('/activate', (req, res) => {
    activate.activateUser(req, res, (response) => res.status(200).send(response));
});

router.post('/login', (req, res) => {
    login.loginUser(req, res, (response) => {
        if (response.exito) {
            response.token = jwt.sign({ id: response.data.id_usuario }, config.secret, {
                expiresIn: 86400 // 24 horas
            });

            response.data = {
                "id_usuario": response.data.id_usuario,
                "nombre": response.data.nombre,
                "apellido": response.data.apellido,
                "email": response.data.email,
                "directorioRaiz": response.data.id_directorio
            };

            const options = {
                uri: `${routes["file-server"]}/auth/login`,
                method: 'POST',
                json: true,
                body: { 'auth-token' : 'ALALA', 'user' : { "id" : response.data.id_usuario }}
            }

            request(options, (error, resp, body) => {
                response["file-token"] = null;

                if (!error && body.exito) {
                    response["file-token"] = body.token;
                } else {
                    if (error) console.log(`Error al comunicarse con el segundo servidor!\n\n${error}`)
                    else console.log(body.error);
                }
                
                res.status(200).send(response);
            });
        } else {
            res.status(500).send(response);
        }
    });
});

router.post('/register', (req, res) => {
    register.registerUser(req, res, (response) => {
        if (response.exito) {
            const options = {
                uri: `${routes["file-server"]}/auth/register`,
                method: 'POST',
                json: true,
                body: { 
                    "auth-token": "ALALA",
                    "user": response.user
                }
            }
            
            request(options, (error, resp, body) => {
                if (error) console.log(`Error al comunicarse con el segundo servidor!\n\n${error}`)
                if (!body.exito) console.log(body.error);
                else directories.createDirectory({ "id_usuario": response.user, "nombre": response.user });
                
                res.status(200).send(response);
            });
        } else {
            res.status(500).send(response);
        }
    });
});

router.post('/reset', (req, res) => {
    if (req.body.action === "request") reset.requestReset(req, res, (response) => res.status(200).send(response));
    else if (req.body.action === "verify") reset.verifyToken(req, res, (response) => res.status(200).send(response));
    else reset.reset(req, res, (response) => res.status(200).send(response));
});

module.exports = router;