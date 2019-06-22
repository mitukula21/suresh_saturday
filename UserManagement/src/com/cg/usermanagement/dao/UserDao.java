package com.cg.usermanagement.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.usermanagement.connection.dbconnection;
import com.cg.usermanagement.dto.User;
import com.cg.usermanagement.exceptions.UMSException;

public class UserDao implements IuserDao {

	Logger logger = Logger.getRootLogger();

	public UserDao() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	@SuppressWarnings("static-access")
	public List<User> UserList() throws SQLException, UMSException {
		// TODO Auto-generated method stub
		Connection connection = null;
         List<User> list=new ArrayList();
       
         


		connection = dbconnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		preparedStatement = connection.prepareStatement(QueryMapper.RETRIVE_ALL_QUERY);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			  User user=new User();
			user.setID(resultSet.getInt(2));
			user.setEmail(resultSet.getString(3));
			user.setPassword(resultSet.getString(5));
			user.setFullname(resultSet.getString(4));
			list.add(user);
			
		}
		return list;
	}

	@SuppressWarnings("static-access")
	public void editUser(User detail) throws SQLException, UMSException {
		// TODO Auto-generated method stub

		User bean = new User();
		bean = detail;
		Connection connection = null;

		dbconnection dbconnectioncon = new dbconnection();

		connection = dbconnectioncon.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(QueryMapper.EDIT_QUERY);
		preparedStatement.setString(1, bean.getEmail());
		preparedStatement.setString(2, bean.getFullname());
		preparedStatement.setString(3, bean.getPassword());
		preparedStatement.setInt(4, bean.getID());
		int queryResult = preparedStatement.executeUpdate();

		if (queryResult == 0) {
			logger.error("Error in editing user details");
		} else {
			logger.info("The User details are edited succesfully");
		}

	}

	@SuppressWarnings("static-access")
	public void deleteUser(User detail) throws SQLException, UMSException {
		// TODO Auto-generated method stub

		User bean = new User();
		bean = detail;
		Connection connection = null;
		int queryResult;

		connection = dbconnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		preparedStatement = connection.prepareStatement(QueryMapper.DELETE_QUERY);
		preparedStatement.setInt(1, bean.getID());
		queryResult = preparedStatement.executeUpdate();
		if (queryResult == 1) {
			logger.info("The user details deleted successfully");
			System.out.println("The user is successfully with ID: " + bean.getID());
		} else {
			logger.info("Error in deleting user details");
		}

	}

	@SuppressWarnings("static-access")
	public int newUser(User details) throws SQLException, UMSException {
		
		User bean = new User();
		bean = details;
		Connection connection = null;
		ResultSet resultSet = null;
		int userId=0;
try {
		connection = dbconnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(QueryMapper.INSERT_QUERY);
		preparedStatement.setString(1, bean.getEmail());
		preparedStatement.setString(2, bean.getFullname());
		preparedStatement.setString(3, bean.getPassword());

		int queryresult = preparedStatement.executeUpdate();

	
		if (queryresult == 0) {
			logger.error("Erron in creating new user");
		} else {
			preparedStatement = null;
			preparedStatement = connection.prepareStatement(QueryMapper.SEQUENCE_ID_QUERY);
			preparedStatement.setString(1, bean.getEmail());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
			userId= resultSet.getInt(1);
				logger.info("The user details successfully added with id: " + resultSet.getDouble(1));
			}
		}
	}catch(SQLException e)
	{
		
		System.out.println("Enter the other email");
	}
return userId;
	}

	@SuppressWarnings("static-access")
	public boolean logincheck(User details) throws SQLException, UMSException {
		

		boolean admincheckflag = false;
		User bean = new User();
		bean = details;
		Connection connection = null;
		connection = dbconnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(QueryMapper.ADMIN_CHECK_QUERY);
		preparedStatement.setString(1, bean.getadmin_email());
		preparedStatement.setString(2, bean.getAdminpassword());
		int i = preparedStatement.executeUpdate();

		if (i == 1) {
			admincheckflag = true;
		}
		if (i != 1) {
			logger.error("Admin login failed");
			admincheckflag = false;
		}
		return admincheckflag;
	}

	@SuppressWarnings("static-access")
	public boolean IDverification(User bean) throws UMSException, SQLException {
		boolean idverificationflag = true;

		Connection connection = null;

		connection = dbconnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(QueryMapper.ID_CHECK_QUERY);
		preparedStatement.setInt(1, bean.getID());

		int i = preparedStatement.executeUpdate();
		if (i == 0) {
			idverificationflag = false;
			
		}

		return idverificationflag;
	}

}
