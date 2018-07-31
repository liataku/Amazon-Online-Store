package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Order;
import model.User;


public class Main {
    public static void main(String[] args){
        
    	ArrayList<Order> orders = new ArrayList<Order>();
        
    	orders = Order.getOrdersForUser(143);
    	
    	System.out.println(orders);
    }
}
