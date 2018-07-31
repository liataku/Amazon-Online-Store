package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mysql.DBConnection;

public class Review {
	
	private int reviewID;
	private Item item;
	private User customer;
	private String description;
	private int rating;
	
	public Review(int reviewID) {
		//
		this.reviewID = reviewID;
		
		Connection con;
		try {
			con = DBConnection.getConnection();
			String query = "select * from review where review_ID = " + reviewID + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			//we need to remove seller_ID from the DB
			this.reviewID = rs.getInt("Review_ID");
			int customerID = rs.getInt("Customer_ID");
			this.customer = new User(customerID);
			int itemID = rs.getInt("Item_ID");	
			this.item = new Item(itemID);
			this.description = rs.getString("Description");
			this.rating = rs.getInt("Rating");
			

		} catch (Exception e) {
			
			e.printStackTrace();
		}	
		
	}
	
	public Review(){
		
	}
	
	public Review(int reviewID,  int customerID) {
		this.reviewID = reviewID;
		

	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


	
	@Override
	public String toString() {
		return "Review [reviewID=" + reviewID + ", customer=" + customer + ", description=" + description + ", rating="
				+ rating + "]";
	}

	public void addReviewToDB() {
		try {
			Connection con = DBConnection.getConnection();
			String query = "insert into review(rating, customer_id, description, item_id) values (" + rating + ", " +
			customer.getUser_id() + ", '" + description + "', " + item.getItemId() + ");";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteReviewFromDB() {
		try {
			Connection con = DBConnection.getConnection();
			String query = "delete from review where review_id = " + reviewID + ";";
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static ArrayList<Review> getReviews(int itemId) {
		ArrayList<Review> reviews = new ArrayList<Review>();

		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from review where item_id =" + itemId + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Review rev = new Review();
				rev.setReviewID(rs.getInt("review_id"));
				rev.setItem(new Item(rs.getInt("item_id")));
				rev.setCustomer(new User(rs.getInt("customer_id")));
				rev.setDescription(rs.getString("description"));
				rev.setRating(rs.getInt("rating"));

				reviews.add(rev);
			}

			DBConnection.close(con, rs, st);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}

	


}

