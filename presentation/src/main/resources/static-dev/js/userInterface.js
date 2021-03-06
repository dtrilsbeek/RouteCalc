function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    console.log("cookieval",value);
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
}
async function loginUser() {
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById("login"))) {
        data.append(pair[0], pair[1]);
    }

    fetch("/login", {
        method: 'post',
        body: data,
    }).then(res => {
        if(res.status === 200) {
            window.location.replace(res.url);
        }
        else if(res.status === 400) {
            showError("Username or Password Incorrect");
        }
        else {
            showError("Connection Error");
        }
    });
}

async function registerUser() {
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById("register"))) {
        data.append(pair[0], pair[1]);
    }

    fetch("/register", {
        method: 'post',
        body: data,
    }).then(res => {
        if(res.status === 200) {
            window.location.replace(res.url);
        }
        else if(res.status === 400) {
            showError("Username Already Exists");
        }
        else {
            showError("Connection Error");
        }
    });
}

function showError(message) {
    const errorElem = document.getElementById("error");
    errorElem.classList.add("alert", "alert-danger");
    errorElem.innerText = message;
}
