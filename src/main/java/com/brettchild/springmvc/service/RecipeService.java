package com.brettchild.springmvc.service;

import java.util.List;

import com.brettchild.springmvc.domain.Recipe;

public interface RecipeService {

	public int insertRecipe(Recipe recipe);
	
	public Recipe getRecipe(int recipeId);
	
	public List<Recipe> getRecipes();
	
	public boolean updateRecipe(Recipe recipe);
	
	public boolean deleteRecipe(Recipe recipeId);
	
}
