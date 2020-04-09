
(function() {

    colorError = "rgb(120, 30, 30)";
    
    window.onload = function() {
	
	var formulario = document.getElementById("formulario");

	var nombre = document.getElementById("nombre");
	var apellido = document.getElementById("apellido");
	var edad = document.getElementById("edad");

	function ponerBlanco(elemento) {
	    elemento.style.backgroundColor = "white";
	    elemento.placeholder = "";
	}
	
	nombre.onfocus = function() {ponerBlanco(nombre)};
	apellido.onfocus = function() {ponerBlanco(apellido)};
	edad.onfocus = function() {ponerBlanco(edad)};
	
	
	formulario.onsubmit = function(event) {
	    var conErrores = false;
	    if(nombre.value.trim() == "") {
		conErrores = true;
		nombre.style.backgroundColor = colorError;
		nombre.placeholder = "El campo no puede ser vacío";
	    }

	    if(apellido.value.trim() == "") {
		conErrores = true;
		apellido.style.backgroundColor = colorError;
		apellido.placeholder = "El campo no puede ser vacío";
	    }

	    if(edad.value < 0 || edad.value.trim() == "" ) {
		conErrores = true;
		edad.style.backgroundColor = colorError;
		edad.placeholder = "El campo no puede ser vacío o menor a 0";
	    }

	    if(conErrores) {
		event.preventDefault();
		var error = document.getElementById("error");
		error.style.display = "block";
		var opacidad = 1;
		var id = setInterval(function() {
		    if(opacidad <= 0) {
			clearInterval(id);
			error.style.display = "none";
		    }
		    opacidad -= 0.03;
		    error.style.opacity = "" + opacidad;
		}, 100);
	    }
	    
	}
    }
})();
