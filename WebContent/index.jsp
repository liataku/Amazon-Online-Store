<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page  import="model.*" %>

<%@ page import="model.User"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">

  </head>

  <body>
	<% if (session.getAttribute("users") == null){ %>
	<%@include file="publicHeader.jsp"%>
	<%}else if (((User)session.getAttribute("users")).getAccountType().equals("customer")){ %> 
	<%@include file="customerHeader.jsp"%>
	<%}else if (((User)session.getAttribute("users")).getAccountType().equals("seller")){%>
	<%@include file="sellerHeader.jsp" %>
	<%} else{ %>
	<%@include file="employeeHeader.jsp"%>
	<%} %>
    
	
	
	
    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <div class="col-lg-3">

          <h1 class="my-4">Shop Name</h1>
          <div class="list-group">
            <a href="/CSE305/ItemController?command=searchByDept&dept=electronics" class="list-group-item">Electronics</a>
            <a href="/CSE305/ItemController?command=searchByDept&dept=books" class="list-group-item">Books</a>
            <a href="/CSE305/ItemController?command=searchByDept&dept=clothes" class="list-group-item">Clothes</a>
          </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

          <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
              <div class="carousel-item active">
                <img class="d-block img-fluid" src="https://i.gyazo.com/da5da3ffceb3e914be926b448f5cdc6f.jpg" alt="First slide">
              </div>
              <div class="carousel-item">
                <img class="d-block img-fluid" src="https://i.gyazo.com/57ee780e104c5a2b4b6e056bbe1588bc.jpg" alt="Second slide">
              </div>
              
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>

          <div class="row">

		<c:forEach var="tempItem" items="${allItems}" > 
	
		<c:url var="itemLink" value="ItemController">
						<c:param name="command" value="getItem" />
						<c:param name="itemId" value="${tempItem.itemId}" />
					</c:url>
								
	
	

            <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <a href="${itemLink}"><img class="card-img-top" src="${tempItem.imageUrl}" alt="Item Image Not Found"></a>
                <div class="card-body">
                  <h4 class="card-title">
                    <a href="${itemLink}">${tempItem.itemName}</a>
                  </h4>
                  <h5>$${tempItem.price}</h5>
                  <p class="card-text"> ${tempItem.description}</p>
                </div>
                <div class="card-footer">
                  
                </div>
              </div>
            </div>
            
            </c:forEach>

          </div>
          <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
