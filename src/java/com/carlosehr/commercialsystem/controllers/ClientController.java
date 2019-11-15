/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.ClientJDBCDAO;
import com.carlosehr.commercialsystem.models.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos Hoyos Rojas
 */
@WebServlet(name = "ClientController", urlPatterns = {"/clients"})
public class ClientController extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClientJDBCDAO daoClient = new ClientJDBCDAO();

        if (request.getParameter("action") != null) {

            String action = (String) request.getParameter("action");

            switch (action) {
                case "new":
                    formNew(request, response);
                    break;
                case "edit":
                    formEdit(request, response);
                    break;
            }
        } else {
            List<Client> listClients = daoClient.listAll();
            request.setAttribute("clients", listClients);

            request.getRequestDispatcher("/WEB-INF/clients/index.jsp")
                    .forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action") != null) {
            String action = (String) request.getParameter("action");
            switch (action) {
                case "create":
                    insertClient(request, response);
                    break;
                case "delete":
                    deleteClient(request, response);
                    break;
                case "update":
                    updateClient(request, response);
                    break;
            }
        }
    }

    private void formNew(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("typeForm", "create");
        request.getRequestDispatcher("/WEB-INF/clients/form.jsp")
                .forward(request, response);

    }

    private void formEdit(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        ClientJDBCDAO daoCliente = new ClientJDBCDAO();

        long idClient = Long.parseLong(request.getParameter("idClie"));
        Client client = daoCliente.findById(idClient);

        if (client != null) {
            request.setAttribute("typeForm", "update");
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/clients/form.jsp")
                    .forward(request, response);
        }
    }

    private void insertClient(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        long idClient = Long.parseLong(request.getParameter("idClie"));
        String idCard = request.getParameter("idCard");
        String companyName = request.getParameter("companyName");
        String contactName = request.getParameter("contactName");
        String direction = request.getParameter("address");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String cellphone = request.getParameter("cellphone");
        String phone = request.getParameter("phone");

        ClientJDBCDAO clientDAO = new ClientJDBCDAO();
        String message = clientDAO
                .insert(new Client(idClient, idCard, companyName, contactName, direction, fax, email, cellphone, phone));

        request.getSession().setAttribute("opClie", message);

        response.sendRedirect("/commercial-system/clients");

    }

    private void deleteClient(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        long idClient = Long.parseLong(request.getParameter("idClie"));

        ClientJDBCDAO clientDAO = new ClientJDBCDAO();
        String message = clientDAO
                .delete(new Client(idClient));

        request.getSession().setAttribute("opClie", message);

        response.sendRedirect("/commercial-system/clients");
    }

    private void updateClient(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        long idClient = Long.parseLong(request.getParameter("idClie"));
        String idCard = request.getParameter("idCard");
        String companyName = request.getParameter("companyName");
        String contactName = request.getParameter("contactName");
        String direction = request.getParameter("address");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String cellphone = request.getParameter("cellphone");
        String phone = request.getParameter("phone");

        ClientJDBCDAO clientDAO = new ClientJDBCDAO();
        String message = clientDAO
                .update(new Client(idClient, idCard, companyName, contactName, direction, fax, email, cellphone, phone));
        
        request.getSession().setAttribute("opClie", message);

        response.sendRedirect("/commercial-system/clients");
    }
}
