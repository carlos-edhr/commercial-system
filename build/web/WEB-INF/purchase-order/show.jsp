<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">
    </head>
    <body>
        <jsp:useBean class="com.carlosehr.commercialsystem.models.Order" id="order" scope="request"></jsp:useBean>
        <jsp:include page="../layouts/header.jsp"></jsp:include>
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12 panel-primary">
                            <div class="content-box-header panel-heading">
                                <div class="panel-title">
                                    Order ${order.orderId}
                                </div>
                            </div>
                            <div class="content-box-large box-with-header">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-sm-3">
                                            Order Date:
                                            <h4><%= new SimpleDateFormat("dd/MM/yyy").format(order.getOrderDate())  %></h4>
                                        </div>
                                        <div class="col-sm-3" >
                                            Vendor: 
                                            <h4>${order.employee.name} ${order.employee.lastName}</h4>
                                        </div>
                                        <div class="col-sm-3" >
                                            client: 
                                            <h4>${order.employee.companyName}</h4>
                                        </div>
                                    </div>
                                </div>   
                                 <hr>
                            <br>
                            <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Amount</th>
                                            <th>Unitary Price</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${order.detail}" var="detail">
                                            <tr>
                                                <td>${detail.product.description}</td>
                                                <td>${detail.amount}</td>
                                                <td>${detail.product.unitPrice}</td>
                                                <td>${detail.total}</td>
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

        <jsp:include page="../layouts/footer.jsp"></jsp:include>
    </body>
</html>
