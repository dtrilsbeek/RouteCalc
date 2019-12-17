var canvas, ctx;

function init() {
    canvas = document.getElementById('route-map');
    ctx = canvas.getContext("2d");
    canvas.width = 800;
    canvas.height = 800;

    drawRandomIntersections(50);
    drawRandomLines(99);

    canvas.addEventListener('click', (e) => {
        const pos = {
            x: e.clientX,
            y: e.clientY
        };
        console.log("position");
        console.log(pos);
        for (var key in intersections){
            if (intersections.hasOwnProperty(key)) {
                if (isIntersect(pos, intersections[key])) {
                    alert('click on intersection: ' + key);
                }
            }
        }
    });
}