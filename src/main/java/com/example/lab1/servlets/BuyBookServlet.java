package com.example.lab1.servlets;

import com.example.lab1.dao.BuyBookDao;
import com.example.lab1.model.BuyBook;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

@WebServlet(name = "BuyBookServlet", value = "/buy-book")
public class BuyBookServlet extends HttpServlet {
    private BuyBookDao buyBookDao;

    public void init(){
        try {
            buyBookDao = new BuyBookDao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void send(int status, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"success\": \"" + status + "\"}");
        out.flush();
    }

    private void deleteRow(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        int result = 0;
        try {
            result = buyBookDao.deleteBuyBook(id);
        } catch (Exception ignored) {

        }
        send(result, response);
    }

    private void updateRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);
        BuyBook buyBook = new BuyBook(obj.getInt("id"), obj.getInt("bookId"), obj.getInt("clientId"),
                obj.getInt("amount"));
        int result = 0;
        try {
            result = buyBookDao.updateBuyBook(buyBook);
        } catch (Exception ignored) {
        }

        send(result, response);
    }

    private void insertRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);
        BuyBook buyBook = new BuyBook(obj.getInt("bookId"), obj.getInt("clientId"),
                obj.getInt("amount"));
        int result = 0;
        try {
            result = buyBookDao.insertBuyBook(buyBook);
        } catch (Exception ignored) {

        }
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
