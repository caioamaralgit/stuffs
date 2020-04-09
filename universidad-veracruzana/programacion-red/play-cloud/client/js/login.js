import '../css/login.css';

$(document).ready(() => {
    $("form.form-horizontal").bind("submit", (event) => {
        event.preventDefault();
        tryLogin();
    });
});

function tryLogin() {
    let email = $("#login-email");
    let password = $("#login-password");
        
    if (email.val().trim() === "") {
        showError("Necesitas poner un correo!");
        return false;
    } else if (password.val().trim() === "") {
        showError("Necesitas poner una contraseña!");
        return false;
    }

    $("#login-alert").hide();
    window.location.href = "index.html";
}

function showError(error) {
    $("#login-alert").html(error);
    $("#login-alert").show();
}