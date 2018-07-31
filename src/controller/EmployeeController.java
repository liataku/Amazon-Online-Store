package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.User;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command == null) command = "listItems";
		switch(command) {
		case "listItems": listItems(request, response);
			break;
		case "listUsers": listUsers(request, response);
			break;
		case "deleteFromInventory": deleteItem(request, response);
			break;
		case "deleteUser": deleteUser(request, response);
			break;
		default: listItems(request, response);
		}
		
	}


	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
		item.deleteItemFromDB();
		listItems(request, response);
	}


	private void listItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Item> items = Item.getAllItems();
		
		request.setAttribute("employeeItems", items);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/employeeItems.jsp");
		dispatch.forward(request, response);
		
	}


	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> users = User.getUsersForEmployee();
		request.setAttribute("users", users);
		RequestDispatcher dispatch = request.getRequestDispatcher("/employeeUsers.jsp");
		dispatch.forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(Integer.parseInt(request.getParameter("userId")));
		user.deleteUserFromDB();
		listUsers(request, response);
	}



}
