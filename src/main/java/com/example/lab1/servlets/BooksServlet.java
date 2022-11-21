package com.example.lab1.servlets;

import com.example.lab1.dao.BookDAO;
import com.example.lab1.model.Book;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "BooksServlet", value = "/books")
public class BooksServlet extends HttpServlet {

    @Inject
    private BookDAO bookDAO;

    private void send(int status, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"success\": \"" + status + "\"}");
        out.flush();
    }

    private void deleteRow(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        // delete bookById
        int result = 0;
        result = bookDAO.removeById(id);
        send(result, response);
    }

    private void updateRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        Book book = bookDAO.findByID(obj.getInt("id"));
        book.update(obj.getString("title"),
                obj.getString("authorName"), obj.getString("genre"), obj.getDouble("price"));

        int result = bookDAO.update(book);

        send(result, response);
    }

    private void insertRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        Book book = new Book();
        book.update(obj.getString("title"), obj.getString("authorName"),
                obj.getString("genre"), obj.getDouble("price"));

        int result = bookDAO.create(book);

        send(result, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("books.html").forward(request,response);
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
