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

function createIntersection(x, y) {
    return { x: x, y: y};
}

function drawRandomIntersections(amount) {
    for (let i = 0; i < amount; i++) {
        let intersection = createIntersection(randomIntFromInterval(10, 790), randomIntFromInterval(10, 790));

        drawIntersection(intersection);
    }
}

function randomIntFromInterval(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}