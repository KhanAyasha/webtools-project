<%-- 
    Document   : my-sessions
    Created on : Aug 13, 2024, 10:55:51 PM
    Author     : ayashakhan
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Sessions</title>
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
        .card {
            background-color: black;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            padding: 20px;
            color: whitesmoke;
        }
        .card h2 {
            margin-top: 0;
            color: whitesmoke;
        }
        .card p {
            margin: 5px 0;
            color: whitesmoke;
        }
        .card button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .card button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<t:header />
<div class="container">
    <h1>Your Study Sessions</h1>

    <c:choose>
        <c:when test="${not empty message}">
            <p>${message}</p>
        </c:when>
        <c:when test="${not empty studySessions}">
            <div class="row">
                <c:forEach var="session" items="${studySessions}">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <!--<h5 class="card-title">Session ID: ${session.studySessionID}</h5>-->
                                <p class="card-text">Course Name: ${session.course.courseName}</p>
                                <p class="card-text">Contributor: ${session.contributor.firstName} ${session.contributor.lastName}</p>
                                <p class="card-text">Date: ${session.date}</p>
                                <p class="card-text">Time: ${session.time}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p>No study sessions available.</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

