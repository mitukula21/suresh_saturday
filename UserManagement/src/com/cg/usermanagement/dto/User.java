package com.cg.usermanagement.dto;

public class User {

	private String email;
	private String fullname;
	private String password;
	private int ID;
	private String adminEmail;
	private String adminPassword;

	public String getadmin_email() {
		return adminEmail;
	}

	public void setadmin_email(String admin_email) {
		this.adminEmail = admin_email;
	}

	@Override
	public String toString() {
		return "email=" + email + ", fullname=" + fullname + ", password=" + password + ", ID=" + ID ;
	}

	public String getAdminpassword() {
		return adminPassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminPassword = adminpassword;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getFullname() {
		return fullname;
	}

	public String getPassword() {
		return password;
	}

}
