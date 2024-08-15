<%-- 
    Document   : register
    Created on : Aug 13, 2024, 10:13:49 PM
    Author     : ayashakhan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
            background-color: #8092a2;
        }
        .register-container {
            max-width: 400px;
            margin: 50px auto;
            margin-top: 170px;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        h2 {
            text-align: center;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #1e90ff;
            margin: 10px;
            border: none;
            border-radius: 10px;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #3742fa;
        }
        .login-link {
            text-align: center;
            margin-top: 10px;
        }
        .login-link a {
            color: #fff;
        }
        .login-link a:hover {
            color: #3742fa;
        }
    </style>
    <script>
        function submitForm(event) {
            event.preventDefault(); // Prevent the default form submission

            // Get the form element
            var form = document.getElementById("registerForm");

            // Create an object to hold form data
            var formData = {
                firstName: form["firstName"].value,
                lastName: form["lastName"].value,
                emailId: form["emailId"].value,
                password: form["password"].value,
                role: form["role"].value,
                major: form["major"] ? form["major"].value : null,
                expertise: form["expertise"] ? form["expertise"].value : null,
                experienceYear: form["experienceYear"] ? form["experienceYear"].value : null
            };

            // Clean up the form data object to remove any null or empty values
            Object.keys(formData).forEach(key => {
                if (formData[key] === null || formData[key] === '') {
                    delete formData[key];
                }
            });

            // Send the form data as JSON to the server
            fetch('/WebProject/register.htm', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => {
                const contentType = response.headers.get('content-type');

                if (response.ok) {
                    // Handle redirect if present
                    if (response.redirected) {
                        window.location.href = response.url;
                    } else if (contentType && contentType.includes('application/json')) {
                        return response.json();
                    } else {
                        return response.text().then(html => {
                            // Handle unexpected HTML response (like error pages)
                            throw new Error("Received an unexpected HTML response. Please try again.");
                        });
                    }
                } else {
                    return response.json().then(data => {
                        throw new Error(data.error || "Registration failed. Please try again.");
                    });
                }
            })
            .then(data => {
                if (data && data.success) {
                    alert("Registration successful!");
                }
            })
            .catch(error => {
                alert(error.message); // Show the error message to the user
            });
        }

        function toggleRoleFields() {
            var role = document.getElementById("role").value;
            var majorField = document.getElementById("major-field");
            var expertiseField = document.getElementById("expertise-field");
            var experienceField = document.getElementById("experience-field");

            if (role === "student") {
                majorField.style.display = "block";
                expertiseField.style.display = "none";
                experienceField.style.display = "none";
            } else if (role === "contributor") {
                majorField.style.display = "none";
                expertiseField.style.display = "block";
                experienceField.style.display = "block";
            } else {
                majorField.style.display = "none";
                expertiseField.style.display = "none";
                experienceField.style.display = "none";
            }
        }

        // Show alert if error parameter is present
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            var errorMessage = urlParams.get('error');
            if (errorMessage) {
                alert(errorMessage);
            }
        };
    </script>
</head>
<body>
    <div class="register-container">
        <h2>Register</h2>
        <form id="registerForm" onsubmit="submitForm(event)">
            <input type="text" name="firstName" placeholder="First Name *" required>
            <input type="text" name="lastName" placeholder="Last Name *" required>
            <input type="email" name="emailId" placeholder="Email *" required>
            <input type="password" name="password" placeholder="Password *" required>
            <select name="role" id="role" onchange="toggleRoleFields()" required>
                <option value="">Select Role *</option>
                <option value="student">Student</option>
                <option value="contributor">Contributor</option>
            </select>
            <div id="major-field" style="display: none;">
                <input type="text" name="major" placeholder="Major (optional)">
            </div>
            <div id="expertise-field" style="display: none;">
                <input type="text" name="expertise" placeholder="Expertise *">
            </div>
            <div id="experience-field" style="display: none;">
                <input type="text" name="experienceYear" placeholder="Experience Year *">
            </div>
            <input type="submit" value="Register">
        </form>
        <div class="login-link">
            <p>Already have an account? <a href="login.htm">Login here</a></p>
        </div>
    </div>
</body>
</html>
