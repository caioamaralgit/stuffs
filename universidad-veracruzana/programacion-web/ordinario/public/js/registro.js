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
                    error = "<strong>Error!</strong> Ese usuario ya está siendo usado!";
                    break;
                default:
                    error = "<strong>Error!</strong> Hay un problema en el servidor. Intente nuevamente más tarde.";
            }

            $("#alert").html(error).show();
        }
    }
});

function verificarFormato() {
    let formElements = $("#form-registro")[0].elements;
    let continuar = true;

    $("#alert").html("<strong>Error!</strong> Todos los campos tienen que ser llenados!").hide();

    for (let i = 0; i < formElements.length; i++) {
        switch (formElements[i].type) {
            case "text":
            case "password":
            case "number":
            case "date": 
                if (formElements[i].value.trim() === "") {
                    $(formElements[i]).addClass("has-error");

                    continuar = false;
                } else {
                    $(formElements[i]).removeClass("has-error");
                }
        }
    }

    if (continuar) $("#form-registro").submit();
    else $("#alert").show();
}