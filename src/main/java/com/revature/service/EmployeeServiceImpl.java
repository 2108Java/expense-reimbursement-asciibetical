package com.revature.service;

import java.util.List;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.repo.RequestDAO;
import com.revature.repo.UserDAO;
import java.util.Base64;

public class EmployeeServiceImpl implements EmployeeService {

	private RequestDAO rDao;
	private UserDAO uDao;

	public EmployeeServiceImpl(UserDAO uDao, RequestDAO rDao) {
		this.uDao = uDao;
		this.rDao = rDao;
	}

	@Override
	public User login(String username) {
		return uDao.selectUser(username);
	}

	@Override
	public boolean authenticate(String username, String password) {

		boolean authenticated = false;
		List<User> allUsers = uDao.selectAllUsers();

		for (User user : allUsers) {
			if (user.getUsername().equals(username)) {
				if (decryptPassword(user.getPassword()).equals(password)) {
					authenticated = true;
					break;
				} else {
					// incorrect password
					authenticated = false;
					break;
				}
			}
		}
		return authenticated;
	}

	@Override
	public boolean checkIfUsernameExists(String username) {

		boolean usernameExists = false;

		List<User> allUsers = uDao.selectAllUsers();

		for (User user : allUsers) {
			if (user.getUsername().equals(username)) {
				usernameExists = true;
				break;
			}
		}

		return usernameExists;
	}

	@Override
	public boolean requestReimbursment(String username, ReimbursementType type, double amount, String description) {
		return rDao.insertRequest(username, type, amount, description);
	}

	@Override
	public List<Request> viewPendingRequests(String username) {
		return rDao.selectRequestByUsernameAndStatus(username, ReimbursementStatus.PENDING);
	}

	@Override
	public List<Request> viewPastRequests(String username) {
		return rDao.selectPastRequestByUsername(username);
	}

	@Override
	public boolean createNewAccount(String username, String email) {
		return uDao.insertUser(username, email);
	}

	@Override
	public boolean changePassword(String username, String newPassword) {
		String encryptedPassword = encryptPassword(newPassword);
		return uDao.updatePassword(username, encryptedPassword);
	}

	private String encryptPassword(String password) {
		return Base64.getEncoder().encode(password.getBytes()).toString();
	}
	
	private String decryptPassword(String password) {
		return Base64.getDecoder().decode(password.getBytes()).toString();
	}
}
