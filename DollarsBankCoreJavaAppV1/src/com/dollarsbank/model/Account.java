package com.dollarsbank.model;

public class Account {

	public int accountId;
	public int custId;
	public String name;
	public String password;
	public int pin;
	
	
	
	

	public Account(int accountId, int custId, String name, String password, int pin) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.password = password;
		this.pin = pin;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
	
	
	
}
