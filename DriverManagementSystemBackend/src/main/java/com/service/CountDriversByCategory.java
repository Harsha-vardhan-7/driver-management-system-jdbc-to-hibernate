package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Driver;
import com.util.DatabaseConnection;

public class CountDriversByCategory {

	public static int countDriversByCategory(String category) {

		String finalCategory = Driver.normalizeCategory(category);

		if (finalCategory == null) {
			return 0;
		}

		String query = "SELECT COUNT(*) from driver WHERE category=?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, finalCategory);
			try (ResultSet rs = preparedStatement.executeQuery();) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}

		return 0;
	}
}
