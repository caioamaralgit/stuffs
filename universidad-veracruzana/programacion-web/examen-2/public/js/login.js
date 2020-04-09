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

    if (continuar) window.location.href = "localhost:3000/"
    else $("#alert").html(error).show();
}