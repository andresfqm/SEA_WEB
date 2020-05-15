window.addEventListener("DOMContentLoaded", function () {
	//Función para calcular el dígito de verificación
	var documento = document.getElementById('NoDocumento');
	documento.addEventListener("change", function () {
		var arreglo, x, y, z, i, documento, DV, DVValue;
		documento = document.getElementById('NoDocumento').value;
		DV = document.getElementById('DV');
		arreglo = new Array(16);
		x = 0;
		y = 0;
		z = documento.length;
		arreglo = [null, 3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71];
		/*arreglo[1]=3;	arreglo[2]=7; 	arreglo[3]=13;
		 arreglo[4]=17; 	arreglo[5]=19;	arreglo[6]=23;
		 arreglo[7]=29; 	arreglo[8]=37;	arreglo[9]=41;
		 arreglo[10]=43; arreglo[11]=47; arreglo[12]=53;
		 arreglo[13]=59; arreglo[14]=67; arreglo[15]=71;*/
		for (i = 0; i < z; i++)
		{
			y = (documento.substr(i, 1));
			x += (y * arreglo[z - i]);
		}
		y = x % 11
		DVValue = (y > 1) ? 11 - y : y;
		DV.value = DVValue;
	});
});