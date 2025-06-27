
package com.osagani.service;

import java.util.List;

import com.osagani.beans.HistoryBean;
import com.osagani.beans.TrainException;

public interface BookingService {

	public List<HistoryBean> getAllBookingsByCustomerId(String customerEmailId) throws TrainException;

	public HistoryBean createHistory(HistoryBean bookingDetails) throws TrainException;

}