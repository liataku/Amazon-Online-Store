<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User"%>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Cart </title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-item.css" rel="stylesheet">

  </head>

  <body>

<% if (session.getAttribute("users") == null){ %>
	<%@include file="publicHeader.jsp"%>
	<%}else if (((User)session.getAttribute("users")).getAccountType().equals("customer")){ %> 
	<%@include file="customerHeader.jsp"%>
	<%}else if (((User)session.getAttribute("users")).getAccountType().equals("seller")){%>
	<%@include file="sellerHeader.jsp" %>
	<%} else{ %>
	<%@include file="employeeHeader.jsp" %>
	<%} %>

    <!-- Page Content -->
    <div class="col-lg-3">

          <h1 class="my-4">My Cart</h1>
          <div class="list-group">
           
          </div>

        </div>
    
    <div class="container">

      <div class="row">

      
        <!-- /.col-lg-3 -->
        

        <div class="col-lg-9">
        
		<c:forEach var="tempItem" items="${cartItems}" > 
        	
          <div class="card mt-4">
            
            <div class="card-body">
		
              <h3 class="card-title">${tempItem.itemName}</h3> 
              
              <h4>$ ${tempItem.price}</h4>
              <p class="card-text">${tempItem.description }</p>
              Sold by ${tempItem.seller.userEmail} <br>
              Quantity: ${tempItem.quantity} <br>
              <br>
              <form action="CartController" method="GET">
              	<input type="hidden" name="command" value="deleteFromCart" />
              	<input type="hidden" name="itemId" value="${tempItem.itemId}" />
           		<input type=submit class="btn btn-success" value = "Remove"> 
           	
        		</form>

            </div>
          
          </div>
          </c:forEach>
          <!-- /.card -->
          <br>
          <h3> Select Billing Info</h3>
            <form action="CartController" method="GET">
           		<input type="hidden" name="command" value="checkout" />
            		    <label for="exampleFormControlSelect1"> </label>
    					<select class="form-control" name="billingId">
    					
      					<c:forEach var = "bInfo" items = "${billingInfo}">
      						<option value = "${bInfo.billing_ID }"> Card Number: ${bInfo.card_Number}
      						</option>
      					</c:forEach>
      					
    					</select>
    					<br>
      
           		<input type=submit class="btn btn-success" value ="Checkout">
            </form>
          <br>
          <br>
          <br>

        </div>
        <!-- /.col-lg-9 -->

      </div>
      </div>


    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>