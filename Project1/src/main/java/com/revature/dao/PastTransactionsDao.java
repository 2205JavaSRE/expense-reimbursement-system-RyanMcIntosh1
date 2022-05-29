package com.revature.dao;

import java.util.List;

import com.revature.models.PastTransactions;

public interface PastTransactionsDao {
	public void insertTransaction(PastTransactions r);
	public PastTransactions selectTransactionByTicketId(PastTransactions r);
	public List<PastTransactions> selectTransactionsById(PastTransactions r);
	public List<PastTransactions> selectTransactionsByApprover(PastTransactions r);
	public List<PastTransactions> selectTransactionsByEmployee(PastTransactions r);
	public List<PastTransactions> selectTransactionsByApproved(PastTransactions r);
	public List<PastTransactions> selectTransactionsByDeclined(PastTransactions r);
	public List<PastTransactions> selectTransactionsByCategory(PastTransactions r);
}
