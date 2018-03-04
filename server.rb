require 'sinatra'
def suma(a, b)
    r = a + b
    return "#{r}"
end

def resta(a, b)
    r = a - b
    return "#{r}"
end

def multiplicacion(a, b)
    r = a * b
    return "#{r}"
end

def division(a, b)
    r = a / b
    return "#{r.to_f}"
end

def potencia(a, b)
    r = a ** b
    return "#{r}"
end

def raiz(a)
    r = Math.sqrt(a)
    return "#{r.to_f}"
end

get('/') {
    "<p>
        <h1>Ruby servidor de operaciones básicas</h1>
        <p>1. '/' retorna esta página index </p>
        <br>
        <h1>Suma</h1>
        <p>1. '/suma/?[:param1]&[:param2]' retorna la suma de param1 mas param2</p>
        <br>
        <h1>Resta</h1>
        <p>2. '/resta/?[:param1]&[:param2]' retorna la resta de param1 menos param2</p>
        <br>
        <h1>Multiplicacion</h1>
        <p>2. '/multiplicacion/?[:param1]&[:param2]' retorna la multiplicacion de param1 por param2</p>
        <br>
        <h1>Division</h1>
        <p>2. '/division/?[:param1]&[:param2]' retorna la division de param1 entre param2</p>
        <br>
        <h1>Potencia</h1>
        <p>2. '/potencia/?[:param1]&[:param2]' retorna eleva param1 a param2</p>
        <br>
        <h1>Raiz</h1>
        <p>2. '/raiz/?[:param1]' retorna la raiz cuadrada de param1</p>
    </p>"
}

get('/suma') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    suma(a, b)
}

get('/resta') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    resta(a, b)
}

get('/multiplicacion') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    multiplicacion(a, b)
}

get('/division') {
    a = params['a'].to_f
    b = params['b'].to_f
    division(a, b)
}

get('/potencia') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    potencia(a, b)
}

get('/raiz') {
    a = Integer(params['a'])
    raiz(a)
}