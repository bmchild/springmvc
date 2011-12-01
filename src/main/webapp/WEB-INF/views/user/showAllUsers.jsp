<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
	<h1>Users</h1>
	<ul>
		<c:forEach var="user" items="${users}">
			<li>${user}</li>
		</c:forEach>
	</ul>
</body>
</html>