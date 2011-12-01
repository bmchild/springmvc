package com.brettchild.springmvc.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.UserDao;
import com.brettchild.springmvc.dao.impl.util.VerifyDataUtil;
import com.brettchild.springmvc.domain.User;

@ContextConfiguration(locations = { "classpath:/spring/DaoImplTest-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoImplTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	private VerifyDataUtil verifyDataUtil;
	
	@Before
	public void init() {
		verifyDataUtil = new VerifyDataUtil("user", "userId", simpleJdbcTemplate);
	}

	@Test
	@Transactional
	public void testInsertUser() {

		User user = new User();
		user.setDateCreated(new Date());
		user.setPassword("TESTER");
		user.setUsername("TESTER");

		int userId = userDao.insertUser(user);

		assertTrue(userId > 0);
		
		assertTrue( verifyDataUtil.verifyDataInTable("userId", Collections.singletonList(String.valueOf(userId)), userId) );

	}

	@Test
	@Transactional
	public void testGetUser() {

		User user = new User();
		user.setDateCreated(new Date());
		user.setPassword("TESTER");
		user.setUsername("TESTER");

		int userId = userDao.insertUser(user);

		assertTrue(userId > 0);

		User user2 = userDao.getUser(userId);

		assertNotNull(user2);

		assertEquals(user.getUserId(), user2.getUserId());
		
		assertTrue( verifyDataUtil.verifyDataInTable("userId", Collections.singletonList(String.valueOf(userId)), userId) );

	}

	@Test
	@Transactional
	public void testGetUsers() {

		User user = new User();
		user.setDateCreated(new Date());
		user.setPassword("TESTER");
		user.setUsername("TESTER");

		User user2 = new User();
		user2.setDateCreated(new Date());
		user2.setPassword("TESTER2");
		user2.setUsername("TESTER2");

		userDao.insertUser(user);
		userDao.insertUser(user2);

		List<User> users = userDao.getUsers();

		assertNotNull(users);

		assertEquals(2, users.size());
		
		List<String> userIds = new ArrayList<String>(2);
		userIds.add( String.valueOf( user.getUserId() ) );
		userIds.add( String.valueOf( user2.getUserId() ) );
		
		assertTrue( verifyDataUtil.verifyDataInTable("userId", userIds, user.getUserId(), user2.getUserId() ) );

	}

	@Test
	@Transactional
	public void testUpdateUser() {

		User user = new User();
		user.setDateCreated(new Date());
		user.setPassword("TESTER");
		user.setUsername("TESTER");
		
		userDao.insertUser(user);

		user.setPassword("UPDATE");
		assertTrue(userDao.updateUser(user));

		assertTrue( verifyDataUtil.verifyDataInTable("password", Collections.singletonList(String.valueOf("UPDATE")), user.getUserId()) );

	}

	@Test
	@Transactional
	public void testDeleteUSer() {

		User user = new User();
		user.setDateCreated(new Date());
		user.setPassword("TESTER");
		user.setUsername("TESTER");

		int userId = userDao.insertUser(user);

		assertTrue(userId > 0);

		assertTrue(userDao.deleteUSer(user));

		assertTrue( !verifyDataUtil.verifyDataInTable("userId", Collections.singletonList(String.valueOf(userId)), userId) );

	}


}
