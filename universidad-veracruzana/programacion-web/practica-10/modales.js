(function() {
    window.onload = () => {
        let div = document.getElementById("imagenes");
        let imagenes = [ "img-1.jpg", "img-2.jpg", "img-3.jpg", "img-4.jpg", "img-5.jpg", "img-6.jpg"];


        let img = document.createElement("img");
        img.setAttribute("src", "imagenes/" + imagenes[0]);
        img.setAttribute("title", "Ver en pantalla completa");
        img.className = "col-12";
        img.onclick = () => { toggleModal(true); };
        document.getElementById("actual").appendChild(img);
        
        imagenes.forEach(function (imagen) {
            img = document.createElement("img");
            img.setAttribute("src", "imagenes/" + imagen);
            img.setAttribute("title", "Ver " + imagen);
            img.className = "col-2";
            img.onclick = function () {
                document.querySelector("#actual img").setAttribute("src", this.getAttribute("src")); 
            }
            div.appendChild(img);
        });

        document.querySelector("#modal div").onclick = () => { toggleModal(false); }
        document.querySelector("#modal img").style.maxHeight = (window.innerHeight - 40) + "px";
    };
})();

function toggleModal(show) {
    document.querySelector("#modal img").setAttribute("src", document.querySelector("#actual img").getAttribute("src"));
    document.getElementById("modal").style.display = show ? "block" : "none";
}