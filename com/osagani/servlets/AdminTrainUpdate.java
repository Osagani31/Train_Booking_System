
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
import com.osagani.service.TrainService;
import com.osagani.service.impl.TrainServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/adminupdatetrain")
public class AdminTrainUpdate extends HttpServlet {

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

		try {
			String trainNo = req.getParameter("trainnumber");
			TrainBean train = trainService.getTrainById(trainNo);
			if (train != null) {
				RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'>Train Schedule Update</div>");
				pw.println("<div class='tab'>" + "<table><form action='updatetrainschedule' method='post'>"
						+ "<tr><td>Train No :</td><td><input type='text' name='trainno' value='" + train.getTr_no()
						+ "'></td></tr>" + "<tr><td>Train Name :</td><td><input type='text' name='trainname' value='"
						+ train.getTr_name() + "'></td></tr>"
						+ "<tr><td>From Station :</td><td><input type='text' name='fromstation' value='"
						+ train.getFrom_stn() + "'></td></tr>"
						+ "<tr><td>To Station :</td><td><input type='text' name='tostation' value='" + train.getTo_stn()
						+ "'></td></tr>"
						+ "<tr><td>Available seats:</td><td><input type='text' name='available' value='"
						+ train.getSeats() + "'></td></tr>"
						+ "<tr><td>Fare (INR) :</td><td><input type='text' name='fare' value='" + train.getFare()
						+ "'></td></tr>"
						+ "<tr><td></td><td><input type='submit' name='submit' value='Update Train Schedule'></td></tr>"
						+ "</form></table>" + "</div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'>Train Not Available</div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());

		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}

}
