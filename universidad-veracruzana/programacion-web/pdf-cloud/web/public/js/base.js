const routes = require('./routes.json');

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';

$(document).ready(function () {
    let search = window.location.search;

    if (search !== "") {       
        search = search.substr(1);
        
        let variables = JSON.parse('{"' + search.replace(/&/g, '","').replace(/=/g,'":"') + '"}');
        
        if (variables["token"] !== undefined) {
            $.post({
                url: `${routes["data-server"]}/users/activate`,
                dataType: "JSON",                
                success: (response) => window.location.search = `activacion=${response.exito}`,
                data: { "token" : variables["token"] }
            });
        } 

        if (variables["activacion"] !== undefined) {
            if (variables["activacion"] === "true") {
                showModal("login");
                $("#modal-alert").html(`Su cuenta fue activada con exito!`).attr("class", "alert alert-success").show();
            } else {
                showModal("login");
                $("#modal-alert").html(`Token no encontrado.`).attr("class", "alert alert-danger").show();
            }
        }
    }
});