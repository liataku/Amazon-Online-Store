package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import mysql.DBConnection;

public class Authenticator {
 
	public static boolean authenticate(String username, String password){
		boolean auth = false;
		try{
			Connection con = DBConnection.getConnection();
			String query = "select * from users where user_email = '" + username + "' and user_password = '" + password + "';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				//result set is not empty, user exists
				auth = true;
			}
			DBConnection.close(con, rs, st);
			
		}catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			return false;
		}
		return auth;
	}
}
