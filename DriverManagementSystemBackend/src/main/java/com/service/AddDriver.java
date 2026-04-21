package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DatabaseConnection;

public class AddDriver {

    public static String addDriver(int driverId, String driverName, String category, int totalDistance) {

        // 1. Basic Validations
        if (driverName == null || driverName.isBlank()) {
            return "Driver name cannot be null or blank";
        }

        if (category == null || category.isBlank()) {
            return "Category cannot be null or blank";
        }

        if (driverId <= 0) {
            return "Driver ID must be greater than 0";
        }

        if (totalDistance < 0) {
            return "Total distance cannot be negative";
        }

        // 2. Normalize Category
        String normalized = category.trim().toLowerCase();
        String finalCategory;

        switch (normalized) {
            case "car":
                finalCategory = "Car";
                break;
            case "auto":
                finalCategory = "Auto";
                break;
            case "lorry":
                finalCategory = "Lorry";
                break;
            default:
                return "Invalid category";
        }

        String insertQuery = "INSERT INTO driver (driver_id, driver_name, category, total_distance) VALUES (?,?,?,?)";
        String selectQuery = "SELECT 1 FROM driver WHERE driver_id = ?";

        // 3. DB Operations using try-with-resources (BEST PRACTICE)
        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement selectPs = conn.prepareStatement(selectQuery);
            PreparedStatement insertPs = conn.prepareStatement(insertQuery)
        ) {

            // Check duplicate
            selectPs.setInt(1, driverId);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    return "Duplicate ID value";
                }
            }

            // Insert
            insertPs.setInt(1, driverId);
            insertPs.setString(2, driverName.trim());
            insertPs.setString(3, finalCategory);
            insertPs.setInt(4, totalDistance);

            int rows = insertPs.executeUpdate();

            if (rows > 0) {
                return "Driver added successfully";
            } else {
                return "Failed to add driver";
            }

        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        }
    }
}