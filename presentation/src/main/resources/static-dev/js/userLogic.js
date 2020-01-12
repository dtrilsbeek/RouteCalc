let type = "START";

function setType(t) {
    type = t;
}

function sendIntersection(intersection) {
    console.log(intersection );
    const test = {type: type, intersectionId: intersection.id};
    socket.send(JSON.stringify(test));
}

function initUserLogic() {
    canvas.addEventListener('click', (e) => {
        const pos = {
            x: e.pageX - canvas.getBoundingClientRect().left,
            y: e.pageY - canvas.getBoundingClientRect().top
        };

        console.log(intersections);
        for (let key in intersections) {
            if (intersections.hasOwnProperty(key)) {
                if (isIntersect(pos, intersections[key])) {
                    console.log("is intersect");
                    sendIntersection(intersections[key]);
                }
            }
        }
    });
}