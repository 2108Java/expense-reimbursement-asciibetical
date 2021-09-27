package com.revature.service;

import java.util.List;

import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.models.User;

public interface EmployeeService {
	
	public boolean authenticate(String employeeUsername, String employeePassword);
	
	public User getUser(String employeeUsername);
	
	public boolean createNewRequest(String employeeUsername, ReimbursementType type, double amount, String description);
	
	public List<Request> getAllPendingRequestsByUsername(String employeeUsername); //Not sure if we'll need this or just pass all the requests to the user and have it filter on the front-end
	
	public List<Request> getAllRequestsByUsername(String employeeUsername);

}
