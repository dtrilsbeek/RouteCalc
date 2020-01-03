function sendChat() {
    var chatBox = document.getElementById("chatBox");
    if (chatBox.value.length > 0) {
        var obj = {
            type: "chat",
            message: chatBox.value
        };
        var json = JSON.stringify(obj);
        socket.send(json);
        chatBox.value = "";
    }
}
function showChat(json, system = false) {
    var node = document.createElement("li");
    var textnode = document.createTextNode(json);
    if(system){
        node.classList.add("system");
    }
    node.appendChild(textnode);
    chatList.append(node);
    if(chatList.childElementCount > 100){
        chatList.firstElementChild.remove();
    }
    if (chatList.scrollHeight > chatList.clientHeight) {
        chatList.scrollTop = chatList.scrollHeight;
    }
}

function sidebarChat(obj) {
    var buttons = document.getElementsByClassName("sidebar-button active");
    for (i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("active");
    }
    obj.classList.add("active");
    document.getElementsByClassName("scoreboard")[0].style.display = "none";
    document.getElementsByClassName("chat")[0].style.display = "block";
}