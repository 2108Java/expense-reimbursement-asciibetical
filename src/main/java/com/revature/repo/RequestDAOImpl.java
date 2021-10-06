package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Request;
import com.revature.util.ConnectionFactory;

public class RequestDAOImpl implements RequestDAO {

	@Override
	public Request selectRequestById(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SECECT * FROM reimbursement WHERE id = ?";
		Request selectedRequestById = null;

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			selectedRequestById = new Request(rs.getInt("id"), rs.getString("user_username"),
					ReimbursementType.valueOf(rs.getString("reimbursement_type")), rs.getDouble("amount"),
					rs.getString("description"), LocalDateTime.parse(rs.getString("time_of_request")),
					ReimbursementStatus.valueOf(rs.getString("status")));

		}

		return selectedRequestById;
	}

	@SuppressWarnings("null")
	@Override
	public List<Request> selectAllRequests() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SECECT * FROM reimbursement";
		List<Request> allRequests = null;

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			allRequests.add(new Request(rs.getInt("id"), rs.getString("user_username"),
					ReimbursementType.valueOf(rs.getString("reimbursement_type")), rs.getDouble("amount"),
					rs.getString("description"), LocalDateTime.parse(rs.getString("time_of_request")),
					ReimbursementStatus.valueOf(rs.getString("status"))));

		}

		return allRequests;
	}

	@SuppressWarnings("null")
	@Override
	public List<Request> selectRequestByUsernameAndStatus(String username, ReimbursementStatus status)
			throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SECECT * FROM reimbursement WHERE username = ? AND status = ?";
		List<Request> selectedRequestByUsernameAndStatus = null;

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, username);
		ps.setString(2, status.name());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			selectedRequestByUsernameAndStatus.add(new Request(rs.getInt("id"), rs.getString("user_username"),
					ReimbursementType.valueOf(rs.getString("reimbursement_type")), rs.getDouble("amount"),
					rs.getString("description"), LocalDateTime.parse(rs.getString("time_of_request")),
					ReimbursementStatus.valueOf(rs.getString("status"))));

		}

		return selectedRequestByUsernameAndStatus;
	}

	@SuppressWarnings("null")
	@Override
	public List<Request> selectPastRequestByUsername(String username) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "SECECT * FROM reimbursement WHERE username = ? AND status = past";
		List<Request> selectedPastRequestByUsername = null;

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			selectedPastRequestByUsername.add(new Request(rs.getInt("id"), rs.getString("user_username"),
					ReimbursementType.valueOf(rs.getString("reimbursement_type")), rs.getDouble("amount"),
					rs.getString("description"), LocalDateTime.parse(rs.getString("time_of_request")),
					ReimbursementStatus.valueOf(rs.getString("status"))));

		}

		return selectedPastRequestByUsername;
	}

	@Override
	public boolean updateRequestStatus(int id, ReimbursementStatus status) throws SQLException {
		boolean success;
		Connection conn = ConnectionFactory.getConnection();

		String sql = "UPDATE reimbursement SET status = ? WHERE id = ?;";
		PreparedStatement updateStatus = conn.prepareStatement(sql);
		updateStatus.setString(1, status.name());
		updateStatus.setInt(2, id);
		updateStatus.execute();

		sql = "SELECT * FROM reimbursement WHERE id = ? AND status = ?";
		PreparedStatement confirmUpdate = conn.prepareStatement(sql);
		confirmUpdate.setInt(1, id);
		confirmUpdate.setString(2, status.name());
		success = confirmUpdate.execute();

		return success;
	}

	@Override
	public boolean insertRequest(String username, ReimbursementType type, double amount, String description)
			throws SQLException {
		boolean success;
		Timestamp timeOfRequest = Timestamp.valueOf(LocalDateTime.now());
		Connection conn = ConnectionFactory.getConnection();

		String sql = "INSERT INTO reimbursement(user_username_fk,reimbursement_type,amount,desciption,time_of_request,status) "
				+ "VALUES( ?, ?, ?, ?, ?, ? );";
		PreparedStatement insertStatement = conn.prepareStatement(sql);
		insertStatement.setString(1, username);
		insertStatement.setString(2, type.name());
		insertStatement.setDouble(3, amount);
		insertStatement.setString(4, description);
		insertStatement.setTimestamp(5, timeOfRequest);
		insertStatement.setString(6, ReimbursementStatus.PENDING.name());
		insertStatement.execute();

		sql = "SELECT * FROM reimbursement WHERE user_username = ? AND time_of_request = ?;";
		PreparedStatement confirmInsert = conn.prepareStatement(sql);
		confirmInsert.setString(1, username);
		confirmInsert.setTimestamp(2, timeOfRequest);
		success = confirmInsert.execute();

		return success;
	}

	@Override
	public boolean deleteRequest(int id) throws SQLException {
		boolean success;
		Connection conn = ConnectionFactory.getConnection();

		String sql = "DELETE FROM reimbursement WHERE id =?;";
		PreparedStatement updateStatus = conn.prepareStatement(sql);
		updateStatus.setInt(1, id);
		updateStatus.execute();

		sql = "SELECT * FROM reimbursement WHERE id = ?;";
		PreparedStatement confirmUpdate = conn.prepareStatement(sql);
		confirmUpdate.setInt(1, id);
		success = !(confirmUpdate.execute());

		return success;
	}

}
