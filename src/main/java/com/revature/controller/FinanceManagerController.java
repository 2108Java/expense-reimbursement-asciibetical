package com.revature.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.repo.RequestDAO;
import com.revature.repo.RequestDAOImpl;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.FinanceManagerService;
import com.revature.service.FinanceManagerServiceImpl;

import io.javalin.http.Context;

public class FinanceManagerController {

	UserDAO uDao = new UserDAOImpl();
	RequestDAO rDao = new RequestDAOImpl();
	
	FinanceManagerService mService = new FinanceManagerServiceImpl(uDao, rDao);
	List<Request> requestList = new ArrayList<>();
	
	public void initializeFakeData() {
		requestList.add(new Request(1, "Someone", ReimbursementType.OTHER, 400, "test", LocalDateTime.now(), ReimbursementStatus.PENDING));
		requestList.add(new Request(2, "Me", ReimbursementType.FOOD, 600, null, LocalDateTime.now(), ReimbursementStatus.APPROVED));
		requestList.add(new Request(3, "Gulliver", ReimbursementType.TRAVEL, 900, null, LocalDateTime.now(), ReimbursementStatus.PENDING));
		requestList.add(new Request(4, "Nessie", ReimbursementType.OTHER, 3.50, "I need about tree fiddy", LocalDateTime.now(), ReimbursementStatus.REJECTED));
		requestList.add(new Request(5, "Someone Else", ReimbursementType.LODGING, 400, null, LocalDateTime.now(), ReimbursementStatus.APPROVED));
	}
	
	public List<Request> getAllReimbursements(Context ctx) {
		ctx.res.setStatus(200);
		
		return requestList;
	}
	
	public boolean updateRequestStatus(Context ctx) {
		int id = Integer.parseInt(ctx.formParam("id"));
		ReimbursementStatus status = ReimbursementStatus.valueOf(ctx.formParam("type"));
		
		if(mService.updateRequestStatus(id, status)) {
			ctx.res.setStatus(200);
			return true;
		}
		
		ctx.res.setStatus(500);
		return false;
	}

}
