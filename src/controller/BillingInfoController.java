package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillingInfo;
import model.Item;
import model.User;

/**
 * Servlet implementation class BillingInfoController
 */
@WebServlet("/BillingInfoController")
public class BillingInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private 
	BillingInfo bi;
	private User user;
	private int user_id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingInfoController() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			user = (User)request.getSession().getAttribute("users");
			
			user_id = user.getUser_id();
			
			
			String command = request.getParameter("command");
			if(command == null) command = "listBill";
			switch(command) {
			case "removeBill": removeBill(request, response);
				break;
			case "addBill": addBill(request, response);
				break;
			case "listBill": listBill(request, response);
				break;
			default: listBill(request, response);
			}

			
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}



	}

	
	private void listBill(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
		ArrayList<BillingInfo> bills = bi.getBillingInfo(user_id);
		request.setAttribute("Billing", bills);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateBillinginfo.jsp");
		
	
		dispatcher.forward(request, response);
		
	}
	
	
	private void removeBill(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

		BillingInfo bill = new BillingInfo(Integer.parseInt(request.getParameter("billing_ID")));
		bill.deleteBillingInfoFromDB();
		listBill(request, response);
	
	}
	
	private void addBill(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ParseException{

		String card_Number = request.getParameter("cardNumber");
		String security_Code = request.getParameter("securityCode");
		String billing_Address = request.getParameter("billingAddress");	
		
        
        String expDate = request.getParameter("expDate");
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
        java.util.Date parsed = format.parse(expDate);
        java.sql.Date expirationDate = new java.sql.Date(parsed.getTime());
        
       
	
		BillingInfo bill = new BillingInfo(user, card_Number, expirationDate,Integer.parseInt(security_Code), billing_Address);
		bill.addBillingInfoToDB();
		
		
		listBill(request, response);
		

		
		
	}
}