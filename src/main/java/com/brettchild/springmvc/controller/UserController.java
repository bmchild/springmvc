package com.brettchild.springmvc.controller;

import java.util.Date;
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
import org.springframework.web.context.request.RequestAttributes;

import com.brettchild.springmvc.domain.User;
import com.brettchild.springmvc.form.UserForm;
import com.brettchild.springmvc.form.UserUpdateForm;
import com.brettchild.springmvc.jsp.JspMessage;
import com.brettchild.springmvc.jsp.JspMessages;
import com.brettchild.springmvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final String FWD_SHOWALLUSERS = "user/showAllUsers";
	private final String FWD_SHOWUSER = "user/showUser";
	private final String RDR_USER = "redirect:/user/";
	private final String FWD_NEWUSER = "user/addNewUser";
	private final String RDR_ALLUSERS = "redirect:/user";
	
	@Autowired
	private UserService userService;
	
	protected static Logger logger = Logger.getLogger(UserController.class);

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		
		logger.debug("Entry");

		List<User> users = userService.getUsers();
		
		model.addAttribute("users", users);

		logger.debug("Exit");
		return FWD_SHOWALLUSERS;
	}
	
	/**
	 * display a user, GET
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String update(@PathVariable String userId, Model model) {
		
		logger.debug("Entry");

		User user = userService.getUser( Integer.valueOf(userId) );
		
		UserUpdateForm userUpdateForm = createUserUpdateForm(user);
		
		model.addAttribute("user", user);
		model.addAttribute("userUpdateForm", userUpdateForm);

		logger.debug("Exit");
		return FWD_SHOWUSER;
	}
	
	/**
	 * 
	 * Update a user, POST
	 * 
	 * @param userUpdateForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public String update(@PathVariable String userId, @Valid UserUpdateForm userUpdateForm, BindingResult bindingResult, Model model) {
		
		logger.debug("Entry");

		String forward;
		
		User user = userService.getUser( Integer.valueOf(userId) );
		
		if(!bindingResult.hasErrors()) {
						
			user.setDateCreated(new Date());
			user.setUsername(userUpdateForm.getUsername());
			user.setUserId(userUpdateForm.getUserId());
			if( userUpdateForm.getPassword() != null && userUpdateForm.getPassword().length() > 0 ) {
				// TODO - Hash Password
				user.setPassword(userUpdateForm.getPassword());
			}
			
			if( !userService.updateUser(user) ) {
				bindingResult.addError( new ObjectError("userUpdateForm", new String[] {"error.update.user"}, null, "User not updated!") );
			}
			
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("user", user);
			
			forward = FWD_SHOWUSER;
		} else {
			forward = RDR_USER + userId;
		}

		logger.debug("Exit");
		return forward;

	}
	
	/**
	 * 
	 * Add a new user, GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addNewUser(Model model) {

		logger.debug("Entry");
		
		logger.debug("Exit");
		return FWD_NEWUSER;
	}
	
	 @ModelAttribute("userForm")
	 public UserForm getUserFormObject() {
	  return new UserForm();
	 }
	
	
	/**
	 * 
	 * insert new user, POST
	 * 
	 * @param userForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addNewUserSubmit(@Valid UserForm userForm, BindingResult bindingResult, Model model) {

		logger.debug("Entry");
		
		String forward;
		int userId = 0;
		
		if(!bindingResult.hasErrors()) {
						
			User user = new User();
			user.setDateCreated(new Date());
			user.setUsername(userForm.getUsername());
			// TODO - Hash Password
			user.setPassword(userForm.getPassword());
			
			userId = userService.insertUser(user);
			
			if(userId == 0) {
				bindingResult.addError( new ObjectError("userForm", new String[] {"error.insert.user"}, null, "User not saved!") );
			}
			
		}
		
		if(bindingResult.hasErrors()) {
			forward = FWD_NEWUSER;
		} else {
			forward = RDR_USER + userId;
		}

		logger.debug("Exit");
		return forward;
	}
	
	/**
	 * @param userId
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{userId}/delete", method= RequestMethod.GET)
	public String deleteUser(@PathVariable String userId, Model model) {
		
		logger.debug("Entry");
		String forward = null;
		User user = null;
		
		JspMessages errors = new JspMessages();
		
		try {
			
			int userIdInt = Integer.valueOf(userId);
			
			user = userService.getUser(userIdInt);
			
			if(user == null) {
				
				errors.add(new JspMessage("error.delete.user.notexist", "That user doesn't exist"));
				
			} else {
				
				if( !userService.deleteUser(user) ) {
					errors.add(new JspMessage("error.delete.user", "User wasn't deleted."));
				}
				
			}
		
		
		} catch(NumberFormatException e) {
			logger.error(e.getMessage() + " on userId" + userId);
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", userId));
		}
		
		if(!errors.isEmpty()) {
			
			model.addAttribute("errors", errors);
			
			if(user == null) {
				
				List<User> users = userService.getUsers();
				model.addAttribute("users", users);
				forward = FWD_SHOWALLUSERS;
				
			} else {
				
				UserUpdateForm userUpdateForm = createUserUpdateForm(user);
				model.addAttribute("userUpdateForm", userUpdateForm);
				forward = FWD_SHOWUSER;
				
			}
			
		} else {
			forward = RDR_ALLUSERS;
		}
		
		logger.debug("Exit");
		return forward;
		
	}

	private UserUpdateForm createUserUpdateForm(User user) {
		
		UserUpdateForm userUpdateForm = new UserUpdateForm();
		userUpdateForm.setPassword(user.getPassword());
		userUpdateForm.setUsername(user.getUsername());
		userUpdateForm.setUserId(user.getUserId());
		return userUpdateForm;
	}
	

	
}
