package controllers.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import forms.CoachForm;
import services.CoachService;
@Controller
@RequestMapping("/coach/f")
public class CoachFamilyController extends ErrorController {
	
	@Autowired
	private CoachService coachService;

	// Constructors -----------------------------------------------------------

	public CoachFamilyController() {
		super();
	}
	
	
	// Ver el perfil de un coach
	@RequestMapping(value = "/verPerfilCoach", method = RequestMethod.GET)
	public ModelAndView verPerfilCoach(@RequestParam int coachId) {
		ModelAndView result;
		Coach p = coachService.findOne(coachId);

//		CoachForm coach = coachService.createForm(p);
		// Assert.notNull(player);
		result = createModelAndView(p);

		result.addObject("requestURI", "coach/f/verPerfilCoach.do?coachId=" + p.getId());
		
		return result;
	}

	
	protected ModelAndView createModelAndView(Coach coach) {
		ModelAndView result;

		result = createModelAndView(coach, null);

		return result;
	}

	protected ModelAndView createModelAndView(Coach coach, String message) {
		ModelAndView result;

		result = new ModelAndView("coach/display");
		result.addObject("coach", coach);

		result.addObject("message", message);

		return result;
	}
	

}
