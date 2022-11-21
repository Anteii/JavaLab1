package com.example.lab1.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MainPageServlet", value = "/main-page")
public class MainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.setAttribute("books", books);
//        request.setAttribute("clients", clients);
//        request.setAttribute("orders", orders);

        request.getRequestDispatcher("main_page.html").forward(request,response);

    }

}
