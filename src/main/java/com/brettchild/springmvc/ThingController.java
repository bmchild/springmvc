package com.brettchild.springmvc;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brettchild.springmvc.domain.Thing;

/**
 * Handles requests for the application thing page.
 */
@Controller
@RequestMapping("/thing")
public class ThingController {

	private static final Logger logger = LoggerFactory
			.getLogger(ThingController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Locale locale, Model model) {

		// Get all Things

		return "thing";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable String id, Locale locale, Model model) {

		// Get thing by Id

		return "thing";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String insert(@ModelAttribute("thing") Thing thing,
			BindingResult bindingResult, Model model) {
		
		// Save thing
		return "thing";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("thing") Thing thing,
			BindingResult bindingResult, Model model) {

		// Update thing
		return "thing";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String delete(@ModelAttribute("thing") Thing thing,
			BindingResult bindingResult, Model model) {

		// Delete thing
		return "thing";
	}

}
