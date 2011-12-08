package com.brettchild.springmvc.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class RecipeForm {

	// Optional.  Validate manually on update. 
	private Integer recipeId;
	
	@NotEmpty
	@Size(max=64)
	private String recipeName;
	
	@NotEmpty
	@Size(max=255)
	private String recipeDirections;
	
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private int userId;
	
	@NotNull
	@Size(min = 1)
	@Valid
	private List<IngredientForm> ingredients;
	

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeDirections() {
		return recipeDirections;
	}

	public void setRecipeDirections(String recipeDirections) {
		this.recipeDirections = recipeDirections;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public List<IngredientForm> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientForm> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "RecipeForm [recipeId=" + recipeId + ", recipeName="
				+ recipeName + ", recipeDirections=" + recipeDirections
				+ ", userId=" + userId + ", ingredients=" + ingredients + "]";
	}

	
	
}
