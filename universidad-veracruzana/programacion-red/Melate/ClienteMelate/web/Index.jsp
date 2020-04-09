<%-- 
    Document   : Teste
    Created on : 21/05/2018, 10:06:26
    Author     : caioaba
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sorteo Melate</title>
        <style>
            body {
                text-align: center;
            }
            
            .resultado {
                border: 1px solid #000;
                border-radius: 50%;
                display: inline-block;
                margin: 10px;
                min-width: 20px;
                padding: 5px;                
            }
        </style>
        <script>
            // Variable que guarda los numeros recibidos
            let numeros = [];
            
            function pedirNumero() {
                // Ese if impide que sean recibidos más que 7 números
                if (numeros.length <= 6) {
                    // Esa variable permite ejecutar una petición asíncrona (AJAX)
                    let xhttp = new XMLHttpRequest();

                    // onreadystatechange = Ejecute esa función cuando el estado estubier "ready" ("onreadystate")
                    xhttp.onreadystatechange = function() {      
                        // Si fue bien sucedida                  
                        if (this.readyState == 4 && this.status == 200) {
                            let result = JSON.parse(this.responseText);

                            // Si el numero recibido ya fue recibido, pide un nuevo numero
                            if (numeros.includes(result.resultado)) {
                                pedirNumero();
                            } else {
                                // Agrega el numero al arreglo de resultados
                                numeros.push(result.resultado);

                                // Crea un elemento div, pone la class resultado y escribe el numero en dentro de la div (innerHTML)
                                let nuevoNum = document.createElement("div");
                                nuevoNum.setAttribute("class", "resultado");
                                nuevoNum.innerHTML = result.resultado;

                                // Pone la nueva div dentro del elemento "resultados", al final.
                                document.getElementById("resultados").appendChild(nuevoNum);
                            }
                        }
                    };

                    // Ahora todo está listo, entoces haga la petición, como GET y para la página "SolicitarNumero" (que es el servlet Java)
                    xhttp.open("GET", "SolicitarNumero", true);
                    xhttp.send();
                }
            }
        </script>
    </head>
    <body>
        <h1>Bienvenido al Sorteo Melate</h1><br />
        <!-- Cuando pulsar el botón, ejecute la función pedirNumero() -->
        <button onclick="pedirNumero()">Pedir número</button><br /><br />
        <div>
            <h3>Resultados:</h3>
            <div id="resultados">
                
            </div>
        </div>
    </body>
</html>
