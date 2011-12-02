package com.brettchild.springmvc.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

	@NotEmpty(message = "{NotEmpty.user.username}")
	@Size(min=3, max=20)
	private String username;
	@NotEmpty
	@Size(min=3, max=20)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
