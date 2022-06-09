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

public class PastTransactionsDaoImpl implements PastTransactionsDao {

	@Override
	public void insertTransaction(ReimbursementRequests r, boolean approve, Employees manager) {
		try {
			String sql = "INSERT into past_transactions(ticket_id, approved, approved_id) VALUES (?,?,?)";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, r.getTicketId());
			ps.setBoolean(1, approve);
			ps.setInt(3, manager.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ReimbursementRequests selectTransactionByTicketId(int input) {
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id WHERE ticket_id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				return transaction;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ReimbursementRequests> selectTransactionsById(int input) {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id WHERE id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}

	@Override
	public List<ReimbursementRequests> selectTransactionsByApprover(int input) {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions WHERE approver_id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}

	@Override
	public List<ReimbursementRequests> selectTransactionsByEmployee(int input) {
		// Do this by Employee ID
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id WHERE id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}

	@Override
	public List<ReimbursementRequests> selectTransactionsByApproved() {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id WHERE approved = true";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}

	@Override
	public List<ReimbursementRequests> selectTransactionsByDeclined() {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id WHERE approved = false";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}

	@Override
	public List<ReimbursementRequests> selectTransactionsByCategory(String input) {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id WHERE category = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
	}
	public List<ReimbursementRequests> selectAllTransactions(){
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM past_transactions INNER JOIN reimbursement_requests ON past_transactions.ticket_id = reimbursement_requests.ticket_id";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReimbursementRequests transaction = new ReimbursementRequests(rs.getInt("ticket_id"),rs.getInt("id"),rs.getFloat("amount"),rs.getString("category"),rs.getBoolean("approved"));
				reimbursementRequestsList.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursementRequestsList;
		
	}


}
