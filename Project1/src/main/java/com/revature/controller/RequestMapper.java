package com.revature.controller;

import com.revature.models.Employees;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class RequestMapper {
	private EmployeesController employeesController = new EmployeesController();
	private PastTransactionsController pastController = new PastTransactionsController();
	private ReimbursementRequestsController requestController = new ReimbursementRequestsController();
	
	public void configureRoutes(Javalin app) {
		app.get("/greetings", ctx -> {ctx.result("Hello there!");});
		app.get("/FinanceManager/category/{category}", ctx -> {
			try {
				employeesController.getTransactionByCategory(ctx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		});
		app.get("/FinanceManager/id/{id}", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.viewRequests(ctx);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not a Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.post("/login", ctx -> {
			
			//some validation would be involved here first
			try {
				employeesController.login(ctx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		app.get("/logout",ctx -> {
			
			//some validation would be involved here first
			try {
				employeesController.logOut(ctx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
