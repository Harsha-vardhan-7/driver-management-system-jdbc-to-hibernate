package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DatabaseConnection;

public class DeleteDriver {

	public static String deleteDriver(int driverId) {

		if (driverId <= 0) {
			return "Driver ID must be greater than 0";
		}

		String query = "DELETE FROM driver WHERE driver_id=?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, driverId);

			int rows = preparedStatement.executeUpdate();
			if (rows == 0) {
				return "Driver not found";
			} else {
				return "Driver deleted successfully";
			}

		} catch (SQLException e) {
			return "Database error: " + e.getMessage();
		}

	}

}
