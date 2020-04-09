#!/usr/bin/env python

from flask import Flask, request

# create app
app = Flask(__name__)
#Metodo GET para traer datos que estan en el servidor al cliente
#app.route Asigna una URL al codigo que maneja
@app.route('/Suma', methods=['GET'])
def suma():
        if request.method == 'GET':
            a=request.args.get('a')
            b=request.args.get('b')
            
            resultadoSuma=int (a)+ int (b)
            return "Resultado:%s" %resultadoSuma;

@app.route('/Resta', methods=['GET'])
def resta():
        if request.method == 'GET':
            a=request.args.get('a')
            b=request.args.get('b')
            
            resultadoresta=int (a)- int (b)
            return "Resultado:%s" %resultadoresta;

@app.route('/Multiplicar', methods=['GET'])
def multiplicar():
        if request.method == 'GET':
            a=request.args.get('a')
            b=request.args.get('b')
            
            resultadomult=int (a)* int (b)
            return "Resultado:%s" %resultadomult;

@app.route('/Dividir', methods=['GET'])
def dividir():
        if request.method == 'GET':
            a=request.args.get('a')
            b=request.args.get('b')
            
            resultadodiv=float (a)/ float (b)
            return "Resultado:%s" %resultadodiv;



# run app
if __name__ == '__main__':
    app.run(debug=True)