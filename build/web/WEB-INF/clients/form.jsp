<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.carlosehr.commercialsystem.models.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">

    </head>
    <%
        String typeForm = (String)request.getAttribute("typeForm");
        Client clie = null;
        if (typeForm.equals("update")) {
            clie = (Client)request.getAttribute("client");
        }
    %>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>
        
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-6 col-sm-offset-3">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title"><%= typeForm %> Clients</div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="clients" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="action" value="<%= typeForm %>">
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idClie" placeholder="Client id..." value="<% if(typeForm.equals("update")){ out.print(clie.getClientId());} %>">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="idCard" placeholder="Id Card..." value="<% if(typeForm.equals("update")){ out.print(clie.getIdCard());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="companyName" placeholder="Company name..." value="<% if(typeForm.equals("update")){ out.print(clie.getCompanyName());} %>">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="contactName" placeholder="Contact name..." value="<% if(typeForm.equals("update")){ out.print(clie.getContactName());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="address" placeholder="Client address..." value="<% if(typeForm.equals("update")){ out.print(clie.getAddress());} %>">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="fax" placeholder="Fax..." value="<% if(typeForm.equals("update")){ out.print(clie.getFax());} %>">
                                            </div>
                                        </div>
                                       
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="email" class="form-control" name="email" placeholder="email@address..." value="<% if(typeForm.equals("update")){ out.print(clie.getEmail());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="cellphone" placeholder="Cellphone..." value="<% if(typeForm.equals("update")){ out.print(clie.getCellphone());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="phone" placeholder="Phone..." value="<% if(typeForm.equals("update")){ out.print(clie.getPhone());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <button type="submit" class="btn btn-primary"><%= typeForm %> Client</button>
                                            </div>
                                        </div>
                                    </form>
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