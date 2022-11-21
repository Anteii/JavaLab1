package com.example.lab1.servlets;

import com.example.lab1.dao.PurchaseDAO;
import com.example.lab1.model.Purchase;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "BuyBookServlet", value = "/buy-book")
public class PurchaseServlet extends HttpServlet {

    @Inject
    private PurchaseDAO purchaseDAO;


    private void send(int status, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"success\": \"" + status + "\"}");
        out.flush();
    }

    private void deleteRow(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        int result = purchaseDAO.removeById(id);
        send(result, response);
    }

    private void updateRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        Purchase purchase = purchaseDAO.findByID(obj.getInt("id"));
        purchase.update(obj.getInt("bookId"), obj.getInt("clientId"),
                obj.getInt("amount"));

        int result = purchaseDAO.update(purchase);

        send(result, response);
    }

    private void insertRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        Purchase purchase = new Purchase();
        purchase.update(obj.getInt("bookId"), obj.getInt("clientId"),
                obj.getInt("amount"));

        int result = purchaseDAO.create(purchase);

        send(result, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("buy_book.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action){
            case "delete":
                deleteRow(request, response, Integer.parseInt(request.getParameter("id")));
                break;
            case "update":
                updateRow(request, response);
                break;
            case "insert":
                insertRow(request, response);
                break;
        }

    }
}
