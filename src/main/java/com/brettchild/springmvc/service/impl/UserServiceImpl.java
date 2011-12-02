package com.brettchild.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.domain.User;
import com.brettchild.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	@Transactional
	public User getUser(final int userId) {
		return userDao.getUser(userId);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	@Transactional
	public boolean deleteUser(User user) {
		return userDao.deleteUSer(user);
	}

}
