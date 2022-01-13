<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>


<body>
<div class="container">
		<spring:url value="/login/verify" var="saveURL" />

		<form:form modelAttribute="oggettoUtente" method="post"
			action="${saveURL}" cssClass="form">
			<form:hidden path="id" />

			<div class="form-group">
				<label>username</label>
				<form:input path="username" cssClass="form-control" id="username" />
				<form:errors path="username" cssClass="error" />
			</div>

			<div class="form-group">
				<label>password</label>
				<form:password path="password" cssClass="form-control"
					id="password" />
				<form:errors path="password" cssClass="error" />
			</div>
			
			
			<button type="submit" class="btn btn-primary">login</button>


		</form:form>
	</div>
</body>


</html>