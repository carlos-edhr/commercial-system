<%@page import="com.carlosehr.commercialsystem.models.Provider"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        List<Provider> listProviders = null;
        
        if (request.getAttribute("providers") != null) {
            listProviders = (List<Provider>)request.getAttribute("providers");
        }
        
        String result = "";
        
        if (request.getAttribute("opProv") != null) {
            result = (String)request.getAttribute("opProv");
        } else if (request.getSession().getAttribute("opProv") != null){
            result = (String)request.getSession().getAttribute("opProv");
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
                    <% request.getSession().removeAttribute("opProv"); %>
                    <% } %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Providers Table</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Provider ID</th>
                                                <th>Name</th>
                                                <th>Contact</th>
                                                <th>Cellphone</th>
                                                <th>Phone</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for (Provider provider : listProviders) { %>
                                           <tr>
                                                <td> <%= provider.getProvider_id()%> </td>
                                                <td> <%= provider.getName()%> </td>
                                                <td> <%= provider.getContact()%> </td>
                                                <td> <%= provider.getCellphone()%> </td>
                                                <td> <%= provider.getPhone()%> </td>
                                                <td>
                                                    <a href="providers?action=edit&idProv=<%= provider.getProvider_id()%>" class="btn btn-primary">Edit</a>
                                                </td>
                                                <td>
                                                    <form action="providers" method="post">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="idProv" value="<%= provider.getProvider_id()%>">
                                                        <input type="submit" value="delete" class="btn btn-danger">
                                                    </form>
                                                </td>
                                            </tr>
                                        <% } %>
                                            
                                        </tbody>
                                    </table>
                                    <a href="providers?action=new" class="btn btn-primary"> New Provider</a>
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