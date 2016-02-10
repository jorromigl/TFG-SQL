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
import domain.Coach;
import domain.Match;
import services.MatchService;

@Controller
@RequestMapping("/match")
public class MatchController extends ErrorController {
		// Services -------------------------------------------------------------------
		
			@Autowired
			private MatchService matchService;
				
		
		// Constructors -----------------------------------------------------------

		public MatchController() {
			super();
		}

		// List all
		@RequestMapping(value = "/listAll", method = RequestMethod.GET)
		public ModelAndView listAll() {
			ModelAndView result;
			Collection<Match> matches;
			
			matches = matchService.findAll();
									
			result = new ModelAndView("match/list");
			result.addObject("matches", matches);
			result.addObject("isAll", true);
			result.addObject("isFuture", false);
			result.addObject("requestURI", "match/listAll.do");

			return result;
		}
		
		// List FUTURE
		@RequestMapping(value = "/coach/listFuture", method = RequestMethod.GET)
		public ModelAndView listFuture() {
			ModelAndView result;
			Collection<Match> matches;
			
			matches = matchService.findFuture();
									
			result = new ModelAndView("match/list");
			result.addObject("matches", matches);
			result.addObject("isAll", false);
			result.addObject("isFuture", true);
			result.addObject("requestURI", "match/listFuture.do");
			
			return result;
		}
		
		// List PAST
		@RequestMapping(value = "/coach/listPast", method = RequestMethod.GET)
		public ModelAndView listPast() {
			ModelAndView result;
			Collection<Match> matches;
			
			matches = matchService.findPast();
			
			result = new ModelAndView("match/list");			
			result.addObject("isAll", false);
			result.addObject("isFuture", false);
			result.addObject("matches", matches);
			result.addObject("requestURI", "match/listPast.do");

			return result;
		}	
			
		// Create
		@RequestMapping(value = "/coach/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Match match;
			
			match = matchService.create();
				
			result = createModelAndView(match);	
			return result;
		}
		
		// Edit
		@RequestMapping(value = "/coach/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int matchId) {
			ModelAndView result;
			Match match;
			
			match = matchService.findOne(matchId);
			
			result = createModelAndView(match);
			return result;
		}
		
		// Save
		@RequestMapping(value = "/coach/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid @ModelAttribute Match match, BindingResult bindingResult) {
			ModelAndView result;

			if (bindingResult.hasErrors()) {
				result = createModelAndView(match);
			} else {
				try {
					matchService.save(match);
					result = new ModelAndView("redirect:../../match/listAll.do");
				} catch (Throwable error) {
					result = createModelAndView(match, "match.commit.error");
				}
			}
			return result;
		}
		
		//Delete
		
		@RequestMapping(value = "/coach/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam int matchId) {
			ModelAndView result;
			Match match;
			
			match= matchService.findOne(matchId);
			
			try {
				matchService.delete(match);
				result = new ModelAndView("redirect:../coach/listFuture.do");
			} catch (Throwable error) {
				result = createModelAndView(match, "coach.commit.error");
			}
			return result;
		}
		
		
		protected ModelAndView createModelAndView(Match match) {
			ModelAndView result;

			result = createModelAndView(match, null);

			return result;
		}

		protected ModelAndView createModelAndView(Match match, String message) {
			ModelAndView result;

			result = new ModelAndView("match/edit");

			result.addObject("match", match);
			result.addObject("message", message);
		
			return result;
		}	
		
		
	}