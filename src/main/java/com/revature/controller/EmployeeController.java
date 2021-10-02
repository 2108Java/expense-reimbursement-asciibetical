package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

import io.javalin.http.Context;

public class EmployeeController {
	
	private List<Request> requestList = new ArrayList<>();
	private EmployeeService eService = new EmployeeServiceImpl();

	public void authenticate(Context ctx) throws IOException {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(eService.checkIfUsernameExists(username)) {
			User user = eService.login(username);
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				ctx.res.setStatus(200);
				ctx.sessionAttribute("user", user);
				ctx.sessionAttribute("isLoggedIn", true);

				ctx.res.sendRedirect("localhost:9000/home");
			}
		}
		
		ctx.res.sendRedirect("/failedLogin");
	}
	
	public boolean createNewRequest(Context ctx) {
		String username = ctx.formParam("username");
		ReimbursementType type = ReimbursementType.valueOf(ctx.formParam("type"));
		double amount = Double.parseDouble(ctx.formParam("amount"));
		String description = ctx.formParam("description");
		
		if(eService.requestReimbursment(username, type, amount, description)) {
			ctx.res.setStatus(200);
			return true;
		}
		
		ctx.res.setStatus(500);
		return false;
	}

	public List<Request> getUserPendingRequests(Context ctx) {
		requestList = eService.viewPendingRequests(ctx.formParam("username"));
		
		return requestList;
	}
	
	public List<Request> getAllPastRequests(Context ctx) {
		requestList = eService.viewPastRequests(ctx.formParam("username"));
		
		return requestList;
	}

}
