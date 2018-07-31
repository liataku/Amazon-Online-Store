package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Payment;
import model.User;

/**
 * Servlet implementation class sellerPaymentsController
 */
@WebServlet("/sellerPaymentsController")
public class sellerPaymentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerPaymentsController() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		listPayments(request,response);
		
	}

	public void listPayments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = ((User)request.getSession().getAttribute("users")).getUser_id();

		ArrayList<Payment> payments = Payment.getPayments(userID);
		request.setAttribute("paymentList", payments);
		RequestDispatcher dispatch = request.getRequestDispatcher("/sellerPayments.jsp");
		dispatch.forward(request, response);
	}

}
