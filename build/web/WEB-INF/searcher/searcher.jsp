<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">
    </head>
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
                                    <div class="panel-title">Categories results</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <c:forEach items="${category}" var="category">
                                        <tr>
                                            <td><a href="category?action=show&idCat=${category.categoryId}" class="btn btn-link">${category.nameCate}</a></td>
                                        </tr> 
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                                        
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Product results</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <c:forEach items="${products}" var="product">
                                        <tr>
                                            <td><a href="products?action=edit&idProd=${product.productId}" class="btn btn-link">${product.description}</a></td>
                                        </tr> 
                                        </c:forEach>
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