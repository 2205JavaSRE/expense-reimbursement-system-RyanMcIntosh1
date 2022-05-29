package com.revature.dao;

import java.util.List;

import com.revature.models.Employees;

public interface EmployeesDao {
	public Employees selectEmployeeById(Employees r);
	public List<Employees> selectEmployeesById(Employees r);
	public Employees selectEmployeeIfFinancialManager(Employees r);
}
