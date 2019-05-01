<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GetTransactions</title>
</head>
<body>
<h2>Transaction Deatils:</h2>
	<table border="1" cellspacing="5" cellpadding="5">
		<tr>
			<th>TransactionID</th>
			<th>AccountNumber</th>
			<th>Amount</th>
			<th>TransactionType</th>
			<th>DateofTransaction</th>
		</tr>
		<c:forEach var="td" items="${requestScope.results}">
			<tr>
				<td><c:out value="${td.getTransactionId()}" /></td>
				<td><c:out value="${td.getAccountNumber()}" /></td>
				<td><c:out value="${td.getAmount()}" /></td>
				<td><c:out value="${td.getTransactionType()}" /></td>
				<td><c:out value="${td.getDateTime()}" /></td>
			</tr>
		</c:forEach>
		<br>
		<form action="./home">
		<input type="submit" value="Home">
		</form>
		<h4><a href="./downloadPDF">Download PDF Document</a></h4>
</body>
</html>