package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employees;
import com.revature.models.ReimbursementRequests;
import com.revature.util.ConnectionFactory;


public class EmployeesDaoImpl implements EmployeesDao {

	public Employees selectEmployeeByCred(String username, String password) {
		try {
			String sql = "SELECT * FROM employee_project_0 WHERE username = ? AND password_for_user = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employees employee = new Employees(rs.getInt("id"), rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("password_for_user"),rs.getBoolean("financial_manager"));
				return employee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Employees> selectEmployeesById(int input) {
		// TODO Auto-generated method stub
		List<Employees> employeesList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM employee_project_0 WHERE id = ?";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employees employee = new Employees(rs.getInt("id"), rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("password_for_user"),rs.getBoolean("financial_manager"));
				employeesList.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeesList;
	}

	@Override
	public Employees selectEmployeeIfFinancialManager(int id) {
		try {
			String sql = "SELECT * FROM employee_project_0 WHERE financial_manager = true AND id = ";
			Connection connection = ConnectionFactory.dataBaseConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employees employee = new Employees(rs.getInt("id"), rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("password"),rs.getBoolean("employee"));
				return employee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
	}
	
}
