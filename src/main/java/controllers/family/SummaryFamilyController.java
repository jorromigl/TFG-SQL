package controllers.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Summary;
import services.SummaryService;
@Controller
@RequestMapping("/summary/family")
public class SummaryFamilyController extends ErrorController {
	
	// Services

			@Autowired
			private SummaryService summaryService;

			// Constructors -----------------------------------------------------------

			public SummaryFamilyController() {
				super();
			}
			// VER RESUMEN
			// --------------------------------------------------------------

			@RequestMapping(value = "/displayA", method = RequestMethod.GET)
			public ModelAndView displayA(int matchId) {
				ModelAndView result;
				Summary s = summaryService.findByMatchId(matchId);

				result = createModelAndView(s);

				result.addObject("requestURI", "summary/family/displayA.do?matchId=" + matchId);
				result.addObject("detailsSummary", true);

				return result;
			}
			
			protected ModelAndView createModelAndView(Summary summary) {
				ModelAndView result;

				result = createModelAndView(summary, null);

				return result;
			}

			protected ModelAndView createModelAndView(Summary summary, String message) {
				ModelAndView result;

				result = new ModelAndView("summary/display");
				result.addObject("summary", summary);

				result.addObject("message", message);

				return result;
			}

}
