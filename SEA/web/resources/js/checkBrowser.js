document.addEventListener("DOMContentLoaded", function () {
	if (!(window.CSS && window.CSS.supports && window.CSS.supports("( display: grid ) and ( --var: grid ) and ( display: var(--var) )"))) {
		if (decodeURI(location.pathname) != "/SEA/errorPages/browserUpdate.xhtml") {
			window.location = "/SEA/errorPages/browserUpdate.xhtml";
		}
	} else if (decodeURI(location.pathname) == "/SEA/errorPages/browserUpdate.xhtml") {
		window.location = "/SEA/auth/";
	}
});