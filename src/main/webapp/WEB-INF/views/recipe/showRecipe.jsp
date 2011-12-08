<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe showRecipe</title>
</head>
<body>

	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}" arguments="${error.arguments}"/>
	</c:forEach>

	<c:if test="${empty errors}">
	
		<h1>${recipe.recipeName}</h1>
		<p>
			<spring:url var="userUrl" value="/user/${recipe.user.userId}" />
			Created By: <a href="${userUrl}">${recipe.user.username}</a>
		</p>
		
		<h3>Directions</h3>
		<p>
			${recipe.recipeDirections}
		</p>
		
		<h3>Ingredients:</h3>
		<ul>
			<c:forEach items="${recipe.ingredients}" var="ingredient">
				<li>
					<span class="bold">${ingredient.ingredientAmount} ${ingredient.unitOfMeasure}</span> 
					${ingredient.ingredientName}
				</li>
			</c:forEach>
		</ul>
		
	</c:if>
	
	
</body>
</html>