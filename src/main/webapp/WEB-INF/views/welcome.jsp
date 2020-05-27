<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>WELCOME</title>
</head>
<body>




	<h3>welcome to this page</h3>

	Click <a href="getTest">here</a> to see all products

	<c:choose>
		<c:when test="${not empty sessionScope.activeUser }">
			<p>Welcome ${sessionScope.activeUser.username}</p>
		</c:when>
		<c:otherwise>
			<p>Welcome guest!
		</c:otherwise>

	</c:choose>


	<!--  

	<c:if test="${not empty activeUser }">
		<p>Welcome ${ sessionScope.activeUser.username }!</p>
	</c:if>
    some comment  -->

	<a href="getUser">Click here to See User </a>

</body>
</html>