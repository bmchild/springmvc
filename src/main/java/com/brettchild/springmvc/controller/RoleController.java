package com.brettchild.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brettchild.springmvc.domain.Role;
import com.brettchild.springmvc.exception.MissingDataException;
import com.brettchild.springmvc.form.RoleForm;
import com.brettchild.springmvc.jsp.JspMessage;
import com.brettchild.springmvc.jsp.JspMessages;
import com.brettchild.springmvc.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/role", method = RequestMethod.GET)
	public String showRoles(Model model) {
		
		List<Role> roles = roleService.getRoles();
		
		model.addAttribute("roles", roles);
		
		return "role/showRoles";
		
	}
	
	@RequestMapping(value="/role/new", method= RequestMethod.GET)
	public String addRole(Model model) {
		return "role/addRole";
	}
	
	@ModelAttribute
	public RoleForm getRoleFormObject() {
		return new RoleForm();
	}
	
	@RequestMapping(value="/role/new", method=RequestMethod.POST)
	public String addRoleSubmit(@Valid @ModelAttribute RoleForm roleForm, BindingResult bindingresult, Model model) {
		
		String forward = null;
		
		JspMessages errors = new JspMessages();
		
		Role role = mapRoleFormToRole(roleForm);
		
		if ( !roleService.insertRole(role) ) {
			errors.add(new JspMessage("error.insert.role", "Role failed to save!"));
		}
		
		if(errors.isEmpty() && !bindingresult.hasErrors()) {
			forward = "redirect:/role";
		} else {
			model.addAttribute("errors", errors);
			forward = "role/addRole";
		}
		
		return forward;
		
	}
	
	@RequestMapping(value="/role/{roleId}/delete", method=RequestMethod.GET)
	public String deleteRole(@PathVariable String roleId, Model model) {
		
		String forward = null;
		
		JspMessages errors = new JspMessages();
		
		try {
			
			int roleIdInt = Integer.valueOf(roleId);
			
			Role role = roleService.getRole(roleIdInt);
			
			if(role == null)
				throw new MissingDataException("Role");
			
			if( !roleService.deleteRole(role) ) {
				errors.add(new JspMessage("error.delete.role",  "Role didn't delete"));
			}
			
			
		} catch (NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", roleId));
		} catch (MissingDataException e) {
			errors.add(new JspMessage("error.data.missing",  "That {0} does not exist", e.getMessage()));
		}
		
		if(errors.isEmpty()) {
			forward = "redirect:/role";
		} else {
			model.addAttribute("errors", errors);
			List<Role> roles = roleService.getRoles();
			model.addAttribute("roles", roles);
			forward = "role/showRoles";
		}
		
		return forward;
		
	}
	
	@RequestMapping(value="/role/{roleId}/update", method=RequestMethod.GET)
	public String updateRole(@PathVariable String roleId, Model model) {
		
		String forward = null;
		
		JspMessages errors = new JspMessages();
		
		try {
			
			int roleIdInt = Integer.valueOf(roleId);
			
			Role role = roleService.getRole(roleIdInt);
			
			if(role == null)
				throw new MissingDataException("Role");
			
			RoleForm roleForm = mapRoleToRoleForm(role);
			
			model.addAttribute("roleForm", roleForm);
			
			
		} catch (NumberFormatException e) {
			errors.add(new JspMessage("error.number.notvalid",  "{0} is not a valid number", roleId));
		} catch (MissingDataException e) {
			errors.add(new JspMessage("error.data.missing",  "That {0} does not exist", e.getMessage()));
		}
		
		if(errors.isEmpty()) {
			forward = "role/addRole";
		} else {
			model.addAttribute("errors", errors);
			List<Role> roles = roleService.getRoles();
			model.addAttribute("roles", roles);
			forward = "role/showRoles";
		}
		
		return forward;
	}
	
	@RequestMapping(value="/role/{roleId}/update", method=RequestMethod.POST)
	public String updateRoleSubmit(@Valid @ModelAttribute RoleForm roleForm, BindingResult bindingResult, Model model) {
		
		String forward = null;
		
		JspMessages errors = new JspMessages();
		
		Role role = mapRoleFormToRole(roleForm);
		role.setRoleName(roleForm.getRoleName());
		
		
		if ( !roleService.updateRole(role) ) {
			errors.add(new JspMessage("error.insert.role", "Role failed to save!"));
		}
		
		if(errors.isEmpty() && !bindingResult.hasErrors()) {
			forward = "redirect:/role";
		} else {
			model.addAttribute("errors", errors);
			forward = "role/addRole";
		}
		
		return forward;
		
	}

	private RoleForm mapRoleToRoleForm(Role role) {

		RoleForm roleForm = new RoleForm();
		roleForm.setRoleId(role.getRoleId());
		roleForm.setRoleName(role.getRoleName());
		
		return roleForm;
	}

	private Role mapRoleFormToRole(RoleForm roleForm) {
		
		Role role = new Role();
		role.setRoleId(roleForm.getRoleId());
		role.setRoleName(roleForm.getRoleName());
		
		return role;
	}
}
