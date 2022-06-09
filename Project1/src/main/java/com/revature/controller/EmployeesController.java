package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.dao.PastTransactionsDaoImpl;
import com.revature.dao.ReimbursementRequestsDaoImpl;
import com.revature.models.Employees;
import com.revature.models.PastTransactions;
import com.revature.models.ReimbursementRequests;
import com.revature.service.EmployeesService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class EmployeesController {
	ReimbursementRequestsDaoImpl reimbursementRequestsDaoImpl = new ReimbursementRequestsDaoImpl();
	EmployeesService employeesService = new EmployeesService();
	PastTransactionsDaoImpl pt = new PastTransactionsDaoImpl();
	Gson gson = new Gson();
	
	public void login(Context ctx) {
		Employees employee = ctx.bodyAsClass(Employees.class);
		try {
		employee =  employeesService.login(employee.getUsername(), employee.getPassword());
		int id = employee.getId();
		if(employee  == null) {
			ctx.result("The password or username is incorrect");
		}else if(employee.isEmployee()) {
			//if true they are a manager
			ctx.result("You are a Manager!");
			ctx.cookieStore("access", true);
			ctx.cookieStore("userinfo", id);
			ctx.cookieStore("employee", false);
			ctx.cookieStore("manager", true);
		}else {
			ctx.result("You are a Employee!");
			ctx.cookieStore("access", true);
			ctx.cookieStore("userinfo", id);
			ctx.cookieStore("employee", true);
			ctx.cookieStore("manager", false);
		}
		}catch(Exception e){
			ctx.result("The password or username is incorrect");
		}
	}
	public void logOut(Context ctx) {
		ctx.result("You are logged out");
		ctx.clearCookieStore();
	}
	public boolean checkIfLogin(Context ctx) {
		Boolean b = ctx.cookieStore("access");
		//if the cookie is non existent or has a different value than expected!
		if(b == null || !b.toString().equals("true")) {
			
			ctx.result("You don't have access!");
			b = false;
			return b;
		}else {
			ctx.result("You have access!");
			return b;
		}
	}
	public void approveRequest(Context ctx) {
		String i = ctx.body();
		int ticketId = Integer.parseInt(i);
		reimbursementRequestsDaoImpl.approveRequest(ticketId);
		
	}
	public void denyRequest(Context ctx) {
		String i = ctx.body();
		int ticketId = Integer.parseInt(i);
		reimbursementRequestsDaoImpl.denyRequest(ticketId);
	}
	
	public void employeeViewRequests(Context ctx) {
		int id = ctx.cookieStore("userinfo");
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList =  reimbursementRequestsDaoImpl.selectRequestsById(id);
		ctx.json(reimbursementRequestsList);
		
	}
	public void employeeViewPastTransactions(Context ctx) {
		int id = ctx.cookieStore("userinfo");
		List<ReimbursementRequests> pastTransactionsList = new ArrayList<>();
		pastTransactionsList =  pt.selectTransactionsById(id);
		ctx.json(pastTransactionsList);
		
	}
	public void managerViewAccepted(Context ctx) {
		List<ReimbursementRequests> pastTransactionsList = new ArrayList<>();
		pastTransactionsList =  pt.selectTransactionsByApproved();
		ctx.json(pastTransactionsList);
	}
	public void managerViewDenied(Context ctx) {
		List<ReimbursementRequests> pastTransactionsList = new ArrayList<>();
		pastTransactionsList =  pt.selectTransactionsByDeclined();
		ctx.json(pastTransactionsList);
	}
	public void insertRequest(Context ctx){
		ReimbursementRequests reimbursementRequest =  ctx.bodyAsClass(ReimbursementRequests.class);
		if(reimbursementRequest.getAmount() > 0) {
		if(employeesService.insertTransaction(reimbursementRequest.getId(), reimbursementRequest.getCategory(), reimbursementRequest.getAmount())) {
			ctx.result("Request Submitted!");
			ctx.status(201);
		}else {
			ctx.result("Please type LODGING, TRAVEL, FOOD, or OTHER");
		}
		
		}else {
			ctx.result("The amount you typed in is invalid");
		}
	}
	public void viewRequests(Context ctx) {
		String id = ctx.body();
		int i=Integer.parseInt(id);
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList = reimbursementRequestsDaoImpl.selectRequestsById(i);
		ctx.json(reimbursementRequestsList);
	}
	public void viewPastTransactionsById(Context ctx) {
		String id = ctx.body();
		int i=Integer.parseInt(id);
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList = pt.selectTransactionsByEmployee(i);
		ctx.json(reimbursementRequestsList);
	}
	
	public void getTransactionByCategory(Context ctx) {
		
		String category = ctx.body();
		category = category.toUpperCase();
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList =  reimbursementRequestsDaoImpl.selectRequestsByCategory(category);
		ctx.json(reimbursementRequestsList);
	}
	public void getAllRequests(Context ctx) {
		List<ReimbursementRequests> pastTransactionsList = new ArrayList<>();
		pastTransactionsList =  reimbursementRequestsDaoImpl.selectAllRequests();
		System.out.println(pastTransactionsList.get(1));
		ctx.json(pastTransactionsList);
	}
	public void getAllPastRequests(Context ctx) {
		List<ReimbursementRequests> pastTransactionsList = new ArrayList<>();
		pastTransactionsList =  pt.selectAllTransactions();
		
		ctx.json(pastTransactionsList);
	}
	public void getPastTransactionByCategory(Context ctx) {
		
		String category = ctx.body();
		category = category.toUpperCase();
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList =  pt.selectTransactionsByCategory(category);
		ctx.json(reimbursementRequestsList);
	}
	
}
	
