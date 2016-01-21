package controllers.coach;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;

@Controller
@RequestMapping("/coach")
public class CoachController extends ErrorController {

	// Services -------------------------------------------------------------------
	

	
	
	// Constructors -----------------------------------------------------------

	public CoachController() {
		super();
	}

	
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("coach/action-1");

		return result;
	}
	
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("coach/action-2");

		return result;
	}
}