document.addEventListener("DOMContentLoaded", function () {
	var loadingWrapper=document.querySelector("#loading-wrapper");
	var loadingProgress=loadingWrapper.querySelector(".mdl-progress");
	var loadingSpinner=loadingWrapper.querySelector(".mdl-spinner");
	var loadingText=loadingWrapper.querySelector("#loading-text");
	
	function showLoadingWrapper(){
	loadingWrapper.classList.add("visible");
	}
	function hideLoadingWrapper(){
	loadingWrapper.classList.remove("visible");
	}
	function showLoading(loadingId){
		showLoadingWrapper();
		loadingId.classList.remove("hidden");
	}
	function hideLoading(loadingId){
		hideLoadingWrapper();
		loadingId.classList.add("hidden");
	}
	function changeLoadingText(text){
		loadingText.innerHTML=text;
	}
	hideLoading(loadingSpinner);
});