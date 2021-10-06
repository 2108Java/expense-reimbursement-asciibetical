package com.revature.repo;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Request;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;

public interface RequestDAO {

	Request selectRequestById(int id) throws SQLException;
	List<Request> selectAllRequests() throws SQLException;
	List<Request> selectRequestByUsernameAndStatus(String username, ReimbursementStatus status) throws SQLException;
	List<Request> selectPastRequestByUsername(String username) throws SQLException;
	boolean updateRequestStatus(int id, ReimbursementStatus status) throws SQLException;
	boolean insertRequest(String username, ReimbursementType type, double amount, String description) throws SQLException;
	boolean deleteRequest(int id) throws SQLException;
}
