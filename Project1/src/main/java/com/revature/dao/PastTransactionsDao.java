package com.revature.dao;

import java.util.List;

import com.revature.models.Employees;
import com.revature.models.PastTransactions;
import com.revature.models.ReimbursementRequests;

public interface PastTransactionsDao {
	//May not need InsertTransaction since I have one in the ReimbusementDao
	public void insertTransaction(ReimbursementRequests r, boolean approve, Employees manager);
	public List<ReimbursementRequests> selectAllTransactions();
	public ReimbursementRequests selectTransactionByTicketId(int input);
	public List<ReimbursementRequests> selectTransactionsById(int input);
	public List<ReimbursementRequests> selectTransactionsByApprover(int input);
	public List<ReimbursementRequests> selectTransactionsByEmployee(int input);
	public List<ReimbursementRequests> selectTransactionsByApproved();
	public List<ReimbursementRequests> selectTransactionsByDeclined();
	public List<ReimbursementRequests> selectTransactionsByCategory(String input);
}
