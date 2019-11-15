
package com.carlosehr.commercialsystem.controllers;

import com.carlosehr.commercialsystem.dao.IUserDAO;
import com.carlosehr.commercialsystem.dao.UserJDBCDAO;
import com.carlosehr.commercialsystem.exception.ErrorLoginException;
import com.carlosehr.commercialsystem.exception.ErrorRegisterException;
import com.carlosehr.commercialsystem.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Carlos Hoyos Rojas
 */
@WebServlet(name = "UsersController", urlPatterns = {"/users"})
public class UsersController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action")!=null) {
            String action = request.getParameter("action");
            switch(action){
                case "closeSession":
                    closeSession(request, response);
                    break;
            }
        }
    }


    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action")!=null) {
            String action = request.getParameter("action");
            switch(action){
                case "createUser":
                    createUser(request, response);
                    break;
                case "validateUser":
                    validateUser(request, response);
                    break;
            }
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password2");
        
        if (pass.equals(pass2)) {
            String encryptedPassword = encryptPassword(pass);
            IUserDAO userDao = new UserJDBCDAO();
            User userObj = new User();
            userObj.setUser(user);
            userObj.setPassword(encryptedPassword);
            
            boolean validateEmail = userObj.validateEmail();
            
            if (!validateEmail) {
                throw new ServletException(new ErrorRegisterException("Please provider valid email address."));
            }
            
            User userSession;
            try {
                userSession = userDao.createUser(userObj);
                request.getSession().setAttribute("user", userSession);
                response.sendRedirect(request.getContextPath()+"/products");
            } catch (ErrorRegisterException ex) {
                throw new ServletException(ex);
            }
            
        }else{
            throw new ServletException(
                    new ErrorRegisterException("Password provided does not match."));
        }
        
    }

    private void validateUser(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String encryptedPassword = encryptPassword(pass);
        
        User userObj = new User();
        userObj.setUser(user);
        userObj.setPassword(encryptedPassword);
        boolean validateEmailAddress = userObj.validateEmail();
        
        if (!validateEmailAddress) {
            throw new ServletException(
                    new ErrorLoginException("Please provide valid email address."));
        }
        
        IUserDAO userDao = new UserJDBCDAO();
        User userSession = userDao.validateUser(user, encryptedPassword);
        if (userSession!= null) {
            request.getSession().setAttribute("user", userSession);
            response.sendRedirect(request.getContextPath()+"/products");
        }else{
            throw new ServletException(
                    new ErrorLoginException("User credentials not found."));
        }
    }    

    private void closeSession(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
    
    private String encryptPassword(String pass){
         return DigestUtils.md5Hex(pass);
    }
}
