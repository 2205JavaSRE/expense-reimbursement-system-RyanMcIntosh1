package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.ds.PGPoolingDataSource;

public class ConnectionFactory {
	//Is going to control our connection to our database. 
		// It will deal with all the configuration details, and simply return a Connection. 
	public static Connection dataBaseConnection(){	
		final String URL = System.getenv("Endpoint");
		final String USERNAME = System.getenv("DBUsername");
		final String PASSWORD = System.getenv("DBPassword");
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection somehow not reached");
		}
		return connection;

	}
		
}
