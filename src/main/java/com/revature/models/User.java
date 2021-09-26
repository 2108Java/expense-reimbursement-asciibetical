package com.revature.models;

public class User {

	private String username;
	private String password;
	private boolean isFinanceManager;
	
	public User() {
		this("placeholder", "placeholder");
	}
	
	public User(String username, String password) {
		this(username, password, false);
	}

	public User(String username, String password, boolean isFinanceManager) {
		super();
		this.username = username;
		this.password = password;
		this.isFinanceManager = isFinanceManager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFinanceManager() {
		return isFinanceManager;
	}

	public void setFinanceManager(boolean isFinanceManager) {
		this.isFinanceManager = isFinanceManager;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", isFinanceManager=" + isFinanceManager + "]";
	}
	
	
}
