package com.brettchild.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brettchild.springmvc.dao.RoleDao;
import com.brettchild.springmvc.domain.Role;
import com.brettchild.springmvc.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public boolean insertRole(Role role) {
		return roleDao.insertRole(role);
	}

	@Override
	@Transactional
	public Role getRole(int roleId) {
		return roleDao.getRole(roleId);
	}

	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	@Transactional
	public boolean updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	@Override
	@Transactional
	public boolean deleteRole(Role roleId) {
		return roleDao.deleteRole(roleId);
	}

}
