<%-- 
    Document   : schedule-session
    Created on : Aug 13, 2024, 10:54:09 PM
    Author     : ayashakhan
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule Session</title>
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
            max-width: 900px;
            margin: 50px auto;
            padding: 20px;
            background-color: #8092a2;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: whitesmoke;
        }

        .session-info {
            margin-top: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            color: black;
        }

        .session-info p {
            margin: 10px 0;
        }

        .form-container {
            margin-top: 30px;
            text-align: center;
        }

        .form-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }

        .form-container button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<t:header />
<div class="container">
    <h1>Schedule a Study Session</h1>
    <div class="session-info">
        <p><strong>Course Name:</strong> ${course.courseName}</p>
        <p><strong>Contributor:</strong> ${course.contributor.firstName} ${course.contributor.lastName}</p>
        <p><strong>Course Description:</strong> ${course.courseDescription}</p>
    </div>

    <div class="form-container">
        <form action="schedule-session.htm/${authenticatedUser.emailId}/${course.courseId}" method="post">
            <button type="submit">Confirm Appointment</button>
        </form>
    </div>
</div>
</body>
</html>
