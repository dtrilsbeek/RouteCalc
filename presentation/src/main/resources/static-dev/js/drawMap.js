let intersections = [];
let route = [];


function drawMap(i, r) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    for (let key in i) {
        if (i.hasOwnProperty(key)) {
            intersections[i[key].id] = i[key];
        }
    }

    for (let key in intersections) {
        if (intersections.hasOwnProperty(key)) {
            drawIntersection(intersections[key]);
        }
    }

    drawRoute(r);
}

function isIntersect(point, intersection) {
    return Math.sqrt((point.x - intersection.x) ** 2 + (point.y - intersection.y) ** 2) < 10;
}

function drawRect(intersection) {
    ctx.fillRect(intersection.x, intersection.y, 5, 5);
}

function drawRoute(route) {

    console.log(route);

    for (let key in route) {
        if (route.hasOwnProperty(key)) {
            let fromId = route[key].id;

            if (intersections.hasOwnProperty(fromId)) {
                let next = parseInt(key) +1;
                if (route.hasOwnProperty(next)) {

                    let toId = route[next].id;
                    if (intersections.hasOwnProperty(toId)) {
                        let intersectionFrom = intersections[fromId];
                        let intersectionTo = intersections[toId];

                        console.log("From", intersectionFrom);
                        console.log("To", intersectionTo);

                        drawLine(intersectionFrom, intersectionTo, "#23a576", 5);
                    }
                }
            }
        }
    }

}

function drawIntersection(intersection) {
    if (intersection.start === true) {
        drawStartIntersection(intersection)
    }
    if (intersection.dest === true) {
        drawDestIntersection(intersection)
    }
    drawCircle(intersection.x, intersection.y, "#2e2e2e");

    const connections = intersection.connections;

    if (connections) {
        for (let key in connections) {
            if (connections.hasOwnProperty(key)) {
                let id = connections[key];
                let intersectionTo = intersections[id];

                drawLine(intersection, intersectionTo);
            }
        }
    }

}

function drawStartIntersection(intersection) {
    drawCircle(intersection.x, intersection.y, "#00568f", 5);
}

function drawDestIntersection(intersection) {
    drawCircle(intersection.x, intersection.y, "#2d8f01", 5);
}

function setStrokeColorWidth(color, width) {
    ctx.strokeStyle = color;
    ctx.lineWidth = width;
}

function drawLine(from, to, color, width) {
    ctx.beginPath();
    ctx.moveTo(from.x, from.y);
    ctx.lineTo(to.x, to.y);
    let prevColor = ctx.strokeStyle;
    let prevWidth = ctx.lineWidth;
    setStrokeColorWidth(color, width);
    ctx.stroke();
    setStrokeColorWidth(prevColor, prevWidth);
}

function drawCircle(x, y, color, width) {
    ctx.beginPath();
    ctx.arc(x, y, 10, 0, 2 * Math.PI);
    let prevColor = ctx.strokeStyle;
    let prevWidth = ctx.lineWidth;
    setStrokeColorWidth(color, width);
    ctx.stroke();
    setStrokeColorWidth(prevColor, prevWidth);
}

function createIntersection(id, x, y) {
    return {id: id, x: x, y: y};
}

function createLine(intersection1, intersection2) {
    return {from: intersection1, to: intersection2}
}

function drawRandomIntersections(amount) {
    for (let i = 0; i < amount; i++) {
        intersections[i] = createIntersection(i, randomIntFromInterval(10, 790), randomIntFromInterval(10, 790));
        drawIntersection(intersections[i]);
    }
}

function drawRandomLines(amount) {
    const iAmount = intersections.length;
    for (let i = 0; i < amount; i++) {
        let intersection1 = intersections[randomIntFromInterval(0, iAmount - 1)];
        let intersection2 = intersections[randomIntFromInterval(0, iAmount - 1)];

        lines[i] = createLine(intersection1, intersection2);
        drawLine(lines[i]);
    }
}

function randomIntFromInterval(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}