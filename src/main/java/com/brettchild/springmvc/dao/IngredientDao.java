package com.brettchild.springmvc.dao;

import com.brettchild.springmvc.domain.Ingredient;

public interface IngredientDao {

	public int insertIngredient(Ingredient ingredient);

	public Ingredient getIngredient(Integer ingredientId);

	public boolean updateIngredient(Ingredient ingredient);

	public boolean deleteIngredient(Ingredient ingredient);
	
}
