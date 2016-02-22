package controllers.coach;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import domain.Player;
import domain.Squadra;
import forms.PlayerForm;
import services.CoachService;
import services.PlayerService;
import services.SquadraService;

@Controller
@RequestMapping("/player/coach")
public class PlayerCoachController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private PlayerService playerService;

	@Autowired
	private SquadraService squadraService;

	@Autowired
	private CoachService coachService;

	// Constructors -----------------------------------------------------------

	public PlayerCoachController() {
		super();
	}

	// List All players same category that login Coach (if are or aren't in his
	// squadra)
	@RequestMapping(value = "/listPlayerSameCategoryCoach", method = RequestMethod.GET)
	public ModelAndView listInItsCategory() {
		ModelAndView result;
		Collection<Player> players;

		players = playerService.findPlayerSameCategoryCoach();

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("mysquadra", true);
		result.addObject("requestURI", "player/coach/listPlayerSameCategoryCoach.do");

		return result;
	}

	// Listar solo los jugadores de su categoria que no tienen equipo asignado
	@RequestMapping(value = "/findInItsCategoryAndNotHaveSquadra", method = RequestMethod.GET)
	public ModelAndView findInItsCategoryAndNotHaveSquadra(@RequestParam int squadraId) {
		ModelAndView result;
		Collection<Player> players;
		Squadra s;
		s = squadraService.findOne(squadraId);
		players = playerService.findInItsCategoryAndNotHaveSquadra(s);

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("mysquadra", false);
		result.addObject("requestURI", "player/coach/findInItsCategoryAndNotHaveSquadra.do");

		return result;
	}

	// Lista de jugadores de su equipo
	@RequestMapping(value = "/listPlayersSquadra", method = RequestMethod.GET)
	public ModelAndView listPlayersSquadra(@RequestParam int squadraId) {
		ModelAndView result;
		Collection<Player> players;
		Squadra s;

		s = squadraService.findOne(squadraId);
		players = playerService.findPlayersSquadra(s);

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("mysquadra", true);
		result.addObject("requestURI", "player/coach/listPlayersSquadra.do");

		return result;
	}

	// Add players Squadra

	@RequestMapping(value = "/AddPlayers", method = RequestMethod.GET)
	public ModelAndView AddPlayers(@RequestParam int squadraId, int playerId) {

		Squadra squadra = squadraService.findOne(squadraId);
		Player player = playerService.findOne(playerId);
		player.setSquadra(squadra);
		ModelAndView result;

		result = createModelAndView(player, null);

		result.addObject("deleteUnits", true);
		result.addObject("player", player);
		result.addObject("squadra", squadra);
//		result.addObject("requestURI", "player/coach/edit.do?squadraId=" + squadraId);
		result.addObject("requestURI", "player/coach/AddPlayers.do");

		return result;
	}

	// save de añadir players al equipo
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid Player player, BindingResult binding) {
//
//		ModelAndView result;
//		if (binding.hasErrors()) {
//			result = createModelAndView(player);
//		} else {
//			try {
//				playerService.save(player);
//				result = new ModelAndView("redirect:../../player/coach/listPlayersSquadra.do");
//			} catch (Throwable oops) {
//				result = createModelAndView(player, "player.commit.error");
//			}
//		}
//
//		return result;
//
//	}

	protected ModelAndView createModelAndView(Player player) {
		assert player != null;
		ModelAndView result;

		result = createModelAndView(player, null);

		return result;
	}

	protected ModelAndView createModelAndView(Player player, String message) {
		ModelAndView result;

		result = new ModelAndView();

		result.addObject("player", player);
		result.addObject("message", message);

		return result;
	}

}
