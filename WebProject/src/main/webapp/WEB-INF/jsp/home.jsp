<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            color: black;
            text-align: center;
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
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            function enableEdit() {
                const role = '${role}';
                const elements = ["firstName", "lastName"];
                if (role === 'student') {
                    elements.push("major");
                } else if (role === 'contributor') {
                    elements.push("expertise", "experienceYears");
                }
                elements.forEach(id => {
                    document.getElementById(id).removeAttribute("readonly");
                });
                document.getElementById("updateButton").style.display = 'inline';
                document.getElementById("editButton").style.display = 'none';
            }

            function updateProfile() {
                const emailId = '${emailId}';
                const role = '${role}';
                const firstName = document.getElementById("firstName").value;
                const lastName = document.getElementById("lastName").value;
                let requestBody = {
                    firstName: firstName,
                    lastName: lastName
                };
                
                if (role === 'student') {
                    requestBody.major = document.getElementById('major').value;
                } else if (role === 'contributor') {
                    requestBody.expertise = document.getElementById('expertise').value;
                    requestBody.experienceYears = document.getElementById('experienceYears').value;
                }
                
                const url = `/WebProject/${role}/update/${emailId}`;
                console.log(`Sending request to: ${url}`);
                
                fetch(url, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestBody)
                })
                .then(response => response.text()) // Use .text() instead of .json()
                .then(data => {
                    alert('Update successful:', data);
                    // Optionally, handle UI updates or show success messages
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('You have some error!');
                });
            }

            document.getElementById("editButton").addEventListener("click", enableEdit);
            document.getElementById("updateButton").addEventListener("click", updateProfile);
        });
    </script>
</head>
<body>
<t:header />
<div class="container">
    <div class="profile">
        <div class="profile-info">
            <h2>Welcome! ${authenticatedUser.lastName}, ${authenticatedUser.firstName} ;)</h2>
            <p>Role: ${role}</p>
            <p>Email: ${authenticatedUser.emailId}</p>
        </div>
        <div class="profile-action">
            <button type="button" id="updateButton" style="display:none;">Update Profile</button>
        </div>
    </div>

    <div class="content">
        <h1>Your Dashboard</h1>
        <div class="form-container">
            <c:choose>
                <c:when test="${role eq 'contributor'}">
                    <h2>Contributor Information</h2>
                    <form>
                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" value="${authenticatedUser.firstName}" readonly />

                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" value="${authenticatedUser.lastName}" readonly />

                        <label for="expertise">Expertise:</label>
                        <input type="text" id="expertise" value="${authenticatedUser.expertise}" readonly />

                        <label for="experienceYears">Years of Experience:</label>
                        <input type="text" id="experienceYears" value="${authenticatedUser.experienceYears}" readonly />

                        <button type="button" id="editButton">Edit Information</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h2>Student Information</h2>
                    <form>
                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" value="${authenticatedUser.firstName}" readonly />

                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" value="${authenticatedUser.lastName}" readonly />

                        <label for="major">Major:</label>
                        <input type="text" id="major" value="${authenticatedUser.major}" readonly />

                        <button type="button" id="editButton">Edit Information</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
