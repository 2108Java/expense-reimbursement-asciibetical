package com.revature.models;

import java.time.LocalDateTime;

public class Request {

	private int id;
	private String username;
	private ReimbursementType type;
	private double amount;
	private String description;
	private LocalDateTime timestamp;
	private ReimbursementStatus status;
	
	public Request() {
		this("placeholder", ReimbursementType.OTHER, 0.0, "placeholder");
	}
	
	public Request(String username, ReimbursementType type, double amount, String description) {
		this(-1, username, type, amount, description, LocalDateTime.now(), ReimbursementStatus.PENDING);
	}
	
	public Request(int id, String username, ReimbursementType type, double amount, String description,
			LocalDateTime timestamp, ReimbursementStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.timestamp = timestamp;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", username=" + username + ", type=" + type + ", amount=" + amount
				+ ", description=" + description + ", timestamp=" + timestamp + ", status=" + status + "]";
	}
	
	
}
