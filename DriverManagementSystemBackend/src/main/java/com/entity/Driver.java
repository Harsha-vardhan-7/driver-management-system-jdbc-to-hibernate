package com.entity;

public class Driver {

	private int driverId;
	private String driverName;
	private String category;
	private int totalDistance;

	public Driver() {

	}

	public Driver(int driverId, String driverName, String category, int totalDistance) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.category = category;
		this.totalDistance = totalDistance;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public static String normalizeCategory(String category) {

		if (category == null || category.isBlank()) {
			return null;
		}

		String normalized = category.trim().toLowerCase();

		switch (normalized) {

		case "car": return "Car";

		case "auto": return "Auto";

		case "lorry": return "Lorry";

		default: return null;

		}

	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", category=" + category
				+ ", totalDistance=" + totalDistance + "]";
	}

}
