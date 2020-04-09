function alternarMenu() {
    let menu = document.getElementById("menu");

    if (menu.style.top != "0px" || menu.style.top == "") {
        menu.style.top = "0px";
        document.getElementById("botonMenu").innerHTML = "Ocultar Menu";
    } else {
        menu.style.top = "-" + menu.clientHeight + "px";
        document.getElementById("botonMenu").innerHTML = "Mostrar Menu";
    }
}

function verificarForm(form) {
    let elementos = form.elements[0].elements;

    for (let i = 0; i < elementos.length; i++) {
        if (elementos[i].value.trim() == "") {
            elementos[i].className = "col-9 error";
            alert("El campo " + elementos[i].name + " no puede quedar vacio.");
            return false;
        }

        if (elementos[i].name == "integrantes" && isNaN(elementos[i].value)) {
            elementos[i].className = "col-9 error";
            alert("El campo " + elementos[i].name + " debe contener solo números.");
            return false;
        } else if (elementos[i].name == "email") {
            let teste = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            
            if (!teste.test(elementos[i].value)) {
                elementos[i].className = "col-9 error";
                alert("El campo " + elementos[i].name + " debe contener un email válido.");
                return false;
            }
        }
    }

    form.submit();
}

function mostrarInfo() {
    let equipo = window.location.href.split("?")[1].split("=")[1];

    switch(equipo) {
        case "1":
            document.getElementById("nombreEquipo").innerHTML = "Los Xalapeños";
            break;
        case "2":
            document.getElementById("nombreEquipo").innerHTML = "Monos";
            break;
        case "3":
            document.getElementById("nombreEquipo").innerHTML = "UVFC";
            break;
        case "4":
            document.getElementById("nombreEquipo").innerHTML = "Toros";
            break;
        default:
            document.getElementById("nombreEquipo").innerHTML = "?";
            break;
    }
}