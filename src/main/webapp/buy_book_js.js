const button = document.getElementById("addBuyBook");
const returnButton = document.getElementById("returnOnMainPage");

fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/GetTableServlet?table=order")
    .then(data => data.json())
    .then(data => {
        let table_data = "";
        let table = document.getElementById("buy-books-table");
        let table_head = `
            <thead>
        <tr>
        <th>ID заказа</th>
        <th>ID книги</th>
        <th>ID клиента</th>
        <th>Количество экземпляров</th>
        <th>Удаление</th>
        <th>Изменение</th>
        </tr>
        </thead>
        `
        data.map(value => {
            table_data += `<tr>
            <td>${value.buyBookId}</td>
            <td>${value.bookId}</td>
            <td>${value.clientId}</td>
            <td>${value.amount}</td>
            <td> <a id="delete${value.buyBookId}" onclick="window.delete_buy_book(${value.buyBookId})"> Удалить заказ </a></td>
            <td> <a id="update${value.buyBookId}" onclick="window.update_buy_book(${value.buyBookId}, ${value.bookId},
                                                                              ${value.clientId}, ${value.amount})"> Изменить заказ </td>
            </tr>`;
        });
        table.insertAdjacentHTML("afterbegin", table_head)
        document.getElementById("buy-books-body").innerHTML=table_data;
    })

function update_buy_book(buyBookId, bookId, clientId, amount){
    localStorage.setItem("action", "update");
    localStorage.setItem("buyBookId", buyBookId);
    localStorage.setItem("bookId", bookId);
    localStorage.setItem("clientId", clientId);
    localStorage.setItem("amount", amount);
    location.href="buy_book_form.html";
}

async function delete_buy_book(buyBookId) {
    let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/buy-book?action=delete&id=" + buyBookId, {
        method: "POST"
    }).then(data => data.json());
    let success = response.success;
    if (success === "1"){
        let delete_row = document.getElementById("delete" + buyBookId);
        delete_row = delete_row.closest("tr");
        delete_row.remove();
    } else {
        alert("Не удалось удалить строку");
    }
}

button.onclick = function () {
    localStorage.setItem("action", "insert");
    location.href="buy_book_form.html";
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