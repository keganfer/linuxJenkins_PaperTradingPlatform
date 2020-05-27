<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<title>Register</title>
</head>
<body>

	<h3>Register here</h3>
 
	 <sf:form action="processRegister" method="post" modelAttribute="user">
		<sf:input type="text" path="username" placeholder="usernane" />
		<sf:input type="password" path="password" placeholder="Enter a password" />
		<input type="submit" value="Register" />
	</sf:form>

	<c:if test="${not empty errorMessage}">
		<p>${errorMessage}</p>
	</c:if>

</body>
</html>