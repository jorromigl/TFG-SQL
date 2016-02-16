package controllers.coach;

import java.util.ArrayList;
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
@RequestMapping("/squadra/coach")
public class SquadraCoachController extends ErrorController {
		
		// Services

		@Autowired
		private SquadraService squadraService;
		
		@Autowired
		private CoachService coachService;
		
		@Autowired
		private PlayerService playerService;
		// Constructors -----------------------------------------------------------

		public SquadraCoachController() {
			super();
		}
		
		// List Squadra
		
		@RequestMapping(value = "/AddPlayers", method = RequestMethod.GET)
		public ModelAndView addOrDeleteUnits(@RequestParam int playerId) {
			
			Coach coachConnect = coachService.findByPrincipal();
			
			Player player = playerService.findOneToEdit(playerId);
			Squadra squadra = new Squadra();
			Collection<Player> players = new ArrayList<Player>();
			squadra.setPlayers(players);
			
//			squadra.setPlayers(players);
			ModelAndView result;		
				

				result = createEditModelAndView(squadra);	
				
				
				
				result.addObject("squadra", squadra);
				result.addObject("player", player);
				result.addObject("requestURI", "/squadra/coach/edit.do?playerId="+playerId);
				

			return result;
		}

		protected ModelAndView createEditModelAndView(Squadra squadra) {
			assert squadra != null;
			ModelAndView result;

			result = createEditModelAndView(squadra, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Squadra squadra, String message) {

			Assert.notNull(squadra);
			ModelAndView result;
			
			Coach coachConnect = coachService.findByPrincipal();

			result = new ModelAndView("squadra/edit");
			result.addObject("squadra", squadra);
			result.addObject("message", message);

			return result;
		}

}
