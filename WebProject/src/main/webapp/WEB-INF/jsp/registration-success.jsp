<%-- 
    Document   : registration-success
    Created on : Aug 13, 2024, 10:42:42 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <style>
        body {
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .success-container {
            max-width: 400px;
            margin: 100px auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
            text-align: center;
        }
        h2 {
            color: #28a745;
        }
        .login-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        .login-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>Registration Successful!</h2>
        <p>Your account has been created successfully. You can now log in with your credentials.</p>
        <a href="login.htm" class="login-button">Go to Login</a>
    </div>
</body>
</html>

