package com.dollarsbank.utility;

public class BankMenus {

	public void welcomeMenu() {
		
		System.out.println("\n\033[34m+---------------------------+");
		System.out.println("| DOLLARSBANK Welcomes You! |");
		System.out.println("+---------------------------+\033[m");
		System.out.println("1. Create and account");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.println("");
		System.out.println("\033[32mEnter Choice from above!\033[m");


	}
	public void createMenu() {
		System.out.println("\n\033[34m+-------------------------------+");
		System.out.println("| Enter Details For New Account |");
		System.out.println("+-------------------------------+\033[m");
		
	}
	
	public void loginMenu() {
		System.out.println("\n\033[34m+---------------------+");
		System.out.println("| Enter Login Details |");
		System.out.println("+---------------------+\033[m");
	}
	
	public void transactionMenu() {
		System.out.println("\n\033[34m+------------------------+");
		System.out.println("| 5 Recent Transactions: |");
		System.out.println("+------------------------+\033[m");
	}
	
	public void mainMenu() {
		System.out.println("\n\033[34m+------------------+");
		System.out.println("| WELCOME CUSTOMER |");
		System.out.println("+------------------+\033[m");
		System.out.println("1. Deposit Amount");
		System.out.println("2. Withdraw Amount");
		System.out.println("3. Funds Transfer");
		System.out.println("4. View 5 Recent Transactions");
		System.out.println("5. Display Customer Information");
		System.out.println("6. Sign Out");
		System.out.println("");
		System.out.println("\033[32mEnter Choice from above!\033[m");
	}
	
	
	
}

