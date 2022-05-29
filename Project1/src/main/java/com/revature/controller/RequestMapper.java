package com.revature.controller;

import io.javalin.Javalin;

public class RequestMapper {
	private EmployeesController employeesController = new EmployeesController();
	private PastTransactionsController pastController = new PastTransactionsController();
	private ReimbursementRequestsController requestController = new ReimbursementRequestsController();
	
	public void configureRoutes(Javalin app) {
		app.get("/greetings", ctx -> {ctx.result("Hello there!");});
	}
}
