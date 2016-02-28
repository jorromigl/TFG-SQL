package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/law")
public class LawController extends ErrorController {

	// Constructor
	public LawController() {
		super();
	}

	
	// Display
	@RequestMapping(value = "/law", method =RequestMethod.GET )
	public ModelAndView laws() {
		
		ModelAndView result;		
		result = new ModelAndView("law/law");
		
		return result;
	}
}