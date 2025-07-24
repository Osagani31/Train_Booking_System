<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login - Train Booking</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #fefefe;
        }

        header {
            width: 100%;
            background-color: #fce4ec;
            padding: 15px 20px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .header-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            font-size: 28px;
            font-weight: bold;
            color: #333333;
        }

        nav {
            display: flex;
            gap: 20px;
            flex: 1;
            justify-content: flex-end;
        }

        nav a {
            text-decoration: none;
            color: #ec407a;
            font-weight: bold;
            font-size: 16px;
            transition: color 0.3s ease;
        }

        nav a:hover {
            color: #ad1457;
        }

        .login-card {
            margin: 80px auto;
            background-color: #ffffff;
            border: 2px solid #f8bbd0;
            border-radius: 12px;
            padding: 40px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            text-align: center;
        }

        .login-title {
            font-size: 26px;
            font-weight: bold;
            color: #ad1457;
            margin-bottom: 10px;
        }

        .login-subtitle {
            font-size: 14px;
            color: #555555;
            margin-bottom: 30px;
        }

        form input[type="text"],
        form input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
        }

        form input[type="submit"] {
            width: 100%;
            background-color: #f48fb1;
            color: #000000;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        form input[type="submit"]:hover {
            background-color: #ec407a;
            color: #ffffff;
        }
    </style>
</head>
<body>

    <header>
        <div class="header-container">
            <div class="logo">National Ticket Booking Spot</div>
            <nav>
                <a href="UserLogin.jsp">Login as User</a>
                <a href="UserRegister.jsp">New User Register</a>
                <a href="AdminLogin.jsp">Login as Admin</a>
            </nav>
        </div>
    </header>

    <div class="login-card">
        <div class="login-title">User Login</div>
        <div class="login-subtitle">Please enter your credentials to access your account</div>
        <form action="userlogin" method="post">
            <input type="text" name="uname" placeholder="User Name" required>
            <input type="password" name="pword" placeholder="Password" required>
            <input type="submit" value="LOGIN">
        </form>
        <div style="margin-top:18px;">
            <jsp:include page="error.jsp"/>
        </div>
    </div>

</body>
</html>
