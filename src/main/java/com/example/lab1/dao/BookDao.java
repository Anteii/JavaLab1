package com.example.lab1.dao;

import com.example.lab1.model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDao {
    private final MyJDBCConnection connection;

    public BookDao() throws Exception {
        connection = new MyJDBCConnection();
    }

    public ArrayList<Book> getAllBooks() throws Exception {
        ArrayList<Book> resultArray = new ArrayList<>();
        String selectAllBooks = "select * from book";

        connection.executeQuery(selectAllBooks, result -> {
            while (result.next()){
                resultArray.add(new Book(result.getInt("book_id"), result.getString("title"),
                        result.getString("author_name"), result.getString("genre"), result.getDouble("price")));
            }
        });

        return resultArray;
    }

    public Book getBookById(int id) throws Exception {
        String select = "select * from book where book_id = " + id + ";";
        final Book[] result = new Book[1];

        connection.executeQuery(select, resultSet -> result[0] = new Book(resultSet.getInt("book_id"), resultSet.getString("title"),
                resultSet.getString("author_name"), resultSet.getString("genre"), resultSet.getDouble("price")));

        return result[0];
    }

    public int insertBook(Book book) throws Exception {
        String addBook = "insert into book(title, author_name, genre, price) values( '" + book.getTitle() + "','" +
                book.getAuthorName() + "', '" + book.getGenre() + "', " + book.getPrice() + ");";
        return connection.executeUpdate(addBook);
    }

    public int deleteBook(int id) throws Exception {
        String removeBook = "delete from book where book_id = " + id + ";";
        return connection.executeUpdate(removeBook);
    }

    public int updateBook(Book book) throws Exception {
        String update = "update book set title = " + "'" + book.getTitle() + "', author_name = '" + book.getAuthorName() + "', "
                + "genre = '" + book.getGenre() + "', price = " + book.getPrice() + " where book_id = " + book.getBookId() + ";";
        return connection.executeUpdate(update);
    }
}
