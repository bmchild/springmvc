<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Roles</title>
</head>
<body>
	<h1>Roles</h1>
	
	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}" arguments="${error.arguments}"/>
	</c:forEach>
	
	<c:if test="${not empty roles }">
	
		<table>
			<thead>
				<tr>
					<td>Id</td>
					<td>Name</td>
					<td>&nbsp;</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roles}" var="role">
					<tr>
						<td>${role.roleId}</td>
						<td>${role.roleName}</td>
						<td>
							<spring:url value="/role/${role.roleId}/update" var="updateRoleUrl" />
							<a href="${updateRoleUrl}">Update</a>
							<spring:url value="/role/${role.roleId}/delete" var="deleteRoleUrl" /> 
							<a href="${deleteRoleUrl}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</c:if>
	
	<spring:url value="/role/new" var="newRoleUrl" />
	<a href="${newRoleUrl}">+ Add a role</a>
</body>
</html>