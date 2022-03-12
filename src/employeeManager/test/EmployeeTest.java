package employeeManager.test;

import employeeManager.common.*;
import java.time.LocalDate;

public class EmployeeTest {

	public static void main(String[] args) {
		CommissionEmployee e1 = new CommissionEmployee("123", "Loc", "Le", 'M',
				LocalDate.now(), "a", "b", "c", 5000, 0.2);
		System.out.println(e1);
		System.out.println(e1.earnings());
		
		BasePlusCommissionEmployee e2 = new BasePlusCommissionEmployee(
				"456", "Anh", "Nguyen", 'F', LocalDate.now(),
				"a", "b", "c", 4000, 0.3, 500);
		System.out.println(e2);
		System.out.println(e2.earnings());
	}

}
