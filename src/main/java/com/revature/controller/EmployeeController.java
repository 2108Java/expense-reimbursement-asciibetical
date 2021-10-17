package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.repo.RequestDAO;
import com.revature.repo.RequestDAOImpl;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

import io.javalin.http.Context;

public class EmployeeController {

	private final UserDAO uDao = new UserDAOImpl();
	private final RequestDAO rDao = new RequestDAOImpl();
	private List<Request> requestList = new ArrayList<>();
	private EmployeeService eService = new EmployeeServiceImpl(uDao, rDao);

	public String authenticate(Context ctx) throws IOException {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");

		if (eService.checkIfUsernameExists(username)) {
			User user = eService.login(username);
//			if(username.equals("User") && password.equals("password")) {
			if (eService.authenticate(username, password)) {
				ctx.res.setStatus(200);
				Boolean isFinanceManager = user.isFinanceManager();
				ctx.sessionAttribute("isLoggedIn", true);
				ctx.cookie("username", username);
				ctx.cookie("isFinanceManager", isFinanceManager.toString());
//				ctx.cookie("isFinanceManager", "true");

				ctx.res.sendRedirect("http://localhost:9000/home");
			}
		}
		return "/login";
	}

	public boolean registerAccount(Context ctx) {

		String username = ctx.formParam("username");
		String email = ctx.formParam("email");

		if (!eService.checkIfUsernameExists(username)) {
			if (eService.createNewAccount(username, email)) {
				ctx.res.setStatus(200);
				return true;
			} else {
				ctx.res.setStatus(500);
				return false;
			}
		} else {
			ctx.res.setStatus(500);
			return false;
		}
	}

	public boolean changePassword(Context ctx) {
		String username = ctx.cookieMap().get("username");
		String password = ctx.formParam("newpassword");
		if(eService.changePassword(username, password)) {
			ctx.res.setStatus(200);
			return true;
			}
		ctx.res.setStatus(500);
		return false;
	}

	public boolean createNewRequest(Context ctx) throws IOException {
		String username = ctx.cookieMap().get("username");
		ReimbursementType type = ReimbursementType.valueOf(ctx.formParam("type").toString());
		double amount = Double.parseDouble(ctx.formParam("amount"));
		String description = ctx.formParam("description");
		if (eService.requestReimbursment(username, type, amount, description)) {
			ctx.res.setStatus(200);
			return true;
		}
		ctx.res.setStatus(500);
		return false;
	}

	public Object getEmployeeReimbursements(Context ctx) {
		String username = ctx.cookieMap().get("username");

		requestList = eService.viewEmployeeRequests(username);

		return requestList;
	}

}
