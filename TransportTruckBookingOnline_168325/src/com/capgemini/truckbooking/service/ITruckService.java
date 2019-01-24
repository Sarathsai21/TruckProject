package com.capgemini.truckbooking.service;

import java.util.List;

import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.bean.TruckBean;
import com.capgemini.truckbooking.exceptions.TruckBookingException;

public interface ITruckService {

	boolean validateCustomerId(String customerId);

	boolean validateTruckId(Integer truckId);

	boolean acceptTruckIdAndNoOfTrucks(Integer truckId, Integer noOfTrucks) throws TruckBookingException;

	boolean validateMobileNumber(Long mobileNumber);

	Integer doBooking(BookingBean bookingBean) throws TruckBookingException;

	List<TruckBean> getAllTruckDetails() throws TruckBookingException;

}
