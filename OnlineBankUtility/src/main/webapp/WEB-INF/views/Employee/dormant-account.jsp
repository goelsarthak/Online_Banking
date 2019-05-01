<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dormant Account</title>
</head>
<body>
	<h2>Dormat Account Details</h2>
	<table border="1" cellspacing="5" cellpadding="5">
		<tr>
			<th>CustomerID</th>
			<th>AccountNumber</th>
			<th>Amount</th>
			<th>AccountType</th>
			<th>DateOpend</th>
		</tr>
		<c:forEach var="account" items="${requestScope.ad}">
			<tr>
				<td><c:out value="${account.getCustomer().getCustomerID()}" /></td>
				<td><c:out value="${account.getAccountNumber()}" /></td>
				<td><c:out value="${account.getAccountBalance()}" /></td>
				<td><c:out value="${account.getAccountType()}" /></td>
				<td><c:out value="${account.getDateOpened()}" /></td>
			</tr>
		</c:forEach>
		<br>
		<form action="./empHome">
		<input type="submit" value="Home">
		</form>
</body>
</html>