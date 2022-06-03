package com.revature.dao;

import java.util.List;

import com.revature.models.Employees;
import com.revature.models.ReimbursementRequests;

public interface ReimbursementRequestsDao {
	//Connection connection = ConnectionFactory.dataBaseConnection();
	public void insertRequest(Employees employee, String category, float amount);
	public ReimbursementRequests selectRequestByTicketId(int input);
	public List<ReimbursementRequests> selectRequestsById(int input);
	public List<ReimbursementRequests> selectRequestsByCategory(String input);
	public void denyRequest(int input, Employees financeManagerId);
	public void approveRequest(int input, Employees financeManagerId);
}
