package com.example.lab1.servlets;

import com.example.lab1.dao.ClientDAO;
import com.example.lab1.model.Client;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "ClientsServlet", value = "/clients")
public class ClientsServlet extends HttpServlet {

    @Inject
    private ClientDAO clientDAO;

    private void send(int status, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"success\": \"" + status + "\"}");
        out.flush();
    }

    private void deleteRow(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {

        int result = clientDAO.removeById(id);
        send(result, response);
    }

    private void updateRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        Client client = clientDAO.findByID(obj.getInt("id"));
        client.update(obj.getString("clientName"), obj.getString("cityName"),
                obj.getString("clientEmail"));

        int result = clientDAO.update(client);

        send(result, response);
    }

    private void insertRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONObject obj = new JSONObject(requestData);

        Client client = new Client();
        client.update(obj.getString("clientName"), obj.getString("cityName"),
                obj.getString("clientEmail"));

        int result = clientDAO.create(client);

        send(result, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("clients.html").forward(request,response);
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
