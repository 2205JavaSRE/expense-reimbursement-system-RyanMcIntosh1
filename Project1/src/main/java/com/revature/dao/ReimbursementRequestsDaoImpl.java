package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employees;
import com.revature.models.PastTransactions;
import com.revature.models.ReimbursementRequests;
import com.revature.util.ConnectionFactory;

public class ReimbursementRequestsDaoImpl implements ReimbursementRequestsDao {

	@Override
	public void insertRequest(Employees employee, String category, float amount) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT into reimbursement_requests(id, category, amount) VALUES (?,?,?)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, employee.getId());
			ps.setString(2, category);
			ps.setFloat(3, amount);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ReimbursementRequests selectRequestByTicketId(int input) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM reimbursement_requests WHERE ticket_id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PastTransactions transaction = new PastTransactions(rs.getInt("ticket_id"),rs.getBoolean("approved"), rs.getInt("approverId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ReimbursementRequests> selectRequestsById(int input) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM reimbursement_requests WHERE id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PastTransactions transaction = new PastTransactions(rs.getInt("ticket_id"),rs.getBoolean("approved"), rs.getInt("approverId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ReimbursementRequests> selectRequestsByCategory(String input) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM reimbursement_requests WHERE category = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PastTransactions transaction = new PastTransactions(rs.getInt("ticket_id"),rs.getBoolean("approved"), rs.getInt("approverId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteRequest(ReimbursementRequests r) {
		// TODO Auto-generated method stub
		//may not need
		
	}

	@Override
	public void approveRequest(int input, Employees financeManagerId) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT into past_transactions(id, approved, approver_id) VALUES (?,true,?)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ps.setInt(2, financeManagerId.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void denyRequest(int input, Employees financeManagerId) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT into past_transactions(id, approved, approver_id) VALUES (?,false,?)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ps.setInt(2, financeManagerId.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
