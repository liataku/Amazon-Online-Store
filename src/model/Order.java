package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mysql.DBConnection;

public class Order {

	private int order_ID;
	private User customer;
	private String order_status;
	private BigDecimal total;
	private Date order_date;

	public Order(int order_ID, User customer, String order_status, BigDecimal total, Date order_date) {

		this.order_ID = order_ID;
		this.customer = customer;
		this.order_date = order_date;
		this.order_status = order_status;
		this.total = total;
	}

	public Order() {
	}

	public Order(int orderID) {
		Connection con;
		try {
			con = DBConnection.getConnection();
			String query = "select * from orders where order_id = " + orderID + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.order_ID = rs.getInt("order_ID");
				this.customer = new User(rs.getInt("customer_id"));
				this.order_date = rs.getDate("order_date");
				this.order_status = rs.getString("order_status");
				this.total = rs.getBigDecimal("total");

			}
			DBConnection.close(con, rs, st);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Order [order_ID=" + order_ID + ", customer=" + customer + ", order_status=" + order_status + ", total="
				+ total + ", order_date=" + order_date + "]";
	}

	public int getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public void addOrderToDB() {
		String order_status = "processed";
		try {
			Connection con = DBConnection.getConnection();
			System.out.println(order_date);
			String query = "insert into orders(order_date, order_status, total, customer_id)" + "VALUES( '" + order_date.toString()
					+ "', ' " + order_status + "', " + total + ", " + customer.getUser_id() + ");";

			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();

		}
	}
	
	public void addOrderItem(int itemId) {

		try {
			Connection con = DBConnection.getConnection();

			String query = "insert into order_items(order_id, item_id)" + "VALUES(" + order_ID
					+ ", " + itemId + ");";

			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();

		}
	}

	public void removeOrderFromDB() {
		try {
			Connection con = DBConnection.getConnection();
			String query = "delete from orders where order_id = " + order_ID + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getMaxOrderId() {
		int max = 0;
		try {
			Connection con = DBConnection.getConnection();
			String query = "select max(order_id) from orders;";
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

	public static ArrayList<Order> getOrdersForUser(int userID) {

		ArrayList<Order> orders = new ArrayList<Order>();

		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from orders where customer_id = " + userID + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Order o = new Order();
				o.customer = new User(userID);
				o.order_date = rs.getDate("order_date");
				o.order_ID = rs.getInt("order_id");
				o.order_status = rs.getString("order_status");
				o.total = rs.getBigDecimal("total");
				orders.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

}
