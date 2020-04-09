function f() {
    alert("Hola Mundo");
}

(() => {
    window.onload = function () {
        document.getElementById("boton").onclick = () => { f(); }
    }
})();