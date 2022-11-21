const button = document.getElementById("addClient");
const returnButton = document.getElementById("returnOnMainPage");

fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/GetTableServlet?table=client")
    .then(data => data.json())
    .then(data => {
        let table_data = "";
        let table = document.getElementById("clients-table");
        let table_head = `
            <thead>
        <tr>
        <th>ID клиента</th>
        <th>Имя</th>
        <th>Город</th>
        <th>Почта</th>
        <th>Изменение</th>
        <th>Удаление</th>
        </tr>
        </thead>
        `
        data.map(value => {
            table_data += `<tr>
            <td>${value.clientId}</td>
            <td>${value.clientName}</td>
            <td>${value.cityName}</td>
            <td>${value.clientEmail}</td>
            <td> <a id="delete${value.clientId}" onclick="window.delete_client(${value.clientId})"> Удалить клиента </a></td>
            <td> <a id="update${value.clientId}" onclick="window.update_client(${value.clientId}, \'${value.clientName}\', \'${value.cityName}\',
                                                                           \'${value.clientEmail}\')"> Изменить клиента </td>
            </tr>`;
        });
        table.insertAdjacentHTML("afterbegin", table_head)
        document.getElementById("clients-body").innerHTML=table_data;
    })

function update_client(clientId, clientName, cityName, clientEmail){
    localStorage.setItem("action", "update");
    localStorage.setItem("clientId", clientId);
    localStorage.setItem("clientName", clientName);
    localStorage.setItem("cityName", cityName);
    localStorage.setItem("clientEmail", clientEmail);
    location.href="client_form.html";
}

async function delete_client(clientId) {
    let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/clients?action=delete&id=" + clientId, {
        method: "POST"
    }).then(data => data.json());
    let success = response.success;
    if (success === "1"){
        let delete_row = document.getElementById("delete" + clientId);
        delete_row = delete_row.closest("tr");
        delete_row.remove();
    } else {
        alert("Не удалось удалить строку");
    }
}

button.onclick = function () {
    localStorage.setItem("action", "insert");
    location.href="client_form.html";
}

returnButton.onclick = function (){
    location.href="http://localhost:8080/lab1-1.0-SNAPSHOT/main-page"
}

window.onload = () => {
    let head = document.getElementsByTagName("head")[0];
    let link  = document.createElement('link');
    link.rel  = 'stylesheet';
    link.type = 'text/css';
    link.href = 'tables_css.css';
    head.appendChild(link)
}