package com.capgemini.truckbooking.dao.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.bean.TruckBean;
import com.capgemini.truckbooking.dao.ITruckDao;
import com.capgemini.truckbooking.dao.TruckDao;
import com.capgemini.truckbooking.exceptions.TruckBookingException;

public class TruckDaoTest {

	ITruckDao truckDao=null;
	
	@Before
	public void setUp() throws Exception {
		truckDao=new TruckDao();
	}

	@After
	public void tearDown() throws Exception {
		truckDao=null;
	}

	@Test
	public void testAcceptTruckIdAndNoOfTrucks() {
		
		BookingBean bookingBean=new BookingBean();
		bookingBean.setTruckId(1003);
		bookingBean.setNoOfTrucks(1);
		
		try {
			boolean resultFlag=truckDao.acceptTruckIdAndNoOfTrucks(bookingBean.getTruckId(), bookingBean.getNoOfTrucks());
			assertEquals(true, resultFlag);
			
		} catch (TruckBookingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Test
	public void testDoBooking() {
		
		BookingBean bookingBean=new BookingBean();
	
		bookingBean.setCustId("B111111");
		bookingBean.setCustMobile(9879879870l);
		String date1="2019-08-14";
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date=LocalDate.parse(date1, formatter);
		bookingBean.setDateOfTransport(date);
		bookingBean.setNoOfTrucks(1);
		bookingBean.setTruckId(1003);
		
		try {
			Integer bookingIdTest=truckDao.doBooking(bookingBean);
			assertNotNull(bookingIdTest);
		} catch (TruckBookingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Test
	public void testGetAllTruckDetails() {
		
		List<TruckBean> list=new ArrayList<>();
		try {
			list=truckDao.getAllTruckDetails();
			assertEquals(8, list.size());
		} catch (TruckBookingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
