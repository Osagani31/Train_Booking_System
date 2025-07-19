
package com.osagani.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.osagani.beans.TrainException;
import com.osagani.beans.UserBean;
import com.osagani.constant.UserRole;
import com.osagani.service.UserService;
import com.osagani.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/userreg")
public class UserRegServlet extends HttpServlet {

	private UserService userService = new UserServiceImpl(UserRole.CUSTOMER);

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		try {
			// Get form parameters
			String mailId = req.getParameter("mailid");
			String password = req.getParameter("pword");
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String address = req.getParameter("address");
			String phoneNo = req.getParameter("phoneno");
			
			// Validate required fields
			if (mailId == null || mailId.trim().isEmpty() ||
				password == null || password.trim().isEmpty() ||
				firstName == null || firstName.trim().isEmpty() ||
				lastName == null || lastName.trim().isEmpty() ||
				address == null || address.trim().isEmpty() ||
				phoneNo == null || phoneNo.trim().isEmpty()) {
				
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu' style='color: red;'>All fields are required!</p1></div>");
				return;
			}
			
			// Validate email format
			if (!mailId.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu' style='color: red;'>Please enter a valid email address!</p1></div>");
				return;
			}
			
			// Validate phone number
			try {
				Long.parseLong(phoneNo);
			} catch (NumberFormatException e) {
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu' style='color: red;'>Please enter a valid phone number!</p1></div>");
				return;
			}
			
			// Create UserBean object
			UserBean user = new UserBean();
			user.setMailId(mailId.trim());
			user.setPWord(password);
			user.setFName(firstName.trim());
			user.setLName(lastName.trim());
			user.setAddr(address.trim());
			user.setPhNo(Long.parseLong(phoneNo.trim()));

			System.out.println("Attempting to register user: " + mailId);
			
			// Register user
			String message = userService.registerUser(user);
			System.out.println("Registration result: " + message);
			
			if ("SUCCESS".equalsIgnoreCase(message)) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu' style='color: green; font-weight: bold;'>User Registered Successfully! Please login with your credentials.</p1></div>");
			} else {
				System.err.println("Registration failed for user: " + mailId + ". Reason: " + message);
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
				rd.include(req, res);
				String userMessage = message.contains(":") ? message.substring(message.indexOf(":") + 1).trim() : message;
				pw.println("<div class='tab'><p1 class='menu' style='color: red;'>Registration failed: " + userMessage + "</p1></div>");
			}

		} catch (Exception e) {
			System.err.println("Registration error: " + e.getMessage());
			e.printStackTrace();
			
			RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu' style='color: red;'>Registration failed due to system error. Please try again.</p1></div>");
		}
	}
}
