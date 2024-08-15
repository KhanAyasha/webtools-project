<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #4e5e6d;
            /*background-image: url('images/background.jpg');*/
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
        .profile {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 20px;
            color: whitesmoke;
        }
        .profile-info {
            flex: 1;
        }
        .profile-info h2 {
            margin: 0;
            font-size: 24px;
            color: whitesmoke;
        }
        .profile-info p {
            margin: 5px 0;
            color: whitesmoke;
        }
        .profile-action {
            flex-shrink: 0;
        }
        .profile-action button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .profile-action button:hover {
            background-color: #0056b3;
        }
        .content {
            text-align: center;
        }
        .form-container {
            margin-top: 30px;
            padding: 20px;
            /*margin-right: 30px;*/
            padding-right: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            margin-top: 0;
            color: #333;
        }
        .form-container label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            /*color: whitesmoke;*/
        }
        .form-container input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .form-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 30px;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<t:header />
<div class="container">
    <div class="profile">
        <div class="profile-info">
            <h2>Welcome, ${sessionScope.authenticatedUser.getEmail()}</h2>
            <p>Role: ${sessionScope.authenticatedUser.getRole()}</p>
        </div>
        <div class="profile-action">
            <button onclick="location.href='update-profile.htm'">Update Profile</button>
        </div>
    </div>

    <div class="content">
        <h1>Your Dashboard</h1>
        <div class="form-container">
            <c:choose>
                <c:when test="${sessionScope.authenticatedUser.getRole() eq 'contributor'}">
                    <h2>Contributor Information</h2>
                    <form>
                        <label for="email">Email:</label>
                        <input type="text" id="email" value="${sessionScope.authenticatedUser.getEmail()}" readonly />

                        <label for="firstName">First Name:</label>
                        <input type="text" id="email" value="${sessionScope.authenticatedUser.getEmail()}" readonly />

                        <label for="lastName">Last Name:</label>
                        <input type="text" id="email" value="${sessionScope.authenticatedUser.getEmail()}" readonly />

                        <label for="role">Role:</label>
                        <input type="text" id="role" value="${sessionScope.authenticatedUser.getRole()}" readonly />

                        <label for="expertise">Expertise:</label>
                        <input type="text" id="expertise" value="${sessionScope.authenticatedUser.expertise}" readonly />

                        <label for="experience">Years of Experience:</label>
                        <input type="text" id="experience" value="${sessionScope.authenticatedUser.experience}" readonly />

                        <button type="button">Edit Information</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h2>Student Information</h2>
                    <form>
                        <label for="emailId">Email:</label>
                        <input type="text" id="email" value="${sessionScope.authenticatedUser.getEmail()}" readonly />

                        <label for="firstName">First Name:</label>
                        <input type="text" id="email" value="${sessionScope.authenticatedUser.getEmail()}" readonly />

                        <label for="lastName">Last Name:</label>
                        <input type="text" id="email" value="${sessionScope.authenticatedUser.getEmail()}" readonly />

                        <label for="role">Role:</label>
                        <input type="text" id="role" value="${sessionScope.authenticatedUser.getRole()}" readonly />

                        <label for="major">Major:</label>
                        <input type="text" id="major" value="${sessionScope.authenticatedUser.major}" readonly />

                        <button type="button">Edit Information</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
