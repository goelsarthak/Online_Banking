<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CustomerRegistration</title>
</head>
<body>
	<H3 align='center'>
		<i>Customer Registration</i>
	</H3>
	<form:form commandName="customer" enctype="multipart/form-data">
		<b>First Name:</b>
		<br>
		<form:input path="firstName" />
		<form:errors path="firstName" />
		<br>
		<b>Last Name:</b>
		<br>
		<form:input path="lastName" />
		<form:errors path="lastName" />
		<br>
		<b>Address:</b>
		<br>
		<form:input path="customerAddress" />
		<form:errors path="customerAddress" />
		<br>
		<b>Phone Number:</b>
		<br>
		<form:input path="phoneNumber" />
		<form:errors path="phoneNumber" />
		<br>
		<b>Email:</b>
		<br>
		<form:input path="customerEmail" />
		<form:errors path="customerEmail" />
		<br>
		<b><abbr title="Password Length - Min 5 Max 10">Password</abbr></b>
		<br>
		<form:input path="customerPassword" />
		<form:errors path="customerPassword" />
		<br>

		<div class="form-group">
			<label for="photo" class="col-sm-4 control-label"><b>Photo:</b></label>
			<div class="col-sm-4">
				<input type="file" id="photo" name="photo" required="required" />
				<p class="help-block">Recent photo in JPG format</p>
			</div>
		</div>

		<br>
		<input type="submit" value="Create" />
	</form:form>

</body>
</html>