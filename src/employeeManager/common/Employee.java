package employeeManager.common;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Employee implements Serializable {
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -6283578906069193774L;
	
	//instance variables
	protected String id;
	protected String firstName;
	protected String lastName;
	protected char gender;
	protected LocalDate doB;
	protected String address;
	protected String phone;
	protected String email;
	
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public LocalDate getDoB() {
		return doB;
	}
	public void setDoB(LocalDate doB) {
		this.doB = doB;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//constructors
	public Employee(String id, String firstName, String lastName, char gender,
			LocalDate doB, String address, String phone, String email) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setDoB(doB);
		setAddress(address);
		setPhone(phone);
		setEmail(email);
	}
	
	//methods
	public abstract double earnings();
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %c, %s, %s, %s, %s",
				id, firstName, lastName, gender, doB, address, phone, email);
	}
}
