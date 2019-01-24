package com.capgemini.truckbooking.dao;

import java.util.List;

import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.bean.TruckBean;
import com.capgemini.truckbooking.exceptions.TruckBookingException;

public interface ITruckDao {

	boolean acceptTruckIdAndNoOfTrucks(Integer truckId, Integer noOfTrucks) throws TruckBookingException;

	Integer doBooking(BookingBean bookingBean) throws TruckBookingException;

	List<TruckBean> getAllTruckDetails() throws TruckBookingException;

}
