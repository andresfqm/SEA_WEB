window.onload = function () {
	console.log("Script cargado");
	var inputsFile = document.querySelectorAll("input[type='file']");
	inputsFile.forEach(function(item){
		item.addEventListener("change", (item) => {
			console.log(item);
			var fileText=document.querySelector("[data-inputFileText='"+item.originalTarget.dataset.inputfile+"']");
			fileText.value = item.originalTarget.files[0].name;
		});
	});
};
