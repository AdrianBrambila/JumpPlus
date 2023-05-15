package com.dollarsbank.controller;

import com.dollarsbank.model.Customer;

public class DollarsBankController {

	public void deposit(Customer cust, double amount) {

		double before = cust.getMoney();

		double after = before + amount;

		cust.setMoney(after);

	}

	public void withdrawl(Customer cust, double amount) {
		double before = cust.getMoney();
		if (amount > before) {
			System.out.println("\u001B[31mInsufficient funds!\u001B[0m");

		} else {
			double after = before - amount;

			cust.setMoney(after);
			// double total = cust.getMoney() - amount;
			System.out.println("Withdrew " + amount + ", new total is: " + cust.getMoney());
			cust.transactions.add("Withdrawl: $" + amount + " Total: $" + cust.getMoney());


		}

	}

	public void transfer(Customer c1, Customer c2, double amount) {
		double before = c1.getMoney();
		if (amount > before) {
			System.out.println("\u001B[31mInsufficient funds!\u001B[0m");

		} else {
			double c1Money = c1.getMoney() - amount;
			c1.setMoney(c1Money);

			double c2Money = c2.getMoney() + amount;
			c2.setMoney(c2Money);
		}
	}

	public void recentTransactions(Customer cust) {
		if (cust.transactions.size() > 5) {

			for (int i = cust.transactions.size() - 5; i < cust.transactions.size(); i++) {
				System.out.println(cust.transactions.get(i));
			}

		} else {
			for (String s : cust.transactions) {
				System.out.println(s);
			}
		}
	}

	public void custInfo() {

	}

}
