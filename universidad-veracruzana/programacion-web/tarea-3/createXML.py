from lxml import etree

def consultarBase():
    import pymysql

    connection = pymysql.connect(host="localhost",
                                user="root",
                                password="sarkhanvol", 
                                db="des_web")

    try:
        with connection.cursor() as cursor: 
            sql = "SELECT * FROM usuario ORDER BY nombre"
            cursor.execute(sql)     
            return cursor.fetchall()
    finally: 
        connection.close()

root = etree.Element("usuarios")

for registro in consultarBase():
    usuario = etree.SubElement(root, "usuario")

    usuario.set("lugar", str(registro[3]))
    usuario.set("tipoConcurso", str(registro[4]))
    usuario.set("puntage", str(registro[5]))
    usuario.set("tiempo", str(registro[6]))
    usuario.set("logeado", str(registro[7]))

    etree.SubElement(usuario, "user").text = registro[0]
    etree.SubElement(usuario, "password").text = registro[1]
    etree.SubElement(usuario, "nombre").text = registro[2]

xml = '<?xml version="1.0" encoding="UTF-8"?>\n'
xml += etree.tostring(root, pretty_print=True).decode("utf-8")

with open('usuarios.xml', 'w') as file:
    file.write(xml)