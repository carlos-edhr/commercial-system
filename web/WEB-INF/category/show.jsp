<%@page import="com.carlosehr.commercialsystem.models.Category"%>
<%@page import="com.carlosehr.commercialsystem.models.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">
    </head>
    <%
        Category cat = (Category)request.getAttribute("category");
    %>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>
        
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Category <%= cat.getName() %> </div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Stock</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for (Product prod : cat.getProducts()) { %>
                                           <tr>
                                                <td> <%= prod.getProductId() %> </td>
                                                <td> <%= prod.getDescription() %> </td>
                                                <td> <%= prod.getStock() %> </td>
                                            </tr>
                                        <% } %>
                                        </tbody>
                                    </table>
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
