package controllers.family;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import domain.Player;
import domain.Squadra;
import services.CoachService;
import services.FamilyService;
import services.PlayerService;
import services.SquadraService;
@Controller
@RequestMapping("/squadra/family")
public class SquadraFamilyController extends ErrorController {
	
	// Services

			@Autowired
			private SquadraService squadraService;

			@Autowired
			private FamilyService familyService;
			
			@Autowired
			private PlayerService playerService;
			
			@Autowired
			private CoachService coachService;
			// Constructors -----------------------------------------------------------

			public SquadraFamilyController() {
				super();
			}
			// Lista de jugadores del equipo de su jugador
			@RequestMapping(value = "/listPlayersSquadra", method = RequestMethod.GET)
			public ModelAndView listPlayersSquadra(@RequestParam int squadraId) {
				ModelAndView result;
				Collection<Player> players;

				players = playerService.findPlayersSquadra(squadraId);

				result = new ModelAndView("player/list");
				result.addObject("players", players);
				result.addObject("mysquadra", true);
				result.addObject("requestURI", "squadra/family/listPlayersSquadra.do");

				return result;
			}
			
			// Ver el entrenador del equipo de su hijo
					@RequestMapping(value = "/viewCoachSquadra", method = RequestMethod.GET)
					public ModelAndView viewCoachSquadra(@RequestParam int squadraId) {
						ModelAndView result;
						Coach coach;
						coach = coachService.findCoachSquadra(squadraId);
						
						result = new ModelAndView("coach/edit");
						result.addObject("coach", coach);
						result.addObject("mysquadra", true);
						result.addObject("requestURI", "squadra/family/viewCoachSquadra.do");

						return result;
					}
					
			

			// Ver el equipo de su hijo
			
					@RequestMapping(value = "/displaySquadraP", method = RequestMethod.GET)
					public ModelAndView displaySquadraP(){
						ModelAndView result;
						Squadra squadra;

						squadra = squadraService.getMySquadraPlayerP();
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
