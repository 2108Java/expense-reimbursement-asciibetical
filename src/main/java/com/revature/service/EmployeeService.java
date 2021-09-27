package com.revature.service;
import java.util.List;
import com.revature.models.ReimbursementStatus;
import com.revature.models.Request;

public interface EmployeeService {
	boolean login();
	boolean authenticate();
	boolean requestReimbursment();
	List<Request> viewPendingRequests(String username, ReimbursementStatus status);
	List<Request> viewPastRequests(String username);
	
}
