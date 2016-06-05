package controllers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import services.ClassificationService;
@Controller
@RequestMapping("/classification/player")
public class ClassificationPlayerController extends ErrorController{
	
	// Services
		// -------------------------------------------------------------------

		@Autowired
		private ClassificationService classificationService;

		// Constructors -----------------------------------------------------------

		public ClassificationPlayerController() {
			super();
		}
		
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display() {
			ModelAndView result;

			result = new ModelAndView("classification/display");

			result.addObject("requestURI", "/classification/player/display.do");

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
