package controllers.coach;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Comment;
import domain.Match;
import domain.Recruitment;
import domain.Squadra;
import services.MatchService;
import services.RecruitmentService;
import services.SquadraService;

@Controller
@RequestMapping("/recruitment/coach")
public class RecruitmentCoachController extends ErrorController  {
	
	// Services -------------------------------------------------------------------
	
				@Autowired
				private RecruitmentService recruitmentService;
				
				@Autowired
				private MatchService matchService;
					
				@Autowired
				private SquadraService squadraService;
			
			// Constructors -----------------------------------------------------------

			public RecruitmentCoachController() {
				super();
			}

			// List recruitments past
			@RequestMapping(value = "/listPast", method = RequestMethod.GET)
			public ModelAndView listPast() {
				ModelAndView result;
				Collection<Recruitment> recruitments;
				
				recruitments = recruitmentService.getMyRecruitmentsFinish();
										
				result = new ModelAndView("recruitment/list");
				result.addObject("recruitments", recruitments);
				result.addObject("isFuture", false);
				result.addObject("requestURI", "recruitment/coach/listPast.do");

				return result;
			}
			
			// List recruitments future
			@RequestMapping(value = "/listFuture", method = RequestMethod.GET)
			public ModelAndView listFuture() {
				ModelAndView result;
				Collection<Recruitment> recruitments;
				
				recruitments = recruitmentService.getMyRecruitmentsNotFinish();
										
				result = new ModelAndView("recruitment/list");
				result.addObject("recruitments", recruitments);
				result.addObject("isFuture", true);
				result.addObject("requestURI", "recruitment/coach/listFuture.do");

				return result;
			}
			
			// CREATE
			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create(@RequestParam int matchId) {
				ModelAndView result;
				Recruitment r;

				r = recruitmentService.create(matchId);
				
				recruitmentService.save(r);
				result = new ModelAndView("redirect:/recruitment/coach/listFuture.do");
				
				return result;
			}
			
			// SAVE
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid @ModelAttribute Recruitment c, BindingResult bindingResult) {
				ModelAndView result;

				if (bindingResult.hasErrors()) {
					result = createModelAndView(c);
				} else {
					try {
						recruitmentService.save(c);
						result = new ModelAndView("redirect:/recruitment/coach/listFuture.do");
					} catch (Throwable oops) {
						result = createModelAndView(c, "recruitment.commit.error");
					}
				}
				return result;
			}

			// Delete

			@RequestMapping(value = "/delete", method = RequestMethod.GET)
			public ModelAndView delete(@RequestParam int recruitmentId) {
				ModelAndView result;
				Recruitment recruitment;

				recruitment = recruitmentService.findOne(recruitmentId);

				try {
					recruitmentService.delete(recruitment);
					result = new ModelAndView("redirect:../../recruitment/coach/listFuture.do");
				} catch (Throwable error) {
					result = createModelAndView(recruitment, "recruitment.commit.error");
				}
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
				
				result = new ModelAndView("recruitment/edit");
				result.addObject("recruitment", recruitment);
				result.addObject("message", message);
			
				return result;
			}	

}
