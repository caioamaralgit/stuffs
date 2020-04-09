from flask import Flask, send_from_directory
from flask_restful import Api
from flask_jwt_simple import JWTManager
from flask_cors import CORS

app = Flask(__name__)
CORS(app)
api = Api(app)

app.config['JWT_SECRET_KEY'] = 'jwt-secret-string'
jwt = JWTManager(app)

import resources

# api.add_resource(resources.GetFile, '/files')
api.add_resource(resources.UserRegister, '/auth/register')
api.add_resource(resources.UserLogin, '/auth/login')
api.add_resource(resources.Upload, '/upload')
api.add_resource(resources.SecretResource, '/secret')


if __name__=='__main__':
    app.run()