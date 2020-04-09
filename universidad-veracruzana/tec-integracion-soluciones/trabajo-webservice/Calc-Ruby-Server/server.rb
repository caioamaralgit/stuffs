# Requisita la gem Sinatra, necesaria para el servidor
require 'sinatra'

# Definicion de las funciones de la calculadora

# Suma: Recibe dos parametros interos y retorna la soma del tipo intero
def suma(a, b)
    r = a + b
    return "#{r}"
end

# Resta: Recibe dos parametros interos y retorna la resta del tipo intero
def resta(a, b)
    r = a - b
    return "#{r}"
end

# Multiplicación: Recibe dos parametros interos y retorna la multiplicación del tipo intero
def multiplicacion(a, b)
    r = a * b
    return "#{r}"
end

# Division: Recibe dos parametros interos y retorna la división del tipo float
def division(a, b)
    r = a / b
    return "#{r.to_f}"
end

# Potencia: Recibe dos parametros interos y retorna la potencia del primero por el segundo, del tipo intero
def potencia(a, b)
    r = a ** b
    return "#{r}"
end

# Raiz: Recibe un parametro intero y retorna la raiz cuadrada del tipo float
def raiz(a)
    r = Math.sqrt(a)
    return "#{r.to_f}"
end

# En este trecho defines los recursos que el servidor va quedar escuchando, y las respuestas

# Define la respuesta si el cliente requisita la index del servidor
get('/') {
    "<p>
        <h1>Ruby servidor de operaciones básicas</h1>
        <p>1. '/' retorna esta página index </p>
        <br>
        <h1>Suma</h1>
        <p>2. '/suma/?[:param1]&[:param2]' retorna la suma de param1 mas param2</p>
        <br>
        <h1>Resta</h1>
        <p>2. '/resta/?[:param1]&[:param2]' retorna la resta de param1 menos param2</p>
        <br>
        <h1>Multiplicacion</h1>
        <p>3. '/multiplicacion/?[:param1]&[:param2]' retorna la multiplicacion de param1 por param2</p>
        <br>
        <h1>Division</h1>
        <p>4. '/division/?[:param1]&[:param2]' retorna la division de param1 entre param2</p>
        <br>
        <h1>Potencia</h1>
        <p>5. '/potencia/?[:param1]&[:param2]' retorna eleva param1 a param2</p>
        <br>
        <h1>Raiz</h1>
        <p>6. '/raiz/?[:param1]' retorna la raiz cuadrada de param1</p>
    </p>"
}

# Respuesta a las requisiciones del recurso "suma"
get('/suma') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    suma(a, b)
}

# Respuesta a las requisiciones del recurso "resta"
get('/resta') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    resta(a, b)
}

# Respuesta a las requisiciones del recurso "multiplicacion"
get('/multiplicacion') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    multiplicacion(a, b)
}

# Respuesta a las requisiciones del recurso "division"
get('/division') {
    # En Ruby, cuando haces una división de interos, el siempre retorna intero. 
    # Por esto necesitamos convertir los parametros para el tipo float.
    a = params['a'].to_f
    b = params['b'].to_f
    division(a, b)
}

# Respuesta a las requisiciones del recurso "potencia"
get('/potencia') {
    a = Integer(params['a'])
    b = Integer(params['b'])
    potencia(a, b)
}

# Respuesta a las requisiciones del recurso "raiz"
get('/raiz') {
    a = Integer(params['a'])
    raiz(a)
}