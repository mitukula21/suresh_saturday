package com.cg.usermanagement.dao.tes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.cg.usermanagement.dao.UserDao;
import com.cg.usermanagement.dto.User;
import com.cg.usermanagement.exceptions.UMSException;


class UserDaoTest {


	@Test
	public void testNewUSer() throws UMSException, SQLException {
		// increment the number next time you test for positive test case
		User user = new User();
		UserDao dao=new UserDao();
		user.setEmail("ram7@gmail.com");
		user.setFullname("Rama");
		user.setPassword("Ram@123");
		int returnnum=dao.newUser(user);
		System.out.println(returnnum);
		assertEquals(213,returnnum );
	}
@Test
public void testIDverification() throws UMSException,SQLException
{
	User user = new User();
	UserDao dao=new UserDao();
	user.setID(206);
	assertEquals(true,dao.IDverification(user));
}
	



	
}
