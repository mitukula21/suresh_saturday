package com.cg.usermanagement.dao.tes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.cg.usermanagement.connection.dbconnection;
import com.cg.usermanagement.dao.UserDao;
import com.cg.usermanagement.exceptions.UMSException;


public class DBConnectionTest {
	static UserDao daotest;
	static Connection dbCon;

	@BeforeClass
	public static void initialise() {
		daotest = new UserDao();
		dbCon = null;
	}

	@Before
	public void beforeEachTest() {
		System.out.println("----Starting DBConnection Test Case----\n");
	}

	/**
	 * Test case for Establishing Connection
	 * 
	 * @throws DonorException
	 **/
	@Test
	public void test() throws UMSException {
		Connection dbCon = dbconnection.getInstance().getConnection();
		Assert.assertNotNull(dbCon);
	}

	@After
	public void afterEachTest() {
		System.out.println("----End of DBConnection Test Case----\n");
	}

	@AfterClass
	public static void destroy() {
		System.out.println("\t----End of Tests----");
		daotest = null;
		dbCon = null;
	}

}
