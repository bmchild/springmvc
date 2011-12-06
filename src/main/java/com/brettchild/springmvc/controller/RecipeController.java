package com.brettchild.springmvc.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brettchild.springmvc.domain.Recipe;
import com.brettchild.springmvc.domain.User;
import com.brettchild.springmvc.exception.MissingDataException;
import com.brettchild.springmvc.form.RecipeForm;
import com.brettchild.springmvc.jsp.JspMessage;
import com.brettchild.springmvc.jsp.JspMessages;
import com.brettchild.springmvc.service.RecipeService;
import com.brettchild.springmvc.service.UserService;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserService userService;
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	protected static Logger logger = Logger.getLogger(RecipeController.class);
	
	/**
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/{userId}/recipe", method = RequestMethod.GET)
	public String showUserRecipes(@PathVariable String userId, final Model model) {

		logger.debug("Entry showUserRecipes");
		
		String forward;
		
		JspMessages errors = new JspMessages();
		
		try {
			
			final int userIdInt = Integer.valueOf(userId);
			
			User user = userService.getUser(userIdInt);
			
			model.addAttribute(user);
			
		} catch (NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", userId));
		}
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors", errors);
			forward = "recipe/showUserRecipes";
		} else {
			forward = "recipe/showUserRecipes";
		}

		logger.debug("Exit showUserRecipes");
		return forward;
	}
	
	/**
	 * @param recipeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/recipe/{recipeId}", method = RequestMethod.GET)
	public String showRecipe(@PathVariable int recipeId, Model model) {

		logger.debug("Entry");
		
		Recipe recipe = recipeService.getRecipe(recipeId);
		
		model.addAttribute("recipe", recipe);

		logger.debug("Exit");
		return "recipe/showRecipe";
	}
	
	/**
	 * 
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/recipe/new", method = RequestMethod.GET)
	public String addRecipe(@PathVariable String userId, Model model) {
		
		JspMessages errors = new JspMessages();
		
		try {
			
			int userIdInt = Integer.valueOf(userId);
			
			User user = userService.getUser(userIdInt);
			
			if(user == null) 
				throw new MissingDataException("User");
		
			RecipeForm recipeForm = getRecipeFormObject();
			recipeForm.setUserId(userIdInt);
			
			model.addAttribute(user);
			model.addAttribute(recipeForm);
			
		} catch(NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", userId));
		} catch (MissingDataException e) {
			errors.add(new JspMessage("error.data.missing",  "{0} data is missing", e.getMessage()));
		}
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors", errors);
		}
		
		return "recipe/addRecipe";
	}
	
	/**
	 * @return
	 */
	@ModelAttribute
	public RecipeForm getRecipeFormObject() {
		return new RecipeForm();
	}
	
	/**
	 * 
	 * @param recipe
	 * @param bindingResult
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/recipe/new", method = RequestMethod.POST)
	public String addRecipe(@Valid RecipeForm recipeForm, BindingResult bindingResult, Model mode) {
		
		String forward;
		int recipeId = 0;
		
		if(!bindingResult.hasErrors()) {
			
			User user = userService.getUser(recipeForm.getUserId());
			
			if( user != null ) {
			
				Recipe recipe = new Recipe();
				recipe.setRecipeDirections(recipeForm.getRecipeDirections());
				recipe.setRecipeName(recipeForm.getRecipeName());
				recipe.setUser(user);
				
				recipeService.insertRecipe(recipe);
			
			} else {
				bindingResult.addError( new ObjectError("recipeForm", new String[] {"error.recipe.user.notfound"}, null, "User not Found!") );
			}
			
		}
		
		if(bindingResult.hasErrors()) {
			forward = "recipe/addRecipe";
		} else {
			forward = "redirect:/recipe/" + recipeId;
		}
		
		return forward;
	}
	
	/**
	 * @param recipeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recipe/{recipeId}/update", method = RequestMethod.GET) 
	public String updateRecipe(@PathVariable String recipeId, Model model) {
		
		
		
		return "thing";
	}
	
	/**
	 * @param recipeForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recipe/{recipeId}/update", method = RequestMethod.POST) 
	public String updateRecipe(@ModelAttribute RecipeForm recipeForm, Model model) {
		
		
		
		return "thing";
	}
	
	/**
	 * @param recipeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recipe/{recipeId}/delete")
	public String deleteRecipe(@PathVariable String recipeId, Model model) {
		
		return "thing"; 
		
	}

}
