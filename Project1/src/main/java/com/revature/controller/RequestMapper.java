package com.revature.controller;

import com.revature.models.Employees;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class RequestMapper {
	private EmployeesController employeesController = new EmployeesController();
	private PastTransactionsController pastController = new PastTransactionsController();
	private ReimbursementRequestsController requestController = new ReimbursementRequestsController();
	private Registry Counter = new Registry();
	
	public void configureRoutes(Javalin app) {
		PrometheusMeterRegistry registry = Registry.registryProm();
		app.get("/greetings", ctx -> {ctx.result("Hello there!");});
		app.post("/employee/request", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					ctx.result("You are not an Employee!");
				}else {
					try {
						employeesController.insertRequest(ctx);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.post("/manager/pending/view/category", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.getTransactionByCategory(ctx);
						Counter.viewCount(registry).increment(1);
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
		app.post("/manager/pending/view/id", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.viewRequests(ctx);
						Counter.viewCount(registry).increment(1);
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
				Counter.loginCount(registry).increment(1);
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
		app.post("/manager/pending/approve", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.approveRequest(ctx);
						Counter.managerDecisionCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
			

		app.post("/manager/pending/deny", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.denyRequest(ctx);
						Counter.managerDecisionCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		
		app.get("/employee/pending/view", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					ctx.result("You are not an Employee!");
					Counter.viewCount(registry).increment(1);
				}else {
					try {
						employeesController.employeeViewRequests(ctx);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.get("/employee/past/view", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					ctx.result("You are not an Employee!");
					Counter.viewCount(registry).increment(1);
				}else {
					try {
						employeesController.employeeViewPastTransactions(ctx);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.get("/manager/past/view/denied", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.managerViewDenied(ctx);
						Counter.viewCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.get("/manager/past/view/approved", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.managerViewAccepted(ctx);
						Counter.viewCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.get("/manager/pending/view/all", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.getAllRequests(ctx);
						Counter.viewCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.get("/manager/past/view/all", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.getAllPastRequests(ctx);
						Counter.viewCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.post("/manager/past/view/id", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.viewPastTransactionsById(ctx);
						Counter.viewCount(registry).increment(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					ctx.result("You are not an Manager!");
				}
			}else {
				ctx.result("You do not have Access!");
			}
		});
		app.post("/manager/past/view/category", ctx -> {
			if(employeesController.checkIfLogin(ctx)) {
				boolean checkIfManager = ctx.cookieStore("manager");
				if(checkIfManager) {
					try {
						employeesController.getPastTransactionByCategory(ctx);
						Counter.viewCount(registry).increment(1);
						
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
		app.get("/metrics", ctx -> {
			ctx.result(registry.scrape());
		});
	}
}
