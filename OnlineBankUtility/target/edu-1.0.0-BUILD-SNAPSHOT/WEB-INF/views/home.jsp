<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Online Bank Utility</title>
</head>
<body>
<h2><i>
	Welcome to Online Banking  
</i></h2>

<form action="/login">
<p><i><font color="Red">Existing Customer SignIn</font></i><br> 
<label for="loginId"><b>Customer Id</b></label>
<input type="text" placeholder="loginId" name="loginId" required><br><br>

<label for="password"><b>Password</b></label>
<input type="text" placeholder="password" name="password" required><br><br>
 
<input type="submit" name="submit" value="submit"/>
</form>

</body>
</html>
