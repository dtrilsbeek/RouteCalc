var timer = document.getElementById("timer");
var timerInterval;

roundTime = 0;
roundTimeLeft = 0;
function startTimer(time){
    roundTime = time;
    roundTimeLeft = time-1;
    stopTimer();
    showTimer();

    timerInterval = setInterval(function () {
        showTimer();
    }, 1000);
}

function showTimer(){
    width = roundTimeLeft/roundTime * 100;
    timer.style.width = width+'%';
    roundTimeLeft--;
    if(roundTimeLeft == -1)
        stopTimer();
}

function stopTimer(){
    clearInterval(timerInterval);
}