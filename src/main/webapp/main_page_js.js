book_button = document.getElementById("change_books");
client_button = document.getElementById("change_clients");
buy_book_button = document.getElementById("change_buy_books");


Promise.all([
    fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/GetTableServlet?table=book")
        .then(data => data.json())
        .then(data => {
            let table_data = "";
            let table = document.getElementById("books-table");
            let table_head = `
            <thead>
        <tr>
        <th>ID книги</th>
        <th>Название</th>
        <th>Имя автора</th>
        <th>Жанр</th>
        <th>Цена</th>
        </tr>
        </thead>
        `
            data.map(value => {
                table_data += `<tr>
            <td>${value.bookId}</td>
            <td>${value.title}</td>
            <td>${value.authorName}</td>
            <td>${value.genre}</td>
            <td>${value.price}</td>
            </tr>`;
            });
            table.insertAdjacentHTML("afterbegin", table_head)
            document.getElementById("books-body").innerHTML=table_data;
        }),

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
        </tr>
        </thead>
        `
            data.map(value => {
                table_data += `<tr>
            <td>${value.clientId}</td>
            <td>${value.clientName}</td>
            <td>${value.cityName}</td>
            <td>${value.clientEmail}</td>
            </tr>`;
            });
            table.insertAdjacentHTML("afterbegin", table_head)
            document.getElementById("clients-body").innerHTML=table_data;
        }),

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
        </tr>
        </thead>
        `
            data.map(value => {
                table_data += `<tr>
            <td>${value.buyBookId}</td>
            <td>${value.bookId}</td>
            <td>${value.clientId}</td>
            <td>${value.amount}</td>
            </tr>`;
            });
            table.insertAdjacentHTML("afterbegin", table_head)
            document.getElementById("buy-books-body").innerHTML=table_data;
        })
    ])

book_button.onclick = function () {
    location.href='http://localhost:8080/lab1-1.0-SNAPSHOT/books';
}

client_button.onclick = function (){
    location.href="http://localhost:8080/lab1-1.0-SNAPSHOT/clients"
}
buy_book_button.onclick = function (){
    location.href="http://localhost:8080/lab1-1.0-SNAPSHOT/buy-book"
}

window.onload = () => {
    let head = document.getElementsByTagName("head")[0];
    let link  = document.createElement('link');
    link.rel  = 'stylesheet';
    link.type = 'text/css';
    link.href = 'tables_css.css';
    head.appendChild(link)
}