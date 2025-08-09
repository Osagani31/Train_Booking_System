package com.osagani.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.osagani.constant.ResponseCode;
import com.osagani.constant.UserRole;
import com.osagani.utility.TrainUtil;

@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");

		String responseMsg = TrainUtil.login(req, res, UserRole.CUSTOMER, uName, pWord);
		if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(responseMsg)) {
			res.sendRedirect("UserHome.jsp");
			return;
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.jsp");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>" + responseMsg + "</p1></div>");
		}
	}

}



