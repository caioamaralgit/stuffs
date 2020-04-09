'use strict';

const Login = require('../../../models/admin/Login');

module.exports = function (router) {
    router.get('/', async function (req, res) {
        res.send(await Login(req.query));
    });
};
