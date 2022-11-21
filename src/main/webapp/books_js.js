const button = document.getElementById("addBook");
const returnButton = document.getElementById("returnOnMainPage");
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
        <th>Удаление</th>
        <th>Изменение</th>
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
            <td> <a id="delete${value.bookId}" onclick="window.delete_book(${value.bookId})"> Удалить книгу </a></td>
            <td> <a id="update${value.bookId}" onclick="window.update_book(${value.bookId}, \'${value.title}\', \'${value.authorName}\',
                                                                           \'${value.genre}\', ${value.price})"> Изменить книгу </td>
            </tr>`;
        });
        table.insertAdjacentHTML("afterbegin", table_head)
        document.getElementById("books-body").innerHTML=table_data;
    })

function update_book(bookId, title, authorName, genre, price){
    localStorage.setItem("action", "update");
    localStorage.setItem("bookId", bookId);
    localStorage.setItem("title", title);
    localStorage.setItem("authorName", authorName);
    localStorage.setItem("genre", genre);
    localStorage.setItem("price", price);
    location.href="book_form.html";
}

async function delete_book(bookId) {
    console.log("удаление книги с ID " + bookId);

    let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/books?action=delete&id=" + bookId, {
        method: "POST"
    }).then(data => data.json());
    console.log(response);
    let success = response.success;
    console.log(success);
    if (success === "1"){
        let delete_row = document.getElementById("delete" + bookId);
        delete_row = delete_row.closest("tr");
        delete_row.remove();
    } else {
        alert("Не удалось удалить строку");
    }
}

button.onclick = function () {
    localStorage.setItem("action", "insert");
    location.href="book_form.html";
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