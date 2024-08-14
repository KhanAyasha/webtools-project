<%-- 
    Document   : register
    Created on : Aug 13, 2024, 10:13:49 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .register-container {
            max-width: 400px;
            margin: 50px auto;
            margin-top: 170px;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        h2{
            text-align: center;
        }
        input[type="text"],
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
        .login-link {
            text-align: center;
            margin-top: 10px;
        }
        .login-link a {
            color: #fff;
        }
        .login-link a:hover {
            color: #3742fa;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Register</h2>
        <form action="register-Success.htm" method="post">
            <input type="text" name="firstName" placeholder="First Name" required>
            <input type="text" name="lastName" placeholder="Last Name" required>
            <input type="email" name="emailId" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="text" name="major" placeholder="Major (optional)">
            <input type="submit" value="Register">
        </form>
        <div class="login-link">
            <p>Already have an account? <a href="login.htm">Login here</a></p>
        </div>
    </div>
</body>
</html>
