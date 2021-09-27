package com.revature.service;

import java.util.List;

import com.revature.models.Request;

public interface ManagerService {

	public boolean approveRequestByID(int id);
	
	public boolean rejectRequestByID(int id);
	
	public List<Request> getAllPendingRequests(); //Not sure if we're going to need these or pass all requests to the user and let them filter on the front-end
	
	public List<Request> getAllApprovedRequests(); //Not sure if we're going to need these or pass all requests to the user and let them filter on the front-end
	
	public List<Request> getAllRejectedRequests(); //Not sure if we're going to need these or pass all requests to the user and let them filter on the front-end
	
	public List<Request> getAllRequests();
}
