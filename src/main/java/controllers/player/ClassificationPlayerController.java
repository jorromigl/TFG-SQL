package controllers.player;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Classification;
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
		
		// List all
					@RequestMapping(value = "/display", method = RequestMethod.GET)
					public ModelAndView listAll() throws IOException, ParserConfigurationException, Exception {
						ModelAndView result;
						Collection<Classification> classifications;
						
						classifications = classificationService.clasification();
												
						result = new ModelAndView("classification/display");
						result.addObject("classifications", classifications);
						result.addObject("requestURI", "classification/player/display.do");

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
