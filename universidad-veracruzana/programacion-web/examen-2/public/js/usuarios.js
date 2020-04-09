$(document).ready(function () {
    $.get("/action/lista-usuarios", function(data, status) {
        if (data.exito) {
            data.usuarios.forEach((usuario) => {
                let tr = document.createElement("tr");

                let td = document.createElement("td");
                td.innerText = usuario.nombre;
                tr.appendChild(td);

                td = document.createElement("td");
                td.innerText = usuario.usuario;
                tr.appendChild(td);

                td = document.createElement("td");
                td.innerText = usuario.edad;
                tr.appendChild(td);

                td = document.createElement("td");
                td.innerText = usuario.interes;
                tr.appendChild(td);
                
                td = document.createElement("td");
                let button = document.createElement("button");
                button.innerText = "Like";
                td.appendChild(button);
                tr.appendChild(td);

                $("#tbody")[0].appendChild(tr);
            });
        } else {
            alert("No fue posible cargar los usuarios! \n" + data.error);
        }
    });
});