function verificarFormato() {
    let formElements = $("#form-registro")[0].elements;
    let continuar = true;

    $("#alert").hide();

    for (let i = 0; i < formElements.length; i++) {
        switch (formElements[i].type) {
            case "text":
            case "password":
            case "number":
            case "textarea": 
                if (formElements[i].value.trim() === "") {
                    $(formElements[i]).addClass("has-error");

                    continuar = false;
                } else {
                    $(formElements[i]).removeClass("has-error");
                }
        }
    }

    if (!$("#form-genero-1")[0].checked && !$("#form-genero-2")[0].checked) continuar = false;

    if (continuar) $("#form-registro").submit();
    else $("#alert").show();
}