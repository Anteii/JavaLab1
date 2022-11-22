package com.example.lab1.servlets;

import com.example.lab1.controller.BookController;
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

@WebServlet(name = "BooksServlet", value = "/books")
public class BooksServlet extends HttpServlet {

    @Inject
    private BookController bookController;

    private void send(int status, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"success\": \"" + status + "\"}");
        out.flush();
    }

    private void deleteRow(int id) {
        bookController.removeBook(id);
    }

    private void updateRow(HttpServletRequest request) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);
        bookController.updateBook(obj.getInt("id"), obj.getString("title"),
                obj.getString("authorName"), obj.getString("genre"), obj.getDouble("price"));
    }

    private void insertRow(HttpServletRequest request) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        bookController.createBook(obj.getString("title"), obj.getString("authorName"),
                obj.getString("genre"), obj.getDouble("price"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("books.html").forward(request,response);
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
