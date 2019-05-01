<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>HomePage</title>
</head>
<body>
	<h2 align="center">
		<i>Welcome to Online Banking </i>
	</h2>

	<style>
.bordered {
	width: 200px;
	height: 240px;
	padding: 10px;
	border: 1px solid black;
	border-radius: 8px;
}
</style>

	<div class="bordered">
		<form action="./login.htm" method="Post">
			<p>
				<i><font color="Red">Existing Customer Sign In</font></i><br> <br>
				<label for="loginId"><b>Customer Id</b></label> <input type="text"
					placeholder="Login Id" name="loginId" required><br> <br>
				<label for="password"><b>Password</b></label> <input type="password"
					placeholder="Password" name="password" required><br> <br>
				<input type="submit" name="submit" value="Login" />
		</form>

		<form action="./register.htm" method="Get">
			<input type="submit" name="submit" value="New Customer">
		</form>
	</div>
	<a href="./emplogin.htm">Click here to goto Employee Login</a>
</body>
</html>
