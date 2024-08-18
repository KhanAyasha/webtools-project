<%-- 
    Document   : add-course-success
    Created on : Aug 13, 2024, 10:53:49 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Added Successfully</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #4e5e6d;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            background-position: center;
            color: #333;
        }
        .container {
            max-width: 400px;
            margin: 100px auto;
            margin-top: 400px;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        .container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: whitesmoke;
        }
        .container p {
            text-align: center;
            font-size: 18px;
            margin-bottom: 20px;
            color: whitesmoke;
        }
        .container a {
            display: flex;
            justify-content: center;
            gap: 10px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Course Added Successfully!</h2>
        <p>Your course has been added to the database.</p>
        
        <br><br>
        <!--<a href="home.jsp">Go to Dashboard</a>-->
    </div>
</body>
</html>

