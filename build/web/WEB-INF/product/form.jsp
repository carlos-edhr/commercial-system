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
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">
     
        <link href="<%= request.getContextPath() %>/vendors/select/bootstrap-select.min.css" rel="stylesheet">
    </head>
    <%
        String typeForm = (String)request.getAttribute("typeForm");

        Product prod = null;

        if (typeForm.equals("update")) {
            prod = (Product)request.getAttribute("product");
        }
        
        List<Category> listCategories = null;
        List<Provider> listProviders = null;
        
        if (request.getAttribute("categories") != null) {
            listCategories = (List<Category>)request.getAttribute("categories");
        }
        
        if (request.getAttribute("providers") != null) {
            listProviders = (List<Provider>)request.getAttribute("providers");
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
                                    <div class="panel-title"><%= typeForm %> Product</div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="products" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="action" value="<%= typeForm %>">
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idProd" placeholder="Product ID..." value="<% if(typeForm.equals("actualizar")){ out.print(prod.getProductId());} %>">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <select class="form-control" name="idProv" autocomplete="off">
                                                    <% if (!typeForm.equals("update")) { %>
                                                    <option value="" selected="true" disabled="true" >Select a provider...</option>
                                                    <% } else {%>
                                                    <% for (Provider prov : listProviders) {%>
                                                    <option value="<%= prov.getProvider_id()%>" <% if (typeForm.equals("update") && prov.getProvider_id()== prod.getProvider().getProvider_id()) {
                                                        out.print("selected=\"true\"");
                                                    }  %> ><%= prov.getName()%></option>
                                                    <% } %>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                                
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <select class="form-control" name="idCat">
                                                    <% if (!typeForm.equals("update")) { %>
                                                    <option value="" selected="true" disabled="true" >Select a category...</option>
                                                    <% } %>
                                                    <% for (Category cat : listCategories) {%>
                                                    <option value="<%= cat.getCategory_id()%>" selected="<% if (typeForm.equals("update") && cat.getCategory_id()== prod.getCategory().getCategory_id()) {
                                                        out.print("true");
                                                    }%>"><%= cat.getName()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                                
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="description" placeholder="Product description..." value="<% if(typeForm.equals("update")){ out.print(prod.getDescription());} %>">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="priceU" placeholder="Unitary price..." value="<% if(typeForm.equals("update")){ out.print(prod.getPriceUnit());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="stock" placeholder="Product in stock..." value="<% if(typeForm.equals("update")){ out.print(prod.getStock());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <button type="submit" class="btn btn-primary"><%= typeForm %></button>
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
<script src="<%= request.getContextPath() %>/vendors/select/bootstrap-select.min.js"></script>
