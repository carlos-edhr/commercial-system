<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.carlosehr.commercialsystem.models.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Comercializadora</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">


    </head>
    <%
        List<Client> listClients = null;
        
        if (request.getAttribute("clients") != null) {
            listClients = (List<Client>)request.getAttribute("clients");
        }
        
        String result = "";
        
        if (request.getAttribute("opClie") != null) {
            result = (String)request.getAttribute("opClie");
        } else if (request.getSession().getAttribute("opClie") != null){
            result = (String)request.getSession().getAttribute("opClie");
        }
    %>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>
        
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <div class="col-md-10">
                    <% if(!result.isEmpty()) { %>
                    <div class="alert alert-success alert-dismissible fade in">
                      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                      <%= result %>
                    </div>
                    <% request.getSession().removeAttribute("opClie"); %>
                    <% } %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Client Table</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>ID Client</th>
                                                <th>ID Card</th>
                                                <th>Company</th>
                                                <th>Contact</th>
                                                <th>Address</th>
                                                <th>Fax</th>
                                                <th>Email</th>
                                                <th>Cellphone</th>
                                                <th>Phone</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for (Client client : listClients) { %>
                                           <tr>
                                               <td><%= client.getClientId() %></td>
                                               <td><%= client.getIdCard()%></td>
                                               <td><%= client.getCompanyName()%></td>
                                               <td><%= client.getContactName()%></td>
                                               <td><%= client.getAddress()%></td>
                                               <td><%= client.getFax() %></td>
                                               <td><%= client.getEmail() %></td>
                                               <td><%= client.getCellphone()%></td>
                                               <td><%= client.getPhone()%></td>
                                               <td>
                                                    <a href="clients?action=edit&idClie=<%= client.getClientId() %>" class="btn btn-primary">Edit</a>
                                               </td>
                                                <td>
                                                    <form action="clients" method="post">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="idClie" value="<%= client.getClientId() %>">
                                                        <input type="submit" value="delete" class="btn btn-danger">
                                                    </form>
                                                </td>
                                            </tr>
                                        <% } %>
                                            
                                        </tbody>
                                    </table>
                                    <a href="clients?action=new" class="btn btn-primary">New Client</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"></jsp:include>
    </body>
</html>
