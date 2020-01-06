let intersections = [];
let lines = [];


function drawMap(i, l) {
    for (var key in i) {
        if (i.hasOwnProperty(key)){
            intersections[i[key].id] = i[key];

            drawIntersection(i[key]);
        }
    }

    for (var key2 in l) {
        if (l.hasOwnProperty(key2)){
            drawLine(l[key2]);
        }

    }
}

function isIntersect(point, intersection) {
    return Math.sqrt((point.x-intersection.x)** 2 + (point.y - intersection.y) ** 2) < 10;
}

function drawCircle(x, y, color, size) {
    ctx.beginPath();
    ctx.arc(x, y, 10, 0, 2 * Math.PI);
    ctx.strokeStyle = color;
    ctx.lineWidth = size;
    ctx.stroke();
}
function drawRect(intersection){
    ctx.fillRect(intersection.x, intersection.y,5,5);
}

function drawIntersection(intersection) {
    drawCircle(intersection.x, intersection.y, "#2e2e2e");
}
function drawStartIntersection(id) {
    let startIntersection = intersections[id];
    drawCircle(startIntersection.x, startIntersection.y, "#00568f", 5);
}
function drawDestIntersection(id) {
    let destIntersection = intersections[id];
    drawCircle(destIntersection.x, destIntersection.y, "#2d8f01", 5);
}
function drawLine(line) {
    let from = intersections[line.from];
    let to = intersections[line.to];

    ctx.beginPath();
    ctx.moveTo(from.x, from.y);
    ctx.lineTo(to.x, to.y);
    ctx.stroke();
}

function createIntersection(id, x, y) {
    return {id: id, x: x, y: y};
}
function createLine(intersection1, intersection2) {
    return { from: intersection1, to: intersection2 }
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
        let intersection1 = intersections[randomIntFromInterval(0, iAmount-1)];
        let intersection2 = intersections[randomIntFromInterval(0, iAmount-1)];

        lines[i] = createLine(intersection1, intersection2);
        drawLine(lines[i]);
    }
}

function randomIntFromInterval(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}