package com.brettchild.springmvc.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.RecipeDao;
import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.dao.impl.util.DaoTestUtil;
import com.brettchild.springmvc.dao.impl.util.VerifyDataUtil;
import com.brettchild.springmvc.domain.Recipe;
import com.brettchild.springmvc.domain.User;

@ContextConfiguration(locations = { "classpath:/spring/DaoImplTest-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RecipeDaoImplTest {

	@Autowired
	private RecipeDao recipeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;

	private VerifyDataUtil verifyRecipeData;
	private VerifyDataUtil verifyUserData;

	private User user = DaoTestUtil.createUser();
	private Recipe recipe = DaoTestUtil.createRecipe();

	@Before
	public void init() {
		verifyRecipeData = new VerifyDataUtil("recipe", "recipeId",
				simpleJdbcTemplate);
		verifyUserData = new VerifyDataUtil("user", "userId",
				simpleJdbcTemplate);
	}

	@Test
	public void testInsertRecipeFailure() {

		// Should not work w/o a user
		int recipeId = recipeDao.insertRecipe(recipe);

		assertEquals(0, recipeId);

	}

	@Test
	public void testInsertRecipe() {

		// This should work
		DaoTestUtil.insertUser(userDao, user);
		recipe.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe);

		assertTrue( verifyUserData.verifyDataInTable("userId",
				Collections.singletonList(String.valueOf(user.getUserId())),
				user.getUserId()) );

		assertTrue( verifyRecipeData
				.verifyDataInTable("recipeId", Collections.singletonList(String
						.valueOf(recipe.getRecipeId())), recipe.getRecipeId()) );

	}

	@Test
	public void testGetRecipe() {

		DaoTestUtil.insertUser(userDao, user);
		recipe.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe);

		Recipe r = recipeDao.getRecipe(recipe.getRecipeId());

		assertNotNull(r);

		assertTrue( verifyRecipeData.verifyDataInTable("recipeId",
				Collections.singletonList(String.valueOf(r.getRecipeId())),
				r.getRecipeId() ) );

	}

	@Test
	public void testGetRecipes() {
		
		DaoTestUtil.insertUser(userDao, user);
		recipe.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe);
		
		Recipe recipe2 = DaoTestUtil.createRecipe();
		recipe2.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe2);
		
		List<Recipe> recipes = recipeDao.getRecipes();
		
		assertNotNull(recipes);
		assertEquals(2, recipes.size());
		
		List<String> recipeIds = new ArrayList<String>(2);
		recipeIds.add( String.valueOf ( recipe.getRecipeId() ) );
		recipeIds.add( String.valueOf ( recipe2.getRecipeId() ) );
		
		assertTrue ( verifyRecipeData.verifyDataInTable( "recipeId", 
				recipeIds, 
				recipe.getRecipeId(), 
				recipe2.getRecipeId() ) );
		
	
		
	}

	@Test
	public void testUpdateRecipe() {
		
		DaoTestUtil.insertUser(userDao, user);
		recipe.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe);
		
		recipe.setRecipeDirections("UPDATE");
		
		assertTrue( recipeDao.updateRecipe(recipe) );
		
		assertTrue( verifyRecipeData.verifyDataInTable("recipeDirections",
				Collections.singletonList( recipe.getRecipeDirections() ),
				recipe.getRecipeId() ) );
		
	}

	@Test
	public void testDeleteRecipe() {
		
		DaoTestUtil.insertUser(userDao, user);
		recipe.setUser(user);
		DaoTestUtil.insertRecipe(recipeDao, recipe);
		
		assertTrue( recipeDao.deleteRecipe(recipe) );
		
		assertTrue( !verifyRecipeData.verifyDataInTable("recipeDirections",
				Collections.singletonList( recipe.getRecipeDirections() ),
				recipe.getRecipeId() ) );
		
	}

}
