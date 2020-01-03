const makeCard = card => `
        <div id="room${card.id}" class="room">
                <div class="title">
                    <h3 class="roomTitle"></h3>
                </div>

                <div class="content">
                    <b>users:</b>
                    <p class="users"></p>
                </div>

                <div class="bottom">
                    ${card.users.length < card.maxusers ?
    `<a href="join/${card.id}"><button class="btnJoin submit-button">Join</button></a>` :
    '<button disabled class="btnJoin">Room is full...</button>'}
                </div>

            </div>   
        `;

const load = async () => {

    const rooms = await (await fetch(window.location.origin + "/rooms")).json();
    console.log(rooms);
    const content = document.querySelector("#content");
    content.innerHTML = "";

    if (rooms.length > 0) {
        for(let room of rooms) {
            content.innerHTML += makeCard(room);
            const name = `${room.name} (${room.users.length}/${room.maxusers})`;
            const users = room.users.join(",");

            document.querySelector(`#room${room.id} .roomTitle`).textContent = name;
            document.querySelector(`#room${room.id} .users`).textContent = users;
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