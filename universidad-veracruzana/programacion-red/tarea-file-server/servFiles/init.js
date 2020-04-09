#!/usr/bin/env node
'use strict';

const net = require('net');
const fs = require('fs');

const PORT = 5000;
const ADDRESS = '192.168.1.82';

let server = net.createServer((socket) => {
    console.log(`Nuevo cliente: ${socket.remoteAddres}:${socket.remotePort}\n`); 

    socket.on('data', (data) => {
        console.log(`Petición recebida:\n\n ${data}`);

        let request = JSON.parse(data);

        if (request["archive"] != null) {
            let fileStream = fs.createReadStream(request["archive"]);

            fileStream.on('error', (err) => {
                console.log(err);
            });

            fileStream.on('open', () => {
                console.log(`Enviando archivo!`);
                fileStream.pipe(socket);
                console.log(`Archivo enviado!`);
            });
        }
    });
});

server.listen(PORT, ADDRESS, () => {
    console.log(` * Servidor iniciado en ${ADDRESS}:${PORT}\n\n`);    
});