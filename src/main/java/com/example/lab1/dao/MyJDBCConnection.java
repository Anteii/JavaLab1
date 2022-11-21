package com.example.lab1.dao;

import java.sql.*;

public class MyJDBCConnection {

    @FunctionalInterface
    public interface QueryCallback {
        void accept(ResultSet resultSet) throws Exception;
    }

    private static final String DB_URL = "jdbc:postgresql://ella.db.elephantsql.com:5432/lszfngcv";
    private static final String USER = "lszfngcv";
    private static final String PASS = "6VybNmhMnVB5SSCV7XxSyDOt5O2MaKwh";

    private final Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public MyJDBCConnection() throws Exception {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public int executeUpdate(String query) throws Exception {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException ex){
            throw new Exception(ex);
        }
    }

    public void executeQuery(String query, QueryCallback callback) throws Exception {
        try (Statement statement = connection.createStatement()) {
            callback.accept(statement.executeQuery(query));
        } catch (SQLException ex){
            throw new Exception(ex);
        }
    }
}
