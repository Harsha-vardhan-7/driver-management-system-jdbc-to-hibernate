package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.entity.Driver;
import com.util.DatabaseConnection;

public class ViewDriversByCategory {

//	- Retrieve all drivers belonging to a specific category
//	- Case-insensitive filtering

	public static List<Driver> viewDriversByCategory(String category) {

		if (category == null || category.isBlank()) {
			return Collections.emptyList();
		}

		String normalized = category.trim().toLowerCase();
		List<Driver> drivers = new ArrayList<>();
		
		String query = "Select driver_id, driver_name, category, total_distance from driver where LOWER(category)=?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setString(1, normalized);

			try (ResultSet rs = preparedStatement.executeQuery();) {
				while (rs.next()) {
					Driver driver = new Driver();
					driver.setDriverId(rs.getInt("driver_id"));
					driver.setDriverName(rs.getString("driver_name"));
					driver.setCategory(rs.getString("category"));
					driver.setTotalDistance(rs.getInt("total_distance"));
					
					drivers.add(driver);
				}
			}
		}
		catch(SQLException e) {
			System.out.println("Database error occurred" + e.getMessage());
		}
		return drivers;
	}
}
