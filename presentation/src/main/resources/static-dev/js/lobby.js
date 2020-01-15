const makeCard = card => `
        <div id="room${card.id}" class="room">
                <div class="title">
                    <h3 class="roomTitle">Room: /${card.id}</h3>
                </div>
                <div class="bottom">
                    <a href="travel/${card.id}"><button class="btnJoin submit-button">Join</button></a>
                </div>

            </div>   
        `;

const load = async () => {

    const rooms = await (await fetch(window.location.origin + "/rooms")).json();
    console.log(rooms);
    const content = document.querySelector("#content");
    content.innerHTML = "";

        for (let key in rooms) {
            if (rooms.hasOwnProperty(key)) {
                let room = rooms[key];

                content.innerHTML += makeCard(room);

                console.log(room);

                // document.querySelector(`#room${room.id} .roomTitle`).textContent = name;
                // document.querySelector(`#room${room.id} .users`).textContent = users;
            }
        }
};

function createRoom() {
    let name = Math.random().toString(36).substring(7);
    let formData = new FormData();
    formData.append('name', name);

    fetch("/create-room", {
        body: formData,
        method: "post"
    }).then(res => {
        window.location.replace(res.url);
    });
}