<%@page import="com.carlosehr.commercialsystem.models.Employee"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        
        Employee emp = null;
        if (typeForm.equals("update")) {
            emp = (Employee)request.getAttribute("employee");
        }
        
        List<Employee> employees = (List<Employee>)request.getAttribute("employees");
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
                                        <%= typeForm %> Employee
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" action="employee" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="action" value="<%= typeForm %>">
                                            <div class="col-sm-12">
                                              <input type="number" class="form-control" name="idEmp" placeholder="Employee ID..." value="<% if(typeForm.equals("update")){ out.print(emp.getEmployee_id());} %>">  
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nameEmp" placeholder="Employee Name..." value="<% if(typeForm.equals("update")){ out.print(emp.getName());} %>">
                                            </div>
                                        </div>
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="lastNameEmp" placeholder="Employee Last Name..." value="<% if(typeForm.equals("update")){ out.print(emp.getLast_name());} %>">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="date" class="form-control" name="birth_day" placeholder="Birth Date..." value="<% if(typeForm.equals("update")){ out.print(new SimpleDateFormat("yyyy-MM-dd").format(emp.getBirth_date()));} %>">
                                            </div>
                                        </div>
                                        
                                            
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <select class="form-control" name="reportsTo">
                                                    <%for (Employee empInt : employees) {%>
                                                    <option value="<%= empInt.getEmployee_id()%>" <% if(typeForm.equals("update") && emp.getReports_to()== empInt.getEmployee_id()){ out.print("selected");} %> > <%= empInt.getCompleteName()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="ext" placeholder="Extension..." value="<% if(typeForm.equals("update")){ out.print(emp.getExt());} %>">
                                            </div>
                                        </div>

                                            
                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <button type="submit" class="btn btn-primary"><%= typeForm %> Employee</button>
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