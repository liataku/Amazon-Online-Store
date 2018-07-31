<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="model.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">

<title>Add Billing Info</title>
</head>
<body>


<div class="container">
  

<% if (session.getAttribute("users") == null){ %>
	<%@include file="publicHeader.jsp"%>
	<%}else if (((User)session.getAttribute("users")).getAccountType().equals("customer")){ %> 
	<%@include file="customerHeader.jsp"%>
	<%}else if (((User)session.getAttribute("users")).getAccountType().equals("seller")){%>
	<%@include file="sellerHeader.jsp" %>
	<%} else{ %>
	<%@include file="employeeHeader.jsp" %>
	<%} %>

<div class="row" style="margin-top:120px" id="center">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" action="BillingInfoController" method="get">
			<fieldset>
				<h2>Add Billing Info</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="text" name="cardNumber" id="cardNumber" class="form-control input-lg" placeholder="Card Number">
				</div>
				<div class="form-group">
                    <input type="text" name="expDate" id="expDate" class="form-control input-lg" placeholder="Expiration Date (MMDDYYYY)">
				</div>
			
				<div class="form-group">
                    <input type="text" name="securityCode" id="securityCode" class="form-control input-lg" placeholder="Security Code">
				</div>
				<div class="form-group">
                    <input type="text" name="billingAddress" id="billingAddress" class="form-control input-lg" placeholder="Billing Address">
				</div>
					<input type="hidden" name="command" value="addBill" />
			
			
				
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Add Billing Info">
					</div>
					
				</div>
			</fieldset>
		</form>
	</div>
</div>

</div>
	
	  <!-- Bootstrap core JavaScript -->
	   <script src="vendor/jquery/jquery.min.js"></script>
	  <script src="javaScript/login.js"></script>
   
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    
    

    
	
	
</body>
</html>




