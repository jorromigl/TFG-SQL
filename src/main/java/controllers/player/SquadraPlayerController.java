package controllers.player;

import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import domain.Player;
import domain.Squadra;
import services.CoachService;
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
		
		@Autowired
		private CoachService coachService;
		// Constructors -----------------------------------------------------------

		public SquadraPlayerController() {
			super();
		}
		// Lista de jugadores de su equipo
		@RequestMapping(value = "/listPlayersSquadra", method = RequestMethod.GET)
		public ModelAndView listPlayersSquadra(@RequestParam int squadraId) {
			ModelAndView result;
			Collection<Player> players;

			players = playerService.findPlayersSquadra(squadraId);

			result = new ModelAndView("player/list");
			result.addObject("players", players);
			result.addObject("mysquadra", true);
			result.addObject("requestURI", "squadra/player/listPlayersSquadra.do");

			return result;
		}
		
		// Ver el entrenador de su equipo
				@RequestMapping(value = "/viewCoachSquadra", method = RequestMethod.GET)
				public ModelAndView viewCoachSquadra(@RequestParam int squadraId) {
					ModelAndView result;
					Coach coach;
					coach = coachService.findCoachSquadra(squadraId);
					
					result = new ModelAndView("coach/edit");
					result.addObject("coach", coach);
					result.addObject("mysquadra", true);
					result.addObject("requestURI", "squadra/player/viewCoachSquadra.do");

					return result;
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
