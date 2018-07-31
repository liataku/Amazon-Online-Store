package controller;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import model.Authenticator;
import model.User;

@WebServlet("/UserAccountController")

public class UserAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	public UserAccountController() {
		super();
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			user = (User)req.getSession().getAttribute("users");
			
			System.out.println(user.getFirst_name());
			
			if(req.getParameter("command") == null)
			loadUser(req,resp);
			else callUpdate(req, resp);
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		
		
		
	}
	
	
	private void callUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstName = req.getParameter("firstName");
		String middleName = req.getParameter("middleName");
		String lastName = req.getParameter("lastName");
		String Email = req.getParameter("Email");
		String Address = req.getParameter("Address");
		
		user.setFirst_name(firstName);
		user.setMiddle_name(middleName);
		user.setLast_name(lastName);
		user.setUserEmail(Email);
		user.setUserAddress(Address);
		
		user.updateUser();
		loadUser(req, resp);
		
	}
	private void loadUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setAttribute("THE_USER", user);
		
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/updateAccount.jsp");
		
	
		dispatcher.forward(req, resp);
		
		
	}
	
}
