function slideShow() {
    var i;
    var slides = document.getElementsByClassName("Containers");
    for (i=0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slidePosition++;
    if (slidePosition > slides.length) {
        slidePosition = 1;
    }
    slides[slidePosition-1].style.display = "block";
    setTimeout(slideShow, 2000);
}
window.addEventListener('load', function () {
    slideShow();
})
var slidePosition = 0;
slideShow();
