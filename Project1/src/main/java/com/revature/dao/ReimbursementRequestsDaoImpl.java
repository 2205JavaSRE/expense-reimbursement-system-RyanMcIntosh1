package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employees;
import com.revature.models.PastTransactions;
import com.revature.models.ReimbursementRequests;
import com.revature.util.ConnectionFactory;

public class ReimbursementRequestsDaoImpl implements ReimbursementRequestsDao {

	@Override
	public void insertRequest(int id, String category, float amount) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT into reimbursement_requests(id, category, amount) VALUES (?,?,?)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
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
			String sql = "SELECT * FROM reimbursement_requests WHERE ticket_id = ? AND decided is NULL";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PastTransactions transaction = new PastTransactions(rs.getInt("ticket_id"),rs.getBoolean("approved"));
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
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM reimbursement_requests WHERE id = ? AND decided is NULL";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"), rs.getInt("id"), rs.getFloat("amount"), rs.getString("Category"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}

	@Override
	public List<ReimbursementRequests> selectRequestsByCategory(String input) {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM reimbursement_requests WHERE category = ? AND decided is NULL";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"), rs.getInt("id"), rs.getFloat("amount"), rs.getString("Category"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}
	@Override
	public void UpdateRequest(int input, boolean decided) {
		try {
			String sql = "UPDATE reimbursement_requests SET decided = ? WHERE ticket_id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, decided);
			ps.setInt(2, input);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void approveRequest(int input) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT into past_transactions(ticket_id, approved) VALUES (?,true)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ps.executeUpdate();
			UpdateRequest(input, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void denyRequest(int input) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT into past_transactions(ticket_id, approved) VALUES (?,false)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ps.executeUpdate();
			UpdateRequest(input,false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<ReimbursementRequests> selectAllRequests(){
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM reimbursement_requests WHERE decided is NULL";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"), rs.getInt("id"), rs.getFloat("amount"), rs.getString("Category"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}


}
