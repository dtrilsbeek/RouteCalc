let intersections = [];
let lines = [];

function drawCircle(intersection) {
    ctx.beginPath();
    ctx.arc(intersection.x, intersection.y, 10, 0, 2 * Math.PI);
    ctx.stroke();
}
function drawRect(intersection){
    ctx.fillRect(intersection.x, intersection.y,5,5);
}

function drawIntersection(intersection) {
    drawCircle(intersection);
}
function drawLine(line) {
    ctx.beginPath();
    ctx.moveTo(line.from.x, line.from.y);
    ctx.lineTo(line.to.x, line.to.y);
    ctx.stroke();
}

function createIntersection(x, y) {
    return { x: x, y: y};
}
function createLine(intersection1, intersection2) {
    return { from: intersection1, to: intersection2 }
}

function drawRandomIntersections(amount) {
    for (let i = 0; i < amount; i++) {
        intersections[i] = createIntersection(randomIntFromInterval(10, 790), randomIntFromInterval(10, 790));
        drawIntersection(intersections[i]);
    }
}
function drawRandomLines(amount) {
    console.log(intersections);
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