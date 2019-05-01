<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Emp Login</title>
</head>
<body>

	<h2 align="center">
		<i>Welcome to Online Banking </i>
	</h2>

	<style>
.bordered {
	width: 200px;
	height: 200px;
	padding: 10px;
	border: 1px solid black;
	border-radius: 8px;
}
</style>

	<div class="bordered">
		<form action="./EmpDashboard.htm" method="Post">
			<p>
				<i><font color="Red">Employee Sign In</font></i><br> <br>
				<label for="loginId"><b>Employee Id</b></label> <input type="text"
					placeholder="Login Id" name="loginId" required><br> <br>
				<label for="password"><b>Password</b></label> <input type="password"
					placeholder="Password" name="password" required><br> <br>
				<input type="submit" name="submit" value="Login" />
		</form>
	</div>

</body>
</html>