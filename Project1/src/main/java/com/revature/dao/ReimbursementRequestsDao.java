package com.revature.dao;

import java.util.List;

import com.revature.models.ReimbursementRequests;

public interface ReimbursementRequestsDao {
	//Connection connection = ConnectionFactory.dataBaseConnection();
	public void insertRequest(ReimbursementRequests r);
	public ReimbursementRequests selectRequestByTicketId(ReimbursementRequests r);
	public List<ReimbursementRequests> selectRequestsById(ReimbursementRequests r);
	public List<ReimbursementRequests> selectRequestsByCategory(ReimbursementRequests r);
	public void deleteRequest(ReimbursementRequests r);
}
