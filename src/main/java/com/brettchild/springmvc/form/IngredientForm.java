package com.brettchild.springmvc.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class IngredientForm {

	// Optional.  Validate manually if needed.
	private Integer ingredientId;
	
	@NotEmpty
	@Size(max = 64)
	private String ingredientName;
	
	@NotNull
	@DecimalMin(".001")
	private BigDecimal ingredientAmount;
	
	@NotEmpty
	@Size(max = 16)
	private String unitOfMeasure;
	
	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
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
		return "IngredientForm [ingredientId=" + ingredientId
				+ ", ingredientName=" + ingredientName + ", ingredientAmount="
				+ ingredientAmount + ", unitOfMeasure=" + unitOfMeasure + "]";
	}

	
	
	
}
