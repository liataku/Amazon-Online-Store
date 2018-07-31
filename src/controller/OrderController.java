package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillingInfo;
import model.Item;
import model.Order;
import model.Shipment;
import model.User;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public OrderController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command == null)
			command = "listOrderItems";
		switch (command) {
		case "listOrderItems":
			listOrderItems(request,response);
			break;
		case "getOrderInfo":
			getOrderInfo(request,response);
			break;
			}
			
		
		//listOrderItems(request, response);
	}


	public void listOrderItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = ((User)request.getSession().getAttribute("users")).getUser_id();
		//int orderID = ((Order)request.getSession().getAttribute("orders")).getOrder_ID();
		System.out.println(userID);
		ArrayList<Order> orders =  Order.getOrdersForUser(userID);
		request.setAttribute("orderList", orders);
		//Shipment ship = Shipment.getShipmentForOrder(orderID);
		RequestDispatcher dispatch = request.getRequestDispatcher("/order.jsp");
		dispatch.forward(request, response);
	}
	
	public void getOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order(Integer.parseInt(request.getParameter("orderID")));
		request.setAttribute("order", order);
		Shipment shipment = new Shipment(order.getOrder_ID());
		request.setAttribute("shipment", shipment);
		ArrayList<Item> orderItems = Item.getOrderItems(order.getOrder_ID());
		request.setAttribute("orderItems", orderItems);
		RequestDispatcher dispatch = request.getRequestDispatcher("/orderinfo.jsp");
		dispatch.forward(request, response);
	}
}
