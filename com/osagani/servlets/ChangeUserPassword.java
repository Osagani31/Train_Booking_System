
package com.osagani.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.osagani.constant.UserRole;
import com.osagani.utility.TrainUtil;

//import com.shashi.beans.UserBean;

@SuppressWarnings("serial")
@WebServlet("/changeuserpassword")
public class ChangeUserPassword extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

		RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
		rd.include(req, res);
		pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + TrainUtil.getCurrentUserName(req)
				+ " ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
		pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>View Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>" + "</div>");
		pw.println("<div class='tab'>Password Change</div>");
		pw.println("<div class='tab'>" + "<table><form action='changeuserpwd' method='post'>"
				+ "<tr><td>User Name :</td><td><input type='text' name='username' placeholder='Enter Your MailId'></td></tr>"
				+ "<tr><td>Old Password :</td><td><input type='password' name='oldpassword'></td></tr>"
				+ "<tr><td>New Password :</td><td><input type='password' name='newpassword'></td></tr>"
				+ "<tr><td></td><td><input type='submit' name='submit' value='Change Password'></td></tr>"
				+ "</form></table>" + "</div>");

	}

}





