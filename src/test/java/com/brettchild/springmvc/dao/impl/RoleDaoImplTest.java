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

import com.brettchild.springmvc.dao.RoleDao;
import com.brettchild.springmvc.dao.impl.util.VerifyDataUtil;
import com.brettchild.springmvc.domain.Role;

@ContextConfiguration(locations = { "classpath:/spring/DaoImplTest-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RoleDaoImplTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	private VerifyDataUtil verifyDataUtil;
	
	@Before
	public void init() {
		verifyDataUtil = new VerifyDataUtil("role", "roleId", simpleJdbcTemplate);
	}

	@Test
	public void testInsertRole() {
		
		Role role = new Role();
		role.setRoleName("TESTER");
		role.setRoleId(0);
		
		assertTrue( roleDao.insertRole(role) );
		
		assertTrue( verifyDataUtil.verifyDataInTable("roleId", Collections.singletonList( String.valueOf( role.getRoleId() ) ), role.getRoleId()));
		
	}

	@Test
	public void testGetRole() {
		
		Role role = new Role();
		role.setRoleName("TESTER");
		role.setRoleId(0);
		
		assertTrue( roleDao.insertRole(role) );
		
		Role role2 = roleDao.getRole(role.getRoleId());
		
		assertNotNull(role2);
		
		assertTrue( verifyDataUtil.verifyDataInTable("roleId", Collections.singletonList( String.valueOf( role2.getRoleId() ) ), role2.getRoleId()));
		
	}

	@Test
	public void testGetRoles() {
		
		Role role = new Role();
		role.setRoleName("TESTER");
		role.setRoleId(0);
		
		assertTrue( roleDao.insertRole(role) );
		
		Role role2 = new Role();
		role2.setRoleName("TESTER");
		role2.setRoleId(1);
		
		assertTrue( roleDao.insertRole(role2) );
		
		List<Role> roles = roleDao.getRoles();
		
		assertNotNull(roles);
		assertEquals(2, roles.size());
		
		List<String> roleIds = new ArrayList<String>(2);
		roleIds.add( String.valueOf( role.getRoleId() ) );
		roleIds.add( String.valueOf( role2.getRoleId() ) );
		
		assertTrue( verifyDataUtil.verifyDataInTable("roleId", roleIds, role.getRoleId(), role2.getRoleId()));
		
	}

	@Test
	public void testUpdateRole() {
		
		Role role = new Role();
		role.setRoleName("TESTER");
		role.setRoleId(0);
		
		assertTrue( roleDao.insertRole(role) );
		
		role.setRoleName("UPDATED");
		
		assertTrue( roleDao.updateRole(role) );
		
		assertTrue( verifyDataUtil.verifyDataInTable("roleName", Collections.singletonList( role.getRoleName() ), role.getRoleId()));
		
	}

	@Test
	public void testDeleteRole() {
		
		Role role = new Role();
		role.setRoleName("TESTER");
		role.setRoleId(0);
		
		assertTrue( roleDao.insertRole(role) );
		
		assertTrue( roleDao.deleteRole(role) );
		
		assertTrue( !verifyDataUtil.verifyDataInTable("roleId", Collections.singletonList( String.valueOf( role.getRoleId() ) ), role.getRoleId()));
		
	}
	
	
}
