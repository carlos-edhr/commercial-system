<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.carlosehr.commercialsystem.models.Category"%>
<%@page import="com.carlosehr.commercialsystem.models.Product"%>
<%@page import="com.carlosehr.commercialsystem.models.Provider"%>
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
        List<Product> listProducts = null;
        
        if (request.getAttribute("products") != null) {
            listProducts = (List<Product>)request.getAttribute("products");
        }
        String result = "";
        
        if (request.getAttribute("opProd") != null) {
            result = (String)request.getAttribute("opProd");
        } else if (request.getSession().getAttribute("opProd") != null){
            result = (String)request.getSession().getAttribute("opProd");
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
                    <% request.getSession().removeAttribute("opProd"); %>
                    <% } %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Products Table</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Product ID</th>
                                                <th>Provider</th>
                                                <th>Category</th>
                                                <th>Description</th>
                                                <th>Total</th>
                                                <th>Stock</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for (Product product : listProducts) { %>
                                           <tr>
                                                <td> <%= product.getProductId() %> </td>
                                                <td> <%= product.getProvider().getName()%> </td>
                                                <td> <%= product.getCategory().getName()%> </td>
                                                <td> <%= product.getDescription()%> </td>
                                                <td> <%= product.getUnitPrice()%> </td>
                                                <td><%= product.getStock() %> </td>
                                                <td>
                                                    <a href="products?action=edit&idProd=<%= product.getProductId() %>" class="btn btn-primary">Edit</a>
                                                </td>
                                                <td>
                                                    <form action="products" method="post">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="idProd" value="<%= product.getProductId() %>">
                                                        <input type="submit" value="delete" class="btn btn-danger">
                                                    </form>
                                                </td>
                                            </tr>
                                        <% } %>
                                            
                                        </tbody>
                                    </table>
                                    <a href="products?action=new" class="btn btn-primary">Create Product</a>
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