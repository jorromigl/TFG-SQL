package controllers.player;

import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Player;
import domain.Squadra;
import services.PlayerService;
import services.SquadraService;
@Controller
@RequestMapping("/squadra/player")
public class SquadraPlayerController extends ErrorController {
	
	// Services

		@Autowired
		private SquadraService squadraService;

		@Autowired
		private PlayerService playerService;
		// Constructors -----------------------------------------------------------

		public SquadraPlayerController() {
			super();
		}

		

		// Ver mi equipo
		
				@RequestMapping(value = "/displaySquadra", method = RequestMethod.GET)
				public ModelAndView displaySquadra(){
					ModelAndView result;
					Squadra squadra;

					squadra = squadraService.getMySquadraPlayer();
					result = createModelAndView(squadra);
					result = new ModelAndView("squadra/edit");
					result.addObject("squadra", squadra);
					result.addObject("details", true);
			
				
					
					return result;
				}
				
				
		

		protected ModelAndView createModelAndView(Squadra squadra) {
			assert squadra != null;
			ModelAndView result;

			result = createModelAndView(squadra, null);

			return result;
		}

		protected ModelAndView createModelAndView(Squadra squadra, String message) {

			ModelAndView result;
			
			result = new ModelAndView("squadra/edit");
			result.addObject("squadra", squadra);
			result.addObject("message", message);

			return result;
		}

}