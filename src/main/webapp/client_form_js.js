const action = localStorage.getItem("action");
const id = localStorage.getItem("id")
const button = document.getElementById("save_change");

const url = "http://localhost:8080/lab1-1.0-SNAPSHOT/clients";

nameElement = document.getElementById("name");
cityElement = document.getElementById("city");
emailElement = document.getElementById("email");


if (action === "update"){
    nameElement.value = localStorage.getItem("name");
    cityElement.value = localStorage.getItem("city");
    emailElement.value = localStorage.getItem("email");
    localStorage.clear();
} else if (action === "insert"){
    localStorage.clear();
}

const createClient = async (name, city, email) => {
    return fetch(url + "?action=insert", {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            name: name,
            city: city,
            email: email
        })
    });
};

const updateClient = async (id, name, city, email) => {
    return fetch(url + "?action=update", {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            id: id,
            name: name,
            city: city,
            email: email
        })
    });
};

// Button click
button.onclick = async function () {
    if (action === "update") {
        updateClient(id, nameElement.value, cityElement.value, emailElement.value)
            .then(function(response){
                if(response.ok) return response => response.json();
                else throw new Error('Can\'t update client data');
            })
            .then(_ => location.href = "clients.html").catch(reason => alert(reason));
    } else {
        createClient(nameElement.value, cityElement.value, emailElement.value)
            .then(function(response){
                if(response.ok) return response => response.json();
                else throw new Error('Can\'t add new client');
            })
            .then(_ => location.href = "clients.html").catch(reason => alert(reason));
    }
};