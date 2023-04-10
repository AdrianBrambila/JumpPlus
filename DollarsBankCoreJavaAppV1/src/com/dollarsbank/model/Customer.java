package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	public int id;
	public int custId;
	public String name;
	public String address;
	public String phoneNumber;
	public int pin;
	public double money;

	

	public Customer() {
		super();
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getPin() {
			return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", phoneNumber=" + phoneNumber;
	}

	
	
	
}
