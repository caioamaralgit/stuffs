const routes = require('./routes.json');

$(document).ready(() => {    
    const margin = (window.outerWidth >= 770 ? $("#nav-large")[0].offsetHeight : $("#nav-small")[0].offsetHeight - 40);

    $("body > section").eq(0).css("margin-top", margin + "px");
    $("#queEs").css("min-height", (window.innerHeight - margin) + "px");
});

window.showModal = (action) => {
    if (action === "login") {
        $("#form-login").show();
        $("#form-registro").hide();

        $("h5.modal-title").html("Login");
        $("#modal-action").html("Hacer login");
        $("#modal-action").unbind("click").bind("click", () => tryLogin());
    } else {
        $("#form-login").hide();
        $("#form-registro").show();

        $("h5.modal-title").html("Registro");
        $("#modal-action").html("Hacer registro");
        $("#modal-action").unbind("click").bind("click", () => registerUser());
    }

    $("#modal").modal("show");
    $("#modal-alert").hide();
}

window.registerUser = () => {
    let data = {
        "nombre" : $("#registro-nombre").val(),
        "apellido" : $("#registro-apellido").val(),
        "email" : $("#registro-email").val(),
        "contrasena" : $("#registro-contrasena").val()
    }

    $.post({
        url: `${routes["data-server"]}/users/register`,
        dataType: "JSON",                
        success: (response) => {
            if (response.exito) {
                showModal("login");
                $("#modal-alert").html(`Su cuenta fue creada con exito!<br />Para hacer login es necesario confirmar su cuenta.<br />En algunos instantes usted recibirá un correo con una URL para activacion.`).attr("class", "alert alert-success").show();
            } else {
                $("#modal-alert").html(response.error).attr("class", "alert alert-danger").show();
            }
        },
        data: data
    });
}

window.tryLogin = () => {
    let data = {
        "email" : $("#login-email").val(),
        "contrasena" : $("#login-contrasena").val()
    }

    $.post({
        url: `${routes["data-server"]}/users/login`,
        dataType: "JSON",                
        success: (response) => {
            if (response.exito) {
                sessionStorage.setItem("usuario", JSON.stringify(response.data));
                sessionStorage.setItem("token", response.token);
                sessionStorage.setItem("file-token", response["file-token"]);
            
                window.location.href = "index.html";
            } else {
                $("#modal-alert").html(response.error).attr("class", "alert alert-danger").show();
            }
        },
        data: data
    });
}