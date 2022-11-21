package com.example.lab1.servlets;

import com.example.lab1.dao.BookDAO;
import com.example.lab1.dao.ClientDAO;
import com.example.lab1.dao.PurchaseDAO;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetTableServlet", value = "/GetTableServlet")
public class GetTableServlet extends HttpServlet {

    @Inject
    private BookDAO bookDAO;
    @Inject
    private ClientDAO clientDAO;
    @Inject
    private PurchaseDAO purchaseDAO;

    public String getJson(List<?> array){
        return new Gson().toJson(array);
    }

    private String sendBooks(){
        return getJson(bookDAO.getAllBooks());
    }

    private String sendClients(){
        return getJson(clientDAO.getAllClients());
    }

    private String sendBuyBook(){
        return getJson(purchaseDAO.getAllPurchases());
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
