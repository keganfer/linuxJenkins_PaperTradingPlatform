<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body class="text-center">
	<div class="container">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
		<h3>REGISTER</h3>

		<br>
		<sf:form action="processRegister" method="post" modelAttribute="user">
			
				<label for="inputEmail" class="sr-only">Email address</label>
				<sf:input type="text" class="form-control" path="username" placeholder="usernane" />
				<br>
				<sf:input type="password" class="form-control" path="password" placeholder="Enter a password" />
				<br>
				<button type="submit" value="Register" class="btn btn-primary">Register</button>
				<!--   <input type="submit" value="login" /> -->
			
		</sf:form>
	</div>
		<c:if test="${not empty errorMessage}">
			<p>${errorMessage}</p>
		</c:if>



	<!-- <form action="processLogin" method="post">
		Username: <input type="text" name="username" /> <br> Password: <input
			type="password" name="password" /> <br> <input type="submit"
			value="processlogin" />



	<button onclick="myFunc();">click for JS</button>

	</form> -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>