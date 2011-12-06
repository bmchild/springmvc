package com.brettchild.springmvc.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class RecipeForm {

	@NotNull
	@Size(max=64)
	private String recipeName;
	
	@NotNull
	@Size(max=255)
	private String recipeDirections;
	
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private int userId;
	
	@NotNull
	@Size(min = 1)
	@Valid
	private Collection<IngredientForm> ingredients = new ArrayList<IngredientForm>();

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

	public Collection<IngredientForm> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Collection<IngredientForm> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "RecipeForm [recipeName=" + recipeName + ", recipeDirections="
				+ recipeDirections + ", userId=" + userId + ", ingredients="
				+ ingredients + "]";
	}
	
	
}
