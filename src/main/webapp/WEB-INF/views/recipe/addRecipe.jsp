<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add recipe</title>
</head>
<body>

	<h1>Add a new Recipe for ${user.username}</h1>

	<c:forEach items="${errors.messages}" var="error">
		<spring:message code="${error.code}" text="${error.message}"
			arguments="${error.arguments}" />
	</c:forEach>

	<c:if test="${empty errors}">
		<form:form method="POST" commandName="recipeForm">
			<%-- Globals Errors --%>
			<form:errors element="div" />
			<form:hidden path="userId" />
			<div>
				<form:label path="recipeName">Recipe Name: </form:label>
				<form:input path="recipeName" />
			</div>
			<div>
				<form:label path="recipeDirections">Directions:</form:label>
				<form:textarea path="recipeDirections" />
			</div>
			<fieldset>
				Ingredients
				<div>
					<label path="ingredients[].ingredientName">Ingredient Name:</label>
					<input path="ingredients[].ingredientName" />
				</div>
				<div>
					<label path="ingredients[].ingredientAmount">Ingredient Amount:</label>
					<input path="ingredients[].ingredientAmount" />
				</div>
				<div>
					<label path="ingredients[].unitOfMeasure">Unit of Measure:</label>
					<select path="ingredients[].unitOfMeasure">
						<option value="cup">Cup</option>
						<option value="oz">Ounce</option>
						<option value="tsp">Teaspoon</option>
					</select>
				</div>
			</fieldset>
			<input type="submit" />

		</form:form>
	</c:if>
</body>
</html>