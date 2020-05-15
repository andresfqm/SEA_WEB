window.addEventListener("DOMContentLoaded", function () {
	var cambioContrasena = document.querySelectorAll('#contrasena-panel input');
	cambioContrasena[0].addEventListener("change", function () {
		for (var i = 1; i < cambioContrasena.length; i++) {
			(cambioContrasena[i].getAttribute("required")) ? cambioContrasena[i].removeAttribute("required") : cambioContrasena[i].setAttribute("required", "true");
		}
	});
});