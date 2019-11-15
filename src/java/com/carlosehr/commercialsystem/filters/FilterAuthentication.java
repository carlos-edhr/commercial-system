package com.carlosehr.commercialsystem.filters;

import com.carlosehr.commercialsystem.exception.NotLoggedException;
import com.carlosehr.commercialsystem.models.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.rmi.server.LogStream.log;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "FilterAuthentication", urlPatterns = {"/*"})
public class FilterAuthentication implements Filter {
    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    
    public FilterAuthentication() {
    }    
    
    private boolean authorizeURL(String url){
        System.out.println("requesting url: " + url);
        return url.contains("index.jsp") || 
               url.contains("register.jsp") || 
               url.equals("/commercial-system/") ||
               url.contains("/bootstrap/") || 
               url.contains("/css/") ||
               url.contains("/js/")||
               url.startsWith("users", "/commercial-system/".length());
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterAuthentication:DoBeforeProcessing");
        }
        HttpServletRequest request1 = (HttpServletRequest)request;
        HttpServletResponse response1 = (HttpServletResponse)response;
        
        boolean validURL = authorizeURL(request1.getRequestURI());
        
        User user = (User)request1.getSession().getAttribute("user");
        
        if (user == null) {
            if (!validURL) {
                throw new ServletException(new NotLoggedException("Log in required before system access."));
            }
        }
    }    
      
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterAuthentication:DoAfterProcessing");
        }

	
    }

 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("FilterAuthentication:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
	   
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

	
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
          
        }
    }


    @Override
    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FilterAuthentication:Initializing filter");
            }
        }
    }

    
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterAuthentication()");
        }
        StringBuffer sb = new StringBuffer("FilterAuthentication(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}



/*


public class AuthenticationFilter implements Filter {
    
     private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthenticationFilter() {
    }

    private boolean authorizeURL(String url){
        System.out.println("url : " + url);
        return url.contains("index.jsp") || 
               url.contains("register.jsp") || 
               url.equals("/commercial-system/") ||
               url.contains("/bootstrap/") || 
               url.contains("/css/") ||
               url.contains("/js/")||
               url.startsWith("users", "/commercial-system/".length());
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoBeforeProcessing");
        }
        
        HttpServletRequest request1 = (HttpServletRequest)request;
        HttpServletResponse response1 = (HttpServletResponse)response;
        
        boolean validURL = authorizeURL(request1.getRequestURI());
        
        User usuario = (User)request1.getSession().getAttribute("user");
        
        if (usuario == null) {
            if (!validURL) {
                throw new ServletException(new NotLoggedException("Log in required before system access."));
            }
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoAfterProcessing");
        }

        
    }

    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("AuthenticationFilter:doFilter()");
        }
        
       doBeforeProcessing(request, response);
       
       Throwable problem = null;
        
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
        }
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthenticationFilter:Initializing filter");
            }
        }
    }

    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

    @Override
    public void destroy() {
    }
    
}

*/