package com.revature.service;
import java.util.List;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.models.User;

public interface EmployeeService {
	User login(String username);
	boolean authenticate(String username, String password);
	boolean requestReimbursment(String username, ReimbursementType type, double amount, String description);
	List<Request> viewPendingRequests(String username, ReimbursementStatus status);
	List<Request> viewPastRequests(String username);
	
}
