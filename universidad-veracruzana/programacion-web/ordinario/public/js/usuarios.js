$(document).ready(function () {
    $.get("/action/lista-usuarios", function(data, status) {
        if (data.exito) {
            let data_actual = new Date();
            data_actual = `${data_actual.getDate()}/${data_actual.getMonth() < 8 ? "0" + (data_actual.getMonth() + 1) : data_actual.getMonth() + 1}`;

            data.usuarios.forEach((usuario) => {
                usuario.fecha_nacimiento = usuario.fecha_nacimiento.substr(0, 10).split('-');
                usuario.fecha_nacimiento = `${usuario.fecha_nacimiento[2]}/${usuario.fecha_nacimiento[1]}/${usuario.fecha_nacimiento[0]}`

                let tr = document.createElement("tr");

                let td = document.createElement("td");
                td.innerText = usuario.nombre;
                tr.appendChild(td);

                td = document.createElement("td");
                td.innerText = usuario.usuario;
                tr.appendChild(td);

                td = document.createElement("td");                
                td.innerText = usuario.fecha_nacimiento;
                tr.appendChild(td);
                
                td = document.createElement("td");
                let button = document.createElement("button");
                button.innerText = "Felicitar";
                button.className = "btn";
                button.onclick = () => felicitarUsuario(usuario.id_usuario);

                if (data_actual !== usuario.fecha_nacimiento.substr(0, 5)) button.setAttribute("disabled", "disabled");

                td.appendChild(button);
                tr.appendChild(td);

                $("#tbody")[0].appendChild(tr);
            });
        } else {
            alert("No fue posible cargar los usuarios! \n" + data.error);
        }
    });

    verificarFelicidades();
});

let exibirAlerta;

function felicitarUsuario(id_usuario) {
    $.post({
        url: "http://localhost:3000/action/felicitar-usuario",
        dataType: "JSON",                
        success: (response) => {
            if (response.exito) mostrarAlerta("Felicitaciones enviadas con exito!", "success");
            else mostrarAlerta("Ocurrió un error al enviar la felicitacion!", "danger");
        },
        data: { id_usuario: id_usuario }
    });
}

function mostrarAlerta(mensaje, classe) {
    clearInterval(exibirAlerta);

    $("#alert")[0].className = `alert alert-${classe}`;
    $("#alert").html(mensaje).show();    

    exibirAlerta = setTimeout(() => { $("#alert").hide() }, 4000);
}

function verificarFelicidades() {
    $.get("/action/verificar-felicitaciones", (data, status) => {
        if (data.exito) {
            if (data.felicitaciones.length > 0) {
                let nombres = "";
    
                data.felicitaciones.forEach((felicitacion, index) => {
                    nombres += (nombres === "" ? "" : index === data.felicitaciones.length - 1 ? " y " : ", ") + felicitacion.nombre;
                });
                
                const mensaje = `${nombres} te ${data.felicitaciones.length > 1 ? "felicitan" : "felicita"} por tu cumpleaños!`;
    
                mostrarAlerta(mensaje, "info");
            }

            setTimeout(verificarFelicidades, 5000);
        } else {
            mostrarAlerta(data.error, "danger");
        }
    });    
}

function hacerLogout() {
    $.post("/action/logout", (data, status) => window.location.href = "");
}