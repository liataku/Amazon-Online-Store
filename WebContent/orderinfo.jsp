<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User"%>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Item</title>

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
	
	<div class="container">

      <div class="row">

      
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
		<h1> Order Information: </h1> <br>
          <div class="card mt-4">
            <div class="card-body">
              
              <h3 class="card-title">${order.order_date}</h3>
              <p class="card-text">Total before shipping: $${order.total }</p>
              Tracking Number: ${shipment.trackingNumber}<br>
              Shipping Service: ${shipment.carrier}<br>
              Shipping Charge: $${shipment.charge}<br>
              Return Address: ${shipment.returnAddress}<br>
              <br>
              
              <c:forEach var ="tempItem" items = "${orderItems}" >
              <h4> Item Name: ${tempItem.itemName } </h4>
              Item Description: ${tempItem.description} <br>
              Price: ${tempItem.price } <br>
              Sold by: ${tempItem.seller.userEmail} <br>
              <br>
              <br>
              
              </c:forEach>
			 </div>
		 </div>
	<!-- col lg 9 -->
	</div>
	<!--  row -->
	</div>
	<!-- container -->
	</div>
	
	<!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	</body>
	
</html>