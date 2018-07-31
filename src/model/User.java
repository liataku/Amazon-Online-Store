package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import mysql.DBConnection;

public class User {

	private String userEmail;
	private String user_Password;
	private int user_id;
	private String accountType;
	private String first_name;
	private String last_name;
	private String middle_name = "";
	private String userAddress;

	public User(String userEmail) {
		this.userEmail = userEmail;

		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			String query = "select * from users where user_email = '" + userEmail + "';";
			st = con.createStatement();
			rs = st.executeQuery(query);

			if (rs.next()) {
				this.user_id = rs.getInt("user_id");
				this.user_Password = rs.getString("user_password");
				this.first_name = rs.getString("user_firstname");
				this.middle_name = rs.getString("user_middlename");
				this.last_name = rs.getString("user_lastname");
				this.userAddress = rs.getString("user_address");
				//this.accountType = rs.getString("account_type);

				if (isCustomer(user_id))
					accountType = "customer";
				else if (isSeller(user_id))
					accountType = "seller";
				else
					accountType = "employee";
			}

			DBConnection.close(con, rs, st);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public User(String email, String password, String account, String firstName, String lastName,
			String middleName, String userAddress) {

		this.userEmail = email;
		this.user_Password = password;
		this.accountType = account;
		this.first_name = firstName;
		this.last_name = lastName;
		this.middle_name = middleName;
		this.userAddress = userAddress;
		
	}

	public User(int id) {
		this.user_id = id;

		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnection.getConnection();
			String query = "select * from users where user_id = " + id + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);

			if (rs.next()) {
				this.userEmail = rs.getString("user_email");
				this.user_Password = rs.getString("user_password");
				this.first_name = rs.getString("user_firstname");
				this.middle_name = rs.getString("user_middlename");
				this.last_name = rs.getString("user_lastname");
				this.userAddress = rs.getString("user_address");

				if (isCustomer(id))
					accountType = "customer";
				else if (isSeller(id))
					accountType = "seller";
				else
					accountType = "employee";
			}

			DBConnection.close(con, rs, st);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + ", user_id=" + user_id + ", accountType=" + accountType
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", userAddress=" + userAddress + "]";
	}

	

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getUsername() {
		return userEmail;
	}

	public String getPassword() {
		return user_Password;
	}

	public void setPassword(String password) {
		this.user_Password = password;
	}
	
	public void addUserToDB(){
		
		addUserToUser();
		addUserIdToObject();
		
		if(accountType.equals("seller")) {
			addUserToSeller();
		}
		
		else if(accountType.equals("customer")) {
			addUserToCustomer();
		}
		else {
			addUserToEmployee();
		}
	}
	
	private void addUserToUser() {
		

		try {
			
			Connection con = DBConnection.getConnection();
			
			String query = "insert into users(user_password,"
					+ "user_firstname,user_middlename,user_lastname," + 
					"user_address,user_email)"
					+ "VALUES("+ "\'" + user_Password+"\'," + "\'" + 
					first_name+"\',"
					+ "\'" + middle_name+"\'," + "\'" + last_name+"\',"
					+ "\'" + userAddress+"\'," + "\'" + userEmail+"\')";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();

		} 
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		
		}
	}
	
	private void addUserToSeller() {
		

		try {
			
			Connection con = DBConnection.getConnection();
			
			String query = "insert into seller(seller_ID)"
					+ "VALUES("+user_id+");";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();

		} 
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		
		}
	}
	
	private void addUserToCustomer() {
		
		try {
			
			Connection con = DBConnection.getConnection();
			
			String query = "insert into customer(customer_ID)"
					+ "VALUES("+user_id+");";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();

		} 
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		
		}
	}
private void addUserToEmployee() {
		
		try {
			
			Connection con = DBConnection.getConnection();
			
			String query = "insert into employee(employee_ID)"
					+ "VALUES("+user_id+");";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();

		} 
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		
		}
}

	
	public static ArrayList<User> getUsersForEmployee(){
		ArrayList<User> users = new ArrayList<User>();
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from users;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				User u = new User(rs.getInt("user_id"));
				if(isCustomer(rs.getInt("user_id"))) {
					u.accountType = "customer";
					users.add(u);
				}
				else if(isSeller(rs.getInt("user_id"))) {
					u.accountType = "seller";
					users.add(u);
				}

			}
			DBConnection.close(con, rs, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			
		}
		return users;
	}
	public void placeOrder(int billingId) {
		ArrayList<Item> cartItems = Item.getCartItems(user_id);
		if(cartItems.isEmpty()) return;
		Order order = new Order();
		order.setCustomer(this);
		order.setOrder_date(new Date(Calendar.getInstance().getTime().getTime()));
		BigDecimal total = new BigDecimal(0);
	
		
		
		
		for(Item item: cartItems) {
			item.deleteItemFromCart(user_id);
			total = total.add(item.getPrice());
			System.out.println(item.getPrice());
			Payment p = new Payment();
			p.setAmount(item.getPrice());
			p.setBillingInfo(new BillingInfo(billingId));
			p.setCustomer(this);
			p.setPaymentType("credit card");
			p.setSeller(item.getSeller());
			p.addPaymentToDB();
			
		}
		System.out.println(total);
		order.setTotal(total);
		order.addOrderToDB();
		order.setOrder_ID(Order.getMaxOrderId());
		
		for(Item item: cartItems) {
			order.addOrderItem(item.getItemId());
		}
		
		Shipment s = new Shipment();
		s.setCarrier("UPS");
		s.setCharge(10);
		s.setCustomer(this);
		s.setReturnAddress("Shop headquarters");
		s.setTrackingNumber((float) Math.random()*1000000);
		s.setOrder(order);
		s.addShipmentToDB();
		
		
	}
	
	public void addUserIdToObject() {
		
		try {
			
			Connection con = DBConnection.getConnection();
			
			String query = "Select user_ID from users Where user_email = \'" + userEmail
					+ "\'";
					
			Statement st = con.createStatement();
			ResultSet rs;
			
			rs = st.executeQuery(query);
			rs.next();
			user_id = rs.getInt("user_ID");
			
			
			con.close();

		} 
		
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		
		}
	}
	
	public void updateUser() {
		
		try {
			

			
			Connection con = DBConnection.getConnection();
			
			String query = "update users set user_firstname = \'" + first_name+"\'"+ ", user_middlename = \'" + middle_name+"\'"+
			
						", user_lastname = \'" + last_name+"\'" +", user_address = \'" + userAddress +"\'"+ ", user_email = \'"+ userEmail + "\'" +
							" where user_id = "+ user_id ;

			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			

			
			
			con.close();
			

		} 
		
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		
		}
	}
	
	public void deleteUserFromDB(){
		
		deleteFromUsers();
		
		
		if(accountType == "customer") {
			deleteFromCustomer();
		}
		else {
			deleteFromSeller();
		}
	}
	
	private void deleteFromUsers() {
		try {
			
			Connection con = DBConnection.getConnection();
			String query = "delete from users where user_ID = " + user_id + ";";
			//String query = "delete from users where user_ID = " + 9876 + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			
		}
		
	}
	
	private void deleteFromSeller() {
		try {
			
			Connection con = DBConnection.getConnection();
			String query = "delete from seller where seller_ID = " + user_id + ";";
			//String query = "delete from seller where seller_ID = " + 9876 + ";";

			Statement st = con.createStatement();
			st.executeUpdate(query);
			con.close();
			
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			
		}
		
	}
	
	private void deleteFromCustomer() {
		try {
			
			Connection con = DBConnection.getConnection();
			String query = "delete from customer where customer_ID = " + user_id + ";";
			//String query = "delete from customer where customer_ID = " + 9876 + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);

			con.close();
			
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			
		}
		
	}

	public static boolean isCustomer(int id) {
		boolean isCustomer = false;
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from customer where customer_id = " + id + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				// result set is not empty, user exists
				isCustomer = true;
			}
			DBConnection.close(con, rs, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			return false;
		}
		return isCustomer;
	}

	public static boolean isSeller(int id) {
		boolean isSeller = false;
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from seller where seller_id = " + id + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				// result set is not empty, user exists
				isSeller = true;
			}
			DBConnection.close(con, rs, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			return false;
		}
		return isSeller;
	}
	
	
	
	


}