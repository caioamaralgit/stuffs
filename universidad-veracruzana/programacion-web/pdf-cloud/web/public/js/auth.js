const routes = require('./routes.json');

$(document).ready(() => {
    if (sessionStorage.getItem("token") === null) {
        window.location.href = "sobre.html";
    } else {
        $.get({
            url: `${routes["data-server"]}/users/token`,           
            headers: {
                'x-access-token': sessionStorage.getItem("token")
            },
            statusCode: {
                403: () =>  window.location.href = "sobre.html", 
                404: () =>  window.location.href = "sobre.html", 
                500: () =>  window.location.href = "sobre.html"
            }
        });
    }
});

window.logout = () => {    
    sessionStorage.clear();
    window.location.href = "sobre.html";
}