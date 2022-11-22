const action = localStorage.getItem("action");
const id = localStorage.getItem("id")
const button = document.getElementById("save_change");

const url = "http://localhost:8080/lab1-1.0-SNAPSHOT/books";

// Inputs
titleElement = document.getElementById("title");
authorNameElement = document.getElementById("authorName");
genreElement = document.getElementById("genre");
priceElement = document.getElementById("price");

// Check how we ended up here
if (action === "update"){
    titleElement.value = localStorage.getItem("title");
    authorNameElement.value = localStorage.getItem("authorName");
    genreElement.value = localStorage.getItem("genre");
    priceElement.value = localStorage.getItem("price");
    localStorage.clear();
} else if (action === "insert"){
    localStorage.clear();
}

const createBook = async (title, authorName, genre, price) => {
    return fetch(url + "?action=insert", {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            title: title,
            authorName: authorName,
            genre: genre,
            price: price
        })
    });
};

const updateBook = async (id, title, authorName, genre, price) => {
    return fetch(url + "?action=update", {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            id : id,
            title: title,
            authorName: authorName,
            genre: genre,
            price: price
        })
    });
};

// Button click
button.onclick = async function () {
    if (action === "update") {
        updateBook(id, titleElement.value, authorNameElement.value, genreElement.value, priceElement.value)
        .then(function(response){
            if(response.ok) return response => response.json();
            else throw new Error('Can\'t add update book data');
        })
        .then(_ => location.href = "books.html").catch(reason => alert(reason));
    } else {
        createBook(titleElement.value, authorNameElement.value, genreElement.value, priceElement.value)
        .then(function(response){
            if(response.ok) return response => response.json();
            else throw new Error('Can\'t add new book.');
        })
        .then(_ => location.href = "books.html").catch(reason => alert(reason));
    }
};