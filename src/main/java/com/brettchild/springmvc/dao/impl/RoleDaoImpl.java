package com.brettchild.springmvc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettchild.springmvc.dao.RoleDao;
import com.brettchild.springmvc.domain.Role;

@Service
public class RoleDaoImpl implements RoleDao {

	private final static Logger logger = Logger.getLogger(RoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertRole(Role role) {

		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.save(role);
			session.flush();
			success = true;
			
		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + role.toString());
			success = false;

		}

		return success;
	}

	@Override
	public Role getRole(int roleId) {

		Role role = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			role = (Role) session.get(Role.class, roleId);
			session.flush();

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + roleId);
			role = null;

		}

		return role;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		
		List<Role> roles = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Role role order by role.roleId asc");
			roles = query.list();
			session.flush();

		} catch (HibernateException e) {

			logger.error(e.getMessage());
			roles = null;

		}

		return roles;
		
	}

	@Override
	public boolean updateRole(Role role) {
		
		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.update(role);
			session.flush();
			success = true;

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + role.toString());
			success = false;

		}

		return success;
		
	}

	@Override
	public boolean deleteRole(Role role) {
		
		boolean success = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(role);
			session.flush();
			success = true;

		} catch (HibernateException e) {

			logger.error(e.getMessage() + " on " + role.toString());
			success = false;

		}

		return success;
		
	}

}
