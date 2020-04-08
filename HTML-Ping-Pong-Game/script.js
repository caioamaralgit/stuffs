let parametrosJuego = {
    "juego" : null, 
    "jugando" : false,
    "jugadores" : 1,    
    "direccion" : { "horizontal" : "derecha", "vertical" : "abajo" },
    "obstaculos" : { "player1" : () => { return (document.getElementById("p1").offsetLeft + document.getElementById("p1").offsetWidth) },
                     "player2" : () => { return (document.getElementById("mesa").clientWidth - (document.getElementById("p1").offsetWidth + document.getElementById("p1").offsetLeft)) },
                     "parede1" : 0,
                     "parede2" : () => { return document.getElementById("mesa").clientWidth }
    },
    "puntos" : {
        "player1" : 0,
        "player2" : 0
    },
    "rebote" : { "x" : 20, "y" : 10 }
};

(() => window.onload = () => {
    parametrosJuego.obstaculos.player1 = parametrosJuego.obstaculos.player1();
    parametrosJuego.obstaculos.player2 = parametrosJuego.obstaculos.player2();
    parametrosJuego.obstaculos.parede2 = parametrosJuego.obstaculos.parede2();

    document.getElementById("ball").style.left = Math.floor(document.getElementById("mesa").offsetWidth / 2) + "px";
    document.getElementById("ball").style.top = Math.floor(document.getElementById("mesa").offsetHeight / 2) + "px";

    window.addEventListener("keydown", (event) => { 
        switch(event.keyCode) {
            case 32: // Space key
                if (document.getElementById("ball").offsetLeft == 0) restartGame();
                else alternarJuego();
                break;
            case 87: // W key
                moverPlayer("p1", "arriba");
                break;
            case 83: // S key
                moverPlayer("p1", "abajo");
                break;
            case 38: // Arrow up
                moverPlayer("p2", "arriba");
                break;
            case 40: // Arrow down
                moverPlayer("p2", "abajo");
                break;
        }
    });
})();

function empezarJuego() {
    const mesa = document.getElementById("mesa");
    const pelota = document.getElementById("ball");
    const ballSize = pelota.offsetWidth;
              
    let posicionX = pelota.offsetLeft;
    let posicionY = pelota.offsetTop;

    parametrosJuego.juego = setInterval(moverPelota, 100);
    parametrosJuego.jugando = true;

    function moverPelota() {        
        if (parametrosJuego.direccion.vertical === "abajo") {            
            let avance = posicionY + parametrosJuego.rebote.y + pelota.offsetHeight;
            let obstaculo = document.getElementById("mesa").clientHeight;
            
            if (avance < obstaculo) {
                posicionY += parametrosJuego.rebote.y;
            } else if ((posicionY + pelota.offsetHeight) < obstaculo) {                
                posicionY += obstaculo - (posicionY + pelota.offsetHeight);
            } else {
                posicionY -= parametrosJuego.rebote.y;
                parametrosJuego.direccion.vertical = "arriba";                
            }
        } else {  
            let avance = posicionY - parametrosJuego.rebote.y;
            let obstaculo = 0;
            
            if (avance > obstaculo) {
                posicionY -= parametrosJuego.rebote.y;
            } else if (posicionY > obstaculo) {                
                posicionY = 0;
            } else {
                posicionY += parametrosJuego.rebote.y;
                parametrosJuego.direccion.vertical = "abajo";                
            }
        }

        if (parametrosJuego.direccion.horizontal === "derecha") {
            let avance = posicionX + parametrosJuego.rebote.x + ballSize;
            let obstaculo = parametrosJuego.jugadores == 1 ? parametrosJuego.obstaculos.parede2 : parametrosJuego.obstaculos.player2;

            if (avance < obstaculo) {
                posicionX += parametrosJuego.rebote.x;
            } else if ((posicionX + ballSize) < obstaculo) {                
                posicionX += obstaculo - (posicionX + ballSize);
                punto(1);
            } else {
                posicionX -= parametrosJuego.rebote.x;
                parametrosJuego.direccion.horizontal = "izquierda";                
            }
        } else {
            let avance = posicionX - parametrosJuego.rebote.x;
            let obstaculo = parametrosJuego.obstaculos.player1;

            if (avance > obstaculo) {
                posicionX -= parametrosJuego.rebote.x;
            } else if (posicionX > obstaculo) {
                posicionX -= posicionX - obstaculo;
            } else {
                let playerYPosition = document.getElementById("p1").offsetTop;
                let playerYRange = playerYPosition + document.getElementById("p1").offsetHeight;

                if (posicionY < playerYPosition) { // Usa el punto abajo de la pelota para referencia
                    if ((posicionY + ballSize) > playerYPosition) { // Rebote 
                        posicionX += parametrosJuego.rebote.x;
                        parametrosJuego.direccion.horizontal = "derecha";                    
                    } else { // No Rebote
                        if (avance > 0) {
                            posicionX -= parametrosJuego.rebote.x;
                        } else {
                            posicionX = 0;
                            punto(2);
                        }
                    }
                } else { // Usa el punto arriba de la pelota para referencia
                    if (posicionY < playerYRange) { // Rebote 
                        posicionX += parametrosJuego.rebote.x;
                        parametrosJuego.direccion.horizontal = "derecha";                
                    } else { // No Rebote
                        if (avance > 0) {
                            posicionX -= parametrosJuego.rebote.x;
                        } else {
                            posicionX = 0;
                            punto(2);
                        }
                    }
                }
            }
        }

        pelota.style.left = posicionX + "px";
        pelota.style.top = posicionY + "px";
    }

    function punto(player) {
        document.getElementById("mesa").className = (player == 1 ? "col-8 punto1" : "col-8 punto2");
        setTimeout(() => document.getElementById("mesa").className = "col-8", 600);

        if (player == 1) {
            parametrosJuego.puntos.player1++;
            document.getElementById("puntosP1").innerText = parametrosJuego.puntos.player1;
        } else { 
            if (parametrosJuego.jugadores == 1) {
                parametrosJuego.jugando = false;
                clearInterval(parametrosJuego.juego);
            } else {
                parametrosJuego.puntos.player2++;
                //  document.getElementById("puntosP2").innerText = parametrosJuego.puntos.player2;
            }
        }
    }
}

function restartGame() {
    if (parametrosJuego.jugando) {        
        parametrosJuego.jugando = false;
        clearInterval(parametrosJuego.juego);
    }

    parametrosJuego.direccion = { "horizontal" : "derecha", "vertical" : "abajo" };
    parametrosJuego.puntos = { "player1" : 0, "player2" : 0 };

    cambiarConfiguracion();
    
    document.getElementById("ball").style.left = Math.floor(document.getElementById("mesa").offsetWidth / 2) + "px";
    document.getElementById("ball").style.top = Math.floor(document.getElementById("mesa").offsetHeight / 2) + "px";

    empezarJuego();
}

function moverPlayer(playerId, posicion) {
    let player = document.getElementById(playerId);
    let mesa = document.getElementById("mesa");

    if (posicion === "arriba" && player.offsetTop > 0) player.style.top = (player.offsetTop - 12 > 0 ? player.offsetTop - 12 : 0) + "px";
    else if (posicion === "abajo" && player.offsetTop < (mesa.clientHeight - 100)) player.style.top = (player.offsetTop + 114 < mesa.clientHeight ? player.offsetTop + 12 : mesa.clientHeight - player.offsetHeight) + "px";
}

function alternarJuego() {
    if (parametrosJuego.jugando) {
        parametrosJuego.jugando = false;
        clearInterval(parametrosJuego.juego);
    } else {
        empezarJuego();
    }
}

function cambiarConfiguracion() {
    if (parametrosJuego.jugando) {
        parametrosJuego.jugando = false;
        clearInterval(parametrosJuego.juego);
    }

    let dificultad = document.getElementsByName("dificultad");

    dificultad.forEach(function(element) {
        if (element.checked) 
            parametrosJuego.rebote = {
                "x" : 20 * element.value,
                "y" : 10 * element.value
            }
    });
}