package com.brettchild.springmvc.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ingredientId;
	@ManyToOne(optional = false)
	private Recipe recipe;
	@Column(length = 64)
	private String ingredientName;
	@Column(length = 6, scale = 2)
	private BigDecimal ingredientAmount;
	@Column(length = 16)
	private String unitOfMeasure;

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public BigDecimal getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(BigDecimal ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", recipe="
				+ recipe + ", ingredientName=" + ingredientName
				+ ", ingredientAmount=" + ingredientAmount + ", unitOfMeasure="
				+ unitOfMeasure + "]";
	}

}
