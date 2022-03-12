package employeeManager.common;

import java.time.LocalDate;

public class CommissionEmployee extends Employee {
	//auto-generated serialVersionUID
	private static final long serialVersionUID = 7086683971537348576L;
	
	//instance variable
	protected double totalSales;
	protected double commissionRate;
	
	//getters and setters
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double sales) {
		if (sales >= 0.0)
			totalSales = sales;
		else throw new IllegalArgumentException("Total sales must be >= 0.0.");
	}
	
	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double rate) {
		if (rate > 0.0 && rate < 1.0)
			commissionRate = rate;
		else throw new IllegalArgumentException("Commission rate must be > 0 and < 1.");
	}
	
	//constructors
	public CommissionEmployee(String id, String firstName, String lastName, char gender,
			LocalDate doB, String address, String phone, String email,
			double totalSales, double commissionRate) {
		super(id, firstName, lastName, gender, doB, address, phone, email);
		setTotalSales(totalSales);
		setCommissionRate(commissionRate);
	}
	
	//method
	public double earnings() {
		return commissionRate * totalSales;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %c, %s, %s, %s, %s, %.2f, %.2f",
				id, firstName, lastName, gender, doB, address, phone, email, totalSales, commissionRate);
	}
}
