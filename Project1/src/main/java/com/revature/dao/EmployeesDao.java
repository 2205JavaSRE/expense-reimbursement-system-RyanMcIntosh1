package com.revature.dao;

import java.util.List;

import com.revature.models.Employees;

public interface EmployeesDao {
	public List<Employees> selectEmployeesById(int input);
	public Employees selectEmployeeIfFinancialManager(int id);
}
