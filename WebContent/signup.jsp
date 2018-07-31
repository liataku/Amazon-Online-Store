<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="model.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/signup.css" rel="stylesheet">

<title>Sign Up</title>
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
		<form role="form" action="SignUpController" method="get">
			<fieldset>
				<h2>Enter Account Information</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address"  required="required">
				</div>
				<div class="form-group">
                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password"  required="required">
				</div>
				<div class="form-group">
					<select class="form-control" name="accounttype">
                	<option value="customer">customer</option>
                	<option value="seller">seller</option>
                	<option value="employee">employee</option>
            	</select>
				</div>
				<div class="form-group">
                    <input type="firstName" name="firstName" id="firstName" class="form-control input-lg" placeholder="First Name"  required="required">
				</div>
				<div class="form-group">
                    <input type="midName" name="midName" id="midName" class="form-control input-lg" placeholder="Middle Name"  required="required">
				</div>
				<div class="form-group">
                    <input type="lastName" name="lastName" id="lastName" class="form-control input-lg" placeholder="Last Name"  required="required">
				</div>
				<div class="form-group">
                    <input type="address" name="address" id="address" class="form-control input-lg" placeholder="Address"  required="required">
				</div>
				<div class="form-group">
                    <input type="card_number" name="card_number" id="card_number" class="form-control input-lg" placeholder="Card Number"  required="required">
				</div>
				<div class="form-group">
                    <input type="expiration_date" name="expiration_date" id="expiration_date" class="form-control input-lg" placeholder="Expiration Date"  required="required">
				</div>
				<div class="form-group">
                    <input type="security_code" name="security_code" id="security_code" class="form-control input-lg" placeholder="Security Code"  required="required">
				</div>
				<div class="form-group">
                    <input type="billing_address" name="billing_address" id="billing_address" class="form-control input-lg" placeholder="Billing Address"  required="required">
				</div>
				
				
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-xl btn-success btn-block" value="Create Account">
					</div>
					
				</div>
			</fieldset>
		</form>
	</div>
</div>

</div>
	
	  <!-- Bootstrap core JavaScript -->
	   <script src="vendor/jquery/jquery.min.js"></script>
	  <script src="javaScript/signup.js"></script>
   
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    
    

    
	
	
</body>
</html>