<%-- 
    Document   : add-account
    Created on : Aug 13, 2024, 10:51:21 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Community Skill Exchange Platform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            text-align: center;
            padding: 0;
            margin: 0;
        }

        .container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: rgba(0, 0, 0, 0.6);
            padding: 20px;
            border-radius: 15px;
        }

        h1 {
            font-size: 48px;
            margin-bottom: 20px;
        }

        p {
            font-size: 20px;
            margin-bottom: 30px;
            max-width: 800px;
        }

        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .button {
            background-color: #1e90ff;
            color: white;
            padding: 15px 30px;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #3742fa;
        }

    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Community Skill Exchange</h1>
        <p>The Community Skill Exchange Platform is designed to allow users to exchange skills and services within a community. It promotes collaboration and learning by enabling users to list their skills, book sessions, and engage in real-time, one-to-one tutoring sessions. Explore, learn, and grow together!</p>
        
        <div class="button-container">
            <a href="login.htm" class="button">Login</a>
            <a href="register.htm" class="button">Register</a>
        </div>
    </div>
</body>
</html>
