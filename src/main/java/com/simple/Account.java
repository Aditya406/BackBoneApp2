package com.simple;

public class Account {
	 private String username;
	  private String email;
	  private String password;
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	

}
