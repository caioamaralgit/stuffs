# Este programa lee el archivo "test-list.txt" e criar grupos de 1 a 4, dividindo cada línea en un grupo
new = open('test-list-divided.txt', 'w')

numero = 1

with open('test-list.txt') as archivo:
    for line in archivo:
        line = str(numero) + " - " + line

        numero = numero + 1

        if numero == 5: 
            numero = 1

        new.write(line)       