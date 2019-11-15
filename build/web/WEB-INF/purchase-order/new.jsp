<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
        <jsp:include page="../layouts/header.jsp"></jsp:include>
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                <jsp:useBean class="com.carlosehr.commercialsystem.models.Order" id="order" scope="session"></jsp:useBean>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12 panel-primary">
                            <div class="content-box-header panel-heading">
                                <div class="panel-title">Do Order</div>
                            </div>
                            <div class="content-box-large box-with-header">
                                <form action="orders" method="post">
                                    <input type="hidden" name="action" value="endOrder">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    Order Date:
                                                    <input type="text" name="orderDate" class="form-control" placeholder="date" value="<%= new SimpleDateFormat("dd/MM/yyy").format(order.getOrderDate())  %>">
                                                </div>
                                            </div>
                                            <div class="row" style="margin-top: 15px;">
                                                <div class="col-sm-6">
                                                    Select employee:
                                                    <select name="idEmployee" id="" class="form-control">
                                                    <c:forEach items="${employees}" var="employee">
                                                        <option value="${employee.employee_id}">${employee.completeName}</option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-sm-6">
                                                    Choose a client:
                                                    <select name="idClient" id="" class="form-control">
                                                    <c:forEach items="${clients}" var="client">
                                                        <option value="${client.clientId}">${client.companyName}</option>
                                                    </c:forEach> 
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-12" style="text-align: center;">
                                                <input type="submit" class="btn btn-lg btn-default" value="End Order">
                                            </div>
                                            <div class="col-sm-12" style="text-align: center;">
                                                <p style="font-size: 60px; color: #9F81F7; vertical-align: baseline;">
                                                 ${order.roundedTotal} 
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <br>
                                <hr>
                                <div class="row">
                                    <form action="orders" method="post">
                                        <input type="hidden" name="action" value="addProduct">
                                        <div class="col-sm-2">
                                            Enter ID:
                                            <input type="text" class="form-control" placeholder="Product ID">
                                        </div>
                                        <div class="col-sm-2">
                                            or select a product
                                            <select name="idProd" id="" class="form-control">
                                                <option value="" selected="true" disabled="true">Select a product...
                                                </option>
                                            <c:forEach items="${products}" var="product" >
                                                <option value="${product.productId}">${product.description}</option>
                                            </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-2">
                                            Amount:
                                            <input type="number" class="form-control" name="amountProd" placeholder="Amount">
                                        </div>
                                        <div class="col-sm-2">
                                            <br>
                                            <input type="submit" value="Add Product" class="btn btn-primary">
                                        </div>
                                    </form>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-12">
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
                                                <c:forEach items="${order.details}" var="detail">
                                                    <tr>
                                                        <td>${detail.product.description} </td>
                                                        <td>${detail.amount} </td>
                                                        <td>${detail.product.unitPrice}</td>
                                                        <td>${detail.roundedTotal}</td>
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
        </div>

        <jsp:include page="../layouts/footer.jsp"></jsp:include>
    </body>
</html>