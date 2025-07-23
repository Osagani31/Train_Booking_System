<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome - Train Management System!</title>
    <style>
        body {
            background-image: url('images/5b.jpg'); /* Your background image */
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            background-attachment: fixed;
            font-family: Arial, sans-serif;

            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .content-box {
            background-color: rgba(252, 228, 236, 0.65); /* #fce4ec with light transparency */
            padding: 50px 60px;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
        }

        h1 {
            color: #000000; /* Black text */
            font-size: 42px;
            margin-bottom: 40px;
        }

        .btn-container {
            display: flex;
            gap: 25px;
            justify-content: center;
        }

        .btn {
            background-color: #f48fb1; /* Cherry blossom pink for buttons */
            color: #000000; /* Black text */
            padding: 14px 28px;
            text-decoration: none;
            border-radius: 8px;
            font-size: 18px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 6px rgba(0,0,0,0.2);
        }

        .btn:hover {
            background-color: #ec407a; /* Darker pink on hover */
            color: #ffffff; /* White text on hover for contrast */
            transform: scale(1.05);
        }
    </style>
</head>
<body>

    <div class="content-box">
        <h1>Welcome to the Train Management System</h1>

        <div class="btn-container">
            <a class="btn" href="AdminLogin.jsp">Admin Login</a>
            <a class="btn" href="UserLogin.jsp">User Login</a>
            <a class="btn" href="UserRegister.jsp">Sign Up</a>
        </div>
    </div>

</body>
</html>
