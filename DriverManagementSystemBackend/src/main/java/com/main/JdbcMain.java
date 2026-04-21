package com.main;

import java.util.List;

import com.entity.Driver;
import com.service.AddDriver;
import com.service.CountDriversByCategory;
import com.service.DeleteDriver;
import com.service.DriverWithMaximumDistance;
import com.service.UpdateDriver;
import com.service.ViewDriverById;
import com.service.ViewDriversByCategory;

public class JdbcMain {

	public static void main(String[] args) {

//		String addDriver = AddDriver.addDriver(6, "Surya", "Auto",50000);
//		System.out.println(addDriver);
//		
		Driver viewDriverById = ViewDriverById.viewDriverById(2);
		if (viewDriverById == null) {
			System.out.println("Driver not found");
		} else {
			System.out.println(viewDriverById);
		}

		List<Driver> viewDriversByCategory = ViewDriversByCategory.viewDriversByCategory("Car");
		System.out.println(viewDriversByCategory);

		int countDriversByCategory = CountDriversByCategory.countDriversByCategory("car");
		System.out.println(countDriversByCategory);

		Driver driverWithMaximumDistance = DriverWithMaximumDistance.driverWithMaximumDistance();
		System.out.println(driverWithMaximumDistance);

		String updateDriver = UpdateDriver.updateDriver(6, "Sasi", "Auto", 60000);
		System.out.println(updateDriver);

		String deleteDriver = DeleteDriver.deleteDriver(6);
		System.out.println(deleteDriver);

	}
}
