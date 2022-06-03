package com.revature.dao;

import java.util.List;

import com.revature.models.Employees;
import com.revature.models.PastTransactions;
import com.revature.models.ReimbursementRequests;

public interface PastTransactionsDao {
	//May not need InsertTransaction since I have one in the ReimbusementDao
	public void insertTransaction(ReimbursementRequests r, boolean approve, Employees manager);
	public PastTransactions selectTransactionByTicketId(int input);
	public List<PastTransactions> selectTransactionsById(int input);
	public List<PastTransactions> selectTransactionsByApprover(int input);
	public List<PastTransactions> selectTransactionsByEmployee(int input);
	public List<PastTransactions> selectTransactionsByApproved();
	public List<PastTransactions> selectTransactionsByDeclined();
	public List<PastTransactions> selectTransactionsByCategory(String input);
}
