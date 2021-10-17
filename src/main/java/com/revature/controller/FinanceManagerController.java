package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import com.revature.models.ReimbursementStatus;
import com.revature.models.Request;
import com.revature.repo.RequestDAO;
import com.revature.repo.RequestDAOImpl;
import com.revature.service.FinanceManagerService;
import com.revature.service.FinanceManagerServiceImpl;

import io.javalin.http.Context;

public class FinanceManagerController {

	RequestDAO rDao = new RequestDAOImpl();
	
	FinanceManagerService mService = new FinanceManagerServiceImpl(rDao);
	List<Request> requestList = new ArrayList<>();
	
	public List<Request> getAllReimbursements(Context ctx) {
		requestList = mService.viewAllRequests();
		ctx.res.setStatus(200);
		
		return requestList;
	}
	
	public boolean updateRequestStatus(Context ctx) {
		int id = Integer.parseInt(ctx.formParam("id"));
		ReimbursementStatus status = ReimbursementStatus.valueOf(ctx.formParam("status"));
		
		if(mService.updateRequestStatus(id, status)) {
			ctx.res.setStatus(200);
			
			return true;
		}
		
		ctx.res.setStatus(500);
		return false;
	}

}
