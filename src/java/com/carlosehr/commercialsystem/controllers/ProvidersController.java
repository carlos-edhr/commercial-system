
package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.ProviderJDBCDAO;
import com.carlosehr.commercialsystem.models.Provider;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProvidersController", urlPatterns = {"/providers"})
public class ProvidersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProviderJDBCDAO daoProvider = new ProviderJDBCDAO();
        
        if (request.getParameter("action") != null) {
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                case "new":
                    newForm(request, response);
                    break;
                case "edit":
                    editForm(request, response);
                    break;
            }
        }
        
        else{
            List<Provider> listProviders = daoProvider.listAll();
            request.setAttribute("providers", listProviders);
        
            request.getRequestDispatcher("/WEB-INF/providers/index.jsp")
                    .forward(request, response);
        }
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action")!=null) {
            String action = (String)request.getParameter("action");
            
            switch(action){
                case "create":
                    insertProvider(request, response);
                    break;
                case "delete":
                    deleteProvider(request, response);
                    break;
                case "update":
                    updateProvider(request, response);
                   break;
            }
        }
        
    }

    private void newForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("typeForm", "create");
        request.getRequestDispatcher("/WEB-INF/providers/form.jsp")
                .forward(request, response);
    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProviderJDBCDAO daoProvider = new ProviderJDBCDAO();
        
        long idProv = Long.parseLong(request.getParameter("idProv"));
        Provider prov = daoProvider.findById(idProv);
        
        if (prov != null) {
            request.setAttribute("typeForm", "update");
            request.setAttribute("provider", prov);
            request.getRequestDispatcher("/WEB-INF/providers/form.jsp")
                    .forward(request, response);
        }
    }

    private void insertProvider(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        long idProv = Long.parseLong(request.getParameter("idProv"));
        String nameProv = request.getParameter("nameProv");
        String contactProv = request.getParameter("contactProv");
        String cellphoneProv = request.getParameter("cellphoneProv");
        String phoneProv = request.getParameter("phoneProv");
        
                
        ProviderJDBCDAO providerDAO = new ProviderJDBCDAO();
        String message = providerDAO
                .insert(new Provider(idProv, nameProv, contactProv, cellphoneProv, phoneProv));

        request.getSession().setAttribute("opProv", message);
        
        response.sendRedirect("/commercial-system/providers"); 
    }

    private void deleteProvider(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long idProv = Long.parseLong(request.getParameter("idProv"));
        
        ProviderJDBCDAO providerDAO = new ProviderJDBCDAO();
        String message = providerDAO
                         .delete(new Provider(idProv));

        request.getSession().setAttribute("opProv", message);
        
        response.sendRedirect("/commercial-system/providers"); 
    }

    private void updateProvider(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        long idProv = Long.parseLong(request.getParameter("idProv"));
        String nameProv = request.getParameter("nameProv");
        String contactProv = request.getParameter("contactProv");
        String cellphoneProv = request.getParameter("cellphoneProv");
        String phoneProv = request.getParameter("phoneProv");
        
        ProviderJDBCDAO providerDAO = new ProviderJDBCDAO();
        String message = providerDAO
                .update(new Provider(idProv, nameProv, contactProv, cellphoneProv, phoneProv));

        request.getSession().setAttribute("opProv", message);
        
        response.sendRedirect("/commercial-system/providers");  
        
    }
}