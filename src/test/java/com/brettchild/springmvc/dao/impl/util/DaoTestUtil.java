package com.brettchild.springmvc.dao.impl.util;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import com.brettchild.springmvc.dao.IngredientDao;
import com.brettchild.springmvc.dao.RecipeDao;
import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.domain.Ingredient;
import com.brettchild.springmvc.domain.Recipe;
import com.brettchild.springmvc.domain.User;

public class DaoTestUtil {

	public static Recipe createRecipe() {
		
		Recipe r = new Recipe();
		r.setRecipeDirections("TEST DIRECTIONS");
		r.setRecipeName("TESTER");
	
		return r;
	}
	
	public static int insertRecipe(RecipeDao dao, Recipe recipe) {
		
		int recipeId = dao.insertRecipe(recipe);
		
		assertTrue(recipeId > 0);
		
		return recipeId;
		
	}
	
	public static User createUser() {
		
		User u = new User();
		u.setDateCreated(new Date());
		u.setPassword("PASSWORD");
		u.setUsername("USERNAME");
		
		return u;
	}
	
	public static int insertUser (UserDao dao, User user) {
		
		int userId = dao.insertUser(user);
		
		assertTrue(userId > 0);
		
		return userId;
		
	}

	public static Ingredient createIngredient() {
		Ingredient i = new Ingredient();
		i.setIngredientAmount(new BigDecimal("1.0"));
		i.setIngredientName("TESTER");
		i.setUnitOfMeasure("CUP");
		return i;
	}
	
	public static int insertIngredient(IngredientDao dao, Ingredient ingredient) {
		
		int ingredientId = dao.insertIngredient(ingredient);
		
		assertTrue(ingredientId > 0);
		
		return ingredientId;
		
	}
}
