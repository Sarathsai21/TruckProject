package com.capgemini.truckbooking.service;

import java.util.List;
import java.util.regex.Pattern;

import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.bean.TruckBean;
import com.capgemini.truckbooking.dao.ITruckDao;
import com.capgemini.truckbooking.dao.TruckDao;
import com.capgemini.truckbooking.exceptions.TruckBookingException;

public class TruckService implements ITruckService {
	
	ITruckDao truckDao=new TruckDao();

	@Override
	public boolean validateCustomerId(String customerId) {

		String customerIdRegEx = "[A-Z]{1}[0-9]{6}$";

		return Pattern.matches(customerIdRegEx, customerId);
	}

	@Override
	public boolean validateTruckId(Integer truckId) {
		String truckIdRegEx="[0-9]{4}$";
		
		return Pattern.matches(truckIdRegEx, truckId.toString());
	}

	@Override
	public boolean acceptTruckIdAndNoOfTrucks(Integer truckId, Integer noOfTrucks) throws TruckBookingException {
		
		return truckDao.acceptTruckIdAndNoOfTrucks(truckId, noOfTrucks);
	}

	@Override
	public boolean validateMobileNumber(Long mobileNumber) {
		String mobileNumberRegEx="[6|7|8|9]{1}[0-9]{9}$";
		
		return Pattern.matches(mobileNumberRegEx, mobileNumber.toString());
	}

	@Override
	public Integer doBooking(BookingBean bookingBean) throws TruckBookingException {
		// TODO Auto-generated method stub
		return truckDao.doBooking(bookingBean);
	}

	@Override
	public List<TruckBean> getAllTruckDetails() throws TruckBookingException {
		// TODO Auto-generated method stub
		return truckDao.getAllTruckDetails();
	}

}
