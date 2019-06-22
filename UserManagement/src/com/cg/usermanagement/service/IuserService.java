package com.cg.usermanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.usermanagement.dto.User;
import com.cg.usermanagement.exceptions.UMSException;

public interface IuserService {

	public int newUser(User details) throws SQLException,UMSException;

	public List<User> userList() throws SQLException,UMSException;

	public void editUser(User details) throws SQLException,UMSException;

	public boolean emailpattern(User details);

	public boolean fullnamepattern(User details);

	public void deleteUser(User details) throws SQLException,UMSException;

	public boolean logincheck(User details) throws SQLException,UMSException;

	public boolean passwordcheck(User details);
}
