package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Driver;
import com.util.DatabaseConnection;

public class DriverWithMaximumDistance {

	public static Driver driverWithMaximumDistance() {

		String query = "SELECT driver_id, driver_name, category, total_distance FROM driver ORDER BY total_distance DESC LIMIT 1";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();) {
			if (rs.next()) {
				Driver driver = new Driver();
				driver.setDriverId(rs.getInt("driver_id"));
				driver.setDriverName(rs.getString("driver_name"));
				driver.setCategory(rs.getString("category"));
				driver.setTotalDistance(rs.getInt("total_distance"));
				return driver;
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return null;
	}
}
