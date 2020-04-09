const express = require('express');
const router = express.Router();

// Modelos
const directories = require('../models/files/directories.js');
const files = require('../models/files/files.js');

// Autenticación
const token = require('./token');
const jwt = require('jsonwebtoken');

router.get('/directories', token, (req, res) => {
    if (req.query.action === "show") directories.showDirectories(req.userId, (response) => res.status(200).send(response));
    else if (req.query.action === "open") directories.openDirectory(req, (response) => res.status(200).send(response));
});

router.post('/directories', token, (req, res) => {
    if (req.query.action === "create") directories.createDirectory(req, (response) => res.status(200).send(response));
});

router.post('/move', token, (req, res) => {
    files.moveFile(req, (response) => res.status(200).send(response));
});

router.post('/create', token, (req, res) => {
    files.newFiles(req, (response) => res.status(200).send(response));
});

router.get('/open', token, (req, res) => {
    files.openFile(req, (response) => res.status(200).send(response));
});

module.exports = router;