package com.cg.usermanagement.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.usermanagement.exceptions.UMSException;

import oracle.jdbc.pool.OracleDataSource;



public class dbconnection {
	private static Connection conn = null;
	private static dbconnection instance = null;
	private static Properties props = null;
	private static OracleDataSource dataSource = null;


	/*************************************************************************************
	 *  - @throws DonorException
	 *  - Private Constructor
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 18/11/2016
	 *  - Desc:Loads the  jdbc.properties file and Driver Class and gets the connection
	 ***************************************************************************************/
	public dbconnection() throws UMSException {
		try {
			props = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			throw new UMSException(
					" Could not read the database details from properties file ");
		} catch (SQLException e) {
			throw new UMSException(e.getMessage());
		}

	}

	/*****************************************************************
	 *  - Method Name:getInstance() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance
	 *  - Throws : DonorException 
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 18/11/2016
	 *  - Description : Singleton and Thread safe class
	 *******************************************************************/
	
	public static dbconnection getInstance() throws UMSException {
		synchronized (dbconnection.class) {
			if (instance == null) {
				instance = new dbconnection();
			}
		}
		return instance;
	}
	
	/*****************************************************************
	 *  - Method Name:getConnection() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance
	 *  - Throws : DonorException 
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 18/11/2016
	 *  - Description :  Returns connection object
	 *******************************************************************/
	public static Connection getConnection() throws UMSException {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {
			
			throw new UMSException(" Database connection problem");
		}
		return conn;
	}
	
	/*****************************************************************
	 *  - Method Name:loadProperties()
	 *  - Input Parameters : 
	 *  - Return Type :Properties object
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 18/11/2016
	 *  - Description : Returns Properties object
	 *******************************************************************/
	
	private Properties loadProperties() throws IOException {

		if (props == null) {
			Properties newProps = new Properties();
			String fileName = "resources/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);

			inputStream.close();

			return newProps;
		} else {
			return props;
		}
	}


	/*****************************************************************
	 *  - Method Name:prepareDataSource() 
	 *  - Input Parameters : 
	 *  - Return Type :OracleDataSource object
	 *  - Author : CAPGEMINI 
	 *  - Creation Date : 18/11/2016
	 *  - Description : Returns OracleDataSource object
	 *******************************************************************/
	
	private OracleDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (props != null) {
				String connectionURL = props.getProperty("dburl");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				dataSource = new OracleDataSource();

				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}

}
