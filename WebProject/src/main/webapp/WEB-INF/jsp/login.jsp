<%-- 
    Document   : login
    Created on : Aug 13, 2024, 10:13:13 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .login-container {
            max-width: 400px;
            margin: 100px auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        h2{
            text-align: center;
        }
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #1e90ff;
            margin: 10px;
            border: none;
            border-radius: 10px;
            color: white;
            cursor: pointer;
            
        }
        input[type="submit"]:hover {
            background-color: #3742fa;
        }
        .register-link {
            text-align: center;
            margin-top: 10px;
        }
        .register-link a {
            color: #fff;
        }
        .register-link a:hover {
            color: #3742fa;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="post">
            <input type="email" name="emailId" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Login">
        </form>
        <div class="register-link">
            <p>Don't have an account? <a href="register.htm">Register here</a></p>
        </div>
    </div>
</body>
</html>

