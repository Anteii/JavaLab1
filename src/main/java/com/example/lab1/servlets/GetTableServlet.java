package com.example.lab1.servlets;

import com.example.lab1.controller.BookController;
import com.example.lab1.controller.ClientController;
import com.example.lab1.controller.PurchaseController;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetTableServlet", value = "/GetTableServlet")
public class GetTableServlet extends HttpServlet {

    @Inject
    private BookController bookController;
    @Inject
    private ClientController clientController;
    @Inject
    private PurchaseController purchaseController;

    public String getJson(List<?> array){
        return new Gson().toJson(array);
    }

    private String sendBooks(){
        return getJson(bookController.getAllBooks());
    }

    private String sendClients(){
        return getJson(clientController.getAllClients());
    }

    private String sendBuyBook(){
        return getJson(purchaseController.getAllPurchases());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String flag = request.getParameter("table");
        String send = "[]";
        switch (flag){
            case "book":
                send = sendBooks();
                break;
            case "client":
                send = sendClients();
                break;
            case "order":
                send = sendBuyBook();
                break;
        }
        response.getWriter().print(send);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().flush();
    }
}
