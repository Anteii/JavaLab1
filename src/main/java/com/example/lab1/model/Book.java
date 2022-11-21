package com.example.lab1.model;

public class Book {
    private int bookId;
    private String title;
    private String authorName;
    private String genre;
    private double price;

    public Book(int bookId, String title, String authorName, String genre, double price) {
        this.bookId = bookId;
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
        this.price = price;
    }

    public Book(String title, String authorName, String genre, double price){
        this.bookId = -1;
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{\"bookId\": " + bookId + ", \"title\": \"" + title + "\", \"authorName\": \"" + authorName + "\"," +
                " \"genre\": \"" + genre + "\", \"price\": " + price + "}";
    }
}
