package controllers.coach;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Player;
import services.PlayerService;

@Controller
@RequestMapping("/player/coach")
public class PlayerCoachController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private PlayerService playerService;

	// Constructors -----------------------------------------------------------

	public PlayerCoachController() {
		super();
	}

	// List All players same category that login Coach
	@RequestMapping(value = "/listPlayerSameCategoryCoach", method = RequestMethod.GET)
	public ModelAndView listInItsCategory() {
		ModelAndView result;
		Collection<Player> players;

		players = playerService.findPlayerSameCategoryCoach();

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("requestURI", "player/coach/listPlayerSameCategoryCoach.do");

		return result;
	}

	

	//Squadra
//	@RequestMapping(value = "/squadra", method = RequestMethod.GET)
//	public ModelAndView squadra(@RequestParam int playerId) {
//
//		ModelAndView result;
//
//		Player p = playerService.findOneToEdit(playerId);
//		playerService.squadra(p);
//		result = new ModelAndView("redirect:squadra.do");
//		
//
//		return result;
//	}
	protected ModelAndView createModelAndView(Player player, String message) {
		ModelAndView result;

		result = new ModelAndView();

		result.addObject("player", player);
		result.addObject("message", message);

		return result;
	}

}
