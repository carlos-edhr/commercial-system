
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
                        <div class="col-sm-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        Dashboard orders
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="col-sm-6">
                                        <a href="orders?action=showOrders" class="btn btn-large btn-primary">View Orders</a>
                                    </div>
                                    <div class="col-sm-6">
                                        <a href="orders?action=doOrder" class="btn btn-large btn-primary">Do Order</a>
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
