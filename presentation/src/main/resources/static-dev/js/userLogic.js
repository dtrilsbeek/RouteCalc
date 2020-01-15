let type = "START";

function setType(t) {
    type = t;
}

function sendIntersection(intersection) {
    const send = {type: type, intersectionId: intersection.id};
    socket.send(JSON.stringify(send));
}

function initUserLogic() {
    canvas.addEventListener('click', (e) => {
        const pos = {
            x: e.pageX - canvas.getBoundingClientRect().left,
            y: e.pageY - canvas.getBoundingClientRect().top
        };

        for (let key in intersections) {
            if (intersections.hasOwnProperty(key)) {
                if (isIntersect(pos, intersections[key])) {
                    console.log("Clicked", intersections[key]);
                    sendIntersection(intersections[key]);
                }
            }
        }
    });
}