<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>E-Trains - Admin Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #fefefe;
        }

        header {
            width: 100%;
            background-color: #fce4ec; /* Off pink */
            padding: 15px 20px; /* Reduced right/left padding */
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        /* Container for centering content */
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
            color: #ec407a; /* Blossom accent */
            font-weight: bold;
            font-size: 16px;
            transition: color 0.3s ease;
        }

        nav a:hover {
            color: #ad1457;
        }

        /* Login container */
        .login-container {
            margin: 80px auto;
            background-color: #fff;
            border: 2px solid #f8bbd0;
            border-radius: 10px;
            padding: 40px 50px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            max-width: 400px;
        }

        .login-container h2 {
            margin-top: 0;
            margin-bottom: 30px;
            font-size: 26px;
            color: #333333;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 16px;
            color: #333333;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
        }

        input[type="submit"] {
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

        input[type="submit"]:hover {
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

    <div class="login-container">
        <h2>Admin Login</h2>
        <form action="adminlogin" method="post">
            <label for="uname">UserName</label>
            <input type="text" id="uname" name="uname" placeholder="Enter your email">

            <label for="pword">Password</label>
            <input type="password" id="pword" name="pword" placeholder="Enter your password">

            <input type="submit" value="Login">
        </form>
    </div>

</body>
</html>
