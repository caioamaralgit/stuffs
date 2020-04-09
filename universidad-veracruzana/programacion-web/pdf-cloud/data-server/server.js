const express = require('express');
const bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-access-token, Authorization");
    next();
});

app.use(require('./controllers'));

const db = require('./models/database.js');
if (global.SQLpool === undefined) global.SQLpool = db.createPool();

app.listen(3000, () => console.log('Servidor escuchando puerto 3000!'));