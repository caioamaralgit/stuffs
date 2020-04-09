import os, json, random, requests, string
from server import app
from flask import jsonify, request
from flask_restful import Resource, reqparse
from flask_jwt_simple import (JWTManager, jwt_required, create_jwt, get_jwt_identity)
from PyPDF2 import PdfFileMerger, PdfFileReader

class GetFile(Resource):
    @jwt_required
    def get(self):
        print("AAA")

class UserRegister(Resource):
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('auth-token', help = 'Es necesario informar el token!', required = True)
        parser.add_argument('user', help = 'Es necesario informar los datos del usuario!', required = True)

        data = parser.parse_args()
        
        try:
            if (data['auth-token'] == "ALALA"):
                os.makedirs("files/%s" % data["user"], exist_ok=True)
                return { 'exito': True, 'error': None }
            else:
                return { 'exito': False, 'error': 'Auth token invalido.' }                
        except:
            return { 'exito': False, 'error': 'Falla al generar el token!' }, 500

class UserLogin(Resource):
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('auth-token', help = 'Es necesario informar el token!', required = True)
        parser.add_argument('user', help = 'Es necesario informar los datos del usuario!', required = True)

        data = parser.parse_args()

        try:
            if (data['auth-token'] == "ALALA"):
                token = create_jwt(identity = json.loads(data['user'].replace("'", "\"")))
                return { 'exito': True, 'error': None, 'token': token }
            else:
                return { 'exito': False, 'error': 'Auth token invalido.' }                
        except:
            return { 'exito': False, 'error': 'Falla al generar el token!' }, 500

class Upload(Resource):
    @jwt_required
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('fileDetails', help = 'Es necesario informar el detalles sobre el archivo!', required = True)
        parser.add_argument('filesInfo', help = 'Es necesario informar las paginas de los archivos!', required = True)

        data = parser.parse_args()

        fileDetails = json.loads(data["fileDetails"])
        filesInfo = json.loads(data["filesInfo"])
        files = []
        
        info = {
            'id_directorio' : fileDetails["id_directorio"],
            'nombre' : fileDetails["nombre"],
            'resumen' : fileDetails["resumen"],
            'archivos' : []
        }

        if len(request.files.getlist("files")) > 1:
            index = 0
            merger = PdfFileMerger()

            for file in request.files.getlist("files"): 
                start = filesInfo[index]["start"] - 1
                end = filesInfo[index]["end"]

                merger.append(PdfFileReader(file), None, (start, end), None)
                index += 1

            filename = ''.join(random.choices(string.ascii_uppercase + string.digits, k=10))

            while os.path.isfile("files/%s%s%s" % (get_jwt_identity(), fileDetails["directorio"], filename)):
                filename = ''.join(random.choices(string.ascii_uppercase + string.digits, k=10))

            try:
                merger.write("files/%s%s%s" % (get_jwt_identity()["id"], fileDetails["directorio"], filename + '.pdf'))
                request.files.getlist("thumbs")[0].save("files/%s%s%s" % (get_jwt_identity()["id"], fileDetails["directorio"], filename + '.png'))
                files.append(filename)
                   
            except Exception as e:
                print("Error al guardar archivo: %s" % e)
                return { 'exito': False, 'error': 'Error al guardar el archivo :(' }, 500 
        elif len(request.files.getlist("files")) == 1:
            index = 0

            for file in filesInfo: 
                start = file["start"] - 1
                end = file["end"]

                merger = PdfFileMerger()
                merger.append(request.files.getlist("files")[0], None, (start, end), None)

                filename = ''.join(random.choices(string.ascii_uppercase + string.digits, k=10))

                while os.path.isfile("files/%s%s%s" % (get_jwt_identity(), fileDetails["directorio"], filename + '.pdf')):
                    filename = ''.join(random.choices(string.ascii_uppercase + string.digits, k=10))

                try:
                    merger.write("files/%s%s%s" % (get_jwt_identity()["id"], fileDetails["directorio"], filename + '.pdf'))
                    request.files.getlist("thumbs")[index].save("files/%s%s%s" % (get_jwt_identity()["id"], fileDetails["directorio"], filename + '.png'))
                    files.append(filename)
                    index = index + 1

                except Exception as e:
                    print("Error al guardar archivo: %s" % e)
                    return { 'exito': False, 'error': 'Error al guardar el archivo :(' }, 500  
        else :
            return { 'exito': False, 'error': 'Ningun archivo recibido.'}

        for nombre in files:
            info["archivos"].append(nombre)

        routes = json.load(open('routes.json', 'r'))
        headers = {
            'x-access-token' : request.headers['x-access-token']
        }

        req = requests.post(routes["data-server"] + '/files/create', headers=headers, data=info)

        if req.status_code == 200 and req.json()["exito"]:
            return { 'exito': True }
        else:
            return { 'exito': False, 'error': 'Ocurrio un error al guardar los archivos en la base de datos.' }
      
class SecretResource(Resource):
    @jwt_required
    def get(self):
        return {
            'answer': 42
        }