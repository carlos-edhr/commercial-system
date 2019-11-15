<%@page import="java.util.List"%>
<%@page import="com.carlosehr.commercialsystem.models.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">

   
    </head>
    <%
        List<Category> listCategory = (List<Category>)request.getAttribute("category");
         
        String result = "";
        if (request.getSession().getAttribute("operationCategory")!= null) {
            result = (String)request.getSession().getAttribute("operationCategory");
        }
        
    %>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>
        
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <div class="col-md-10">
                <% if (!result.isEmpty()) { %>
                    <div class="alert alert-success alert-dismissible fade in">
                      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                      <%= result %>
                    </div>        
                      <% request.getSession().removeAttribute("insertCategory"); %>
                <% }  %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Basic Table</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>ID Category</th>
                                                <th>Category Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for (Category category : listCategory) { %>
                                           <tr>
                                                <td> <%= category.getCategory_id() %> </td>
                                                <td> <%= category.getName() %> </td>
                                                <td>
                                                    <a href="category?action=show&idCat=<%= category.getCategory_id() %>" class="btn btn-default">Show</a>
                                                </td>
                                                <td> 
                                                   <a href="category?action=edit&idCat=<%= category.getCategory_id() %>" class="btn btn-primary">Edit</a>
                                                </td>
                                                <td>
                                                    <form action="category" method="post">
                                                        <input type="hidden" name="action" value="delete" >
                                                        <input type="hidden" name="idCat" value="<%= category.getCategory_id() %>">
                                                        <input type="submit" value="delete" class="btn btn-danger" >
                                                    </form>
                                                </td>
                                            </tr>
                                        <%}%>
                                            
                                        </tbody>
                                    </table>
                                    <a href="category?action=new" class="btn btn-primary">New Category</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"></jsp:include>
    </body>

