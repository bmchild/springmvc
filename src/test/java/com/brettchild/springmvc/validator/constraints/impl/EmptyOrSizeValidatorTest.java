package com.brettchild.springmvc.validator.constraints.impl;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.brettchild.springmvc.form.UserUpdateForm;

public class EmptyOrSizeValidatorTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void isValid() {

		UserUpdateForm userUpdateForm = new UserUpdateForm();
		userUpdateForm.setUserId(1);
		userUpdateForm.setUsername("TSTER");

		// Empty is ok
		Set<ConstraintViolation<UserUpdateForm>> constraintViolations = validator
				.validate(userUpdateForm);

		outContrainstViolationsMessage(constraintViolations);
		
		
		assertEquals(0, constraintViolations.size());
		
		// It should be too short
		userUpdateForm.setPassword("1");
		
		constraintViolations = validator.validate(userUpdateForm);
		
		assertEquals(1, constraintViolations.size());
		
		outContrainstViolationsMessage(constraintViolations);
		
		// It should be too long
		userUpdateForm.setPassword("123456789012345678901234567890");
		
		constraintViolations = validator.validate(userUpdateForm);

		assertEquals(1, constraintViolations.size());
		
		outContrainstViolationsMessage(constraintViolations);
		
		// It should be ok
		userUpdateForm.setPassword("12345");
		
		constraintViolations = validator.validate(userUpdateForm);
		
		assertEquals(0, constraintViolations.size());

	}

	private void outContrainstViolationsMessage(
			Set<ConstraintViolation<UserUpdateForm>> constraintViolations) {
		
		if(constraintViolations.size() > 0) {
			System.out.println(constraintViolations.iterator().next().getMessage());
		}
		
	}

}
