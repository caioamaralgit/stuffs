// Basado en https://medium.freecodecamp.org/securing-node-js-restful-apis-with-json-web-tokens-9f811a92bb52
const jwt = require('jsonwebtoken');
const config = require('../config/token.json');

function verifyToken(req, res, next) {
  const token = req.headers['x-access-token'];

  if (!token) return res.status(403).send({ exito: false, error: 'Es necesario enviar el token.' });

  jwt.verify(token, config.secret, (err, decoded) => {
    if (err) return res.status(500).send({ exito: false, error: 'Falla al identificar el token.' });
    
    req.userId = decoded.id;
    next();
  });
}

module.exports = verifyToken;