package com.revature.dao;

import java.util.List;

import com.revature.models.PastTransactions;
import com.revature.models.ReimbursementRequests;

public interface PastTransactionsDao {
	public void insertTransaction(ReimbursementRequests r, String approver);
	public PastTransactions selectTransactionByTicketId(int input);
	public List<PastTransactions> selectTransactionsById(PastTransactions r);
	public List<PastTransactions> selectTransactionsByApprover(PastTransactions r);
	public List<PastTransactions> selectTransactionsByEmployee(PastTransactions r);
	public List<PastTransactions> selectTransactionsByApproved(PastTransactions r);
	public List<PastTransactions> selectTransactionsByDeclined(PastTransactions r);
	public List<PastTransactions> selectTransactionsByCategory(PastTransactions r);
}
