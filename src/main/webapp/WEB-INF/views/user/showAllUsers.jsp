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
	<h1>Users</h1>
	
	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}" arguments="${error.arguments}"/>
	</c:forEach>
	
	<ul>
		<c:forEach var="user" items="${users}">
			<li>
				<spring:url var="userUrl" value="/user/${user.userId}" />
				<a href="${userUrl}">${user.username}</a>
			</li>
		</c:forEach>
	</ul>
	
	<p>
		<spring:url var="newUserUrl" value="/user/new" />
		<a href="${newUserUrl}">Add a user</a>
	</p>
</body>
</html>