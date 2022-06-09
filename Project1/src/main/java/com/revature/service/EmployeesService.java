package com.revature.service;
import java.util.List;

import com.revature.dao.*;
import com.revature.models.Employees;
import com.revature.models.ReimbursementRequests;
public class EmployeesService {
	ReimbursementRequestsDaoImpl rdi = new ReimbursementRequestsDaoImpl();
	EmployeesDaoImpl employeesDaoImpl = new EmployeesDaoImpl();
	public Employees login(String username, String password) {
		
		Employees employee = employeesDaoImpl.selectEmployeeByCred(username, password);
		if(employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
			return employee;
		}
		return null;
	}
	public boolean checkManager(Employees employee) {
		if(employee.isEmployee()) {
			//is manager
			return true;
		}
		//is not manager
		return false;
	}
	public boolean insertTransaction(int id, String category, float amount) {
		if(category.equals("LODGING") || category.equals("TRAVEL") || category.equals("FOOD") || category.equals("OTHER")) {
			rdi.insertRequest(id, category, amount);
			return true;
		}
		return false;
	}
}
