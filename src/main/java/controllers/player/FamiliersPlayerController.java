package controllers.player;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import domain.Family;
import domain.Player;
import forms.PlayerForm;
import services.CoachService;
import services.FamilyService;
import services.PlayerService;



@Controller
@RequestMapping("/familiers/player")
public class FamiliersPlayerController extends ErrorController {
	
	// Services
		// -------------------------------------------------------------------

		@Autowired
		private PlayerService playerService;

		@Autowired
		private FamilyService familyService;
		
		// Constructors -----------------------------------------------------------

		public FamiliersPlayerController() {
			super();
		}
	
	// Ver su familia
		@RequestMapping(value = "/viewMyFamily", method = RequestMethod.GET)
		public ModelAndView viewMyFamily() {
			ModelAndView result;
			Collection<Family> families;
			families = familyService.findMyFamily();
			
			result = new ModelAndView("coach/edit");
			result.addObject("families", families);
			result.addObject("requestURI", "familiers/player/viewMyFamily.do");

			return result;
		}

		// Ancillary methods
			// ---------------------------------------------------------
		
			protected ModelAndView createModelAndView(PlayerForm playerForm) {
		
				ModelAndView result;
		
				result = createModelAndView(playerForm, null);
		
				return result;
			}
		
			protected ModelAndView createModelAndView(PlayerForm playerForm, String message) {
		
				ModelAndView result;
				Player player = playerService.create();
		
				result = new ModelAndView("register/register");
				result.addObject("playerForm", playerForm);
				result.addObject("message", message);
				result.addObject("isPlayer", true);
				result.addObject("isCoach", false);
				result.addObject("player", player);
				result.addObject("user", "playerForm");
		
				return result;
			}
		
	
		

}
