let parametrosJuego = {
    "juego" : null, 
    "jugando" : false,
    "direccion" : { "horizontal" : "derecha", "vertical" : "abajo" },
    "obstaculos" : { "player1" : () => { return (document.getElementById("p1").offsetLeft + document.getElementById("p1").offsetWidth) },
                     "player2" : () => { return (document.getElementById("p2").offsetLeft + document.getElementById("p2").offsetWidth) },
                     "parede1" : 0,
                     "parede2" : () => { return document.getElementById("mesa").clientWidth }

    },
    "rebote" : { "x" : 20, "y" : 10 }
};

(() => window.onload = () => {
    parametrosJuego.obstaculos.player1 = parametrosJuego.obstaculos.player1();
    parametrosJuego.obstaculos.player2 = parametrosJuego.obstaculos.player2();
    parametrosJuego.obstaculos.parede2 = parametrosJuego.obstaculos.parede2();

    window.addEventListener("keydown", (event) => { 
        switch(event.keyCode) {
            case 32: // Space key
                alternarJuego();
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
    let mesa = document.getElementById("mesa");
    let pelota = document.getElementById("ball");
              
    let posicionX = pelota.offsetLeft;
    let posicionY = pelota.offsetTop;


    parametrosJuego.juego = setInterval(moverPelota, 100);
    parametrosJuego.jugando = true;

    function moverPelota() {
        if (parametrosJuego.direccion.horizontal === "derecha") {
            if ((posicionX + parametrosJuego.rebote.x) < (mesa.clientWidth - pelota.offsetWidth)) {
                posicionX += parametrosJuego.rebote.x;
            } else if (posicionX < (mesa.clientWidth - pelota.offsetWidth)) {
                posicionX += mesa.clientWidth - (posicionX + pelota.offsetWidth);
            } else {
                posicionX -= parametrosJuego.rebote.x;
                parametrosJuego.direccion.horizontal = "izquierda";                
            }
        } else {
            if ((posicionX - parametrosJuego.rebote.x) < (mesa.clientWidth - pelota.offsetWidth)) {
                posicionX -= parametrosJuego.rebote.x;
            } else if (posicionX < (mesa.clientWidth - pelota.offsetWidth)) {
                posicionX += mesa.clientWidth - (posicionX + pelota.offsetWidth);
            } else {
                posicionX -= parametrosJuego.rebote.x;
                parametrosJuego.direccion.horizontal = "izquierda";                
            }
            posicionX -= 20;            
        }
        
        if (parametrosJuego.direccion.vertical === "abajo") {
            //posicionY += 10;
            
        } else {
            posicionY -= 10;

        }

        pelota.style.left = posicionX + "px";
        pelota.style.top = posicionY + "px";

        if (posicionX == 0) clearInterval(juego);
    }
}

function moverPlayer(playerId, posicion) {
    let player = document.getElementById(playerId);
    let mesa = document.getElementById("mesa");

    if (posicion === "arriba" && player.offsetTop > 0) player.style.top = (player.offsetTop - 12 > 0 ? player.offsetTop - 12 : 0) + "px";
    else if (posicion === "abajo" && player.offsetTop < (mesa.clientHeight - 100)) player.style.top = (player.offsetTop + 114 < mesa.clientHeight ? player.offsetTop + 12 : mesa.clientHeight - player.offsetHeight) + "px";
}

function alternarJuego() {
    console.log(parametrosJuego);
    if (parametrosJuego.jugando) {
        parametrosJuego.jugando = false;
        clearInterval(parametrosJuego.juego);
    } else {
        empezarJuego();
    }
}