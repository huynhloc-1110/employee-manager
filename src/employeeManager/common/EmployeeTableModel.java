package employeeManager.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 8306859669618792933L;

	private String[] columnNames = {
		"Id", "First Name", "Last Name", "Gender", "DoB", "Address", "Phone",
		"Email", "Sales", "Rate", "Base Salary", "Total earning"
	};
	
	private List<Employee> employeeList;
	
	public EmployeeTableModel() {
		this.employeeList = new ArrayList<Employee>();
	}
	
	public EmployeeTableModel(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	//Override
	public int getColumnCount() {
		return columnNames.length;
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}
	public int getRowCount() {
		return employeeList.size();
	}
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 3: return char.class;
		case 4: return LocalDate.class;
		case 8: case 9 : case 10: return double.class;
		case 11: return double.class;
		default: return String.class;
		}
	}
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	public Object getValueAt(int row, int column) {
		Employee e = employeeList.get(row);
		switch (column) {
		case 0: return e.getId();
		case 1: return e.getFirstName();
		case 2: return e.getLastName();
		case 3: return e.getGender();
		case 4: return e.getDoB();
		case 5: return e.getAddress();
		case 6: return e.getPhone();
		case 7: return e.getEmail();
		case 8:
			if (e instanceof CommissionEmployee)
				return ((CommissionEmployee)e).getTotalSales();
			else return null;
		case 9:
			if (e instanceof CommissionEmployee)
				return ((CommissionEmployee)e).getCommissionRate();
			else return null;
		case 10:
			if (e instanceof BasePlusCommissionEmployee)
				return ((BasePlusCommissionEmployee)e).getBaseSalary();
			else return null;
		case 11: return e.earnings();
		default: return null;
		}
	}
	public void setValueAt(Object value, int row, int column) {
		Employee e = employeeList.get(row);
		switch (column) {
		case 0: e.setId((String)value); break;
		case 1: e.setFirstName((String)value); break;
		case 2: e.setLastName((String)value); break;
		case 3: e.setGender((char)value); break;
		case 4: e.setDoB((LocalDate)value); break;
		case 5: e.setAddress((String)value); break;
		case 6: e.setPhone((String)value); break;
		case 7: e.setEmail((String)value); break;
		case 8:
			if (e instanceof CommissionEmployee)
				((CommissionEmployee)e).setTotalSales((double)value);
			break;
		case 9:
			if (e instanceof CommissionEmployee)
				((CommissionEmployee)e).setCommissionRate((double)value);
			break;
		case 10:
			if (e instanceof BasePlusCommissionEmployee)
				((BasePlusCommissionEmployee)e).setBaseSalary((double)value);
			break;
		}
	}
	
	//additional methods
	public void addEmployee(Employee e) {
		int row = getRowCount();
		employeeList.add(row, e);
		fireTableRowsInserted(row, row);
	}
	public void removeEmployee(int row) {
		employeeList.remove(row);
		fireTableRowsDeleted(row, row);
	}
	public void removeAllEmployee() {
		int row = getRowCount();
		employeeList.clear();
		fireTableRowsDeleted(0, row);
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void updateEmployee(Employee e, int row) {
		employeeList.remove(row);
		employeeList.add(row, e);
		fireTableRowsUpdated(row, row);
	}
}
