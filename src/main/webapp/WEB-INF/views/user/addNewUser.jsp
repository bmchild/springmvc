<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
	<h1>Add new User</h1>
	
	<form:form action="/new" method="POST">
		<form:input path="username"/><br />
		<form:input path="password"/>
		<input name="submit" type="submit" value="Create New User" />
	</form:form>
</body>
</html>