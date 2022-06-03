package com.revature.service;
import java.util.List;

import com.revature.dao.*;
import com.revature.models.Employees;
import com.revature.models.ReimbursementRequests;
public class EmployeesService {
	public Employees login(String username, String password) {
		EmployeesDaoImpl employeesDaoImpl = new EmployeesDaoImpl();
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
	public List<ReimbursementRequests> insertTransaction(Employees employee, String category, float amount) {
		if(category.equals("LODGING") || category.equals("TRAVEL") || category.equals("FOOD") || category.equals("OTHER")) {
			return true;
		}
		return null;
	}
}
