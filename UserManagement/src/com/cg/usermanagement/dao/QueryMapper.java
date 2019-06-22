package com.cg.usermanagement.dao;

public interface QueryMapper {
	public static final String RETRIVE_ALL_QUERY = "select * from usermanagement";
	public static final String EDIT_QUERY = "update usermanagement set email=?, fullname=?, password=? where id=?";
	public static final String DELETE_QUERY = "delete from usermanagement where id=?";
	public static final String INSERT_QUERY = "insert into usermanagement values(sno_seq.nextval,id_seq.nextval,?,?,?)";
	public static final String ADMIN_CHECK_QUERY = "select * from admin_details where admin_username=? AND admin_password=?";
	public static final String ID_CHECK_QUERY = "select * from usermanagement where id=?";
	public static final String SEQUENCE_ID_QUERY="select id from usermanagement where email=?";
}
