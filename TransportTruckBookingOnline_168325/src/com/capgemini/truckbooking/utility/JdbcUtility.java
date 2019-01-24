package com.capgemini.truckbooking.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.capgemini.truckbooking.exceptions.TruckBookingException;

public class JdbcUtility {

	static Logger logger = Logger.getLogger(JdbcUtility.class);
	private static Connection connection = null;

	public static Connection getConnection() throws TruckBookingException {

		File file = null;
		FileInputStream inputStream = null;

		file = new File("resources/jdbc.properties");
		logger.info("file has been inserted");
		try {
			inputStream = new FileInputStream(file);
			logger.info("file has been found");
		} catch (FileNotFoundException e1) {
			logger.error("file not found exception");
			throw new TruckBookingException("file not found");

		}

		Properties properties = new Properties();
		logger.info("properties object has been created ");
		try {
			properties.load(inputStream);
			logger.info("loaded in properties file");
			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			logger.info("class has been loaded");
			connection = DriverManager.getConnection(url, username, password);
			logger.info("connected to the database");

		} catch (IOException e1) {
			logger.error("not able to load the file");
			throw new TruckBookingException("unable to load the file");
		} catch (ClassNotFoundException e) {
			logger.error("class as been not loaded");
			throw new TruckBookingException("class not loaded..");
		} catch (SQLException e) {
			logger.error("not connected to the database");

			throw new TruckBookingException("not connected..");
		}

		return connection;
	}
}
