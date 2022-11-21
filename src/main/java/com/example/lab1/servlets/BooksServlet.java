package com.example.lab1.servlets;

import com.example.lab1.dao.BookDao;
import com.example.lab1.model.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

@WebServlet(name = "BooksServlet", value = "/books")
public class BooksServlet extends HttpServlet {

    private BookDao bookDao;

    public void init(){
        try {
            bookDao = new BookDao();
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
            result = bookDao.deleteBook(id);
        } catch (Exception ignored) {

        }
            send(result, response);
    }

    private void updateRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);
        Book book = new Book(obj.getInt("id"), obj.getString("title"),
                             obj.getString("authorName"), obj.getString("genre"), obj.getDouble("price"));
        int result = 0;
        try {
            result = bookDao.updateBook(book);
        } catch (Exception ignored) {
        }

        send(result, response);
    }

    private void insertRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);
        Book book = new Book(obj.getString("title"), obj.getString("authorName"),
                             obj.getString("genre"), obj.getDouble("price"));
        int result = 0;
        try {
            result = bookDao.insertBook(book);
        } catch (Exception ignored) {

        }
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
