package com.capgemini.truckbooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.bean.TruckBean;
import com.capgemini.truckbooking.exceptions.TruckBookingException;
import com.capgemini.truckbooking.utility.JdbcUtility;

public class TruckDao implements ITruckDao {

	Connection connection = null;
	PreparedStatement prepareStament = null;
	ResultSet resultSet = null;
	static Logger logger = Logger.getLogger(TruckDao.class);

	/**
	 *            method      : acceptTruckIdAndNoOfTrucks 
	 *            argument    : it's taking truckId and noOftrucks as an argument 
	 *            return type : this method returns the boolean flag
	 *            Author      : Capgemini 
	 *            Date        : 21-Jan-2019
	 * 
	 */
	@Override
	public boolean acceptTruckIdAndNoOfTrucks(Integer truckId, Integer noOfTrucks) throws TruckBookingException {

		connection = JdbcUtility.getConnection();
		logger.info("connected to the database");
		boolean truckIdPresentFlag = false;

		try {
			prepareStament = connection.prepareStatement(QueryMapper.checkTruckId);
			logger.info("Getting information from the query");

			resultSet = prepareStament.executeQuery();
			logger.info("Storing it to result set");
			while (resultSet.next()) {

				Integer truckIdData = resultSet.getInt("truckId");
				if (truckId.equals(truckIdData)) {
					logger.info("truck id as been matched");
					Integer noOfTrucksData = resultSet.getInt("availableNos");
					logger.info("no of trucks as been stored into variable");

					if (noOfTrucksData > noOfTrucks) {
						logger.info("no of trucks required is less than the total no of trucks");
						truckIdPresentFlag = true;
					} else {
						System.out.println("No of trucks present are less than the required number of trucks");
						logger.info("no of trucks present are less than the required");
					}

				}
			}

		} catch (SQLException e) {
			logger.error("prepare statement has not been created");
			throw new TruckBookingException("Prepare statement has not been created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("Result set has not been closed");
				throw new TruckBookingException("Result set has not been closed");
			}
			try {
				prepareStament.close();
			} catch (SQLException e) {
				logger.error("prepare statement has not been closed");
				throw new TruckBookingException("Prepare statement has not been closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Connection has not been closed");
				throw new TruckBookingException("Connection has not been closed");
			}
		}

		return truckIdPresentFlag;
	}

	/**
	 *              method      : doBooking 
	 *              argument    : it's taking model object as an argument 
	 *              return type : this method returns the generated id to the user
	 *              Author      : Capgemini
	 *              Date        : 21-Jan-2019
	 * 
	 */
	@Override
	public Integer doBooking(BookingBean bookingBean) throws TruckBookingException {

		connection = JdbcUtility.getConnection();
		Date date = Date.valueOf(bookingBean.getDateOfTransport());
		logger.info("date as been converted to sql date");
		Integer bookingId;

		try {
			prepareStament = connection.prepareStatement(QueryMapper.insertIntoBookingDetails);
			logger.info("prepare statement as been called");
			prepareStament.setString(1, bookingBean.getCustId());
			prepareStament.setLong(2, bookingBean.getCustMobile());
			prepareStament.setInt(3, bookingBean.getTruckId());
			prepareStament.setInt(4, bookingBean.getNoOfTrucks());
			prepareStament.setDate(5, date);
			prepareStament.executeUpdate();
			logger.info("Data as been inseted into the booking details");

			prepareStament = connection.prepareStatement(QueryMapper.updateTruckDetails);
			logger.info("prepare statement as been called to update number of available trucks");
			prepareStament.setInt(1, bookingBean.getNoOfTrucks());
			prepareStament.setInt(2, bookingBean.getTruckId());
			prepareStament.executeUpdate();
			logger.info("database table as been updated");

			prepareStament = connection.prepareStatement(QueryMapper.getBookingId);
			logger.info("prepare statement as been called for generating id");
			resultSet = prepareStament.executeQuery();

			resultSet.next();
			bookingId = resultSet.getInt(1);
			logger.info("booking is as been returned which is:" + bookingId);

		} catch (SQLException e) {
			logger.error("prepare statement has not been created");
			throw new TruckBookingException("Prepare statement has not been created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("Result set has not been closed");
				throw new TruckBookingException("Result set has not been closed");
			}
			try {
				prepareStament.close();
			} catch (SQLException e) {
				logger.error("prepare statement has not been closed");
				throw new TruckBookingException("Prepare statement has not been closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("connection has not been closed");
				throw new TruckBookingException("Connection has not been closed");
			}
		}

		return bookingId;
	}

	/**
	 *            method      : getAllTruckDetails 
	 *            argument    : it doesn't take any argument 
	 *            return type : this method returns the list of the trucks present 
	 *            Author      : Capgemini
	 *            Date        : 21-Jan-2019
	 * 
	 */
	@Override
	public List<TruckBean> getAllTruckDetails() throws TruckBookingException {
		
		

		connection = JdbcUtility.getConnection();
		 
		List<TruckBean> list = new ArrayList<>();
		logger.info("list of truckBean object as been created");

		try {
			prepareStament = connection.prepareStatement(QueryMapper.selectAllTruckDetails);
			logger.info("prepare statement as been created for the selecting of all trucks");
			resultSet = prepareStament.executeQuery();
			logger.info("it has been stored into the resultset");
			while (resultSet.next()) {

				int truckId = resultSet.getInt("truckId");
				String truckType = resultSet.getString("truckType");
				String origin = resultSet.getString("origin");
				String destination = resultSet.getString("destination");
				float charges = resultSet.getFloat("charges");
				int availableNos = resultSet.getInt("availableNos");
				logger.info("all the data as been taken from the database in this row");
				TruckBean truckBean = new TruckBean();
				truckBean.setAvailableNos(availableNos);
				truckBean.setCharges(charges);
				truckBean.setDestination(destination);
				truckBean.setOrigin(origin);
				truckBean.setTruckID(truckId);
				truckBean.setTruckType(truckType);
				logger.info("the data is initiated to the present object");
				list.add(truckBean);
				logger.info("The whole date is being inserted into the list of truckBean type");

			}

		} catch (SQLException e) {
			logger.error("prepare statement has not been created");
			throw new TruckBookingException("prepare statement not created");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("Result set has not been closed");
				throw new TruckBookingException("Result set has not been closed");
			}
			try {
				prepareStament.close();
			} catch (SQLException e) {
				logger.error("prepare statement has not been closed");
				throw new TruckBookingException("Prepare statement has not been closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("connection has not been closed");
				throw new TruckBookingException("Connection has not been closed");
			}
		}

		return list;
	}

}
