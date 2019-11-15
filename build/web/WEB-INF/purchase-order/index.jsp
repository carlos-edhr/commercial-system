<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Commercial system</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>
        <jsp:useBean class="com.carlosehr.commercialsystem.models.Order" id="orderCreated" scope="session"></jsp:useBean>
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <div class="col-md-10">
                    <% if(orderCreated != null ) { %>
                    <div class="alert alert-success alert-dismissible fade in">
                      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                      Order created with ID <%= orderCreated.getOrderId()%>
                    </div>
                    <% request.getSession().removeAttribute("orderCreated"); %>
                    <% } %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Orders Table</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Order date</th>
                                                <th>Client</th>
                                                <th>Total</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${orders}" var="order">
                                            <tr>
                                                <td>${order.orderId}</td>
                                                <td>${order.orderDate}</td>
                                                <td>${order.client.companyName}</td>
                                                <td>${order.total}</td>
                                                <td><a href="orders?action=showOrder&idOrder=${order.orderId}" class="btn btn-info btn-xs">View Details</a></td>
                                            </tr> 
                                            </c:forEach>
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