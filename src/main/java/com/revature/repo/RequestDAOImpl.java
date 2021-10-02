package com.revature.repo;

import java.util.List;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Request;

public class RequestDAOImpl implements RequestDAO {

	@Override
	public Request selectRequestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> selectAllRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> selectRequestByUsernameAndStatus(String username, ReimbursementStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> selectPastRequestByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRequestStatus(int id, ReimbursementStatus status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertRequest(String username, ReimbursementType type, double amount, String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRequest(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
