package controllers.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;

import services.ClassificationService;

@Controller
@RequestMapping("/classification/coach")
public class ClassificationCoachController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private ClassificationService classificationService;

	// Constructors -----------------------------------------------------------

	public ClassificationCoachController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;

		result = new ModelAndView("classification/display");

		result.addObject("requestURI", "/classification/coach/display.do");

		return result;
	}

	protected ModelAndView createModelAndView() {

		ModelAndView result;

		result = createModelAndView(null);

		return result;
	}

	protected ModelAndView createModelAndView(String message) {
		ModelAndView result;

		result = new ModelAndView();

		result.addObject("message", message);

		return result;
	}

}
