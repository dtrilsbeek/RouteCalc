let socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/travel/" +  window.location.pathname.split("/").pop());

socket.onopen = function (e) {
    body.classList.remove("error");
    navbarTitle.innerText = "RouteCalc";
};

socket.onmessage = function (event) {
    var received = JSON.parse(event.data);
    console.log(received);
    switch (received.type) {

        case received.isSystem === true:
        case "system":
            showChat(received.message, true);
            break;

        case "drawMap":
            const {intersections} = received;
            const {route} = received;
            const {explored} = received;

            drawMap(intersections, route, explored);
            break;

        case "CHAT":
            showChat(received.sender + ": " + received.message);
            break;
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
};

socket.onerror = function (error) {
    body.classList.add("error");
};

function sendHeartbeat() {
    const pulse = {type: "PULSE"};
    socket.send(JSON.stringify(pulse));
}
function heartbeat() {
    if (!socket) return;
    if (socket.readyState !== 1) return;
    sendHeartbeat();
    setTimeout(heartbeat,  30000);
}