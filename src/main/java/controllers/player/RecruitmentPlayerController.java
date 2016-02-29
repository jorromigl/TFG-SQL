package controllers.player;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Recruitment;
import services.RecruitmentService;

@Controller
@RequestMapping("/recruitment/player")
public class RecruitmentPlayerController extends ErrorController {
	
	// Services -------------------------------------------------------------------
	
		@Autowired
		private RecruitmentService recruitmentService;
			

	// Constructors -----------------------------------------------------------

	public RecruitmentPlayerController() {
		super();
	}

	// List recruitments past
	@RequestMapping(value = "/listPast", method = RequestMethod.GET)
	public ModelAndView listPast() {
		ModelAndView result;
		Collection<Recruitment> recruitments;
		
		recruitments = recruitmentService.getMyRecruitmentsFinishPlayer();
								
		result = new ModelAndView("recruitment/list");
		result.addObject("recruitments", recruitments);
		result.addObject("isFuture", false);
		result.addObject("requestURI", "recruitment/player/listPast.do");

		return result;
	}

	// List recruitments future
	@RequestMapping(value = "/listFuture", method = RequestMethod.GET)
	public ModelAndView listFuture() {
		ModelAndView result;
		Collection<Recruitment> recruitments;
		
		recruitments = recruitmentService.getMyRecruitmentsNotFinishPlayer();
								
		result = new ModelAndView("recruitment/list");
		result.addObject("recruitments", recruitments);
		result.addObject("isFuture", true);
		result.addObject("requestURI", "recruitment/player/listFuture.do");

		return result;
	}



	protected ModelAndView createModelAndView(Recruitment recruitment) {
		ModelAndView result;

		result = createModelAndView(recruitment, null);

		return result;
	}

	protected ModelAndView createModelAndView(Recruitment recruitment, String message) {
		ModelAndView result;

		result = new ModelAndView();
		
		result.addObject("recruitment", recruitment);
		result.addObject("message", message);

		return result;
	}	

}
