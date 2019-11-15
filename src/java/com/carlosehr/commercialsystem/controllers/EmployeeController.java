/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.EmployeeJDBCDAO;
import com.carlosehr.commercialsystem.models.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sylvia
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/employee"})
public class EmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EmployeeJDBCDAO daoEmployee = new EmployeeJDBCDAO();
        
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
        }
        
        else{
            List<Employee> listEmployee = daoEmployee.listAll();
            request.setAttribute("employee", listEmployee);
        
            request.getRequestDispatcher("/WEB-INF/employee/index.jsp")
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
                    insertEmployee(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                case "update":
                    updateEmployee(request, response);
                   break;
            }
        }
    }

    private void formNew(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        EmployeeJDBCDAO daoEmployee = new EmployeeJDBCDAO();
        List<Employee> employee = daoEmployee.listAll();
        request.setAttribute("employees", employee);
        request.setAttribute("typeForm", "create");
        request.getRequestDispatcher("/WEB-INF/employee/form.jsp")
                .forward(request, response);
    }

    private void formEdit(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
       EmployeeJDBCDAO daoEmployee = new EmployeeJDBCDAO();
        
        long idEmp = Long.parseLong(request.getParameter("idEmp"));
        Employee employee = daoEmployee.findById(idEmp);
        
        if (employee != null) {
            List<Employee> employees = daoEmployee.listAll();
            request.setAttribute("employees", employees);
            request.setAttribute("typeForm", "update");
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/WEB-INF/employee/form.jsp")
                    .forward(request, response);
        } 
    }

    private void insertEmployee(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        try {
            long idEmp = Long.parseLong(request.getParameter("idEmp"));
            String name = request.getParameter("nameEmp");
            String lastName = request.getParameter("lastNameEmp");
            String birth_day = request.getParameter("birth_day");
            int reportsTo = Integer.parseInt(request.getParameter("reportsTo"));
            int ext = Integer.parseInt(request.getParameter("ext"));
            
            java.util.Date dateBirth = new SimpleDateFormat("yyyy-MM-dd").parse(birth_day);
            java.sql.Date dateSQL = new java.sql.Date(dateBirth.getTime());
            
            
            EmployeeJDBCDAO empleadoDAO = new EmployeeJDBCDAO();
            String message = empleadoDAO
                    .insert(new Employee(idEmp, name, lastName, dateSQL, reportsTo, ext));
            
            request.getSession().setAttribute("opEmp", message);

            response.sendRedirect("/commercial-system/employee");
            
        } catch(ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteEmployee(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        
        long claveEmp = Long.parseLong(request.getParameter("idEmp"));
        
        EmployeeJDBCDAO employeeDAO = new EmployeeJDBCDAO();
        String message = employeeDAO
                         .delete(new Employee(claveEmp));

        request.getSession().setAttribute("opEmp", message);
        
        response.sendRedirect("/commercial-system/employee");  
    }

    private void updateEmployee(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        try {
             long idEmp = Long.parseLong(request.getParameter("idEmp"));
            String name = request.getParameter("nameEmp");
            String lastName = request.getParameter("lastNameEmp");
            String birth_day = request.getParameter("birth_day");
            int reportsTo = Integer.parseInt(request.getParameter("reportsTo"));
            int ext = Integer.parseInt(request.getParameter("ext"));
            
            java.util.Date dateBirth = new SimpleDateFormat("yyyy-MM-dd").parse(birth_day);
            java.sql.Date dateSQL = new java.sql.Date(dateBirth.getTime());

            EmployeeJDBCDAO employeeDAO = new EmployeeJDBCDAO();
            String message = employeeDAO
                    .update(new Employee(idEmp, name, lastName, dateSQL, reportsTo, ext));
            
            request.getSession().setAttribute("opEmp", message);

            response.sendRedirect("/commercial-system/employee");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
