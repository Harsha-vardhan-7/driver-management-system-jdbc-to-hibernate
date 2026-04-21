package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.constants.Constants;

public class DatabaseConnection {

	public static Connection getConnection() {

		try {
			return DriverManager.getConnection(Constants.URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PreparedStatement getPreparedStatement(String query) {

		try {
			return DatabaseConnection.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
