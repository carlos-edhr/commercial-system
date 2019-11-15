<!DOCTYPE html>
<html>
    <head>
        <title>Commercial System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <link href="css/styles.css" rel="stylesheet">
        
        <link href="css/login.css" rel="stylesheet">

    </head>
    <body class="login-bg " >
        <div class="container-fluid">
            <div class="page-content container">
                <div class="row center-block">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="login-wrapper">
                            <div class="box">
                                <div class="content-wrap">
                                    <h6>Sign Up</h6>
                                    <form action="/commercial-system/users" method="post">
                                        <input type="hidden" name="action" value="createUser">
                                        <input name="user" class="form-control" type="email" placeholder="Enter a valid email address">
                                        <br>
                                        <input name="password" class="form-control" type="password" placeholder="Password">
                                        <input name="password2" class="form-control" type="password" placeholder="Confirm Password">
                                        <div class="action">
                                            <input type="submit" value="register" class="btn btn-primary signup">
                                        </div>                
                                    </form>
                                </div>
                            </div>
                            <div class="listo">
                                <p>¿Do you already have an account?</p>
                                <a href="index.jsp">Login</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery.js"></script>

        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
    
</html>