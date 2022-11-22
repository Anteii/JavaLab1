package com.example.lab1.controller;

import com.example.lab1.dao.ClientDAO;
import com.example.lab1.model.Client;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ClientController {
    @Inject
    private ClientDAO clientDAO;

    public void removeClient(Integer id){
        clientDAO.removeById(id);
    }
    public Client createClient(String name, String city, String email){
        Client client = new Client();
        client.update(name, city, email);
        clientDAO.create(client);

        return client;
    }
    public Client updateClient(Integer id, String name, String city, String email){
        Client client = clientDAO.findByID(id);
        client.update(name, city, email);
        clientDAO.update(client);

        return client;
    }

    public List<Client> getAllClients(){
        return clientDAO.getAllClients();
    }
}