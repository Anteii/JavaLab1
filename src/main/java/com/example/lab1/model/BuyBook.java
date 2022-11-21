package com.example.lab1.model;

public class BuyBook {
    private int buyBookId;
    private int bookId;
    private int clientId;
    private int amount;

    public BuyBook(int buyBookId, int bookId, int clientId, int amount) {
        this.buyBookId = buyBookId;
        this.bookId = bookId;
        this.clientId = clientId;
        this.amount = amount;
    }

    public BuyBook(int bookId, int clientId, int amount) {
        this.buyBookId = -1;
        this.bookId = bookId;
        this.clientId = clientId;
        this.amount = amount;
    }

    public int getBuyBookId() {
        return buyBookId;
    }

    public void setBuyBookId(int buyBookId) {
        this.buyBookId = buyBookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {

//        return "{\"clientId\": " + clientId + ", \"clientName\": \"" + clientName + "\"" +
//                ", \"cityName\": \"" + cityName + "\", \"clientEmail\": \"" + clientEmail + "\"}";

        return "{\"buyBookId\": " + buyBookId + ", \"bookId\": " + bookId +
                ", \"clientId\": " + clientId + ", \"amount\": " + amount + "}";
    }
}
