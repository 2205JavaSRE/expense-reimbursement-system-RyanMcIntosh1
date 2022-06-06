package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.dao.EmployeesDaoImpl;
import com.revature.dao.ReimbursementRequestsDaoImpl;
import com.revature.models.Employees;
import com.revature.models.ReimbursementRequests;
import com.revature.service.EmployeesService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class EmployeesController {
	ReimbursementRequestsDaoImpl reimbursementRequestsDaoImpl = new ReimbursementRequestsDaoImpl();
	EmployeesService employeesService = new EmployeesService();
	public void insertRequest(Context ctx){
		ReimbursementRequests reimbursementRequest =  ctx.bodyAsClass(ReimbursementRequests.class);
		//EmployeesDaoImpl.insertRequest(reimbursementRequest);
		
		ctx.status(201);
	}
	public void viewRequests(Context ctx) {
		String id = ctx.pathParam("id");
		int i=Integer.parseInt(id);
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList = reimbursementRequestsDaoImpl.selectRequestsById(i);
		ctx.json(reimbursementRequestsList);
	}
	public void viewPastTransactions(Context ctx) {
		
	}
	
	public void getTransactionByCategory(Context ctx) {
		String category = ctx.pathParam("category");
		List<ReimbursementRequests> reimbursementRequestsList = new ArrayList<>();
		reimbursementRequestsList =  reimbursementRequestsDaoImpl.selectRequestsByCategory(category);
		ctx.json(reimbursementRequestsList);
	}
	public void login(Context ctx) {
		Employees employee = ctx.bodyAsClass(Employees.class);
		try {
		employee =  employeesService.login(employee.getUsername(), employee.getPassword());
		if(employee  == null) {
			ctx.result("The password or username is incorrect");
		}else if(employee.isEmployee()) {
			//if true they are a manager
			ctx.result("You are a Manager!");
			ctx.cookieStore("access", true);
			ctx.cookieStore("userinfo", employee);
			ctx.cookieStore("employee", false);
			ctx.cookieStore("manager", true);
		}else {
			ctx.result("You are a Employee!");
			ctx.cookieStore("access", true);
			ctx.cookieStore("userinfo", employee);
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
}
	
