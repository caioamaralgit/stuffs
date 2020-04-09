$(document).ready(() => {    
    let search = window.location.search;

    if (search !== "") {       
        search = search.substr(1);
        
        let variables = JSON.parse('{"' + search.replace(/&/g, '","').replace(/=/g,'":"') + '"}');
        
        if (variables["error"] !== undefined) {
            let error = "";

            switch (variables["error"]) {
                case "1":
                    error = "<strong>Error!</strong> Los campos no pueden estar vacios!";
                    break;
                case "2":
                    error = "<strong>Error!</strong> Usuario no encontrado!";
                    break;
                case "3":
                    error = "<strong>Error!</strong> Contraseña inválida!";
                    break;
                default:
                    error = "<strong>Error!</strong> Hay un problema en el servidor. Intente nuevamente más tarde.";
            }

            $("#alert").html(error).show();
        }
    }
});

function hacerLogin() {
    let formElements = $("#form-login")[0].elements;
    let continuar = true;
    let error = "";

    $("#alert").hide();

    for (let i = 0; i < formElements.length; i++) {
        switch (formElements[i].type) {
            case "text":
            case "password": 
                if (formElements[i].value.trim() === "") {
                    $(formElements[i]).addClass("has-error");

                    continuar = false;
                    error = "Tienes que llenar todos los campos!";
                } else {
                    $(formElements[i]).removeClass("has-error");
                }
        }
    }

    if (continuar) $("#form-login").submit();
    else $("#alert").html(error).show();
}