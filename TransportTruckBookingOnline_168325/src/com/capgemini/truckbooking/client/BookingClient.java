package com.capgemini.truckbooking.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.bean.TruckBean;
import com.capgemini.truckbooking.exceptions.TruckBookingException;
import com.capgemini.truckbooking.service.ITruckService;
import com.capgemini.truckbooking.service.TruckService;

public class BookingClient {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		
		int choice=0;
		boolean flag=true;
		do {
			System.out.println("**********Transport Truck Booking Online*********");
			System.out.println("1. Book Trucks");
			System.out.println("2. Exit");

			System.out.println("Enter your choice");
		try {
		 choice = scanner.nextInt();

		switch (choice) {
		case 1:
			DateTimeFormatter formatter = null;
			boolean dateFlag = false;
			LocalDate date = null;
			ITruckService service = new TruckService();
			List<TruckBean> list = new ArrayList<TruckBean>();
			try {
				list = service.getAllTruckDetails();
				System.out.println("TruckId" + "    " + "TruckType" + "   " + "Origin" + "    " + "Destination" + "    "
						+ "Charge" + "    " + "Available Nos");
				for (TruckBean bean : list) {

					System.out.println(bean.getTruckID() + "      " + bean.getTruckType() + "    " + bean.getOrigin()
							+ "     " + bean.getDestination() + "   " + bean.getCharges() + "   "
							+ bean.getAvailableNos());
				}

			} catch (TruckBookingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			scanner.nextLine();
			System.out.println("Enter customer Id:");
			String customerId = scanner.nextLine();

			boolean customerIdFlag = service.validateCustomerId(customerId);
			if (customerIdFlag) {
				System.out.println("Enter the truck Id:");
				Integer truckId = scanner.nextInt();
				System.out.println("Enter number of truck to be booked");
				Integer noOfTrucks = scanner.nextInt();
				if (noOfTrucks > 0) {
					boolean truckIdFlag = service.validateTruckId(truckId);
					if (truckIdFlag) {

						try {
							boolean acceptTruckFlag = service.acceptTruckIdAndNoOfTrucks(truckId, noOfTrucks);
							System.out.println(acceptTruckFlag);
							if (acceptTruckFlag) {

								System.out.println("Enter mobile number:");
								Long mobileNumber = scanner.nextLong();
								scanner.nextLine();
								System.out.println("Enter the date of transportation in the format of 'yyyy-MM-dd':");
								String dateOfTransportation = scanner.nextLine();
								formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
								try {
									date = LocalDate.parse(dateOfTransportation, formatter);
									dateFlag = true;
								} catch (DateTimeParseException e) {
									dateFlag = false;
									System.err.println(
											"Date is not in the given format, date should be in the format of 'yyyy-mm-dd':");

								}

								if (dateFlag) {
									boolean mobileNumberFlag = service.validateMobileNumber(mobileNumber);
									if (mobileNumberFlag) {

										BookingBean bookingBean = new BookingBean();
										bookingBean.setCustId(customerId);
										bookingBean.setCustMobile(mobileNumber);
										bookingBean.setDateOfTransport(date);
										bookingBean.setNoOfTrucks(noOfTrucks);
										bookingBean.setTruckId(truckId);

										Integer bookingId = service.doBooking(bookingBean);
										System.out.println("Thank you. your booking id is :" + bookingId);

									} else {
										System.err.println(
												"mobile number should containt 10 digits and should start with either 6|7|8|9");
									}
								}

							} else {
								System.out.println(
										"Your given number truck id is either not present or number of trucks are less compared to your requirement");
							}

						} catch (TruckBookingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						System.out.println("please enter valid truck id which consists of 4 digits");
					}

				} else {
					System.out.println("Required number of trucks should be greater than 0");
				}
			} else {
				System.out.println("Your customer id should start with an uppercase followed by 6 digits");
			}
             flag=false;
			break;
		case 2:
			System.out.println("You are exited from the switch case");
			flag=false;
			break;
		default:flag=true;
			break;
		}
		}catch(InputMismatchException e) {
			System.out.println("you should wnter only digits");
		}
		}while(flag==true);
	}

}
