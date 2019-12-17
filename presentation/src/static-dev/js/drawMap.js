function drawIntersection(canvas, intersection) {
    canvas.fillRect(intersection.x, intersection.y,20,20)
}

function createIntersection(x, y) {
    return { x: x, y: y};
}

function drawRandomIntersections() {
    const canvas = document.getElementById("route-map");

    for (let i = 0; i < 100; i++) {
        let intersection = createIntersection(randomIntFromInterval(50, 100), randomIntFromInterval(50, 100))
    }
}

function randomIntFromInterval(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}