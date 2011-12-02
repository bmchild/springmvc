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
	<h1>Add new User</h1>
	 
	<form:form action="new" method="POST" commandName="userForm">
		<%-- Globals Errors --%>
		<form:errors element="div"/>
		<div>
			<form:label path="username">Username:</form:label>
			<form:input path="username" cssErrorClass="error-input"/> 
			<%-- Restrict to just one message --%>
			<spring:bind path="userForm.username">
				<span class="error-span">
			        <c:if test="${status.error}">
			        	${status.errorMessages[0]}
			        </c:if>
		        </span>
			</spring:bind>
		</div>
		<div>
			<form:label path="password">Password:</form:label>
			<form:password path="password" cssErrorClass="error-input"/> 
			<form:errors path="password" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
		</div>
		<div>
			<input name="submit" type="submit" value="Create New User" />
		</div>
	</form:form>
</body>
</html>