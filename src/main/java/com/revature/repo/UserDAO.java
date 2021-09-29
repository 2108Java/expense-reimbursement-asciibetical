package com.revature.repo;
import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	List<String> selectAllUsernames();
	User selectUser(String username);
	boolean updatePassword(String username, String newPassword);
	boolean insertUser(String username, String password);
	boolean deleteUser(String username);
}