
package com.osagani.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.osagani.beans.TrainBean;
import com.osagani.beans.TrainException;
import com.osagani.constant.UserRole;
import com.osagani.service.TrainService;
import com.osagani.service.impl.TrainServiceImpl;
import com.osagani.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/adminsearchtrain")
public class AdminSearchTrain extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		try {
			String trainNo = req.getParameter("trainnumber");
			TrainBean train = trainService.getTrainById(trainNo);
			if (train != null) {
				RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.jsp");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Searched Train Detail</p1></div>");
				pw.println("<div class='tab'>" + "<table>" + "<tr><td class='blue'>Train Name :</td><td>"
						+ train.getTr_name() + "</td></tr>" + "<tr><td class='blue'>Train Number :</td><td>"
						+ train.getTr_no() + "</td></tr>" + "<tr><td class='blue'>From Station :</td><td>"
						+ train.getFrom_stn() + "</td></tr>" + "<tr><td class='blue'>To Station :</td><td>"
						+ train.getTo_stn() + "</td></tr>" + "<tr><td class='blue'>Available Seats:</td><td>"
						+ train.getSeats() + "</td></tr>" + "<tr><td class='blue'>Fare (INR) :</td><td>"
						+ train.getFare() + " RS</td></tr>" + "</table>" + "</div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Train No." + trainNo + " is Not Available !</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}

	}

}

