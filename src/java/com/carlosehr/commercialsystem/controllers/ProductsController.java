/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.CategoryJDBCDAO;
import com.carlosehr.commercialsystem.dao.ProductJDBCDAO;
import com.carlosehr.commercialsystem.dao.ProviderJDBCDAO;
import com.carlosehr.commercialsystem.models.Category;
import com.carlosehr.commercialsystem.models.Product;
import com.carlosehr.commercialsystem.models.Provider;
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
@WebServlet(name = "ProductsController", urlPatterns = {"/products"})
public class ProductsController extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductJDBCDAO daoProduct = new ProductJDBCDAO();
        
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
            List<Product> listProducts = daoProduct.listAll();
            request.setAttribute("products", listProducts);
        
            request.getRequestDispatcher("/WEB-INF/products/index.jsp")
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
                    insertProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                   break;
            }
        }
    }

    private void newForm(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        List<Provider> providers = new ProviderJDBCDAO().listAll();
        List<Category> categories = new CategoryJDBCDAO().listAll();
        
        request.setAttribute("categories", categories);
        request.setAttribute("providers", providers);
        request.setAttribute("typeForm", "create");

        request.getRequestDispatcher("/WEB-INF/products/form.jsp")
                .forward(request, response);
    }

    private void editForm(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        ProductJDBCDAO daoProduct = new ProductJDBCDAO();
        
        long idProd = Long.parseLong(request.getParameter("idProd"));
        Product product = daoProduct.findById(idProd);
        
        if (product != null) {
            List<Provider> providers = new ProviderJDBCDAO().listAll();
            List<Category> categories = new CategoryJDBCDAO().listAll();

            request.setAttribute("categories", categories);
        request.setAttribute("providers", providers);            
            request.setAttribute("typeForm", "update");
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/products/form.jsp")
                    .forward(request, response);
        }
    }

    private void insertProduct(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        long idProduct = Long.parseLong(request.getParameter("idProd"));
        long idProvider = Long.parseLong(request.getParameter("idProv"));
        long idCat = Long.parseLong(request.getParameter("idCat"));
        String description = request.getParameter("description");
        double priceU = Double.parseDouble(request.getParameter("priceU"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        ProductJDBCDAO productDAO = new ProductJDBCDAO();
        Provider prov = new ProviderJDBCDAO().findById(idProvider);
        Category cat = new CategoryJDBCDAO().findById(idCat);
        String message = productDAO
                .insert(new Product(idProduct, prov, cat, description, priceU, stock));

        request.getSession().setAttribute("opProd", message);

        response.sendRedirect("/commercial-system/products");
    }

    private void deleteProduct(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        long idProd = Long.parseLong(request.getParameter("idProd"));
        
        ProductJDBCDAO productDAO = new ProductJDBCDAO();
        String message = productDAO
                         .delete(new Product(idProd));

        request.getSession().setAttribute("opProducto", message);
        
        response.sendRedirect("/commercial-system/products");
    }

    private void updateProduct(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        long idProduct = Long.parseLong(request.getParameter("idProd"));
        long idProvider = Long.parseLong(request.getParameter("idProv"));
        long idCat = Long.parseLong(request.getParameter("idCat"));
        String description = request.getParameter("description");
        double priceU = Double.parseDouble(request.getParameter("priceU"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        ProductJDBCDAO productDAO = new ProductJDBCDAO();
        Provider prov = new ProviderJDBCDAO().findById(idProvider);
        Category cat = new CategoryJDBCDAO().findById(idCat);
        
        String message = productDAO
                .update(new Product(idProduct, prov, cat, description, priceU, stock));

        request.getSession().setAttribute("opProd", message);

        response.sendRedirect("/commercial-system/products");
    }
}

