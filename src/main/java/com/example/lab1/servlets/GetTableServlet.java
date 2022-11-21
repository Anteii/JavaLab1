package com.example.lab1.servlets;

import com.example.lab1.dao.BookDao;
import com.example.lab1.dao.BuyBookDao;
import com.example.lab1.dao.ClientDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetTableServlet", value = "/GetTableServlet")
public class GetTableServlet extends HttpServlet {

    private BookDao bookDao;
    private ClientDao clientDao;
    private BuyBookDao buyBookDao;

    public void init(){
        try {
            bookDao = new BookDao();
            clientDao = new ClientDao();
            buyBookDao = new BuyBookDao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJson(List<?> array){
        StringBuilder str = new StringBuilder("[");
        String prefix = "";
        for (Object obj: array){
            str.append(prefix);
            str.append(obj.toString());
            prefix = ",";
        }
        str.append("]");
        return str.toString();
    }

    private String sendBooks(){
        try {
            return getJson(bookDao.getAllBooks());
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    private String sendClients(){
        try {
            return getJson(clientDao.getAllClients());
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    private String sendBuyBook(){
        try {
            return getJson(buyBookDao.getAllBuyBooks());
        } catch (Exception e){
            throw new RuntimeException();
        }
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
