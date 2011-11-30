package com.brettchild.springmvc.domain;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recipeId;
	private String recipeName;
	private String recipeDirections;
	
	@ManyToOne(optional=false)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_recipeId")
	private Collection<Ingredient> ingredients = new LinkedHashSet<Ingredient>();

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeDirections() {
		return recipeDirections;
	}

	public void setRecipeDirections(String recipeDirections) {
		this.recipeDirections = recipeDirections;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName
				+ ", recipeDirections=" + recipeDirections + ", user="
				+ user + ", ingredients=" + ingredients + "]";
	}

	
	
	
}
