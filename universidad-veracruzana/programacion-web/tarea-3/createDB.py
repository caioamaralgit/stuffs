import pymysql
from lxml import etree

def verificarNone(text):
    if str(text) == "None":
        return "0"
    else:
        return text

sql = ""
tree = etree.parse("usuarios.xml")

root = tree.getroot()

for registro in list(root):
    sql += "INSERT INTO usuario (user, password, nombre, lugar, tipoConcurso, puntaje, tiempo, logeado) VALUES (" if sql == "" else ", ("
    sql += "'" + registro[0].text + "', "
    sql += "'" + registro[1].text + "', "
    sql += "'" + registro[2].text + "', "
    sql += verificarNone(registro.get("lugar")) + ", "
    sql += verificarNone(registro.get("tipoConcurso")) + ", "
    sql += verificarNone(registro.get("puntaje")) + ", "
    sql += verificarNone(registro.get("tiempo")) + ", "
    sql += verificarNone(registro.get("logeado")) + ")"

connection = pymysql.connect(host="localhost",
                            user="root",
                            password="sarkhanvol", 
                            db="des_web")

try:
    with connection.cursor() as cursor: 
        cursor.execute(sql)     
finally: 
    connection.commit()
    connection.close()