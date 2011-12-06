<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe showUserREcipes</title>
</head>
<body>

	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}" arguments="${error.arguments}"/>
	</c:forEach>

	<h1>${user.username}</h1>
	
	<ul>
		<c:forEach var="recipe" items="${user.recipes}">
			<li>
				<spring:url var="recipeUrl" value="/recipe/${recipe.recipeId}" />
				<a href="${recipeUrl}">${recipe.recipeName}</a>
			</li>
		</c:forEach>
	</ul>
	
	
</body>
</html>