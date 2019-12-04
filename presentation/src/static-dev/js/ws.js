let socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/" +  window.location.pathname.split("/").pop());

socket.onopen = function (e) {
    body.classList.remove("error");
    navbarTitle.innerText = "RouteCalc";
};

socket.onmessage = function (event) {
    var received = JSON.parse(event.data);
    if (received.type === "system" || received.isSystem === true) {
        showChat(received.message, true);
    }
    if (received.type === "chat") {
        showChat(received.sender + ": " + received.message);
    }
};

socket.onclose = function (event) {
    if (event.wasClean) {
        navbarTitle.innerText = "Connection closed";
        body.classList.add("error");
    } else {
        // e.g. server process killed or network down
        // event.code is usually 1006 in this case
        navbarTitle.innerText = "Connection lost...";
        body.classList.add("error");
    }
    dialog.close();
};

socket.onerror = function (error) {
    body.classList.add("error");
    dialog.close();
};