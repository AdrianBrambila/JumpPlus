package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Customer;

public class CustomerDaoSQL implements CustomerDao {

	private Connection conn = ConnectionManager.getConnection();

	
	@Override
	public List<Customer> getAllCustomers() {
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_list");
			
			List<Customer> custList = new ArrayList<Customer>();
			
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("login_id");
				String username = rs.getString("user_name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				int pin = rs.getInt("pin");
				double money = rs.getDouble("balance");
				//List<String> transactions = new ArrayList<String>();
				
				Customer cust = new Customer();
				cust.setId(id);
				cust.setCustId(user_id);
				cust.setName(username);
				cust.setAddress(address);
				cust.setPhoneNumber(phone);
				cust.setPin(pin);
				cust.setMoney(money);
				
				custList.add(cust);
			
		}
		return custList;
		}catch (SQLException e) {
			System.out.println("Could not retrieve list of users");

		}
		
		return null;
	}

	@Override
	public Customer getCustomerById(int id) {
		try {
			// set up prepared statement to get a department using its id
			PreparedStatement pstmt = conn.prepareStatement("select * from user_list where id = ?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.first();

			int user_id = rs.getInt("login_id");
			String username = rs.getString("user_name");
			String address = rs.getString("address");
			String phone = rs.getString("phone");
			int pin = rs.getInt("pin");
			double money = rs.getDouble("balance");
			
			Customer cust = new Customer();
			cust.setCustId(user_id);
			cust.setName(username);
			cust.setAddress(address);
			cust.setPhoneNumber(phone);
			cust.setPin(pin);
			cust.setMoney(money);
			return cust;

		} catch (SQLException e) {
			System.out.println("User with id = " + id + " not found.");
		}

		return null;		
	}

	@Override
	public Customer getCustomerByLogin(int uid) {
		try (PreparedStatement pstmt = conn.prepareStatement("select * from user_list where login_id = ?")){
			
			pstmt.setInt(1, uid);
			
			ResultSet rs = pstmt.executeQuery();

			//rs.first();
			Customer cust = new Customer();

			if(rs.next()) {
			int id = rs.getInt("id");
			int user_id = rs.getInt("login_id");
			String username = rs.getString("user_name");
			String address = rs.getString("address");
			String phone = rs.getString("phone");
			int pin = rs.getInt("pin");
			double money = rs.getDouble("balance");


			cust.setId(id);
			cust.setName(username);
			cust.setAddress(address);
			cust.setPhoneNumber(phone);
			cust.setCustId(user_id);
			cust.setPin(pin);
			cust.setMoney(money);
			}
			rs.close();
			
			return cust;

		} catch (SQLException e) {
			System.out.println("User with id = " + uid + " not found.");
		}

		return null;		
	}

	@Override
	public boolean addCustomer(Customer c) {
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT into user_list(id, user_name, address, phone, login_id, pin, balance) values(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, 0);
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getAddress());
			pstmt.setString(4, c.getPhoneNumber());
			pstmt.setInt(5, c.getCustId());
			pstmt.setInt(6, c.getPin());
			pstmt.setDouble(7, c.getMoney());


			int i = pstmt.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateBalance(Customer c) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE user_list SET balance = ? WHERE login_id = ?");
			pstmt.setDouble(1, c.getMoney());
			pstmt.setInt(2, c.getCustId());
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateTransactions(Customer c, String transaction) {
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT into transactions(trans_id, user_id, transaction_name) values(?, ?, ?)")){
			
			pstmt.setInt(1, 0);
			pstmt.setInt(2, c.getCustId());
			pstmt.setString(3, transaction);
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public List<String> getTransactionsByUser(Customer c) {
		
		List<String> transactions = new ArrayList<String>();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM transactions where user_id = " + c.getCustId());){
			
			
			while (rs.next()) {
//				int trans_id = rs.getInt("trans_id");
//				int user_id = rs.getInt("user_id");
				String transaction_name = rs.getString("transaction_name");

				
				transactions.add(transaction_name);
			
		}
		return transactions;
		}catch (SQLException e) {
			System.out.println("Could not retrieve list of transactions");

		}
		
		return null;
	}


}
