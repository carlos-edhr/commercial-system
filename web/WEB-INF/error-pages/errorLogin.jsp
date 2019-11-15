<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body class="login-bg " >
        <div class="container-fluid">
            <div class="page-content container">
                <div class="row center-block">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="alert alert-danger">
                            <strong>¡Error!</strong> ${pageContext.exception.message }
                        </div>
                        <div class="login-wrapper">
                            <div class="box">
                                <div class="content-wrap">
                                    <h6>Sign In</h6>
                                    <form action="/commercial-system/users" method="post">
                                        <input type="hidden" name="action" value="validateUser">
                                        <input class="form-control" name="user" type="email" placeholder="Enter your email...">
                                        <br>
                                        <input class="form-control" name="password" type="password" placeholder="Password">
                                        <div class="action">
                                            <input type="submit" value="login" class="btn btn-primary signup">
                                        </div>                
                                    </form>
                                </div>
                            </div>
                            <div class="listo">
                                <p>¿No account?</p>
                                <a href="register.jsp">Sign up!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
   
</html>