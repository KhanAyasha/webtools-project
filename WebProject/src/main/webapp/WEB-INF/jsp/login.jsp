<%-- 
    Document   : login
    Created on : Aug 13, 2024, 10:13:13?PM
    Author     : ayashakhan
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        
        body {
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .login-container {
            max-width: 400px;
            margin: 100px auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
        }
        h2{
            text-align: center;
        }
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
        .register-link {
            text-align: center;
            margin-top: 10px;
        }
        .register-link a {
            color: #fff;
        }
        .register-link a:hover {
            color: #3742fa;
        }
    </style>
    <script>
        window.onload = function() {
            let urlSplit = window.location.href.split("?");
            if(urlSplit.length > 1) {
               let queryString = urlSplit[1];
               let queryStringFragments = queryString.split("&");
               let parameters = {};
               for(const queryStringFragment of queryStringFragments) {
                   const parameterSplit = queryStringFragment.split("=");
                   parameters[parameterSplit[0]] = parameterSplit[1];
               }
               if(parameters.logout === "true") {
                   alert("Successfully logged out the user");
               }
            }
            
            document.querySelector("form").onsubmit = function(event) {
                const email = document.querySelector("input[name='emailId']");
                const password = document.querySelector("input[name='password']");
                const role = document.querySelector("select[name='role']");
                
                const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]{8,}$/;

                if (!email.value.match(emailRegex)) {
                    alert("Please enter a valid email address.");
                    email.focus();
                    event.preventDefault();
                    return false;
                }

                if (!password.value.match(passwordRegex)) {
                    alert("Password must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, and one number.");
                    password.focus();
                    event.preventDefault();
                    return false;
                }

                if (role.value === "") {
                    alert("Please select a role.");
                    role.focus();
                    event.preventDefault();
                    return false;
                }

                return true;
            };
        };
    </script>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="login.htm" method="post">
            <input type="email" name="emailId" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <select name="role" required>
                <option value="">Select your role</option>
                <option value="student">Student</option>
                <option value="contributor">Contributor</option>
            </select>
            <input type="submit" value="Login">
        </form>
        <div class="register-link">
            <p>Don't have an account? <a href="register.htm">Register here</a></p>
        </div>
    </div>
</body>
</html>
