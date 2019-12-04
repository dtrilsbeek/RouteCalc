let socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/" +  window.location.pathname.split("/").pop());

socket.onopen = function (e) {
    body.classList.remove("error");
    navbarTitle.innerText = "TekenGame";
    //save();
};

socket.onmessage = function (event) {
    var received = JSON.parse(event.data);
    if (received.type === "system" || received.isSystem === true) {
        showChat(received.message, true);
    }
    if (received.type === "chat") {
        showChat(received.sender + ": " + received.message);
    } else if (received.type === "leaderboard"){
        updateScoreboard(received.records);
    } else if (received.type === "freedraw"){
        freeDraw = true;
    }
    else if (received.type === "next_drawer"){
        navbarTitle.innerText = "TekenGame";
        isDrawer = false;
        freeDraw = false;
        var drawControls = document.getElementsByClassName("drawerOnly"); //divsToHide is an array
        for(var i = 0; i < drawControls.length; i++){
            drawControls[i].style.display = "none";
        }
        startTimer(received.time/1000);
        findxy('out', event);
        color("black");
        dialog.close();
    }
    else if (received.type === "stop_round"){
        navbarTitle.innerText = "TekenGame";
        isDrawer = false;
        findxy('out', event);
        startTimer(1);
        dialog.close();
    }
    else if (received.type === "next_word"){
        navbarTitle.innerText = received.message;
        document.getElementById('dialogWord').innerText = received.message;
        dialog.showModal();
        isDrawer = true;
        freeDraw = false;
        var drawControls = document.getElementsByClassName("drawerOnly"); //divsToHide is an array
        for(var i = 0; i < drawControls.length; i++){
            drawControls[i].style.display = "inline-block";
        }
    }
    else if (received.type === "draw") {
    if(!isDrawer || freeDraw) {
            let canvas = document.querySelector("canvas");

            prevX = received.prevX * 1.0 / received.canvasWidth * canvas.width;
            prevY = received.prevY * 1.0 / received.canvasHeight * canvas.height;
            currX = received.currX * 1.0 / received.canvasWidth * canvas.width;
            currY = received.currY * 1.0 / received.canvasHeight * canvas.height;
            brushSize = received.brushSize;
            color(received.color);
            draw(false);
        }
    }
    else if(received.type == "clear"){
        eraseRemote();
    }
    else if(received.type == "fill"){
        fillRemote(received.color);
    }
    else if(received.type == "save"){
        save(false);
    }
    else if(received.type == "undo"){
        undo(false);
    }
    else if(received.type == "redo"){
        redo(false);
    }
    else if(received.type == "room_full"){
        navbarTitle.innerText = "This room is full";
        navbar.classList.add("connecting");
        roomFull = true;
    }
};

socket.onclose = function (event) {
    if(!roomFull) {
        if (event.wasClean) {
            navbarTitle.innerText = "Connection closed";
            body.classList.add("error");
        } else {
            // e.g. server process killed or network down
            // event.code is usually 1006 in this case
            navbarTitle.innerText = "Connection lost...";
            body.classList.add("error");
        }
    }
    dialog.close();
};

socket.onerror = function (error) {
    body.classList.add("error");
    dialog.close();
};