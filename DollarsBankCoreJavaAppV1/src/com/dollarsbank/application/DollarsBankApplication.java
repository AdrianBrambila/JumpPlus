package com.dollarsbank.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.dao.CustomerDaoSQL;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.BankMenus;

public class DollarsBankApplication {

	public static void main(String[] args) {
		List<Customer> users = new ArrayList<Customer>();
		BankMenus menus = new BankMenus();
		DollarsBankController controller = new DollarsBankController();
		CustomerDaoSQL cdao = new CustomerDaoSQL();

		Scanner s = new Scanner(System.in);

		boolean b = true;
		while (b) {
			

			users = cdao.getAllCustomers();
			for (int j = 0; j < users.size(); j++) {

				Customer printCust = users.get(j);
				String str = printCust.toString();
				System.out.println(j + 1 + " | " + str + "\n");

			}
			menus.welcomeMenu();
			int choice = s.nextInt();
			s.nextLine();

			switch (choice) {
			case 1: { // create

				Customer newCust = new Customer();
				System.out.println("Customer Name: \u001B[36m");
				String name = s.nextLine();
				System.out.println("\u001B[0m");
				newCust.setName(name);

				System.out.println("Customer Address: \u001B[36m");
				String address = s.nextLine();
				System.out.println("\u001B[0m");
				newCust.setAddress(address);

				System.out.println("Customer Contact Number: \u001B[36m");
				String num = s.nextLine();
				System.out.println("\u001B[0m");
				newCust.setPhoneNumber(num);

				System.out.println("User Id: \u001B[36m");
				int id = s.nextInt();
				s.nextLine();
				System.out.println("\u001B[0m");
				newCust.setCustId(id);

				System.out.println("Enter Pin: \u001B[36m");
				int pin = s.nextInt();
				s.nextLine();
				System.out.println("\u001B[0m");
				newCust.setPin(pin);

				System.out.println("Initial Deposit Amount: \u001B[36m");
				Double init = s.nextDouble();
				s.nextLine();
				System.out.println("\u001B[0m");
				newCust.setMoney(init);

				cdao.addCustomer(newCust);
				
				String trans = "Initial Deposit: $" + init + " Total: $\u001B[0m" + newCust.getMoney();
				cdao.updateTransactions(newCust, trans);
			
				newCust.toString();
				users = cdao.getAllCustomers();

				break;

			}
			case 2: { // login
				//users = cdao.getAllCustomers();

				if (users.isEmpty()) {
					System.out.println("No users in the database, please create a new user!");
					break;
				}

				menus.loginMenu();

				System.out.println("User ID: \u001B[36m");
				// int id = Integer.parseInt(s.nextLine());
				int id = s.nextInt();
				s.nextLine();
				System.out.println("\u001B[0m");
				
				System.out.println(id);

				System.out.println("Pin:\u001B[36m ");
				int pass = s.nextInt();
				s.nextLine();
				System.out.println("\u001B[0m");

				// for (int i = 0; i < users.size(); i++) {
				// Customer user = users.get(i);
				Customer user = cdao.getCustomerByLogin(id);

				if (user.custId == id) {
					if (user.pin == pass) {

						// int loggedId = i;
						boolean b2 = true;
						while (b2) {
							users = cdao.getAllCustomers();
							menus.mainMenu();
							choice = s.nextInt();
							switch (choice) {
							case 1: { // deposit
								System.out.println("How much would you like to deposit? \n");
								double amt = s.nextInt();
								s.nextLine();
								controller.deposit(user, amt);
								System.out.println("Deposited " + amt + ", new total is : " + user.getMoney());

								String trans = "Deposit: $" + amt + " Total: $" + user.getMoney();
								cdao.updateTransactions(user, trans);
								break;
							}
							case 2: {// withdraw
								System.out.println("How much would you like to withdraw? \n");
								int amt = s.nextInt();
								s.nextLine();
								controller.withdrawl(user, amt);

								break;
							}
							case 3: { // transfer
								if (users.size() <= 1) {
									System.out.println("No other users to transfer to!");

								} else {
									System.out.println("Please choose who you want to transfer to: ");
									for (int j = 0; j < users.size(); j++) {

										Customer printCust = users.get(j);
										String str = printCust.toString();
										System.out.println(j + 1 + " | " + str + "\n");

									}
									int tchoice = s.nextInt();
									s.nextLine();

									//Customer recipient = users.get(tchoice);
									Customer recipient = cdao.getCustomerByLogin(tchoice);


									System.out.println("How much would you like to send?");
									double send = s.nextDouble();

									controller.transfer(user, recipient, send);

									String trans = "Sent: $" + send + " To user: " + recipient.getName() + " New total: $" + user.getMoney();
									cdao.updateTransactions(user, trans);
									System.out.println(trans);

									String trans2 = "Recieved: $" + send + " From user: " + user.getName() + " New total: $" + recipient.getMoney();
									cdao.updateTransactions(recipient, trans2);
									System.out.println(trans2);

								}

								break;
							}
							case 4: { // view 5
								controller.recentTransactions(user);
								// user.getTransactions();
								break;
							}
							case 5: { // display info
								System.out.println(user);

								break;
							}
							case 6: { // sign out
								b2 = false;
								break;
							}

							}
						}

					} else {
						System.out.println("\u001B[31mIncorrect Username or Password, please try again!\u001B[0m");
					}

				}
			}

				break;

			// }
			case 3: { // exit
				b = false;
				break;
			}
			}

		}
		System.out.println("Thank you for choosing DOLLARSBANK");
		s.close();
	}

}



