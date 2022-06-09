package com.revature.models;

import java.util.Objects;

public class PastTransactions {
	private int ticketId;
	private boolean approved;
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

	@Override
	public int hashCode() {
		return Objects.hash(approved,ticketId);
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
		return approved == other.approved && ticketId == other.ticketId;
	}
	@Override
	public String toString() {
		return "PastTransactions [ticketId=" + ticketId + ", approved=" + approved  + "]";
	}

	public PastTransactions(int ticketId, boolean approved) {
		super();
		this.ticketId = ticketId;
		this.approved = approved;
	}
	public PastTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}
}
