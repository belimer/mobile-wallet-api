package com.comulynx.wallet.rest.api.service;

public class CustomerResponse {
	private String pin;
	private String firstName;
	private String lastName;
	private String email;
	private String customerId;
	private String accountNo;
	private double balance;
	public CustomerResponse(String pin, String firstName, String lastName, String email, String customerId,
			String accountNo, double balance) {
		super();
		this.pin = pin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.customerId = customerId;
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}


