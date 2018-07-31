<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="model.User"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html>
<head>


<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Account</title>
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

		<h3> Update Account</h3>
		
		<form action="UserAccountController" method="GET">
		
			
				<table>
					<tbody>
					<tr>
						<td> <label>First name:</label></td>
						<td><input type="text" name="firstName"value="${THE_USER.first_name}" /> </td>
					</tr>
					
					<tr>
						<td> <label>Middle name:</label></td>
						<td><input type="text" name="middleName"value="${THE_USER.middle_name}" /> </td>
					</tr>
					
					<tr>
						<td> <label>Last name:</label></td>
						<td><input type="text" name="lastName" value="${THE_USER.last_name}" /> </td>
					</tr>

					
					<tr>
						<td> <label>Address:</label></td>
						<td><input type="text" name="Address" value="${THE_USER.userAddress}" /> </td>
					</tr>
					
					
					<tr>
						<td> <label>Email:</label></td>
						<td><input type="text" name="Email" value="${THE_USER.userEmail}" /> </td>
					</tr>

	
					</tbody>
				
				</table>
		
		<input type ="submit" value="Save" />
		<input type="hidden" name="command" value="update" />
		</form>
	
	
	</div>
	
	
	
 <!-- Bootstrap core JavaScript -->
	   <script src="vendor/jquery/jquery.min.js"></script>
	  <script src="javaScript/login.js"></script>
   
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>