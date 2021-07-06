package com.aimuc.pojo;

public class User {
	private String username;
	private String password;
	private String usertype;
	private String userstate;

	public void User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

	public User(String username, String password, String usertype, String userstate) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.userstate = userstate;
	}

	public String Getuserstate() {
		return userstate;
	}
	
	public String Getname() {
		return username;
	}

	public String Getpass() {
		return password;
	}

	public String Getusertype() {
		return usertype;
	}
}
