const action = localStorage.getItem("action");
const id = localStorage.getItem("bookId")
const button = document.getElementById("save_change");
title = document.getElementById("title");
authorName = document.getElementById("authorName");
genre = document.getElementById("genre");
price = document.getElementById("price");

if (action === "update"){
    title.value = localStorage.getItem("title");
    authorName.value = localStorage.getItem("authorName");
    genre.value = localStorage.getItem("genre");
    price.value = localStorage.getItem("price");
    localStorage.clear();
} else if (action === "insert"){
    localStorage.clear();
}



button.onclick = async function () {
    if (action === "update") {
       let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/books?action=update", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                id : id,
                title: title.value,
                authorName: authorName.value,
                genre: genre.value,
                price: price.value
            })
        }).then(data => data.json());
        console.log(response);
        let success = response.success;
        console.log(success);
        if(success === "1"){
            location.href="books.html";
        } else {
            alert("Не удалось обновить книгу")
        }
    } else {
        let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/books?action=insert", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                title: title.value,
                authorName: authorName.value,
                genre: genre.value,
                price: price.value
            })
        }).then(data => data.json());
        console.log(response);
        let success = response.success;
        console.log(success);
        if(success === "1"){
            location.href="books.html";
        } else {
            alert("Не удалось добавить книгу")
        }
    }
}