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
     <!--  <form action="OrderController" method="GET">
           		<input type=submit class="btn btn-success" value = "See Order History"> 
           	
        		</form> -->
    <!-- Page Content -->
    <div class="col-lg-3">

          <h1 class="my-4">Order History</h1>
          <div class="list-group">
           
          </div>

        </div>
    
    <div class="container">
    
    <div class="row">
    
    <div class="col-lg-9">
   
    <c:forEach var= "tempItem" items = "${orderList}" > 
    
    	<c:url var ="itemLink" value = "OrderController">
    		<c:param name="command" value = "getOrderInfo"/>
    		<c:param name="orderID" value = "${tempItem.order_ID}" />
    	</c:url>
    <div class="card mt-4">
            
            <div class="card-body">
    		
    		<h4 class="card-title">
    		<a href="${itemLink}">${tempItem.order_date}</a>
    		</h4>
    		Order Status: ${tempItem.order_status} <br>
    		Total Price: ${tempItem.total} <br>
    		
    		
    		
    		
    		</div>
    </div>
    </c:forEach>
    </div>
    </div>
	</div>
    <!-- /.container -->

    <!-- Footer -->

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>