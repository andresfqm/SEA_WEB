/* 
 * The MIT License
 *
 * Copyright 2017 Depurador.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
 * Dialogos
 */
function mostrarDialogos(data) {
	if (!document.querySelector('#dialogExceptions')) {
		crearDialogExceptions();
	}
	var dialog = document.querySelector('#dialogExceptions');
	if (!dialog.showModal) {
		dialogPolyfill.registerDialog(dialog);
		console.log("Dialogos no soportados por el navegador");
	}
	dialog.querySelector('.mdl-dialog__title').innerHTML = data.titulo;
	dialog.querySelector('.mdl-dialog__content p').innerHTML = data.mensaje;
	dialog.showModal();
	dialog.querySelector('.close').addEventListener('click', function () {
		dialog.close();
	});
}
function crearDialogExceptions() {
	var dialogTag = document.createElement('dialog');
	var dialogTitle = document.createElement('h4');
	var dialogContent = document.createElement('div');
	var dialogParagraph = document.createElement('p');
	var dialogActions = document.createElement('div');
	var dialogButton = document.createElement('button');

	agregarAtributos(dialogTag, atributos = {
		"class": "mdl-dialog",
		"id": "dialogExceptions"
	});
	agregarAtributos(dialogTitle, atributos = {
		"class": "mdl-dialog__title"
	});
	agregarAtributos(dialogContent, atributos = {
		"class": "mdl-dialog__content"
	});
	agregarAtributos(dialogActions, atributos = {
		"class": "mdl-dialog__actions"
	});
	agregarAtributos(dialogButton, atributos = {
		"class": "mdl-button mdl-js-button mdl-button--raised mdl-button--colored close",
		"type": "button"
	});

	document.body.appendChild(dialogTag);
	dialogTag.appendChild(dialogTitle);
	dialogTag.appendChild(dialogContent);
	dialogContent.appendChild(dialogParagraph);
	dialogTag.appendChild(dialogActions);
	dialogActions.appendChild(dialogButton);
	dialogButton.innerHTML="Aceptar";
}
/*
 * Snackbar
 */
function mostrarSnackbar(data){
	var snackbarMessage = document.querySelector('#snackbarMessage');
	console.log(data);
	snackbarMessage.MaterialSnackbar.showSnackbar(data);
}
function crearSnackbar(){
	var snackbarTag = document.createElement('div');
	var snackbarText = document.createElement('div');
	var snackbarButton = document.createElement('div');
	
	agregarAtributos(snackbarTag, atributos = {
		"id": "snackbarMessage",
		"class": "mdl-js-snackbar mdl-snackbar"
	});
	agregarAtributos(snackbarText, atributos = {
		"class": "mdl-snackbar__text"
	});
	agregarAtributos(snackbarButton, atributos = {
		"aria-relevant": "text",
		"class": "mdl-snackbar__action"
	});
	
	document.body.appendChild(snackbarTag);
	snackbarTag.appendChild(snackbarText);
	snackbarTag.appendChild(snackbarButton);
}
function agregarAtributos(elemento, atributos) {
	for (var atributo in atributos) {
		elemento.setAttribute(atributo, atributos[atributo]);
	}
}
