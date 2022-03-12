package employeeManager.common;

import java.time.LocalDate;

public class BasePlusCommissionEmployee extends CommissionEmployee {
	//auto-generated serialVersionUID
	private static final long serialVersionUID = 5579225851683350585L;
	
	//instance variables
	protected double baseSalary;
	
	//getters and setters
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double salary) {
		if (salary > 0.0)
			baseSalary = salary;
		else throw new IllegalArgumentException("The base salary must be a positive number.");
	}

	//constructors
	public BasePlusCommissionEmployee(String id, String firstName, String lastName, char gender,
			LocalDate doB, String address, String phone, String email,
			double totalSales, double commissionRate, double baseSalary) {
		super(id, firstName, lastName, gender, doB, address, phone, email, totalSales, commissionRate);
		setBaseSalary(baseSalary);
	}
	
	//methods
	@Override
	public double earnings() {
		return super.earnings() + baseSalary;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %c, %s, %s, %s, %s, %.2f, %.2f, %.2f",
				id, firstName, lastName, gender, doB, address, phone, email,
				totalSales, commissionRate, baseSalary);
	}
}
