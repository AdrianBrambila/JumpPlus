package com.dollarsbank.controller;

import java.util.List;

import com.dollarsbank.dao.CustomerDaoSQL;
import com.dollarsbank.model.Customer;

public class DollarsBankController {
	
	CustomerDaoSQL cdao = new CustomerDaoSQL();



	public void deposit(Customer cust, double amount) {

		double before = cust.getMoney();

		double after = before + amount;

		cust.setMoney(after);
		
		cdao.updateBalance(cust);
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
			//cust.transactions.add();
			String trans = "Withdrawl: $" + amount + " Total: $" + cust.getMoney();
			cdao.updateBalance(cust);
			cdao.updateTransactions(cust, trans);

		}

	}

	public void transfer(Customer c1, Customer c2, double amount) {
		double before = c1.getMoney();
		if (amount > before) {
			System.out.println("\u001B[31mInsufficient funds!\u001B[0m");

		} else {
			double c1Money = c1.getMoney() - amount;
			c1.setMoney(c1Money);
			cdao.updateBalance(c1);


			double c2Money = c2.getMoney() + amount;
			c2.setMoney(c2Money);
			cdao.updateBalance(c2);

			
		}
	}

	public void recentTransactions(Customer cust) {
		List<String> userTransactions = cdao.getTransactionsByUser(cust);
		
		if (userTransactions.size() > 5) {

			for (int i = userTransactions.size() - 5; i < userTransactions.size(); i++) {
				System.out.println(userTransactions.get(i));
			}

		} else {
			for (String s : userTransactions) {
				System.out.println(s);
			}
		}
	}

	public void custInfo() {

	}

}
