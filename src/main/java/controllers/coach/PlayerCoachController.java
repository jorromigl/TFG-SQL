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
import domain.Recruitment;
import domain.Squadra;
import forms.PlayerForm;
import services.CoachService;
import services.PlayerService;
import services.RecruitmentService;
import services.SquadraService;

@Controller
@RequestMapping("/player/c")
public class PlayerCoachController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private PlayerService playerService;

	@Autowired
	private SquadraService squadraService;

	@Autowired
	private CoachService coachService;

	@Autowired
	private RecruitmentService recruitmentService;

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
		result.addObject("requestURI", "player/c/listPlayerSameCategoryCoach.do");

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
		result.addObject("squadraId", squadraId);
		result.addObject("players", players);
		result.addObject("mysquadra", false);
		result.addObject("requestURI", "player/c/findInItsCategoryAndNotHaveSquadra.do");

		return result;
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
		result.addObject("requestURI", "player/c/listPlayersSquadra.do");

		return result;
	}

	// Lista de jugadores de su equipo que no están en esa convocatoria
	// @RequestMapping(value = "/listPlayersNotRecruitment", method =
	// RequestMethod.GET)
	// public ModelAndView listPlayersNotRecruitment(@RequestParam int
	// squadraId, int recruitmentId) {
	// ModelAndView result;
	// Collection<Player> players;
	//
	// players = playerService.findPlayersNotRecruitment(recruitmentId,
	// squadraId);
	//
	// result = new ModelAndView("player/list");
	// result.addObject("players", players);
	// result.addObject("mysquadra", true);
	// result.addObject("requestURI", "player/c/listPlayersNotRecruitment.do");
	//
	// return result;
	// }

	// Lista de jugadores de su equipo
	@RequestMapping(value = "/listPlayersSquadraToRecruit", method = RequestMethod.GET)
	public ModelAndView listPlayersSquadraToRecruit(@RequestParam int squadraId, int recruitmentId) {
		ModelAndView result;
		Collection<Player> players;

		players = playerService.findPlayersSquadraToRecruit(squadraId, recruitmentId);

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("mysquadra", true);
		result.addObject("recruitmentId", recruitmentId);
		result.addObject("requestURI", "player/c/listPlayersSquadraToRecruit.do");

		return result;
	}

	// Add players Recruitment

	@RequestMapping(value = "/AddPlayersRecruitment", method = RequestMethod.GET)
	public ModelAndView AddPlayersRecruitment(@RequestParam int recruitmentId, int playerId) {

		recruitmentService.addPlayerRecruitment(recruitmentId, playerId);
		ModelAndView result;

		result = new ModelAndView("redirect:../../recruitment/coach/listFuture.do");

		return result;
	}

	// Delete

		@RequestMapping(value = "/deletePlayerRecruitment", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam int playerId, int recruitmentId) {
			ModelAndView result;
			Player player;
			
			player = playerService.findOne(playerId);
			try {
				playerService.deleteFromRecruitment(player, recruitmentId);
				result = new ModelAndView("redirect:../../player/c/listPlayersByRecruitment.do?recruitmentId="+ recruitmentId);
			} catch (Throwable error) {
				result = createModelAndView(player, "player.commit.error");
			}
			return result;
		}
		
	// Add players Squadra

	@RequestMapping(value = "/AddPlayers", method = RequestMethod.GET)
	public ModelAndView AddPlayers(@RequestParam int squadraId, int playerId) {

		squadraService.addPlayer(squadraId, playerId);
		ModelAndView result;

		result = new ModelAndView("redirect:../../squadra/coach/mysquadra.do");

		return result;
	}

	// save de añadir players al equipo
	// @RequestMapping(value = "/edit", method = RequestMethod.POST, params =
	// "save")
	// public ModelAndView save(@Valid Player player, BindingResult binding) {
	//
	// ModelAndView result;
	// if (binding.hasErrors()) {
	// result = createModelAndView(player);
	// } else {
	// try {
	// playerService.save(player);
	// result = new
	// ModelAndView("redirect:../../player/coach/listPlayersSquadra.do");
	// } catch (Throwable oops) {
	// result = createModelAndView(player, "player.commit.error");
	// }
	// }
	//
	// return result;
	//
	// }

	// Listar los jugadores pertenecientes a un Recruitment
	@RequestMapping(value = "/listPlayersByRecruitment", method = RequestMethod.GET)
	public ModelAndView listPlayersByRecruitment(@RequestParam int recruitmentId) {
		ModelAndView result;
		Collection<Player> players;

		players = playerService.findPlayersByRecruitment(recruitmentId);

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("recruitment", true);
		result.addObject("recruitmentId", recruitmentId);
		result.addObject("requestURI", "player/c/listPlayersByRecruitment.do");

		return result;
	}
	

	// Ver el perfil de un jugador
	@RequestMapping(value = "/verPerfilJugador", method = RequestMethod.GET)
	public ModelAndView verPerfilJugador(@RequestParam int playerId) {
		ModelAndView result;
		Player p = playerService.findOne(playerId);

//		PlayerForm player = playerService.createForm(p);
		// Assert.notNull(player);
		result = createModelAndView1(p);

		result.addObject("requestURI", "player/verPerfilJugador.do?playerId=" + p.getId());
		result.addObject("detailsPlayer", true);

		return result;
	}

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

	protected ModelAndView createModelAndView1(Player player) {
		ModelAndView result;

		result = createModelAndView1(player, null);

		return result;
	}

	protected ModelAndView createModelAndView1(Player player, String message) {
		ModelAndView result;

		result = new ModelAndView("player/display");
		result.addObject("player", player);

		result.addObject("message", message);

		return result;
	}

}
