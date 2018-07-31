<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
<title>Update Billing Info</title>
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
	
	
	<div>
		<br></br>
		<br></br>

		
		<!-- Page Content -->
    <div class="col-lg-3">

          <h1 class="my-4">Billing Info</h1>
          <div class="list-group">
           
          </div>

        </div>
    
    <div class="container">
		<a href="addBillingInfo.jsp" class="btn btn-success" role="button">Add Billing Info</a>
      <div class="row">
      
	

      
        <!-- /.col-lg-3 -->
        

        <div class="col-lg-9">
        
		<c:forEach var="tempItem" items="${Billing}" > 
        	
          <div class="card mt-4">
            
            <div class="card-body">
		
             
              
              <h4> Card Number: ${tempItem.card_Number}</h4>
              
              <p class="card-text">Security Code: ${tempItem.security_Code }</p>
              Expiration Date: ${tempItem.expirationDate.toString()} <br>
              Billing Address ${tempItem.billing_Address} <br>
              
              <br>
              <form action="BillingInfoController" method="GET">
              	<input type="hidden" name="command" value="removeBill" />
              	<input type="hidden" name="billing_ID" value="${tempItem.billing_ID}" />
           		<input type=submit class="btn btn-success" value = "Remove"> 
           	
        		</form>

            </div>
          
          </div>
          </c:forEach>
          <!-- /.card -->
          
          <br>
          <br>
          <br>

        </div>
        <!-- /.col-lg-9 -->

      </div>
      </div>
	
	
	</div>




   <!-- Bootstrap core JavaScript -->
	   <script src="vendor/jquery/jquery.min.js"></script>
	  <script src="javaScript/login.js"></script>
   
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>




</html>