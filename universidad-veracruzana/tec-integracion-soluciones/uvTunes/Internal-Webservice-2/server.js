const bodyParser = require('body-parser');
const cors = require('cors');
const express = require('express');
const app = express();

app.use(bodyParser.json());
app.use(cors());

app.options("/*", function(req, res, next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.get('/', function (req, res) {
    res.send("Hello world");
});

app.post('/banco', function (req, res) {
    res.send({ "exito" : true });
});

app.post('/hacienda/facturar', function (req, res) {
    res.send({ "exito" : true });
});

app.post('/paqueteria/consulta', function (req, res) {
    let result = { "precio": parseFloat((100 * req.body.pesototal).toFixed(2), 10) };
    res.send(result);
});

app.post('/paqueteria/solicitacion', function (req, res) {
    let result = { "cod_rastreo": Math.random().toString(36).substr(2, 7) };
    res.send(result);
});

app.post('/shop/activate/', function (req, res) {
    const activate = require('./routes/shop/activate.js');
    activate(req.body).then((result) => res.send(result));
});

app.post('/shop/login/', function (req, res) {
    const login = require('./routes/shop/login.js');
    login(req.body).then((result) => res.send(result));
});

app.post('/shop/logout/', function (req, res) {
    res.send(true);
});

app.get('/productos/', function (req, res) {
    const productos = require('./routes/shop/productos.js');
    productos(req.body).then((result) => res.send(result));
});

app.post('/shop/buy/', function (req, res) {
    const buy = require('./routes/shop/buy.js');
    buy(req.body).then((result) => res.send(result));
});

app.post('/shop/register/', function (req, res) {
    const register = require('./routes/shop/register.js');
    register(req.body).then((result) => res.send(result));
});

app.post('/shop/update/', function (req, res) {
    const update = require('./routes/shop/update.js');
    update(req.body).then((result) => res.send(result));
});

app.post('/shop/verify-email/', function (req, res) {
    const verifyEmail = require('./routes/shop/verifyEmail.js');
    verifyEmail(req.body).then((result) => res.send(result));
});

app.listen(8000, function () {
    console.log('Servidor ejecutando en el puerto 8000.');
  });