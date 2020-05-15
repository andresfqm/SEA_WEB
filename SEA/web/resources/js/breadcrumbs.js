// Agregando el evento onload para que se ejecute el script
window.addEventListener('load', breadcrums);
function breadcrums() {
	/*
	 Se obtiene la locación actual del usuario (La locación no tiene hash ni el query string),
	 se decodifica la url
	 se quita la extención de la página
	 */
	var locacionActual = decodeURI(location.pathname).replace(/\.\w{3,5}$/, '');
	var listItem; // Guarda temporalmente cada item de las breadcrumbs
	var link; // Guarda el path
	var path = "/"; // Es necesario agregar el "/" inicial a las URLs
	/*
	 Lista de nombres de archivo que no quieres que aparezcan en las breadcrums
	 Agrega los nombres de los arhivos SIN extensión. Por ejemplo, index (de index.html)
	 */
	var defaultPages = ["index", "default"];
	var regexDefaultPages = new RegExp("^" + defaultPages.join("|^"), "i");
	var home = "SEA"; // Texto que aparecerá en el enlace raíz
	var URLArray = locacionActual.split("/"); // Separando la locación actual por el caracter "/"
	var breadcrumbs = document.getElementById("breadcrumbs"); // Aquí se agregará la lista de enlaces

	// Si la página actual está vacía, es index.* o default.*, se remueve del arreglo
	if ((URLArray.length >= 3) && (URLArray[URLArray.length - 1] === '' || URLArray[URLArray.length - 1].match(regexDefaultPages) && URLArray.length > 1)) {
		URLArray.length--;
	}
	for (let i = 0; i < URLArray.length - 1; i++) {  // Ciclo para mostrar los links
		listItem = document.createElement("li"); // Se crea un elemento de lista
		link = document.createElement("a"); // Se crea un elemento enlace
		if (i === 0) {
			link.innerHTML = home; // Se agrega el texto del enlace
		} else {
			path += URLArray[i] + "/"; // Al path se le suma la carpeta que sigue
			link.innerHTML = URLArray[i].unCamelCase();
		}
		link.href = path; // Se agrega el atributo href con el valor que tiene path
		listItem.appendChild(link); // A listItem se le añade link como hijo
		breadcrumbs.appendChild(listItem); // A breadcrumbs se le añade listItem como hijo
	}
	/*Este es el item para la página actual*/
	if (URLArray.length > 1) {
		listItem = document.createElement("li"); // Se crea un elemento de lista
		listItem.innerHTML = document.title.replace(/\s\|\s.+$/i, "");
		breadcrumbs.appendChild(listItem); // A breadcrumbs se le añade listItem como hijo
	}
}
String.prototype.unCamelCase = function () {
	return this
			// Reemplazando los "_" por espacios
			.replace(/_/g, ' ')
			// Insertando un espacio entre minúscula y mayúscula
			.replace(/([a-z])([A-Z])/g, '$1 $2')
			// Insertando un espacio en una secuencia de n mayúsculas y una minúscula
			// El espacio se inserta antes de la última mayúscula encontrada
			.replace(/\b([A-Z]+)([A-Z])([a-z])/, '$1 $2$3')
			// Capitalizando el primer carácter
			.replace(/^./, (str) => str.toUpperCase());
}