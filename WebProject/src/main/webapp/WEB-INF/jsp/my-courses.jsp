<%-- 
    Document   : my-courses
    Created on : Aug 13, 2024, 10:55:42 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.webtools.ayasha.WebProject.model.Course" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Courses</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f4;
            font-family: Arial, sans-serif;
            padding-top: 20px;
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .container {
            
            /*padding: 30px;*/
            border-radius: 10px;
            /*box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);*/
            margin: 100px auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            /*border-radius: 10px;*/
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        .container h2 {
            text-align: center;
            margin-bottom: 20px;
            /*color: #333;*/
        }
        .card {
            margin-bottom: 20px;
            color: whitesmoke;
            background-color: black;
           
            
        }
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
            text-align: center;
        }
        .card-text {
            font-size: 1rem;
        }
        .card-body {
            padding: 15px;
        }
        .btn-update, .btn-delete {
            margin-top: 10px;
        }
        .col-12 {
            text-align: center;
            margin-top: 10px;
            font-size: 20px;
        }
        .col-12 a {
            color: #fff;
        }
        .col-12 a:hover {
            color: #3742fa;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center mb-4">Your Courses</h2>
        <div class="row">
            <%
                // Assuming you have a list of Course objects in the session or request attribute named "courses"
                List<Course> courses = (List<Course>) request.getAttribute("courses");
                if (courses != null && !courses.isEmpty()) {
                    for (Course course : courses) {
            %>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= course.getCourseName() %></h5>
                        <p class="card-text">
                            <strong>Description:</strong> <%= course.getDescription() %><br>
                            <strong>Category:</strong> <%= course.getCategory() %><br>
                            <strong>Duration:</strong> <%= course.getDuration() %> hours<br>
                        </p>
                        <a href="updateCourse.jsp?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-update">Update Course</a>
                        <a href="deleteCourseAction.jsp?courseId=<%= course.getCourseId() %>" class="btn btn-danger btn-delete" onclick="return confirm('Are you sure you want to delete this course?');">Delete Course</a>
                    </div>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <div class="col-12">
                <p class="text-center">No courses found. <a href="add-course.htm">Add a new course</a></p>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

