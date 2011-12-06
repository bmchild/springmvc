package com.brettchild.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.IngredientDao;
import com.brettchild.springmvc.domain.Ingredient;
import com.brettchild.springmvc.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDao ingredientDao;
	
	@Override
	@Transactional
	public int insertIngredient(Ingredient ingredient) {
		return ingredientDao.insertIngredient(ingredient);
	}

	@Override
	@Transactional
	public Ingredient getIngredient(Integer ingredientId) {
		return ingredientDao.getIngredient(ingredientId);
	}

	@Override
	@Transactional
	public boolean updateIngredient(Ingredient ingredient) {
		return ingredientDao.updateIngredient(ingredient);
	}

	@Override
	@Transactional
	public boolean deleteIngredient(Ingredient ingredient) {
		return ingredientDao.deleteIngredient(ingredient);
	}

}
