<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>

	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}" arguments="${error.arguments}"/>
	</c:forEach>

	<h1>Update ${user.username}</h1>
	<form:form method="POST" commandName="userUpdateForm">
		<form:errors element="div"/>
		
		<form:hidden path="userId" />
		<div>
			<form:label path="username">Username:</form:label>
			<form:input path="username" cssErrorClass="error-input"/> 
			<%-- Restrict to just one message --%>
			<form:errors path="username" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
		</div>
		<div>
			<form:label path="password">Password:</form:label>
			<form:password path="password" cssErrorClass="error-input" showPassword="true"/> (optional)
			<form:errors path="password" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
		</div>
		<div>
			<input name="submit" type="submit" value="Create New User" />
		</div>
	</form:form>
	<p>
		<spring:url var="deleteUserUrl" value="/user/${user.userId}/delete" />
		<a href="${deleteUserUrl}">Delete User</a>
	</p>
</body>
</html>