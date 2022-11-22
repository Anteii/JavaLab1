package com.example.lab1.servlets;

import com.example.lab1.controller.PurchaseController;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "BuyBookServlet", value = "/buy-book")
public class PurchaseServlet extends HttpServlet {

    @Inject
    private PurchaseController purchaseController;

    private void send(int status, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"success\": \"" + status + "\"}");
        out.flush();
    }

    private void deleteRow(int id) {
        purchaseController.removeClient(id);
    }

    private void updateRow(HttpServletRequest request) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        purchaseController.updatePurchase(obj.getInt("id"), obj.getInt("bookId"), obj.getInt("clientId"),
                obj.getInt("amount"));
    }

    private void insertRow(HttpServletRequest request) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        purchaseController.createPurchase(obj.getInt("bookId"), obj.getInt("clientId"),
                obj.getInt("amount"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("buy_book.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        switch (action){
            case "delete":
                deleteRow(Integer.parseInt(request.getParameter("id")));
                break;
            case "update":
                updateRow(request);
                break;
            case "insert":
                insertRow(request);
                break;
        }
    }
}
