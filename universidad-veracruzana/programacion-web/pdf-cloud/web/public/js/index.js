$(document).ready(() => {
    // Carga los datos del usuario guardados y los pone en la pantalla
    const userData = JSON.parse(sessionStorage.getItem("usuario"));
    
    $("#user-name").html(`${userData["nombre"]} ${userData["apellido"]}`);
    $("#user-email").html(userData["email"]);
    
    const margin = (window.outerWidth >= 770 ? $("#nav-large")[0].offsetHeight : $("#nav-small")[0].offsetHeight - 40);
    const minHeight = (window.innerHeight - margin - $("footer")[0].offsetHeight);

    $("#content").eq(0).css({
        "margin-top": margin + "px",
        "min-height": minHeight + "px"
    });
});

window.showModal = (action) => {
    if (action === "upload") {
        $("#form-upload").show();

        $("h5.modal-title").html("Upload");
        $("#modal-action").html("Publicar");
        $("#modal-action").unbind("click").bind("click", () => uploadFile());
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