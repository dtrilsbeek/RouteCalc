var canvas, ctx;

function init() {
    canvas = document.getElementById('route-map');
    ctx = canvas.getContext("2d");
    canvas.width = 800;
    canvas.height = 800;

    drawRandomIntersections(50);
    drawRandomLines(99);
}