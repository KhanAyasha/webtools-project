<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Courses</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            margin-bottom: 20px;
            color: whitesmoke;
            background-color: black;
        }
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .card-body {
            padding: 15px;
        }
        .btn-schedule {
            margin-top: 10px;
        }
    </style>
    
    
</head>
<body>
<t:header />
<div class="container">
    <h1 class="text-center">Your Courses</h1>
    <div class="row">
        <c:forEach var="course" items="${courses}">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${course.courseName}</h5>
                        <p class="card-text">
                            <strong>Course ID</strong> ${course.courseId}<br>
                            <strong>Description:</strong> ${course.description}<br>
                            <strong>Contributor:</strong> ${course.contributor.contributorId}<br>
                            <strong>First Name:</strong> ${course.contributor.firstName}<br>
                            <strong>Last Name:</strong> ${course.contributor.lastName}<br>
                            <strong>Expertise:</strong> ${course.contributor.expertise}<br>
                            <strong>Experience:</strong> ${course.contributor.experienceYears}<br>
                        </p>

                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty courses}">
            <div class="col-12">
                <p class="text-center text-light">No courses found.</p>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>

