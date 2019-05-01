<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TransferFunds</title>
</head>
<body>
	<H2 align='center'>
		<i>Transfer Funds Within the Accounts</i>
	</H2>
	<form action="./transferAmount.htm" method="Post">
		<label for="loginId"><b>Customer Id</b></label><br> <input
			type="text" placeholder="Login Id" name="loginId"
			value="${customer.getCustomerID()}" readonly> <br>
		<br> <b>From Account Number:</b> <br>
		<br> <select name="FromAccNum">
			<c:forEach var="cust" items="${requestScope.customer.accountDetails}">
				<option value="${cust.getAccountNumber()}">${cust.getAccountNumber()}</option>
			</c:forEach>
		</select> <br>
		<br> <b>To Account Number:</b> <br> <select name="ToAccNum">
			<c:forEach var="cust" items="${customer.accountDetails}">
				<option value="${cust.getAccountNumber()}">${cust.getAccountNumber()}</option>
			</c:forEach>
		</select> <br>
		<br> <label for="amount"><b>Amount</b></label><br> <input
			type="text" placeholder="$Amount" name="amount" min="1" required> <br>
		<br> <input type="submit" value="Transfer">
	</form>
</body>
</html>