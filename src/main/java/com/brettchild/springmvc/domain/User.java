package com.brettchild.springmvc.domain;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String username;
	private String password;
	private Date dateCreated;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "UserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private Collection<Role> userRoles = new LinkedHashSet<Role>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_userId")
	private Collection<Recipe> recipes = new LinkedHashSet<Recipe>();
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Collection<Role> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Collection<Role> userRoles) {
		this.userRoles = userRoles;
	}
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", dateCreated=" + dateCreated
				+ "]";
	}
	
	
}
