package com.brettchild.springmvc.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.IngredientDao;
import com.brettchild.springmvc.dao.RecipeDao;
import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.dao.impl.util.DaoTestUtil;
import com.brettchild.springmvc.dao.impl.util.VerifyDataUtil;
import com.brettchild.springmvc.domain.Ingredient;
import com.brettchild.springmvc.domain.Recipe;
import com.brettchild.springmvc.domain.User;

@ContextConfiguration(locations = {"classpath:/spring/DaoImplTest-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IngredientDaoImplTest {
	
	@Autowired
	private RecipeDao recipeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IngredientDao ingredientDao;
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	private VerifyDataUtil verifyDataUtil;
	
	private User user = DaoTestUtil.createUser();
	private Recipe recipe = DaoTestUtil.createRecipe();
	private Ingredient ingredient = DaoTestUtil.createIngredient();
	
	@Before
	public void init() {
		
		verifyDataUtil = new VerifyDataUtil("ingredient", "ingredientId", simpleJdbcTemplate);
		
		DaoTestUtil.insertUser(userDao, user);
		recipe.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe);
		
	}		
	

	@Test
	public void testInsertIngredient() {
		
		ingredient.setRecipe(recipe);
		DaoTestUtil.insertIngredient(ingredientDao, ingredient);
		
		assertTrue( verifyDataUtil.verifyDataInTable("ingredientId",
				Collections.singletonList( String.valueOf( ingredient.getIngredientId() ) ),
				ingredient.getIngredientId() ) );
		
		
	}

	@Test
	public void testGetIngredient() {
		
		ingredient.setRecipe(recipe);
		DaoTestUtil.insertIngredient(ingredientDao, ingredient);
		
		Ingredient i = ingredientDao.getIngredient(ingredient.getIngredientId());
		
		assertNotNull(i);
		assertEquals(ingredient.getIngredientId(), i.getIngredientId());
		
		assertTrue( verifyDataUtil.verifyDataInTable("ingredientId",
				Collections.singletonList( String.valueOf( i.getIngredientId() ) ),
				i.getIngredientId() ) );
		
	}

	@Test
	public void testUpdateIngredient() {
		
		ingredient.setRecipe(recipe);
		DaoTestUtil.insertIngredient(ingredientDao, ingredient);
		
		ingredient.setIngredientName("UPDATE");
		
		assertTrue( ingredientDao.updateIngredient(ingredient) );
		
		assertTrue( verifyDataUtil.verifyDataInTable("ingredientName",
				Collections.singletonList( String.valueOf( ingredient.getIngredientName() ) ),
				ingredient.getIngredientId() ) );
		
	}

	@Test
	public void testDeleteIngredient() {
		
		ingredient.setRecipe(recipe);
		DaoTestUtil.insertIngredient(ingredientDao, ingredient);
		
		assertTrue( ingredientDao.deleteIngredient(ingredient) );
		
		assertTrue( !verifyDataUtil.verifyDataInTable("ingredientId",
				Collections.singletonList( String.valueOf( ingredient.getIngredientId() ) ),
				ingredient.getIngredientId() ) );
		
	}

}
