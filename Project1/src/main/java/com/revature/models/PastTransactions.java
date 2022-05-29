package com.revature.models;

import java.util.Objects;

public class PastTransactions {
	private int ticketId;
	private boolean approved;
	private int approverId;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public int getApproverId() {
		return approverId;
	}
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(approved, approverId, ticketId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PastTransactions other = (PastTransactions) obj;
		return approved == other.approved && approverId == other.approverId && ticketId == other.ticketId;
	}
	@Override
	public String toString() {
		return "PastTransactions [ticketId=" + ticketId + ", approved=" + approved + ", approverId=" + approverId + "]";
	}
	public PastTransactions(int ticketId, boolean approved, int approverId) {
		super();
		this.ticketId = ticketId;
		this.approved = approved;
		this.approverId = approverId;
	}
	public PastTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}
}
