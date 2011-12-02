package com.brettchild.springmvc.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.brettchild.springmvc.validator.constraints.EmptyOrSize;

public class UserUpdateForm {

	@NumberFormat(style = Style.NUMBER)
    @Min(1)
	int userId;
	
	@NotEmpty(message = "{NotEmpty.user.username}")
	@Size(min=3, max=20)
	private String username;
	
	@EmptyOrSize(min=3, max=20)
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
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

	@Override
	public String toString() {
		return "UserUpdateForm [userId=" + userId + ", username=" + username
				+ ", password=" + password + "]";
	}

	
	
	
	
	
}
