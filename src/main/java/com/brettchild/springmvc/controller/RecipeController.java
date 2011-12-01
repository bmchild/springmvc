package com.brettchild.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	
	protected static Logger logger = Logger.getLogger(RecipeController.class);
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String showAllRecipes(@PathVariable("id") String userId, Model model) {

		logger.debug("Entry");
		// Get all Things

		logger.debug("Exit");
		return "thing";
	}

}
