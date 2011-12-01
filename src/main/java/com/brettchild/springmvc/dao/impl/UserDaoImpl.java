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
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	private final static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public int insertUser(User user) {

		int userId = 0;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.save(user);
			session.flush();
			userId = user.getUserId();

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + user.toString());
			userId = 0;

		}

		return userId;
	}

	@Override
	public User getUser(int userId) {

		User user = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			user = (User) session.get(User.class, userId);
			session.flush();

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + userId);
			userId = 0;

		}

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {

		List<User> users = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User");
			users = query.list();
			session.flush();

		} catch (HibernateException e) {

			logger.error(e.getMessage());
			users = null;

		}

		return users;
	}

	@Override
	public boolean updateUser(User user) {

		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.update(user);
			session.flush();
			success = true;

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + user.toString());
			success = false;

		}

		return success;
	}

	@Override
	public boolean deleteUSer(User user) {

		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(user);
			session.flush();
			success = true;

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + user.toString());
			success = false;

		}

		return success;

	}

}
