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
            x: e.pageX - canvas.getBoundingClientRect().left,
            y: e.pageY - canvas.getBoundingClientRect().top
        };

        for (var key in intersections) {
            if (intersections.hasOwnProperty(key)) {
                if (isIntersect(pos, intersections[key])) {
                    sendIntersection(intersections[key]);
                }
            }
        }
    });
}

function sendIntersection(intersection) {
    console.log("Intersection Send: " + intersection.id + ", " +intersection.x + ", " + intersection.y  );

    socket.send(JSON.stringify(intersection));

}