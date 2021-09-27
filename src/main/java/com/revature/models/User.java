package com.revature.models;

public class User {

	private String username;
	private String password;
	private String email;
	private boolean isFinanceManager;
	
	public User() {
		this("placeholder", "placeholder", "placeholder");
	}
	
	public User(String username, String email) {
		this(username, "temporary", email);
	}
	
	public User(String username, String password, String email) {
		this(username, password, email, false);
	}

	public User(String username, String password, String email, boolean isFinanceManager) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", isFinanceManager="
				+ isFinanceManager + "]";
	}
	
	
	
	
}
