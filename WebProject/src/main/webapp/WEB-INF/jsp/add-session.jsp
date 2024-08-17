<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Course</title>
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
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #8092a2;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: whitesmoke;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
            color: whitesmoke;
        }

        input[type="text"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            align-self: center;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <t:header />
    <div class="container">
        <h1>Add Course</h1>
        <form:form method="post" action="${pageContext.request.contextPath}/path-to-your-endpoint" modelAttribute="yourModelAttribute">
            <label for="date">Date</label>
            <input type="date" name="date" id="date" required>

            <label for="time">Time</label>
            <input type="time" name="time" id="time" required>

            <label for="contributorId">Contributor ID</label>
            <input type="number" name="contributorId" id="contributorId" required>

            <label for="courseId">Course ID</label>
            <input type="number" name="courseId" id="courseId" required>

            <label for="sessionId">Session ID</label>
            <input type="number" name="sessionId" id="sessionId" required>

            <input type="submit" value="Submit">
        </form:form>

    </div>
</body>
</html>
