package com.brettchild.springmvc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.brettchild.springmvc.dao.RecipeDao;
import com.brettchild.springmvc.domain.Recipe;

@Repository
public class RecipeDaoImpl implements RecipeDao {

	private final static Logger logger = Logger.getLogger(RecipeDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insertRecipe(Recipe recipe) {

		int recipeId = 0;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.save(recipe);
			session.flush();
			recipeId = recipe.getRecipeId();

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + recipe.toString());
			recipeId = 0;

		}

		return recipeId;

	}

	@Override
	public Recipe getRecipe(int recipeId) {

		Recipe recipe = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			recipe = (Recipe) session.get(Recipe.class, recipeId);
			session.flush();

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + recipe.toString());
			recipe = null;

		}

		return recipe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> getRecipes() {

		List<Recipe> recipes = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Recipe");
			recipes = query.list();
			session.flush();

		} catch (HibernateException e) {

			logger.error(e.getMessage());
			recipes = null;

		}

		return recipes;
	}

	@Override
	public boolean updateRecipe(Recipe recipe) {

		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.update(recipe);
			session.flush();
			success = true;

		} catch (HibernateException e) {

			e.printStackTrace();
			logger.error(e.getMessage() + " on " + recipe.toString());
			success = false;

		}

		return success;
	}

	@Override
	public boolean deleteRecipe(Recipe recipe) {

		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(recipe);
			session.flush();
			success = true;

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + recipe.toString());
			success = false;

		}

		return success;

	}
}
