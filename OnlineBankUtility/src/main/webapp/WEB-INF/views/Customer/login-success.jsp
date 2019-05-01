<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CustomerLogin</title>

<style>
.split {
	height: 100%;
	width: 50%;
	position: relative;
	z-index: 1;
	top: 0;
	overflow-x: hidden;
	padding-top: 10px;
	display:inline-block;
}

.left {
	left: 0;
	psoition: absolute;
}

.right {
	right:-350px;
	psoition: absolute;
}
</style>
</head>
<body>
	<H2>
		<i>Welcome ${requestScope.customer.getFirstName()}</i>
	</H2>

	<style>
.bordered {
	width: 150px;
	height: 50px;
	padding: 10px;
	border: 1px solid black;
	border-radius: 8px;
}

.borderFunds {
	width: 150px;
	height: 80px;
	padding: 10px;
	border: 1px solid black;
	border-radius: 8px;
}
.tt{


}
</style>
<div class="split right">
<table border="1" cellspacing="5" cellpadding="5" align="center">
		<thead>
			<th><b>CustomerID</b></th>
			<th><b>AccountNumber</b></th>
			<th><b>AccountType</b></th>
			<th><b>Account Balance</b></th>
			<th><b>DateOpened</b></th>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="cust"
					items="${requestScope.customer.accountDetails}">
					<tr>
						<td><c:out value="${cust.getCustomer().getCustomerID()}" /></td>
						<td><c:out value="${cust.getAccountNumber()}" /></td>
						<td><c:out value="${cust.getAccountType()}" /></td>
						<td><c:out value="${cust.getAccountBalance()}" /></td>
						<td><c:out value="${cust.getDateOpened()}" /></td>
					</tr>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div class="split left">
		<div class="bordered" align="left">
			<font color="red">Open a new Account?</font>
			<form action="./newAccount.htm" method="Get">
				<input type="Submit" value="Open New Account">
			</form>
		</div>
		<br> <br>
		<div class="borderFunds" align="center">
			<font color="red">Transfer Funds</font>
			<form action="./transferwithinAccount.htm" method="Get">
				<input type="submit" value="Transfer Within Accounts">
			</form>
			<form action="./transferoutAccount.htm" method="Get">
				<input type="submit" value="Transfer To Other Account">
			</form>
		</div>
		<br> <br>
		<div class="bordered" align="left">
			<font color="red">Get Transaction List</font>
			<form action="./retrieveTransactions.htm" method="Get">
				<input type="Submit" value="Retrieve Transactions">
			</form>
		</div>
		<br> <br>
		<div class="bordered" align="left">
			<font color="red">Update Customer Information</font>
			<form action="./updateCustomerInfo.htm" method="Get">
				<input type="Submit" value="Update Info">
			</form>
		</div>
	</div>

	<form action="./logout.htm" method="Get">
		<input type="submit" value="Logout">
	</form>
	<br>
	<br>
	
</body>
</html>