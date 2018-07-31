package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null)
			command = "listItems"; // use list as default command

		switch (command) { // check the command and route accordingly
		case "listItems":
			listItems(request, response);
			break;
		case "addItemBySeller":
			addItemBySeller(request, response);
			break;
		case "deleteItemBySeller":
			deleteItemBySeller(request, response);
			break;
		case "getSellerItems":
			getSellerItems(request, response);
			break;
		case "searchByDept":
			searchByDept(request, response);
			break;
		case "addReview":
			addReview(request, response);
			break;
		case "getItem":
			getItem(request, response);
			break;
		case "search":
			search(request, response);
			break;
		default:
			listItems(request, response);
		}

	}

	private void getSellerItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("users");
		ArrayList<Item> items = Item.getSellerItems(user.getUser_id());
		request.setAttribute("sellerItems", items);
		RequestDispatcher dispatch = request.getRequestDispatcher("/sellerItems.jsp");
		dispatch.forward(request, response);

	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = (String) request.getParameter("search");
		ArrayList<Item> items = Item.searchItems(search);
		request.setAttribute("allItems", items);
		System.out.println(items);
		RequestDispatcher dispatch = request.getRequestDispatcher("/index.jsp");
		dispatch.forward(request, response);

	}

	private void addReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reviewDescr = request.getParameter("reviewDesc");
		if (reviewDescr != null && !(reviewDescr.trim().equals(""))) {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			if (request.getSession().getAttribute("users") != null) {
				Review r = new Review();
				r.setDescription(reviewDescr);
				r.setCustomer(((User) request.getSession().getAttribute("users")));
				r.setItem(new Item(itemId));
				r.addReviewToDB();
				getItem(request, response);
			}

		}

	}

	private void searchByDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dept = (String) request.getParameter("dept");
		ArrayList<Item> items = Item.searchItemsByDept(dept);
		request.setAttribute("allItems", items);
		System.out.println(items);
		RequestDispatcher dispatch = request.getRequestDispatcher("/index.jsp");
		dispatch.forward(request, response);

	}

	private void getItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
		ArrayList<Review> reviews = Review.getReviews(item.getItemId());
		request.setAttribute("item", item);
		request.setAttribute("reviews", reviews);
		System.out.println(reviews);
		RequestDispatcher dispatch = request.getRequestDispatcher("/item.jsp");
		dispatch.forward(request, response);

	}

	// deletes the item passed in request, performed by seller
	private void deleteItemBySeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		Item item = new Item(itemId);
		item.deleteItemFromDB();
		getSellerItems(request, response);

	}

	// adds the item using attributes passed in request, performed by seller
	private void addItemBySeller(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user= ((User)request.getSession().getAttribute("users"));
			String itemName = request.getParameter("itemName");
			double pr = Double.parseDouble(request.getParameter("price"));
			BigDecimal price = new BigDecimal(pr, MathContext.DECIMAL64);
			String department = request.getParameter("department");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String description = request.getParameter("description");
			String imageUrl = request.getParameter("imageUrl");

			Item item = new Item();
			item.setDepartment(department);
			item.setDescription(description);
			item.setImageUrl(imageUrl);
			item.setItemName(itemName);
			item.setPrice(price);
			item.setQuantity(quantity);
			item.setSeller(user);
			
			item.addItemToDB();
			item.setItemId(Item.getMaxItemId());
			item.addItemToSeller(user.getUser_id());
			getSellerItems(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Item> items = Item.getAllItems();

		request.setAttribute("allItems", items);

		RequestDispatcher dispatch = request.getRequestDispatcher("/index.jsp");
		dispatch.forward(request, response);
	}

}