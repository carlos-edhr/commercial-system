
package com.carlosehr.commercialsystem.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.carlosehr.commercialsystem.dao.CategoryJDBCDAO;
import com.carlosehr.commercialsystem.dao.ProductJDBCDAO;
import com.carlosehr.commercialsystem.models.Category;
import com.carlosehr.commercialsystem.models.Product;


@WebServlet(name = "CategoryController", urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null) {
            String action = (String) request.getParameter("action");
            switch(action){
                case "show":
                    showCategory(request, response);
                    break;
                case "new":
                    formNew(request, response);
                    break;
                case "edit":
                    formEdit(request, response);
                    break;
            }
        }
        else{
            CategoryJDBCDAO daoCategory = new CategoryJDBCDAO();
        
            List<Category> listCategory = daoCategory.listAll();
        
            request.setAttribute("category", listCategory);

            request.getRequestDispatcher("/WEB-INF/category/index.jsp").
                    forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null) {
            
            String action = (String) request.getParameter("action");
            switch(action){
                case "create":
                    insertCategory(request, response);
                    break;
                case "delete":
                    deleteCategory(request, response);
                    break;
                case "update":
                    updateCategory(request, response);
                    break;
            }
        }
        
    }
    
    private void formNew(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        request.setAttribute("typeForm", "create");
        request.getRequestDispatcher("/WEB-INF/category/form.jsp").forward(request, response);
    }

    private void insertCategory(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        long keyCat = Long.parseLong(request.getParameter("idCat"));
        String nameCate = request.getParameter("nameCat");
        
        CategoryJDBCDAO categoryDAO = new CategoryJDBCDAO();
        
        String message = categoryDAO.insert(new Category(keyCat, nameCate));
        
        request.getSession().setAttribute("operationCategory", message);
        
        response.sendRedirect("/commercial-system/category");
    }
    

    private void formEdit(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        long idCat = Long.parseLong(request.getParameter("idCat"));
        
        CategoryJDBCDAO categoryDAO = new CategoryJDBCDAO();
        
        Category cat = categoryDAO.findById(idCat);
        
        if (cat != null) {
            request.setAttribute("typeForm", "update");
            request.setAttribute("category", cat);
            request.getRequestDispatcher("/WEB-INF/category/form.jsp").forward(request, response);
        }
        
    }

    private void updateCategory(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        long keyCat = Long.parseLong(request.getParameter("idCat"));
        String nameCat = request.getParameter("nameCat");
        
        CategoryJDBCDAO categoryDAO = new CategoryJDBCDAO();
        String message = categoryDAO.update(new Category(keyCat, nameCat));
        
        request.getSession().setAttribute("operationCategory", message);
        response.sendRedirect("/commercial-system/category");   
    }

    private void deleteCategory(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        long keyCat = Long.parseLong(request.getParameter("idCat"));
        
        CategoryJDBCDAO categoryDAO = new CategoryJDBCDAO();
        String message = categoryDAO.delete(new Category(keyCat));
        
        request.getSession().setAttribute("operationCategory", message);
        response.sendRedirect("/commercial-system/category");   
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryJDBCDAO daoCat = new CategoryJDBCDAO();
        ProductJDBCDAO daoProd = new ProductJDBCDAO();
        
        long idCat = Long.parseLong(request.getParameter("idCat"));
        
        Category cat = daoCat.findById(idCat);
        List<Product> products = daoProd.getProductsByCategory(cat);
        
        cat.setProducts(products);
        
        request.setAttribute("category", cat);
        
        request.getRequestDispatcher("/WEB-INF/category/show.jsp")
                .forward(request, response);
        
    }

}
