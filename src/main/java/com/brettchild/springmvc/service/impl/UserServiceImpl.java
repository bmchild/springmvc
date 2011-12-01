package com.brettchild.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.domain.User;
import com.brettchild.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TransactionTemplate transactionTemplate;

	@Override
	@Transactional
	public int insertUser(final User user) {
		return userDao.insertUser(user);
	}

	@Override
	@Transactional
	public User getUser(final int userId) {
		
		 return transactionTemplate
			.execute(new TransactionCallback<User>() {

				@Override
				public User doInTransaction(TransactionStatus arg0) {
					return userDao.getUser(userId);
				}

			});
		 
		
	}

	@Override
	@Transactional
	public List<User> getUsers() {

		return transactionTemplate
				.execute(new TransactionCallback<List<User>>() {

					@Override
					public List<User> doInTransaction(TransactionStatus arg0) {
						return userDao.getUsers();
					}

				});

	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	@Transactional
	public boolean deleteUSer(User user) {
		return userDao.deleteUSer(user);
	}

}
