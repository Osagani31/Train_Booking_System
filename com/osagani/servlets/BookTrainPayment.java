package com.osagani.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.osagani.beans.HistoryBean;
import com.osagani.beans.TrainBean;
import com.osagani.beans.TrainException;
import com.osagani.constant.ResponseCode;
import com.osagani.constant.UserRole;
import com.osagani.service.BookingService;
import com.osagani.service.TrainService;
import com.osagani.service.impl.BookingServiceImpl;
import com.osagani.service.impl.TrainServiceImpl;
import com.osagani.utility.TrainUtil;

@WebServlet("/payment")
public class BookTrainPayment extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TrainService trainService = new TrainServiceImpl();
	private BookingService bookingService = new BookingServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

		HttpSession session = req.getSession();

		try {
			// Get payment details
			String cardNumber = req.getParameter("cardNumber");
			String expiryDate = req.getParameter("expiryDate");
			String cvv = req.getParameter("cvv");
			String cardOwner = req.getParameter("cardOwner");
			
			// Get booking details from session
			Integer seat = (Integer) session.getAttribute("seats");
			String trainNo = (String) session.getAttribute("trainnumber");
			String journeyDate = (String) session.getAttribute("journeydate");
			String seatClass = (String) session.getAttribute("class");

			// Null checks for all required attributes
			if (seat == null || trainNo == null || journeyDate == null || seatClass == null) {
				RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu red'>Session expired or invalid booking flow. Please start your booking again.</p1></div>");
				return;
			}

			// Validate payment details (basic validation)
			if (cardNumber == null || cardNumber.trim().isEmpty() || 
				expiryDate == null || expiryDate.trim().isEmpty() ||
				cvv == null || cvv.trim().isEmpty() ||
				cardOwner == null || cardOwner.trim().isEmpty()) {
				
				RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu red'>Please fill all payment details!</p1></div>");
				return;
			}

			// Process payment (simulate payment processing)
			boolean paymentSuccess = processPayment(cardNumber, expiryDate, cvv, cardOwner);
			
			if (paymentSuccess) {
				// Payment successful - create booking
				String userMailId = TrainUtil.getCurrentUserEmail(req);
				
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date utilDate;
				String date = LocalDate.now().toString();
				utilDate = inputFormat.parse(journeyDate);
				date = outputFormat.format(utilDate);

				TrainBean train = trainService.getTrainById(trainNo);

				if (train != null) {
					int avail = train.getSeats();
					if (seat > avail) {
						RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu red'>Only " + avail + " Seats are Available in this Train!</p1></div>");
					} else if (seat <= avail) {
						// Update available seats
						avail = avail - seat;
						train.setSeats(avail);
						String responseCode = trainService.updateTrain(train);
						
						if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(responseCode)) {
							// Create booking history
							HistoryBean bookingDetails = new HistoryBean();
							Double totalAmount = train.getFare() * seat;
							bookingDetails.setAmount(totalAmount);
							bookingDetails.setFrom_stn(train.getFrom_stn());
							bookingDetails.setTo_stn(train.getTo_stn());
							bookingDetails.setTr_no(trainNo);
							bookingDetails.setSeats(seat);
							bookingDetails.setMailId(userMailId);
							bookingDetails.setDate(date);

							HistoryBean transaction = bookingService.createHistory(bookingDetails);
							
							// Build success message
							StringBuilder successMsg = new StringBuilder();
							successMsg.append("<div class='tab'><p class='menu green'>Payment Successful! " + seat + " Seats Booked Successfully!</p></div>");
							successMsg.append("<div class='tab'><p class='menu'>Your Transaction ID is: " + transaction.getTransId() + "</p></div>");
							successMsg.append("<div class='tab'>" + "<p class='menu'>" + "<table>"
									+ "<tr><td>PNR No: </td><td colspan='3' style='color:blue;'>" + transaction.getTransId()
									+ "</td></tr><tr><td>Train Name: </td><td>" + train.getTr_name()
									+ "</td><td>Train No: </td><td>" + transaction.getTr_no()
									+ "</td></tr><tr><td>Booked From: </td><td>" + transaction.getFrom_stn()
									+ "</td><td>To Station: </td><td>" + transaction.getTo_stn() + "</td></tr>"
									+ "<tr><td>Date Of Journey:</td><td>" + transaction.getDate()
									+ "</td><td>Time(HH:MM):</td><td>11:23</td></tr><tr><td>Passengers: </td><td>"
									+ transaction.getSeats() + "</td><td>Class: </td><td>" + seatClass + "</td></tr>"
									+ "<tr><td>Booking Status: </td><td style='color:green;'>CNF/S10/35</td><td>Amount Paid:</td><td>Rs. "
									+ transaction.getAmount() + "</td></tr>" + "</table>" + "</p></div>");

							req.setAttribute("successMsg", successMsg.toString());
							RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
							rd.include(req, res);
						} else {
							RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
							rd.include(req, res);
							pw.println("<div class='tab'><p1 class='menu red'>Transaction Failed. Try Again!</p1></div>");
						}
					}
				} else {
					RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
					rd.include(req, res);
					pw.println("<div class='tab'><p1 class='menu red'>Invalid Train Number!</p1></div>");
				}
			} else {
				// Payment failed
				RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu red'>Payment Failed! Please check your card details and try again.</p1></div>");
			}

		} catch (Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("Payment.jsp");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu red'>Error: " + e.getMessage() + "</p1></div>");
		}

		// Clean up session attributes
		session.removeAttribute("seats");
		session.removeAttribute("trainnumber");
		session.removeAttribute("journeydate");
		session.removeAttribute("class");
	}
	
	// Simulate payment processing
	private boolean processPayment(String cardNumber, String expiryDate, String cvv, String cardOwner) {
		// Basic validation
		if (cardNumber.length() < 13 || cardNumber.length() > 19) {
			return false;
		}
		if (cvv.length() < 3 || cvv.length() > 4) {
			return false;
		}
		if (cardOwner.trim().isEmpty()) {
			return false;
		}
		
		// Simulate payment processing (90% success rate)
		return Math.random() > 0.1;
	}
}



