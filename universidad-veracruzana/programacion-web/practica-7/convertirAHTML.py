from lxml import etree
import sys

plantilla = """ 
    <!DOCTYPE html>
    <html lang="es">
        <head>
            <title>Discos</title>
            <meta charset="UTF-8" />
        </head>
        <body>
            <table>
                <tr>
                    <th>Title</th>
                    <th>Artista</th>
                    <th>Country</th>
                    <th>Company</th>
                    <th>Price</th>
                    <th>Year</th>
                </tr>
                %s
            </table>
        </body>
    </html>"""

plantillaFila = """
    <tr>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
        <td>%s</td>
    </tr>
"""

def generarHTML(pathXML):
    tree = etree.parse(pathXML)
    root = tree.getroot()
    fila = ''

    for cd in root:
        fila += plantillaFila % (cd[0].text, cd[1].text, cd[2].text, cd[3].text, cd[4].text, cd[5].text)
    
    return plantilla % fila

html = generarHTML(sys.argv[1])
with open(sys.argv[2], 'w'):
    archivo.write(html)