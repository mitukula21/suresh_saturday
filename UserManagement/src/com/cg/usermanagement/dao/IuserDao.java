package com.cg.usermanagement.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.usermanagement.dto.User;
import com.cg.usermanagement.exceptions.UMSException;

public interface IuserDao {
	public List<User> UserList() throws SQLException, UMSException;

	public void editUser(User detail) throws SQLException, UMSException;

	public void deleteUser(User detail) throws SQLException, UMSException;

	public int newUser(User details) throws SQLException,UMSException;

	public boolean logincheck(User details) throws SQLException,UMSException;

}
