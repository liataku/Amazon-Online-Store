<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="model.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">

<title>Add Item</title>
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
		<form role="form" action="ItemController" method="get">
			<fieldset>
				<h2>Add Item</h2>
				
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="text" name="itemName" id="itemName" class="form-control input-lg" placeholder="Item Name">
				</div>
				<div class="form-group">
                    <input type="text" name="quantity" id="quantity" class="form-control input-lg" placeholder="Quantity">
				</div>
			
				<div class="form-group">
                    <input type="text" name="department" id="department" class="form-control input-lg" placeholder="Department">
				</div>
				<div class="form-group">
                    <input type="text" name="price" id="price" class="form-control input-lg" placeholder="Price ($)">
				</div>
				<div class="form-group">
                    <input type="text" name="description" id="description" class="form-control input-lg" placeholder="Description">
				</div>
				<div class="form-group">
                    <input type="text" name="imageUrl" id="imageUrl" class="form-control input-lg" placeholder="imageUrl">
				</div>
					<input type="hidden" name="command" value="addItemBySeller" />
			
			
				
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Add Item">
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




