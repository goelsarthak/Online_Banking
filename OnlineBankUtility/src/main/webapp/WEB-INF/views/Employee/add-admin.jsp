<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Admin</title>
</head>
<body>
	<H3 align='center'>
		<i>Admin Registration</i>
	</H3>
	<form:form commandName="employee">
		<b>Admin Name:</b>
		<br>
		<form:input path="empName" />
		<form:errors path="empName" />
		<br>
		<b>Password:</b>
		<br>
		<form:input path="password" />
		<form:errors path="password" />
		<br>
		<b>EmailID:</b>
		<br>
		<form:input path="emailId" />
		<form:errors path="emailId" />
		<br>
		<b>Phone Number:</b>
		<br>
		<form:input path="phone" />
		<form:errors path="phone" />
		<br>
		<br>
		<input type="submit" value="Create" />
	</form:form>
</body>
</html>