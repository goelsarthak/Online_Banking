<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin HomePage</title>
</head>
<body>
	<H2>
		<i>Welcome ${requestScope.employee.getEmpName()}</i>
	</H2>
	<style>
.bordered {
	width: 150px;
	height: 40px;
	padding: 10px;
	border: 1px solid black;
	border-radius: 8px;
}
</style>

	<div class="bordered" align="left">
		<b>ADD ADMIN</b>
		<form action="./employeeRegister.htm" method="Get">
			<input type="Submit" value="Add Admin">
		</form>
	</div>
	<br>
	<br>
	<div class="bordered" align="center">
		<b>Dormant A/C Details</b>
		<form action="./dormantAccounts.htm" method="Get">
			<input type="submit" value="Get Dormant Details">
		</form>
	</div>
	<br>
	<br>
	<div class="bordered" align="center">
		<b>Low A/C Balance</b>
		<form action="./minimumBalance.htm" method="Get">
			<input type="submit" value="Low Account Balance">
		</form>
	</div>
	
	<form action="./logout.htm" method="Get">
		<input type="submit" value="Logout">
	</form>
</body>
</html>