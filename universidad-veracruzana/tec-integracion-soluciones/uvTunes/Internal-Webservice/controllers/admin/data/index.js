'use strict';

const Data = require('../../../models/admin/Data');

module.exports = function (router) {
    router.get('/', async function (req, res) {
        res.send(await Data(req.query));
    });
};
