$(document).ready(() => {
    let bar = $("#progress");
    let percentage = $("#percentage_progress");
    let progress = 0;
    let segundos = 0;
    
    let interval = setInterval(() => {
        segundos += 300;

        if (segundos <= 30000) { 
            progress++;
            setPercentage(progress);
        } else {
            bar.removeClass("active");
            clearInterval(interval);
        }
    }, 300);

    function setPercentage(percent) {
        if (percent > 100) percent = 100;

        bar.css("width", `${percent}%`);
        bar.attr("aria-valuenow", percent);
        percentage.html(percent);
    }
});