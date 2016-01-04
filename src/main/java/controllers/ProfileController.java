package controllers;

import org.aspectj.bridge.AbortException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController extends ErrorController {
	
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;
				
		result = new ModelAndView("profile/action-1");
		
		return result;
	}
	
	// Action-2 ---------------------------------------------------------------		
	
	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;
				
		result = new ModelAndView("profile/action-2");
		
		return result;
	}
	
	// Action-2 ---------------------------------------------------------------		
	
	@RequestMapping("/action-3")
	public ModelAndView action3() {
		throw new AbortException("ERROR ACTION 3");
	}
	
}