//https://www.kirupa.com/html5/animated_scroll_to_top_with_easing.htm
document.addEventListener("DOMContentLoaded", function () {
	"use strict"
	var requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;

	var bodyElement = document.querySelector("body");
	var element = document.createElement("i");
	element.setAttribute("id", "scrollButton");
	element.setAttribute("class", "material-icons");
	element.setAttribute("aria-hidden", "true");
	element.innerHTML="navigation";
	bodyElement.appendChild(element);

	var scrollButton = document.querySelector("#scrollButton");

	var currentScrollPosition;
	var iteration;
	var start = false;

	function setup() {
		// do something when the up arrow is clicked
		scrollButton.addEventListener("click", animateToTopOfPage);
		// deal with the mouse wheel
		bodyElement.addEventListener("mousewheel", stopEverything);
		bodyElement.addEventListener("DOMMouseScroll", stopEverything);

		// wheeeeeeee!
		animationLoop();
	}
	setup();

	//
	// kick of the animation to scroll your window back to the top
	//
	function animateToTopOfPage(e) {
		currentScrollPosition = getScrollPosition();

		start ^= true;
		iteration = 0;
	}

	//
	// stop the animation and reset start and iteration
	//
	function stopEverything() {
		start = false;
	}

	//
	// a cross-browser (minus Opera) way of getting the current scroll position
	//
	function getScrollPosition() {
		if (document.documentElement.scrollTop == 0) {
			return document.body.scrollTop;
		} else {
			return document.documentElement.scrollTop;
		}
	}

	//
	// kicks into high gear only when the start variable is true
	//
	function animationLoop() {
		// start is true when you click on the up arrow
		if (start) {
			// where the magic happens
			window.scrollTo(0, easeOutCubic(iteration, currentScrollPosition, -currentScrollPosition, 50));

			iteration++;

			// once you reach the top of the document, stop the scrolling
			if (getScrollPosition() <= 0) {
				stopEverything();
			}
		}
		requestAnimationFrame(animationLoop);
	}
	function easeOutCubic(currentIteration, startValue, changeInValue, totalIterations) {
		return changeInValue * (Math.pow(currentIteration / totalIterations - 1, 3) + 1) + startValue;
	}
	//Evento para mostrar el botón
	function showButton() {
		var scrollButton = document.querySelector("#scrollButton");
		if (window.pageYOffset >= window.innerHeight) {
			console.info("100vh");
			scrollButton.classList.add("visible");
		} else {
			scrollButton.classList.remove("visible");
		}
	}
	window.addEventListener("scroll", showButton);
});