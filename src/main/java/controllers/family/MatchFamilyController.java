package controllers.family;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Match;
import services.MatchService;
@Controller
@RequestMapping("/match/family")
public class MatchFamilyController extends ErrorController {
	
	// Services -------------------------------------------------------------------
	
	@Autowired
	private MatchService matchService;
		

// Constructors -----------------------------------------------------------

public MatchFamilyController() {
	super();
}


// List FUTURE
@RequestMapping(value = "/listFuture", method = RequestMethod.GET)
public ModelAndView listFuture() {
	ModelAndView result;
	Collection<Match> matches;
	
	matches = matchService.findFuture();
							
	result = new ModelAndView("match/list");
	result.addObject("matches", matches);
	result.addObject("isAll", false);
	result.addObject("isFuture", true);
	result.addObject("requestURI", "match/family/listFuture.do");
	
	return result;
}

// List PAST
@RequestMapping(value = "/listPast", method = RequestMethod.GET)
public ModelAndView listPast() {
	ModelAndView result;
	Collection<Match> matches;
	
	matches = matchService.findPast();
	
	result = new ModelAndView("match/list");			
	result.addObject("isAll", false);
	result.addObject("isFuture", false);
	result.addObject("matches", matches);
	result.addObject("requestURI", "match/family/listPast.do");

	return result;
}	

}
