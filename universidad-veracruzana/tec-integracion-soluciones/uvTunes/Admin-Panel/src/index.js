import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/layout.css';

(function() {
    window.onload = function () {
        document.getElementById("content").style.minHeight = (window.innerHeight - 88) + "px";
    }
})();