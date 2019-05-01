<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function Validate() {
		var amt = document.getElementById("amnt");
		if (amt < 1) {
			alert("minimum amount should be 1")
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TransferFunds</title>
</head>
<body>
	<form action="./transferIntraBank.htm" method="Post">
		<label for="loginId"><b>Customer Id</b></label><br> <input
			type="text" placeholder="Login Id" name="loginId"
			value="${customer.getCustomerID()}" readonly> <br> <br>
		<b>From Account Number:</b> <br> <br> <select id="fromAcN"
			name="FromAccNum" required>
			<c:forEach var="cust"
				items="${requestScope.customer.getAccountDetails()}">
				<option value="${cust.getAccountNumber()}">${cust.getAccountNumber()}</option>
			</c:forEach>
		</select> <br> <br> <b> <label for="ToAccNum">Beneficiary
				Account Number</label></b> <br> <input type="text"
			placeholder="Beneficiary Account Number" name="ToAccNum" required> <br>
		<br> <label for="amount"><b>Amount</b></label><br> <input
			type="text" placeholder="$Amount" id="amnt" name="amount" min="1" required>
		<br> <br> <input type="submit" value="Transfer" onclick="Validate()">
	</form>
</body>
</html>