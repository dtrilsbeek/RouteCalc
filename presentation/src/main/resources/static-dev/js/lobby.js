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
    await getUsername();
    const rooms = await (await fetch(window.location.origin + "/rooms")).json();
    const content = document.querySelector("#content");
    content.innerHTML = "";

        for (let key in rooms) {
            if (rooms.hasOwnProperty(key)) {
                let room = rooms[key];

                content.innerHTML += makeCard(room);
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

async function setUsername(res) {
    let body = await res.text();
    const elem = document.getElementById("username");

    console.log(body);
    if (body.length > 0) {
        const user = JSON.parse(body);
        elem.innerText = "Welcome, " + user.name;
    }
}

async function getUsername() {
    fetch("/getUser", {
        method: 'get'
    }).then(res => {
        console.log(res);
        if(res.status === 200) {
            setUsername(res);
        }
        else if(res.status === 400) {
            showError("Username Already Exists");
        }
        else {
            showError("Connection Error");
        }
    });
}