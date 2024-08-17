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
    
    <script>
    function deleteCourse(courseId) {
        console.log(courseId);
        const contextPath = "${pageContext.request.contextPath}";
        const url = contextPath + "/contributor/delete-course.htm/" + courseId;
        console.log("URL:", url);
        fetch(url, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert("Course deleted successfully!");
                // Optionally, refresh the page or redirect to another page
                window.location.reload();
            } else {
                alert("Failed to delete course. Course not found.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
    }
</script>
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
                            <strong>Description:</strong> ${course.courseId}<br>
                            <strong>Description:</strong> ${course.description}<br>
                            <strong>Contributor:</strong> ${course.contributor.contributorId}<br>
                            <strong>Contributor:</strong> ${course.contributor.firstName}<br>
                            <strong>Contributor:</strong> ${course.contributor.lastName}<br>
                            <strong>Contributor:</strong> ${course.contributor.expertise}<br>
                            <strong>Contributor:</strong> ${course.contributor.experienceYears}<br>
                        </p>
                            <!--<a href="${pageContext.request.contextPath}/delete-course.htm/${course.contributor.contributorId}" class="btn btn-primary btn-schedule">Schedule Appointment</a>-->
        <a href="#" onclick="deleteCourse(${course.courseId})" class="btn btn-primary btn-schedule">Delete Course</a>

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
