package com.capgemini.truckbooking.bean;

import java.time.LocalDate;

public class BookingBean {

	private int bookingID;
	private String custId;
	private long custMobile;
	private int truckId;
	private int noOfTrucks;
	private LocalDate dateOfTransport;

	public BookingBean() {
		// TODO Auto-generated constructor stub
	}

	public BookingBean(int bookingID, String custId, long custMobile, int truckId, int noOfTrucks,
			LocalDate dateOfTransport) {
		super();
		this.bookingID = bookingID;
		this.custId = custId;
		this.custMobile = custMobile;
		this.truckId = truckId;
		this.noOfTrucks = noOfTrucks;
		this.dateOfTransport = dateOfTransport;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public long getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(long custMobile) {
		this.custMobile = custMobile;
	}

	public int getTruckId() {
		return truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public int getNoOfTrucks() {
		return noOfTrucks;
	}

	public void setNoOfTrucks(int noOfTrucks) {
		this.noOfTrucks = noOfTrucks;
	}

	public LocalDate getDateOfTransport() {
		return dateOfTransport;
	}

	public void setDateOfTransport(LocalDate dateOfTransport) {
		this.dateOfTransport = dateOfTransport;
	}

}
