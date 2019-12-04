function sidebarScoreboard(obj) {
    var buttons = document.getElementsByClassName("sidebar-button active");
    for (i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("active");
    }
    obj.classList.add("active");
    document.getElementsByClassName("chat")[0].style.display = "none";
    document.getElementsByClassName("scoreboard")[0].style.display = "block";
}

function updateScoreboard(records){
    var scoreBoardList = document.getElementById("scoreboardList");
    scoreBoardList.innerHTML = "";


records.sort((a,b) => b.score - a.score).forEach(record => {
        var node = document.createElement("li");
        var textnode = document.createTextNode(record.username + ": " + record.score);
        node.appendChild(textnode);
        scoreBoardList.append(node);
    });
}