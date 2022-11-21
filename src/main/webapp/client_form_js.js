const action = localStorage.getItem("action");
const id = localStorage.getItem("clientId")
const button = document.getElementById("save_change");
clientName = document.getElementById("clientName");
cityName = document.getElementById("cityName");
clientEmail = document.getElementById("clientEmail");

if (action === "update"){
    clientName.value = localStorage.getItem("clientName");
    cityName.value = localStorage.getItem("cityName");
    clientEmail.value = localStorage.getItem("clientEmail");
    localStorage.clear();
} else if (action === "insert"){
    localStorage.clear();
}



button.onclick = async function () {
    if (action === "update") {
        let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/clients?action=update", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                id : id,
                clientName: clientName.value,
                cityName: cityName.value,
                clientEmail: clientEmail.value,
            })
        }).then(data => data.json());
        console.log(response);
        let success = response.success;
        console.log(success);
        if(success === "1"){
            location.href="clients.html";
        } else {
            alert("Не удалось обновить книгу")
        }
    } else {
        let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/clients?action=insert", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                clientName: clientName.value,
                cityName: cityName.value,
                clientEmail: clientEmail.value,
            })
        }).then(data => data.json());
        console.log(response);
        let success = response.success;
        console.log(success);
        if(success === "1"){
            location.href="clients.html";
        } else {
            alert("Не удалось добавить клиента")
        }
    }
}