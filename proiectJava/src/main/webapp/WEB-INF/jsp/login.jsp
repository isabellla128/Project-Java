<html>
<head>
    <title>
        Login Page
    </title>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-form {
            width: 400px;
            height: 200px;
            background: #34568B;
            position: absolute;
            border-radius: 20px;
            top:50%;
            left:50%;
            margin-right: -50%;
            transform: translate(-50%,-50%);
        }
    </style>
</head>
<body>
    <div class="alert alert-danger" role="alert">${errorMsg}</div>
    <div class="login-form">
        <div class="container-fluid">
            <form method="post">
                <input type="text" class="form-control mt-3" name="userName" placeholder="Username" />
                <input type="password" class="form-control mt-3" name="password" placeholder="Password" />
                <button class="btn btn-dark btn-block mt-3">Login</button>
            </form>
        </div>
    </div>
<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>