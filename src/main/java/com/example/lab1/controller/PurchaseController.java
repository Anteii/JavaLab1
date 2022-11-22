package com.example.lab1.controller;

import com.example.lab1.dao.PurchaseDAO;
import com.example.lab1.model.Purchase;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;


@Stateless
public class PurchaseController {
    @Inject
    private PurchaseDAO purchaseDAO;

    public void removeClient(Integer id){
        purchaseDAO.removeById(id);
    }

    public Purchase updatePurchase(Integer id, Integer bookId, Integer clientId, Integer amount) {
        Purchase purchase = purchaseDAO.findByID(id);
        purchase.update(bookId, clientId, amount);
        purchaseDAO.update(purchase);

        return purchase;
    }

    public Purchase createPurchase(Integer bookId, Integer clientId, Integer amount) {
        Purchase purchase = new Purchase();
        purchase.update(bookId, clientId, amount);
        purchaseDAO.create(purchase);

        return purchase;
    }

    public List<Purchase> getAllPurchases(){
        return purchaseDAO.getAllPurchases();
    }
}