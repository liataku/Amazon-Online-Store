<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 
<c:url var="loginLink" value="/login.jsp">
					</c:url>
<a href="${loginLink}"> Log In </a> 

Items: <br>
<table>
	<tr>
		<th>Item Name</th>
		<th>Item Price</th>
		<th>Item Description</th>
		<th>Seller Name</th>
		
	</tr>
	<c:forEach var="tempItem" items="${allItems}" > 
	
		<c:url var="itemLink" value="ItemController">
						<c:param name="command" value="getItem" />
						<c:param name="itemId" value="${tempItem.itemId}" />
					</c:url>
								
		<tr>
			<td> <a href="${itemLink}"> ${tempItem.itemName} </a> </td>
			<td>${tempItem.price}</td>
			<td>${tempItem.description}</td>
			<td>${tempItem.seller.first_name}</td>
		</tr>	
	</c:forEach>
</table>

</body>
</html>