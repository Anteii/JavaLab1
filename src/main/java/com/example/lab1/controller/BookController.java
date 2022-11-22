package com.example.lab1.controller;

import com.example.lab1.dao.BookDAO;
import com.example.lab1.model.Book;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class BookController {
    @Inject
    private BookDAO bookDAO;

    public void removeBook(Integer id){
        bookDAO.removeById(id);
    }
    public Book createBook(String title, String authorName, String genre, Double price){
        Book book = new Book();
        book.update(title, authorName, genre, price);
        bookDAO.create(book);

        return book;
    }
    public Book updateBook(Integer id, String title, String authorName, String genre, Double price){
        Book book = bookDAO.findByID(id);
        book.update(title, authorName, genre, price);
        bookDAO.update(book);

        return book;
    }

    public List<Book> getAllBooks(){
        return bookDAO.getAllBooks();
    }
}
