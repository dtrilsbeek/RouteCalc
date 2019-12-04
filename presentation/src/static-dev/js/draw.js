function color(color) {
    var selectedColor = document.getElementsByClassName("selectedColor");
    var newColor = document.getElementById(color);

    if(selectedColor[0] != newColor) {
        selectedColor[0].classList.remove("selectedColor");
        newColor.classList.add("selectedColor");
        switch (color) {
            case "green":
                x = "green";
                break;
            case "blue":
                x = "blue";
                break;
            case "red":
                x = "red";
                break;
            case "yellow":
                x = "yellow";
                break;
            case "orange":
                x = "orange";
                break;
            case "black":
                x = "black";
                break;
            case "white":
                x = "white";
                break;
        }
        if (x == "white") brushSize = 35;
        else brushSize = 2;
    }
}

function draw(send = true) {
    ctx.beginPath();
    ctx.moveTo(prevX, prevY);
    ctx.lineTo(currX, currY);
    ctx.strokeStyle = x;
    ctx.lineWidth = brushSize;
    ctx.stroke();
    ctx.closePath();
    if (send) {
        let canvas = document.querySelector("canvas");
        var obj = {
            type: "draw",
            prevX: prevX,
            prevY: prevY,
            currX: currX,
            currY: currY,
            color: x,
            brushSize: brushSize,
            canvasWidth: canvas.width,
            canvasHeight: canvas.height,
        };
        var json = JSON.stringify(obj);
        socket.send(json);
    }
}

function erase() {
    var m = confirm("Do you want to clear the drawing field?");
    if (m) {
        var obj = {
            type: "clear"
        };
        var json = JSON.stringify(obj);
        socket.send(json);
        ctx.clearRect(0, 0, w, h);
    }
}
function eraseRemote() {
    ctx.clearRect(0, 0, w, h);
}

function fill() {
    var m = confirm("Do you want to fill the drawing field with the selected color? This will remove everything you drew so far.");
    if (m) {
        var obj = {
            type: "fill",
            color: x
        };
        var json = JSON.stringify(obj);
        socket.send(json);
        ctx.fillStyle = x;
        ctx.fillRect(0, 0, w, h);
    }
}
function fillRemote(color) {
    ctx.fillStyle = color;
    ctx.fillRect(0, 0, w, h);
}

function save(send = true) {
    undoStep = 1;
    var dataURL = canvas.toDataURL();
    snapshots.push(dataURL);
    if(send) {
        var obj = {
            type: "save"
        };
        var json = JSON.stringify(obj);
        socket.send(json);
    }
}

function biggerBrush(){
    brushSize++;
}

function smallerBrush(){
    brushSize--;
}

function undo(send = true) {
    firstRedo = true;
    undoStep++;
    if (undoStep > snapshots.length) {
        undoStep = snapshots.length;
        return;
    }
    var image = new Image();
    image.src = snapshots[snapshots.length - undoStep];
    image.onload = function () {
        ctx.clearRect(0, 0, w, h);
        ctx.drawImage(image, 0, 0);
    };
    if(send) {
        var obj = {
            type: "undo"
        };
        var json = JSON.stringify(obj);
        socket.send(json);
    }
}

function redo(send = true) {
    if (firstRedo) {
        undoStep = undoStep - 2;
        firstRedo = false;
    }
    else {
        undoStep--;
    }
    if (undoStep < 0) {
        undoStep = 0;
        return;
    }
    var image = new Image();
    image.src = snapshots[snapshots.length - (undoStep + 1)];
    image.onload = function () {
        ctx.clearRect(0, 0, w, h);
        ctx.drawImage(image, 0, 0);
    };
    if(send) {
        var obj = {
            type: "redo"
        };
        var json = JSON.stringify(obj);
        socket.send(json);
    }
}

function findxy(res, e) {
    if(isDrawer || freeDraw) {
        if (res == 'down') {
            prevX = currX;
            prevY = currY;
            currX = e.clientX - canvas.offsetLeft;
            currY = e.clientY - canvas.offsetTop;

            flag = true;
            dot_flag = true;
            if (dot_flag) {
                ctx.beginPath();
                ctx.fillStyle = x;
                ctx.fillRect(currX, currY, 2, 2);
                ctx.closePath();
                dot_flag = false;
            }
        }
        if (res == 'up' || res == "out") {
            if (flag) {
                save();
            }
            flag = false;
        }
        if (res == 'move') {
            if (flag) {
                prevX = currX;
                prevY = currY;
                currX = e.clientX - canvas.offsetLeft;
                currY = e.clientY - canvas.offsetTop;
                draw();
            }
        }
    }
}