package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mysql.DBConnection;

public class Item {
	private int itemId;
	private String itemName;
	private int quantity;
	private String department;
	private BigDecimal price;
	private String description;
	private User seller;
	private String imageUrl;

	public Item(int itemId) {
		this.itemId = itemId;

		Connection con;
		try {
			con = DBConnection.getConnection();
			String query = "select * from item where item_id = " + itemId + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.itemName = rs.getString("item_name");
				this.quantity = rs.getInt("quantity");
				this.department = rs.getString("department");
				this.price = rs.getBigDecimal("price");
				this.description = rs.getString("description");
				this.imageUrl = rs.getString("image_url");
			}
			
			seller = new User(rs.getInt("seller_id"));
			DBConnection.close(con, rs, st);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public Item(String itemName, int quantity, String department, BigDecimal price, String description, int sellerID) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.department = department;
		this.price = price;
		this.description = description;
		this.seller = new User(sellerID);
	}


	public Item() {
		// TODO Auto-generated constructor stub
	}



	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", department=" + department + ", price=" + price
				+ ", seller=" + seller + "]";
	}

	public void addItemToDB() {
		try {
			Connection con = DBConnection.getConnection();

			String query = "insert into item(item_name, quantity, department, price, " + "description, seller_id, image_url)"
					+ "VALUES( '" + itemName + "', " + quantity + ", '" + department + "'," + price + ", '"
					+ description + "', " + seller.getUser_id() + ",'" + imageUrl + " ');";
			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();

		}
	}

	// deletes this item from db
	public void deleteItemFromDB() {
		try {
			Connection con = DBConnection.getConnection();
			String query = "delete from item where item_id = " + itemId + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// decrements quantity of item in db when bought
	public void decrementQuantity() {
		try {
			Connection con = DBConnection.getConnection();
			String query = "update item set quantity = quantity -1 where quantity > 0 and item_id = " + itemId + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItemToCart(int userId) {
		try {
			Connection con = DBConnection.getConnection();
			String query = "insert into cart_items(customer_id, item_id) values(" + userId + ", " + itemId + ");";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnection.close(con, null, st);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItemToSeller(int userId) {
		try {
			Connection con = DBConnection.getConnection();
			String query = "insert into seller_items(seller_id, item_id) values(" + userId + ", " + itemId + ");";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnection.close(con, null, st);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteItemFromCart(int userId) {
		try {
			Connection con = DBConnection.getConnection();
			String query = "delete from cart_items where customer_id = " + userId + " and item_id = " + itemId + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnection.close(con, null, st);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getMaxItemId() {
		int max = 0;
		try {
			Connection con = DBConnection.getConnection();
			String query = "select max(item_id) from item;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				max = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return max;

	}
	
	public static ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from item;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				Item i = new Item(rs.getInt("item_id"));
				i.itemId = rs.getInt("item_id");
				i.itemName = rs.getString("item_name");
				i.department = rs.getString("department");
				i.description = rs.getString("description");
				i.price = rs.getBigDecimal("price");
				i.quantity = rs.getInt("quantity");
				i.seller = new User(rs.getInt("seller_id"));
				i.imageUrl = rs.getString("image_url");
				
				items.add(i);
			}
			DBConnection.close(con, rs, st);
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	public static ArrayList<Item> searchItems(String search){
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from item where item_name like '%" + search + "%';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Item i = new Item(rs.getInt("item_id"));
				i.itemId = rs.getInt("item_id");
				i.itemName = rs.getString("item_name");
				i.department = rs.getString("department");
				i.description = rs.getString("description");
				i.price = rs.getBigDecimal("price");
				i.quantity = rs.getInt("quantity");
				i.seller = new User(rs.getInt("seller_id"));
				i.imageUrl = rs.getString("image_url");
				
				items.add(i);
			}
			DBConnection.close(con, rs, st);
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	public static ArrayList<Item> searchItemsByDept(String department){
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from item where department = '" + department + "';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Item i = new Item(rs.getInt("item_id"));
				i.itemId = rs.getInt("item_id");
				i.itemName = rs.getString("item_name");
				i.department = rs.getString("department");
				i.description = rs.getString("description");
				i.price = rs.getBigDecimal("price");
				i.quantity = rs.getInt("quantity");
				i.seller = new User(rs.getInt("seller_id"));
				i.imageUrl = rs.getString("image_url");
				
				items.add(i);
			}
			DBConnection.close(con, rs, st);
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	public static ArrayList<Item> getCartItems(int userID){
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from cart_items where customer_id = '" + userID + "';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				items.add(new Item(itemId));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	public static ArrayList<Item> getSellerItems(int userID){
		
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from item where seller_id = '" + userID + "';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Item i = new Item(rs.getInt("item_id"));
				i.itemId = rs.getInt("item_id");
				i.itemName = rs.getString("item_name");
				i.department = rs.getString("department");
				i.description = rs.getString("description");
				i.price = rs.getBigDecimal("price");
				i.quantity = rs.getInt("quantity");
				i.seller = new User(rs.getInt("seller_id"));
				i.imageUrl = rs.getString("image_url");
				
				items.add(i);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	public static ArrayList<Item> getOrderItems(int orderID){
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from order_items where order_id = '" + orderID + "';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				items.add(new Item(itemId));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	

	
	

}
