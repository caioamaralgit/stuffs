const express = require('express');
const router = express.Router();

router.use('/users', require('./users'));
router.use('/files', require('./files'));
router.use('/', (req, res) => res.send('El servidor está corriendo.'));

module.exports = router;