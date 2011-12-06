package com.brettchild.springmvc.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class IngredientForm {

	@NotEmpty
	@Size(max = 64)
	private String ingredientName;
	
	@NotEmpty
	@DecimalMin(".001")
	private BigDecimal ingredientAmount;
	
	@NotEmpty
	@Size(max = 16)
	private String unitOfMeasure;

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
		return "IngredientForm [ingredientName=" + ingredientName
				+ ", ingredientAmount=" + ingredientAmount + ", unitOfMeasure="
				+ unitOfMeasure + "]";
	}
	
	
}
