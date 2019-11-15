package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.ClientJDBCDAO;
import com.carlosehr.commercialsystem.dao.EmployeeJDBCDAO;
import com.carlosehr.commercialsystem.dao.IProductDAO;
import com.carlosehr.commercialsystem.dao.OrderDetailJDBCDAO;
import com.carlosehr.commercialsystem.dao.OrderJDBCDAO;
import com.carlosehr.commercialsystem.dao.ProductJDBCDAO;
import com.carlosehr.commercialsystem.models.Client;
import com.carlosehr.commercialsystem.models.Employee;
import com.carlosehr.commercialsystem.models.Order;
import com.carlosehr.commercialsystem.models.OrderDetail;
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
@WebServlet(name = "OrderController", urlPatterns = {"/orders"})
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action")!= null) {
            String action = request.getParameter("action");
            switch(action){
                case "showOrders":
                    showOrders(request, response);
                    break;
                case "doOrder":
                    doOrder(request, response);
                    break;
                case "showOrder":
                    showOrder(request, response);
                    break;
              }
        }else{
           request.getRequestDispatcher("/WEB-INF/purchase-order/dashboard.jsp")
                .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action")!= null) {
            String accion = request.getParameter("action");
            switch(accion){
                case "addProduct":
                    addProduct(request, response);
                    break;
                case "endOrder":
                    endOrder(request, response);
                    break;
            }
        
        }

    }

    private void showOrders(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        OrderJDBCDAO daoOrder = new OrderJDBCDAO();
        List<Order> orders = daoOrder.listAll();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/purchase-order/index.jsp")
                    .forward(request, response);
        
    }

    private void doOrder(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        
        Order order = (Order)request.getSession().getAttribute("order");
        
        ClientJDBCDAO daoClie = new ClientJDBCDAO();
        EmployeeJDBCDAO daoEmp = new EmployeeJDBCDAO();
        ProductJDBCDAO daoProd = new ProductJDBCDAO();
        
        List<Employee> employees = daoEmp.listAll();
        List<Client> clients = daoClie.listAll();
        List<Product> products = daoProd.listAll();
        
        request.setAttribute("employees", employees);
        request.setAttribute("clients", clients);
        request.setAttribute("products", products);
        
        if (order == null) {
            order = new Order();
            order.setTotal(0.0);
            order.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));
        }
        else{
            double totalOrder = 0.0;
            for (OrderDetail detail : order.getDetails()) {
                totalOrder += detail.getTotal();
            }
            order.setTotal(totalOrder);
        }
        
        request.getSession().setAttribute("order", order);
        
        request.getRequestDispatcher("/WEB-INF/purchase-order/new.jsp")
                    .forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        
        long idProduct = Long.parseLong(request.getParameter("idProd"));
        double amount = Double.parseDouble(request.getParameter("amountProd"));
        
        IProductDAO productDao = new ProductJDBCDAO();
        Product product = productDao.findById(idProduct);
        double total = product.getUnitPrice()* amount;
        
        Order order = (Order)request.getSession().getAttribute("order");
        
        OrderDetail detail = new OrderDetail();
        detail.setAmount(amount);
        detail.setProduct(product);
        detail.setOrder(order);
        detail.setTotal(total);
        
        if (order == null) {
            order = new Order();
            request.getSession().setAttribute("order", order);
        }
        
        order.getDetails().add(detail);
        response.sendRedirect(request.getContextPath()+"/orders?action=doOrder");
        
    }

    private void endOrder(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        long idClient = Long.parseLong(request.getParameter("idClient"));
        long idEmployee = Long.parseLong(request.getParameter("idEmployee"));
        
        ClientJDBCDAO daoClie = new ClientJDBCDAO();
        EmployeeJDBCDAO daoEmp = new EmployeeJDBCDAO();
        
        Client client = daoClie.findById(idClient);
        Employee employee = daoEmp.findById(idEmployee);
        
        Order order = (Order)request.getSession().getAttribute("order");
        
        order.setClient(client);
        order.setEmployee(employee);
        
        OrderJDBCDAO daoOrder = new OrderJDBCDAO();
        
        Order orderCreated = daoOrder.insert(order);
        
        if (orderCreated != null) {
            request.getSession().setAttribute("orderCreated", orderCreated);
            response.sendRedirect("/commercial-system/orders?action=showOrders");
        }
    }

    private void showOrder(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        
         long id = Long.parseLong(request.getParameter("idOrder"));
         OrderJDBCDAO daoOrder =  new OrderJDBCDAO();
         OrderDetailJDBCDAO daoDetails = new OrderDetailJDBCDAO();
         
        Order order = daoOrder.findById(id);
        List<OrderDetail> details = daoDetails.getDetails(order);
        order.setDetails(details);
        
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/purchase-order/show.jsp").forward(request, response);
    }
}
