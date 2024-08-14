<%-- 
    Document   : add-course
    Created on : Aug 13, 2024, 10:53:26 PM
    Author     : ayashakhan
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Course</title>
    <style>
        body {
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .container {
            max-width: 400px;
            margin: 100px auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        .container h2 {
            text-align: center;
            margin-bottom: 20px;
            /*color: #333;*/
        }
        .container input[type="text"],
        .container textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
        }
        .container textarea {
            resize: none;
        }
        .container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #1e90ff;
            margin: 10px;
            border: none;
            border-radius: 10px;
            color: white;
            cursor: pointer;
        }
        .container input[type="submit"]:hover {
            background-color: #3742fa;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Course</h2>
        <form action="addCourseAction.jsp" method="post">
            <label for="courseName">Course Name:</label>
            <input type="text" id="courseName" name="courseName" required>

            <label for="courseDescription">Course Description:</label>
            <textarea id="courseDescription" name="courseDescription" rows="4" required></textarea>

            <label for="courseDuration">Course Duration (in hours):</label>
            <input type="text" id="courseDuration" name="courseDuration" required>

            <input type="submit" value="Add Course">
        </form>
    </div>
</body>
</html>
