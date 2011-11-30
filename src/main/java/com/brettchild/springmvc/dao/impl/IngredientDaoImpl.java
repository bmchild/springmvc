package com.brettchild.springmvc.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettchild.springmvc.dao.IngredientDao;
import com.brettchild.springmvc.domain.Ingredient;

@Service
public class IngredientDaoImpl implements IngredientDao {

	private final static Logger logger = Logger.getLogger(IngredientDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insertIngredient(Ingredient ingredient) {

		int ingredientId = 0;

		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.save(ingredient);
			session.flush();
			ingredientId = ingredient.getIngredientId();
			
		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + ingredient.toString());
			ingredientId = 0;
			
		}

		return ingredientId;
	}

	@Override
	public Ingredient getIngredient(Integer ingredientId) {
		
		Ingredient ingredient = null;
		
		try {
			
			Session session = sessionFactory.getCurrentSession();
			ingredient = (Ingredient)session.get(Ingredient.class, ingredientId);
			session.flush();
			
		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + ingredientId);
			ingredient = null;
			
		}
		
		return ingredient;
	}

	@Override
	public boolean updateIngredient(Ingredient ingredient) {
		
		boolean success = false;

		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.update(ingredient);
			session.flush();
			success = true;
			
		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + ingredient.toString());
			success = false;
			
		}

		return success;
	}

	@Override
	public boolean deleteIngredient(Ingredient ingredient) {
		
		boolean success = false;

		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.delete(ingredient);
			session.flush();
			success = true;
			
		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + ingredient.toString());
			success = false;
			
		}

		return success;
		
	}

}
