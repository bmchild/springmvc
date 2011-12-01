package com.brettchild.springmvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brettchild.springmvc.domain.User;
import com.brettchild.springmvc.form.UserForm;
import com.brettchild.springmvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	protected static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		
		logger.debug("Entry");

		List<User> users = userService.getUsers();
		
		model.addAttribute("users", users);

		logger.debug("Exit");
		return "user/showAllUsers";
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String showUser(@PathVariable String userId, Model model) {
		
		logger.debug("Entry");

		User user = userService.getUser( Integer.valueOf(userId) );
		
		model.addAttribute("user", user);

		logger.debug("Exit");
		return "user/showUser";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addNewUser(Model model) {

		logger.debug("Entry");
		
		
		
		logger.debug("Exit");
		return "user/addNewUser";
	}
	
	 @ModelAttribute("userForm")
	 public UserForm getUserFormObject() {
	  return new UserForm();
	 }
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addNewUserSubmit( @ModelAttribute("userForm") UserForm user, Model model, BindingResult bindingResult) {

		logger.debug("Entry");
		// Get all Things

		logger.debug("Exit");
		return "thing";
	}

	
}
