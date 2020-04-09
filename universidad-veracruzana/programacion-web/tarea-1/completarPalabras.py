# Este programa completa las palabras en el archivo "word.txt", dejandolas con 8 caracteres
# Al final genera un archivo llamado "test-list.txt" con las nuevas palabras
new = open('test-list.txt', 'w')

with open('words.txt') as archivo:
    for line in archivo:
        line = line.replace(" ", "").replace("\n", "")

        for x in range(0, 10):
            if len(line) == 6:
                nuevaPalabra = ""
                for y in range(0,10):
                    nuevaPalabra += str(x) + str(y) + line + "\n"
                    nuevaPalabra += str(x) + line + str(y) + "\n"
                    nuevaPalabra += line + str(x) + str(y) + "\n"
            else:
                nuevaPalabra = str(x) + line + "\n"
                nuevaPalabra = nuevaPalabra + line + str(x) + "\n"

            new.write(nuevaPalabra)