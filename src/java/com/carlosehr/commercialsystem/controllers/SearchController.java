/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.CategoryJDBCDAO;
import com.carlosehr.commercialsystem.dao.ICategoryDAO;
import com.carlosehr.commercialsystem.dao.IProductDAO;
import com.carlosehr.commercialsystem.dao.ProductJDBCDAO;
import com.carlosehr.commercialsystem.models.Category;
import com.carlosehr.commercialsystem.models.Product;
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
@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("param");
        ICategoryDAO daoCategory = new CategoryJDBCDAO();
        IProductDAO daoProduct = new ProductJDBCDAO();
        
        List<Category> category = daoCategory.searchByCriteria(parameter);
        List<Product> products = daoProduct.searchByCriteria(parameter);
        
        request.setAttribute("category", category);
        request.setAttribute("products", products);
        
        request.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(request, response);
    }
}