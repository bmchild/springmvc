package com.brettchild.springmvc.service;

import java.util.List;

import com.brettchild.springmvc.domain.User;

public interface UserService {

	public int insertUser(User user);
	
	public User getUser(int userId);
	
	public List<User> getUsers();
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);
	
}
