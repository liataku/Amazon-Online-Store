package model;

import java.sql.Connection;

import mysql.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Shipment {
	private float trackingNumber;
	private Order order;
	private String returnAddress;
	private String carrier;
	private int charge;
	private User customer;
	
	public Shipment(int orderID){ //we should make order id a primary key for shipment in the db
		//there shouldnt be multiple orders per shipment
		
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from shipment where order_id = " + orderID + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				trackingNumber = rs.getFloat("tracking_number");
				customer = new User(rs.getInt("customer_id")); 
				order = new Order(rs.getInt("order_id"));
				returnAddress = rs.getString("returnAddress");
				carrier = rs.getString("carrier");
				charge = rs.getInt("charge");
				
				
				
			}
			DBConnection.close(con, rs, st);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public Shipment() {}
	
	public float getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(float trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	
	
	@Override
	public String toString() {
		return "Shipment [trackingNumber=" + trackingNumber + ", order=" + order + ", returnAddress=" + returnAddress
				+ ", carrier=" + carrier + ", charge=" + charge + "]";
	}
	public void addShipmentToDB() {
		try {
			Connection con = DBConnection.getConnection();

			String query = "insert into shipment(tracking_number, order_id, returnaddress, carrier, charge, customer_id)" 
					+ "VALUES( " + trackingNumber + ", " + order.getOrder_ID() + ", '"+returnAddress + 
					"', '"+ carrier +"', " + charge + ", " + customer.getUser_id() + ");"; 
					 
			Statement st = con.createStatement();
			st.executeUpdate(query);

			DBConnection.close(con, null, st);

		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	public static Shipment getShipmentForOrder(int order_id) {
		Shipment s = new Shipment();
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();
			String query = "select * from shipment where order_id = " + order_id + ";";
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()) {
				s.carrier = rs.getString("carrier");
				s.charge = rs.getInt("charge");
				s.customer = new User(rs.getInt("customer_id"));
				s.returnAddress = rs.getString("returnaddress");
				s.order = new Order(order_id);
				s.trackingNumber = rs.getFloat("tracking_number");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
}
