package com.brettchild.springmvc.validator.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.brettchild.springmvc.validator.constraints.EmptyOrSize;

public class EmptyOrSizeValidator implements ConstraintValidator<EmptyOrSize, String> {

	private int min;
	private int max;
	
	@Override
	public void initialize(EmptyOrSize constraintAnnotation) {
		
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
		
		validateParameters();
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean isValid = false;
		
		if(value != null && value.length() > 0) {
			
			int length = value.length();
			isValid =  length >= min && length <= max;
			
		} else 
			isValid =  true;
		
		return isValid;
	}
	
	private void validateParameters() {
		if ( min < 0 ) {
			throw new IllegalArgumentException( "The min parameter cannot be negative." );
		}
		if ( max < 0 ) {
			throw new IllegalArgumentException( "The max parameter cannot be negative." );
		}
		if ( max < min ) {
			throw new IllegalArgumentException( "The length cannot be negative." );
		}
	}

}
