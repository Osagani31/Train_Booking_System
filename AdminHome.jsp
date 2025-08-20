<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>E-Trains</title>
    <link rel="stylesheet" href="Css/UserHome.css">
    <style>
        body {
            background: linear-gradient(135deg, #f8fafc 0%, #e3e9f0 100%);
            min-height: 100vh;
            color: #222;
            font-family: 'Segoe UI', sans-serif;
        }

        /* Dashboard Card Container */
        .dashboard {
            display: flex;
            justify-content: center;
            gap: 30px;
            flex-wrap: wrap;
            padding: 40px 20px;
        }

        /* Individual Card Styling */
        .card {
            background-color: #fce4ec;
            border: 2px solid #f8bbd0;
            border-radius: 12px;
            padding: 25px 30px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
            min-width: 260px;
            flex: 1;
            max-width: 300px;
            transition: 0.3s ease;
        }

        .card h3 {
            font-size: 20px;
            color: #000000;
            margin-bottom: 10px;
        }

        .card p {
            font-size: 15px;
            color: #000000;
            line-height: 1.6;
        }

        .card:hover {
            transform: translateY(-5px);
            background-color: #f8bbd0;
        }

        /* Blossom Footer (Optional: can reuse if needed) */
        footer {
            background-color: #fce4ec;
            color: #000000;
            text-align: center;
            padding: 16px 0;
            font-size: 15px;
            border-top: 2px solid #f8bbd0;
            box-shadow: 0 -4px 12px rgba(0, 0, 0, 0.05);
        }
    </style>
</head>
<body>
    <header>
        <h1 class="hd">National Ticket Booking Spot</h1>
        <div class="home"><p class="menu"><a href="AdminHome.jsp">Home</a></p></div>
        <div class="home"><p class="menu"><a href="adminviewtrainfwd">View Trains</a></p></div>
        <div class="home"><p class="menu"><a href="adminsearchtrainfwd">Search By TrainNo</a></p></div>
        <div class="home"><p class="menu"><a href="addtrainfwd">Add Train</a></p></div>
        <div class="home"><p class="menu"><a href="cancletrainfwd">Delete Train</a></p></div>
        <div class="home"><p class="menu"><a href="updatetrain">Update Train Details</a></p></div>
        <div class="home"><p class="menu"><a href="adminlogout">Logout</a></p></div>
    </header>

    <div class="tab">
        <p class="menu">Hey, Admin! Welcome to our new NITRTC Website</p>
    </div>

    <!-- Instructional Dashboard Cards -->
    <div class="dashboard">
        <!-- Welcome Card -->
        <div class="card">
            <h3>Welcome, Admin!</h3>
            <p>Glad to have you onboard ?</p>
            <p>Use the top menu to manage trains and view bookings.</p>
        </div>

        <!-- Add Train Card -->
        <div class="card">
            <h3>Add New Trains</h3>
            <p>Click "Add Train" to register a new train.</p>
            <p>Ensure all train details are accurate before submitting.</p>
        </div>

        <!-- Manage Card -->
        <div class="card">
            <h3>Manage Trains</h3>
            <p>Use "Search", "Update", or "Delete" to handle existing trains.</p>
            <p>Always double-check updates before confirming.</p>
        </div>
    </div>
</body>
</html>
