package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Driver;
import com.util.DatabaseConnection;

public class ViewDriverById {

	public static Driver viewDriverById(int driverId) {

		if (driverId <= 0) {
			return null;
		}

		String query = "Select driver_id, driver_name, category, total_distance from driver where driver_id=?";
		try (Connection connection = DatabaseConnection.getConnection();							
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, driverId);

			try (ResultSet rs = preparedStatement.executeQuery();) {

				if (rs.next()) {
					Driver driver = new Driver();
					driver.setDriverId(rs.getInt("driver_id"));
					driver.setDriverName(rs.getString("driver_name"));
					driver.setCategory(rs.getString("category"));
					driver.setTotalDistance(rs.getInt("total_distance"));
					return driver;
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error occurred" + e.getMessage());
		}
		return null;
	}
}
