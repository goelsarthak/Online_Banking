<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Customer Information</title>
</head>
<body>
	<h2>Update Customer Information</h2>
	<form action="./updateCustInfo.htm" , method="Post">
		<label for="customerId"><b>CustomerId</b></label> <input type="text"
			name="customerId" value="${customer.getCustomerID()}" readonly>
		<br> <br> <label for="firstName"><b>FirstName:</b></label> <input
			type="text" name="firstName" value="${customer.getFirstName()}"
			readonly> <br> <br> <label for="lastName"><b>LastName:</b></label>
		<input type="text" name="lastName" value="${customer.getLastName()}"
			readonly> <br> <br> <label for="address"><b>Address:</b></label>
		<input type="text" name="address"
			value="${customer.getCustomerAddress()}" required> <br>
		<br> <label for="email"><b>Email:</b></label> <input type="text"
			name="email" value="${customer.getCustomerEmail()}" required>
		<br> <br> <label for="phone"><b>Phone:</b></label> <input
			type="text" name="phone" value="${customer.getPhoneNumber()}"
			required> <br> <br> <input type="submit"
			value="Update Details">
	</form>
	<form action="./home">
		<input type="submit" value="Home Page">
	</form>
</body>
</html>