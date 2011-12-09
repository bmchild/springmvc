package com.brettchild.springmvc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brettchild.springmvc.domain.Ingredient;
import com.brettchild.springmvc.domain.Recipe;
import com.brettchild.springmvc.domain.User;
import com.brettchild.springmvc.exception.MissingDataException;
import com.brettchild.springmvc.form.IngredientForm;
import com.brettchild.springmvc.form.RecipeForm;
import com.brettchild.springmvc.jsp.JspMessage;
import com.brettchild.springmvc.jsp.JspMessages;
import com.brettchild.springmvc.service.IngredientService;
import com.brettchild.springmvc.service.RecipeService;
import com.brettchild.springmvc.service.UserService;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserService userService;
	@Autowired
	private IngredientService ingredientService;
	
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
	public String showRecipe(@PathVariable String recipeId, Model model) {

		logger.debug("Entry");
		
		JspMessages errors = new JspMessages();
		
		try {
			
			int recipeIdInt = Integer.valueOf(recipeId);
			
			Recipe recipe = recipeService.getRecipe(recipeIdInt);
			
			if(recipe == null) 
				throw new MissingDataException("recipe");
			
			model.addAttribute("recipe", recipe);
			
		} catch (NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", recipeId));
		} catch (MissingDataException e) {
			errors.add(new JspMessage("error.data.missing",  "That {0} does not exist", e.getMessage()));
		}
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors", errors);
		}
		
		

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
			errors.add(new JspMessage("error.data.missing",  "That {0} does not exist", e.getMessage()));
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
	public String addRecipe(@Valid @ModelAttribute RecipeForm recipeForm, BindingResult bindingResult, Model mode) {
		
		String forward;
		int recipeId = 0;
		
		if(!bindingResult.hasErrors()) {
			
			User user = userService.getUser(recipeForm.getUserId());
			
			if( user != null ) {
			
				Recipe recipe = mapRecipeFormToRecipe(recipeForm);
				recipe.setUser(user);
				
				recipeId = recipeService.insertRecipe(recipe);
				
				if(recipeId == 0) {
					bindingResult.addError( new ObjectError("recipeForm", new String[] {"error.recipe.notsaved"}, null, "Recipe failed to save!") );
				}
			
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
		
		JspMessages errors = new JspMessages();
		
		try {
			
			int recipeIdInt = Integer.valueOf(recipeId);
			
			Recipe recipe = recipeService.getRecipe(recipeIdInt);
			
			if(recipe == null) 
				throw new MissingDataException("Recipe");
		
			RecipeForm recipeForm = mapRecipeToRecipeForm(recipe);
			
			model.addAttribute("user", recipe.getUser());
			model.addAttribute(recipeForm);
			
		} catch(NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", recipeId));
		} catch (MissingDataException e) {
			errors.add(new JspMessage("error.data.missing",  "That {0} does not exist", e.getMessage()));
		}
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors", errors);
		}
		
		return "recipe/addRecipe";
		
	}
	
	/**
	 * @param recipeForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recipe/{recipeId}/update", method = RequestMethod.POST) 
	public String updateRecipeSubmit(@PathVariable String recipeId, @Valid @ModelAttribute RecipeForm recipeForm, BindingResult bindingResult, Model model) {
		
		JspMessages errors = new JspMessages();
		
		String forward;
		
		int recipeIdInt = 0;
		
		try {
			
			recipeIdInt = Integer.valueOf(recipeId);
			
		} catch(NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", recipeId));
		}
		
		if(errors.isEmpty() && !bindingResult.hasErrors()) {
			
			User user = userService.getUser(recipeForm.getUserId());
			
			Recipe recipe = recipeService.getRecipe(recipeIdInt);
			
			if( user != null ) {
			
				updateRecipeFromForm(recipeForm, recipe);
								
				if( !recipeService.updateRecipe(recipe) ) {
					errors.add(new JspMessage("error.recipe.notsaved", "Recipe failed to save!") );
				}
			
			} else {
				errors.add(new JspMessage("error.recipe.user.notfound", "User not Found!") );
			}
			
		}
		
		if(!errors.isEmpty() || bindingResult.hasErrors()) {
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
	@RequestMapping(value = "/recipe/{recipeId}/delete")
	public String deleteRecipe(@PathVariable String recipeId, Model model) {
		
		String forward = null;
		JspMessages errors = new JspMessages();
		int userId = 0;
		
		try {
			
			int recipeIdInt = Integer.valueOf(recipeId);
			
			Recipe recipe = recipeService.getRecipe(recipeIdInt);
			
			if(recipe == null) 
				throw new MissingDataException("Recipe");
		
			userId = recipe.getUser().getUserId();
			
			// Delete ingredients
			Collection<Ingredient> ingredients = recipe.getIngredients();
			int size = ingredients.size();
			
			Collection<Ingredient> ingredientsToDelete = new LinkedHashSet<Ingredient>(size);
			ingredientsToDelete.addAll(ingredients);
			
			for( Ingredient ingredient : ingredientsToDelete ) {
				ingredients.remove(ingredient);
				ingredientService.deleteIngredient(ingredient);
			}
			
			// Delete Recipe
			if( !recipeService.deleteRecipe(recipe) ) {
				errors.add(new JspMessage("error.recipe.delete.failure",  "Recipe did not delete"));
				model.addAttribute("recipe", recipe);
			} 
			
		} catch(NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", recipeId));
		} catch (MissingDataException e) {
			errors.add(new JspMessage("error.data.missing",  "That {0} does not exist", e.getMessage()));
		}
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors", errors);
			forward = "recipe/showRecipe";
		} else {
			forward = "redirect:/user/" + userId;
		}
		
		return forward;
		
	}

	/**
	 * @param recipeForm
	 * @param recipe
	 */
	private void updateRecipeFromForm(RecipeForm recipeForm, Recipe recipe) {
		
		recipe.setRecipeDirections(recipeForm.getRecipeDirections());
		recipe.setRecipeName(recipeForm.getRecipeName());
		
		// Merge ingredient collection
		List<IngredientForm> ingredientsIn = recipeForm.getIngredients();
		Collection<Ingredient> ingredientsOut = recipe.getIngredients();
		
		// Check for delete scenarios, exists in persistent collection but not in new
		Collection<Ingredient> ingredientsToDelete = new LinkedHashSet<Ingredient>();
		for(Ingredient ingredient : ingredientsOut) {
			
			IngredientForm ingredientForm = getIngredientFromCollection(ingredientsIn, ingredient.getIngredientId());
			
			if(ingredientForm == null) {
				ingredientsToDelete.add(ingredient);
			}
			
		}
		// Now we delete
		// If we only remove it from the collection hibernate tries to set the recipe_id = null, which it can't
		for(Ingredient ingredient : ingredientsToDelete) {
			// Remove it first or else Hibernate will flog you
			ingredientsOut.remove(ingredient);
			ingredientService.deleteIngredient(ingredient);
		}
		
		// Check for update, new scenarios
		for(IngredientForm ingredientIn : ingredientsIn) {
			
			Integer ingredientId = ingredientIn.getIngredientId();
			
			if( ingredientId != null ) {
				
				// Look it up in persisted collection
				Ingredient ingredient = getIngredientFromCollection(ingredientsOut, ingredientId);
				
				if(ingredient != null) {
					
					// Update it
					ingredient.setIngredientAmount(ingredientIn.getIngredientAmount());
					ingredient.setIngredientName(ingredientIn.getIngredientName());
					ingredient.setUnitOfMeasure(ingredientIn.getUnitOfMeasure());
					
				} 
				
			} else {
				
				// Make a new one
				Ingredient ingredient = new Ingredient();
				ingredient.setIngredientAmount(ingredientIn.getIngredientAmount());
				ingredient.setIngredientName(ingredientIn.getIngredientName());
				ingredient.setUnitOfMeasure(ingredientIn.getUnitOfMeasure());
				ingredient.setRecipe(recipe);
				
				ingredientsOut.add(ingredient);
				
			}
			
		}
		
		
	}

	/**
	 * @param <T>
	 * @param ingredients
	 * @param ingredientId
	 * @return
	 */
	private <T> T getIngredientFromCollection(
			Collection<T> ingredients, Integer ingredientId) {
		
		T ingredient = null;

		for( T ingredientLoop : ingredients ) {
			
			Integer loopIngredientId = null;
			
			if(ingredientLoop instanceof Ingredient) {
				loopIngredientId = ((Ingredient) ingredientLoop).getIngredientId();
			} else if( ingredientLoop instanceof IngredientForm) {
				loopIngredientId = ((IngredientForm) ingredientLoop).getIngredientId();
			}
			
			if(ingredientId.equals(loopIngredientId)) {
				ingredient = ingredientLoop;
				break;
			}
			
		}
		
		return ingredient;
		
	}

	/**
	 * @param recipeForm
	 * @return
	 */
	private Recipe mapRecipeFormToRecipe(RecipeForm recipeForm) {
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(recipeForm.getRecipeId());
		recipe.setRecipeDirections(recipeForm.getRecipeDirections());
		recipe.setRecipeName(recipeForm.getRecipeName());
		
		List<IngredientForm> ingredientsIn = recipeForm.getIngredients();
		Collection<Ingredient> ingredientsOut = new LinkedHashSet<Ingredient>(ingredientsIn.size());
		
		for(IngredientForm ingredientIn : ingredientsIn) {
			
			Ingredient ingredientOut = new Ingredient();
			ingredientOut.setIngredientId(ingredientIn.getIngredientId());
			ingredientOut.setIngredientAmount(ingredientIn.getIngredientAmount());
			ingredientOut.setIngredientName(ingredientIn.getIngredientName());
			ingredientOut.setUnitOfMeasure(ingredientIn.getUnitOfMeasure());
			ingredientOut.setRecipe(recipe);
			
			ingredientsOut.add(ingredientOut);
			
		}
		
		recipe.setIngredients(ingredientsOut);
		
		
		return recipe;
	}

	private RecipeForm mapRecipeToRecipeForm(Recipe recipe) {
		
		RecipeForm recipeForm = new RecipeForm();
		recipeForm.setRecipeId(recipe.getRecipeId());
		recipeForm.setRecipeDirections(recipe.getRecipeDirections());
		recipeForm.setRecipeName(recipe.getRecipeName());
		recipeForm.setUserId(recipe.getUser().getUserId());
		
		Collection<Ingredient> ingredientsIn = recipe.getIngredients();
		List<IngredientForm> ingredientsOut = new ArrayList<IngredientForm>(ingredientsIn.size());
		
		
		for(Ingredient ingredientIn : ingredientsIn) {
			
			IngredientForm ingredientOut = new IngredientForm();
			ingredientOut.setIngredientId(ingredientIn.getIngredientId());
			ingredientOut.setIngredientAmount(ingredientIn.getIngredientAmount());
			ingredientOut.setIngredientName(ingredientIn.getIngredientName());
			ingredientOut.setUnitOfMeasure(ingredientIn.getUnitOfMeasure());
			
			ingredientsOut.add(ingredientOut);
			
		}
		
		recipeForm.setIngredients(ingredientsOut);
		
		
		return recipeForm;
	}

}
