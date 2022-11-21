package com.example.lab1.dao;

import com.example.lab1.model.BuyBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BuyBookDao {

    private final MyJDBCConnection connection;

    public BuyBookDao() throws Exception {
        connection = new MyJDBCConnection();
    }

    public ArrayList<BuyBook> getAllBuyBooks() throws Exception {
        ArrayList<BuyBook> resultList = new ArrayList<>();
        String selectAllBuyBooks = "select * from buy_book";

        connection.executeQuery(selectAllBuyBooks, resultSet -> {
            while (resultSet.next()){
                resultList.add(new BuyBook(resultSet.getInt("buy_book_id"), resultSet.getInt("book_id"),
                        resultSet.getInt("client_id"), resultSet.getInt("amount")));
            }
        });

        return resultList;
    }

    public int updateBuyBook(BuyBook buyBook) throws Exception {
        String updateString = "update buy_book set book_id = " + buyBook.getBookId()
                + ", client_id = " + buyBook.getClientId() + ", amount = " + buyBook.getAmount()
                + " where buy_book_id = " + buyBook.getBuyBookId() + ";";
        return connection.executeUpdate(updateString);
    }

    public int deleteBuyBook(int id) throws Exception {
        String removeBuyBook = "delete from buy_book where buy_book_id = " + id + ";";
        return connection.executeUpdate(removeBuyBook);
    }

    public int insertBuyBook(BuyBook buyBook) throws Exception {
        String addBuyBook = "insert into buy_book(book_id, client_id, amount) values( " + buyBook.getBookId() + ", "
                + buyBook.getClientId() + ", " + buyBook.getAmount() + ");";
        return connection.executeUpdate(addBuyBook);
    }
}
