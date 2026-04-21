package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.entity.Driver;
import com.util.DatabaseConnection;

public class UpdateDriver {

	public static String updateDriver(int driverId, String driverName, String category, int totalDistance) {
		if (driverName == null || category == null || driverName.isBlank() || category.isBlank()) {
			return "Driver name or category cannot be null or blank";
		}

		if (totalDistance < 0) {
			return "Total distance cannot be negative";
		}

		if (driverId <= 0) {
			return "Driver id must be greater than 0";
		}
		int rows = 0;
		String query = "UPDATE driver SET driver_name=?, category=?, total_distance=? WHERE driver_id=?";
		String finalCategory = Driver.normalizeCategory(category);
		if (finalCategory == null) {
			return "Invalid category";
		}
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, driverName.trim());
			preparedStatement.setString(2, finalCategory);
			preparedStatement.setInt(3, totalDistance);
			preparedStatement.setInt(4, driverId);
			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			return "Database error: " + e.getMessage();
		}

		if (rows == 0) {
			return "Driver not found";
		} else {
			return "Driver updated successfully";
		}
	}
}
