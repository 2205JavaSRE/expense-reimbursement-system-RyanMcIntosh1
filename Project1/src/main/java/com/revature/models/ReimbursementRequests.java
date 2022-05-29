package com.revature.models;

import java.util.Objects;

public class ReimbursementRequests {
	private int ticketId;
	private int id;
	private float amount;
	private String category;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, category, id, ticketId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementRequests other = (ReimbursementRequests) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount)
				&& Objects.equals(category, other.category) && id == other.id && ticketId == other.ticketId;
	}
	@Override
	public String toString() {
		return "ReimbursementRequests [ticketId=" + ticketId + ", id=" + id + ", amount=" + amount + ", category="
				+ category + "]";
	}
	public ReimbursementRequests(int ticketId, int id, float amount, String category) {
		super();
		this.ticketId = ticketId;
		this.id = id;
		this.amount = amount;
		this.category = category;
	}
	public ReimbursementRequests() {
		super();
		// TODO Auto-generated constructor stub
	}
}
