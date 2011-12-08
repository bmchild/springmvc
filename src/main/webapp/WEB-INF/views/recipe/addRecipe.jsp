<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="ingredientRow">
	<div class="ingredient">
		<div>
			<label for="ingredientsXXX.ingredientName">Ingredient Name:</label> 
			<input id="ingredientsXXX.ingredientName" name="ingredients[XXX].ingredientName" type="text">
		</div>
		<div>
			<label for="ingredientsXXX.ingredientAmount">Ingredient Amount:</label> 
			<input id="ingredientsXXX.ingredientAmount" name="ingredients[XXX].ingredientAmount" type="text">
		</div>
		<div>
			<label for="ingredientsXXX.unitOfMeasuret">Unit of Measure:</label>
			<select id="ingredientsXXX.unitOfMeasure" name="ingredients[XXX].unitOfMeasure">
				<option value="cup">Cup</option>
				<option value="oz" >Ounce</option>
				<option value="tsp">Teaspoon</option>
			</select> 
		</div>
	</div>
</c:set>
<spring:message text="${ingredientRow}" javaScriptEscape="true" var="escapedIngredientRow"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add recipe</title>

<c:url value="/js/jquery-1.7.1.min.js" var="jqueryUrl"/>
<script type="text/javascript" src="${jqueryUrl }"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".add-ingredient").click(function() {
			
			var size = $(".ingredient").size();
			
			$("#ingredients").append( generateRow ( size ) );
			
		});
		
	 });
	
	function generateRow(index) {
		
		var row = "${escapedIngredientRow}";
		
		row = row.replace(/XXX/g, index);
		
		return row;
		
	}
</script>

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
			<form:errors element="div"/>
			<form:hidden path="userId" />
			<form:hidden path="recipeId" />
			<div>
				<form:label path="recipeName">Recipe Name: </form:label>
				<form:input path="recipeName" />
				<form:errors path="recipeName" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
			</div>
			<div>
				<form:label path="recipeDirections">Directions:</form:label>
				<form:textarea path="recipeDirections" />
				<form:errors path="recipeDirections" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
			</div>
			
			<fieldset id="ingredients">
				<legend>Ingredients</legend>
				<form:errors path="ingredients" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
				
				<!--  needed in case of populated RecipeForm such as on errors -->
				<c:forEach items="${recipeForm.ingredients}" var="ingredient"
					varStatus="x">
					<div class="ingredient">
						<form:hidden path="ingredients[${x.index}].ingredientId" />
						<div>
							<form:label path="ingredients[${x.index}].ingredientName">Ingredient Name:</form:label> 
							<form:input path="ingredients[${x.index}].ingredientName" />
							<form:errors path="ingredients[${x.index}].ingredientName" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
						</div>
						<div>
							<form:label path="ingredients[${x.index}].ingredientAmount">Ingredient Amount:</form:label> 
							<form:input path="ingredients[${x.index}].ingredientAmount" />
							<form:errors path="ingredients[${x.index}].ingredientAmount" delimiter=";&nbsp;" element="span" cssClass="errors-span" />
						</div>
						<div>
							<form:label path="ingredients[${x.index}].unitOfMeasure">Unit of Measure:</form:label> 
							<form:select path="ingredients[${x.index}].unitOfMeasure">
								<form:option value="cup">Cup</form:option>
								<form:option value="oz">Ounce</form:option>
								<form:option value="tsp">Teaspoon</form:option>
							</form:select>
							<form:errors path="ingredients[${x.index}].unitOfMeasure" delimiter=";&nbsp;" element="span" cssClass="errors-span" /> 
						</div>
					</div>
				</c:forEach>
				
			</fieldset>
			<a href="javascript:void(null)" class="add-ingredient">+ Add Ingredient</a>
			<input type="submit" />

		</form:form>
	</c:if>
</body>
</html>