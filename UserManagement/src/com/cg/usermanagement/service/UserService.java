package com.cg.usermanagement.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.PropertyConfigurator;

import com.cg.usermanagement.dao.UserDao;
import com.cg.usermanagement.dto.User;
import com.cg.usermanagement.exceptions.UMSException;

public class UserService implements IuserService {

	public UserService() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	@Override
	public int newUser(User details) throws SQLException, UMSException {

		UserDao dao = new UserDao();
		return dao.newUser(details);
	}

	@Override
	public List<User> userList() throws SQLException, UMSException {

		UserDao dao = new UserDao();
		return dao.UserList();

	}

	public void editUser(User details) throws SQLException, UMSException {

		User detail = new User();
		detail = details;
		UserDao dao = new UserDao();
		dao.editUser(detail);
	}

	public boolean emailpattern(User details) {
		boolean emailcheck = false;
		String emailpattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email = details.getadmin_email();
		if (email.matches(emailpattern)) {
			emailcheck = true;
		} else {
			System.err.println("Email pattern is wrong");
		}
		return emailcheck;
	}

	public boolean fullnamepattern(User details) {
		boolean fullnamecheckflag = false;
		if (details.getFullname().matches("[A-Z]{1}[a-z]{5,30}")) {
			fullnamecheckflag = true;
		} else {
			System.err.println("Enter the first letter capital and no spaces between words");
		}

		return fullnamecheckflag;
	}

	public void deleteUser(User details) throws SQLException, UMSException {
		User detail = new User();
		detail = details;
		UserDao dao = new UserDao();
		dao.deleteUser(detail);

	}

	public boolean logincheck(User details) throws SQLException, UMSException {

		boolean admincheckflag = false;
		UserDao dao = new UserDao();
		admincheckflag = dao.logincheck(details);
		return admincheckflag;

	}

	public boolean passwordcheck(User details) {
		boolean passwordcheckflag = false;
		if (details.getAdminpassword().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")) {
			passwordcheckflag = true;
		} else {
			System.err.println("Enter the password correctly");
			passwordcheckflag = false;
		}
		return passwordcheckflag;

	}

	public boolean useremailpattern(User details) {
		boolean emailcheck = false;
		String emailpattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email = details.getEmail();
		if (email.matches(emailpattern)) {
			emailcheck = true;
		} else {
			System.err.println("Email pattern is wrong");
		}
		return emailcheck;
	}

	public boolean userpasswordcheck(User details) {
		boolean passwordcheckflag = false;
		if (details.getPassword().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")) {
			passwordcheckflag = true;
		} else {
			System.err.println("Enter the password correctly");
			passwordcheckflag = false;
		}
		return passwordcheckflag;

	}

	public boolean Idverification(User details) throws UMSException, SQLException {

		boolean idverificationflag = false;

		UserDao dao = new UserDao();
		idverificationflag = dao.IDverification(details);
		return idverificationflag;
	}

}
