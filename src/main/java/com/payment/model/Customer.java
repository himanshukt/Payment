package com.payment.model;

public class Customer {
	
	private String merchantCustomerId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	public Customer(String customerID, String firstName, String lastName, String email, String phone) {
		super();
		this.merchantCustomerId = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	public String getmerchantCustomerId() {
		return merchantCustomerId;
	}
	public void setCustomerID(String customerID) {
		this.merchantCustomerId = customerID;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [merchantCustomerId=" + merchantCustomerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + "]";
	}
	
}
