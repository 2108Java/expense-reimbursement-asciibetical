package com.revature.service;

import java.util.List;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.repo.RequestDAO;
import com.revature.repo.UserDAO;

public class EmployeeServiceImpl implements EmployeeService {

	private RequestDAO rDao;
	private UserDAO uDao;
	
	public EmployeeServiceImpl(UserDAO uDao, RequestDAO rDao) {
		this.uDao = uDao;
		this.rDao = rDao;
	}
	
	@Override
	public User login(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean checkIfUsernameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestReimbursment(String username, ReimbursementType type, double amount, String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Request> viewPendingRequests(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> viewPastRequests(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
