<%@ page import="com.osagani.utility.DBUtil,java.sql.Connection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database Connection Status</title>
    <link rel="stylesheet" href="Css/UserHome.css">
    <style>
        .status {
            margin: 40px auto;
            max-width: 400px;
            padding: 32px;
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 4px 24px rgba(44,62,80,0.08);
            text-align: center;
        }
        .success { color: #27ae60; font-weight: bold; }
        .fail { color: #e74c3c; font-weight: bold; }
    </style>
</head>
<body>
    <div class="status">
        <h2>Database Connection Status</h2>
        <hr/>
        <%
            try {
                Connection con = DBUtil.getConnection();
                if (con != null && !con.isClosed()) {
        %>
            <div class="success">Connected to the database successfully!</div>
        <%
                } else {
        %>
            <div class="fail">Failed to connect to the database.</div>
        <%
                }
            } catch (Exception e) {
        %>
            <div class="fail">Error: <%= e.getMessage() %></div>
        <%
            }
        %>
    </div>
</body>
</html> 