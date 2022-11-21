const action = localStorage.getItem("action");
const id = localStorage.getItem("buyBookId")
const button = document.getElementById("save_change");
bookId = document.getElementById("bookId");
clientId = document.getElementById("clientId");
amount = document.getElementById("amount");

if (action === "update"){
    bookId.value = localStorage.getItem("bookId");
    clientId.value = localStorage.getItem("clientId");
    amount.value = localStorage.getItem("amount");
    localStorage.clear();
} else if (action === "insert"){
    localStorage.clear();
}



button.onclick = async function () {
    if (action === "update") {
        let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/buy-book?action=update", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                id : id,
                bookId: bookId.value,
                clientId: clientId.value,
                amount: amount.value,
            })
        }).then(data => data.json());
        console.log(response);
        let success = response.success;
        console.log(success);
        if(success === "1"){
            location.href="buy_book.html";
        } else {
            alert("Не удалось обновить книгу")
        }
    } else {
        let response = await fetch("http://localhost:8080/lab1-1.0-SNAPSHOT/buy-book?action=insert", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                bookId: bookId.value,
                clientId: clientId.value,
                amount: amount.value,
            })
        }).then(data => data.json());
        console.log(response);
        let success = response.success;
        console.log(success);
        if(success === "1"){
            location.href="buy_book.html";
        } else {
            alert("Не удалось добавить книгу")
        }
    }
}