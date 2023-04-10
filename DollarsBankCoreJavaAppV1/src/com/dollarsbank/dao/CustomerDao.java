package com.dollarsbank.dao;

import java.util.List;

import com.dollarsbank.model.Customer;

public interface CustomerDao {

	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(int id);
	
	public Customer getCustomerByLogin(int user_id);

	public boolean addCustomer(Customer c);
	
	public boolean updateBalance(Customer c);

	public boolean updateTransactions(Customer c, String transaction);

	List<String> getTransactionsByUser(Customer c);


	
}
