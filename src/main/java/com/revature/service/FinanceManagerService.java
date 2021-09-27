package com.revature.service;

import java.util.List;
import com.revature.models.Request;
import com.revature.models.ReimbursementStatus;

public interface FinanceManagerService extends EmployeeService{
	List<Request> viewAllRequests();
	boolean updateRequestStatus(int id, ReimbursementStatus status);
	
}
