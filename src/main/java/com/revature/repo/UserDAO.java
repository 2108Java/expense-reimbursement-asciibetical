package com.revature.repo;
import com.revature.models.User;

public interface UserDAO {
	User selectUser(String username);
	boolean updatePassword(String username, String newPassword);
	boolean insertUser(String username, String password);
	boolean deleteUser(String username);
}