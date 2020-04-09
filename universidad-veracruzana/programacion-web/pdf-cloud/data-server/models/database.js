// Basado en https://geekstrick.com/connecting-mysql-database-using-node-express/

const mysql = require("mysql");

const DB = function () {};

DB.prototype.createPool = () => {
	return mysql.createPool(require("../config/mysql.json"));
}

DB.prototype.getConnection = (pool, callback) => {
	pool.getConnection((err, connection) => {
		if (err) {
			console.log(err);
			callback(true);
			return;
        }
        
		connection.on('error', function(err) {
			if (err.code === "PROTOCOL_CONNECTION_LOST") connection.destroy();				
            else connection.release();
            
			console.log(err);
			callback(true);
			return;
        });
        
		callback(null, connection);
	});
}

DB.prototype.createTransaction = (pool, callback) => {
	const self = this;
	self.getConnection(pool, (err, connection) => {
		if (err) {
			console.log(err);
			callback(true);
			return;
        }
        
		connection.beginTransaction((err) => {
			if (err) {
				console.log(err);
				callback(true);
				return;
            }
            
			callback(null, connection);
		});
	});
}

module.exports = new DB();