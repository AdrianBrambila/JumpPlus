package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	public int custId;
	public String name;
	public String address;
	public String phoneNumber;
	//public String password;
	public int pin;
	public double money;
	public 	List<String> transactions = new ArrayList<String>();

	
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
	//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public void getTransactions() {
		for(String s: transactions) {
			System.out.println(s);
		}
		//return 0;
	}
	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", money=" + money + "]";
	}

	
	
	
}
