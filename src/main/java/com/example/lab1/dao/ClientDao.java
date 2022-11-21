package com.example.lab1.dao;

import com.example.lab1.model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ClientDao {

    private final MyJDBCConnection connection;

    public ClientDao() throws Exception {
        connection = new MyJDBCConnection();
    }

    public ArrayList<Client> getAllClients() throws Exception {
        ArrayList<Client> resultArray = new ArrayList<>();
        String selectAllClients = "select * from client";

        connection.executeQuery(selectAllClients, result -> {
            while (result.next()){
                resultArray.add(new Client(result.getInt("client_id"), result.getString("client_name"),
                        result.getString("city_name"), result.getString("client_email")));
            }
        });
        return resultArray;
    }

    public int insertClient(Client client) throws Exception {
        String addClient = "insert into client(client_name, city_name, client_email) values("
            + "'" + client.getClientName() + "', '" + client.getClientName() + "', '" + client.getClientEmail() + "');";
        return connection.executeUpdate(addClient);
    }

    public int deleteClient(int id) throws Exception {

        String removeClient = "delete from client where client_id = " + id + ";";
        return connection.executeUpdate(removeClient);
    }

    public int updateClient(Client client) throws Exception {
        String updateString = "update client set "
                + "client_name = '" + client.getClientName() + "', city_name = '" + client.getCityName() + "', "
                + "client_email = '" + client.getClientEmail() + "' where client_id = " + client.getClientId() + ";";
        return connection.executeUpdate(updateString);
    }
}
