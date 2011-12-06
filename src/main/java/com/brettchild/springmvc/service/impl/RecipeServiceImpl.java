package com.brettchild.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.RecipeDao;
import com.brettchild.springmvc.domain.Recipe;
import com.brettchild.springmvc.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao recipeDao;
	
	@Override
	@Transactional
	public int insertRecipe(Recipe recipe) {
		return recipeDao.insertRecipe(recipe);
	}

	@Override
	@Transactional
	public Recipe getRecipe(int recipeId) {
		return recipeDao.getRecipe(recipeId);
	}

	@Override
	@Transactional
	public List<Recipe> getRecipes() {
		return recipeDao.getRecipes();
	}

	@Override
	@Transactional
	public boolean updateRecipe(Recipe recipe) {
		return recipeDao.updateRecipe(recipe);
	}

	@Override
	@Transactional
	public boolean deleteRecipe(Recipe recipeId) {
		return recipeDao.deleteRecipe(recipeId);
	}

}
