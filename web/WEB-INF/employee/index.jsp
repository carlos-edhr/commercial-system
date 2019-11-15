<%@page import="com.carlosehr.commercialsystem.models.Employee"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.carlosehr.commercialsystem.models.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

    </head>
    <%
        List<Employee> listEmployees = null;

        if (request.getAttribute("employee") != null) {
            listEmployees = (List<Employee>) request.getAttribute("employee");
        }

        String result = "";
        if (request.getSession().getAttribute("opEmp") != null) {
            result = (String) request.getSession().getAttribute("opEmp");
        }

    %>
    <body>
        <jsp:include page="../layouts/header.jsp" ></jsp:include>

            <div class="page-content">
                <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                    <div class="col-md-10">
                    <% if (!result.isEmpty()) {%>
                    <div class="alert alert-success alert-dismissible fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <%= result%>
                    </div>        
                    <% request.getSession().removeAttribute("opEmp"); %>
                    <% }  %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Employee Table</div>
                                </div>
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>ID Employee</th>
                                                <th>Name</th>
                                                <th>Last Name</th>
                                                <th>Birth Day</th>
                                                <th>Reports to</th>
                                                <th>Ext</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Employee employee : listEmployees) { %>
                                            <tr>
                                                <td> <%= employee.getEmployee_id()%> </td>
                                                <td> <%= employee.getName() %>  </td>
                                                <td> <%= employee.getLast_name()%> </td>
                                                <td> <%= new SimpleDateFormat("dd-MM-yyyy").format(
                                                          employee.getBirth_date()) %> </td>
                                                <td> <%= employee.getBoss()%> </td>
                                                <td> <%= employee.getExt()%> </td>
                                                <td> <a href="employee?action=edit&idEmp=<%= employee.getEmployee_id()%>" class="btn btn-primary">Edit</a> </td>
                                                <td> <form action="employee" method="post">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="idEmp" value="<%= employee.getEmployee_id() %>">    
                                                        <input type="submit" value="delete" class="btn btn-danger">
                                                    </form> 
                                                </td>
                                            </tr>
                                            <% } %>
                                            
                                        </tbody>
                                    </table>
                                    <a href="employee?action=new" class="btn btn-primary">New Employee</a>
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
