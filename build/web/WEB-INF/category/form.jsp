<%@page import="java.util.List"%>
<%@page import="com.carlosehr.commercialsystem.models.Category"%>
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
        String typeForm = (String)request.getAttribute("typeForm");
        
        Category cat = null;
        if (typeForm.equals("update")) {
            cat = (Category)request.getAttribute("category");
        }
    %>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>
        
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <%= typeForm %> Category
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="category" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="action" value="<%= typeForm %>">
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idCat" placeholder="Category ID..." value="<% if(typeForm.equals("update")){ out.print(cat.getCategory_id());} %>"  >
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nameCat" placeholder="Name category..." value="<% if(typeForm.equals("update")){ out.print(cat.getName());} %>">
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
