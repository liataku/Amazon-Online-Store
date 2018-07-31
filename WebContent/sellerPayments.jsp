<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Payments</title>

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

          <h1 class="my-4">My Payments</h1>
          <div class="list-group">
           
          </div>

        </div>
    
    <div class="container">

      <div class="row">

      
        <!-- /.col-lg-3 -->
        

        <div class="col-lg-9">
        
		<c:forEach var="tempItem" items="${paymentList}" > 
        	
          <div class="card mt-4">
            
            <div class="card-body">
		
              
              <div style="clear: both"> 
              	<h4>Amount: </h4> 
              	<h5>$ ${tempItem.amount} </h5>
              </div>
              Customer: ${tempItem.customer.userEmail}<br>
              Payment Type: ${tempItem.paymentType} <br>
              <br>
             

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