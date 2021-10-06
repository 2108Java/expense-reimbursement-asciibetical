package com.revature.repo;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	List<String> selectAllUsernames() throws SQLException;
	User selectUser(String username) throws SQLException;
	boolean updatePassword(String username, String newPassword) throws SQLException;
	boolean insertUser(String username, String email) throws SQLException;
	boolean deleteUser(String username) throws SQLException;
}