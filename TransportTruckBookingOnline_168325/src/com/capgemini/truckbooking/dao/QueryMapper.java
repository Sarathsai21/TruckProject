package com.capgemini.truckbooking.dao;

public interface QueryMapper {

	public static final String checkTruckId = "select truckId,availableNos from truckdetails";

	public static final String insertIntoBookingDetails = "insert into bookingdetails values (booking_id_seq.nextval,?,?,?,?,?)";

	public static final String getBookingId = "select booking_id_seq.currval from dual";

	public static final String updateTruckDetails = "update truckdetails set availableNos=availableNos-? where truckId=?";

	public static final String selectAllTruckDetails = "select * from truckdetails";

}
