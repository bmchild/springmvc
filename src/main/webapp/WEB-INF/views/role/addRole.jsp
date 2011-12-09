<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Role</title>
</head>
<body>
	<h1>Add Role</h1>
	
	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}" arguments="${error.arguments}"/>
	</c:forEach>
	
	<form:form commandName="roleForm" method="POST">
		<div>
			<form:label path="roleId">Id:</form:label>
			<form:input path="roleId"/>
			<form:errors path="roleId"  delimiter=";&nbsp;" element="span" cssClass="errors-span" />
		</div>
		<div>
			<form:label path="roleName">Role Name:</form:label>
			<form:input path="roleName"/>
			<form:errors path="roleName"  delimiter=";&nbsp;" element="span" cssClass="errors-span" />
		</div>
		<input type="submit" />
	</form:form>
	
	<spring:url value="/role" var="rolesUrl" />
	<a href="${rolesUrl}">Cancel</a>
</body>
</html>