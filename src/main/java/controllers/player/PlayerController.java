package controllers.player;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import domain.Player;
import forms.CoachForm;
import forms.PlayerForm;
import forms.PlayerForm2;
import security.Authority;
import security.UserAccount;
import services.CoachService;
//import forms.PlayerRegistrationForm;
import services.PlayerService;

@Controller
@RequestMapping("/player")
public class PlayerController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private PlayerService playerService;

	@Autowired
	private CoachService coachService;
	
	// Constructors -----------------------------------------------------------

	public PlayerController() {
		super();
	}

	// List All players same category that login Player (if are or aren't in his
	// squadra)
	@RequestMapping(value = "/listPlayerSameCategory", method = RequestMethod.GET)
	public ModelAndView listInItsCategory() {
		ModelAndView result;
		Collection<Player> players;

		players = playerService.findPlayerSameCategory();

		result = new ModelAndView("player/list");
		result.addObject("players", players);
		result.addObject("requestURI", "player/listPlayerSameCategory.do");

		return result;
	}
	
	// Listar los jugadores pertenecientes a un Recruitment 
		@RequestMapping(value = "/listPlayersByRecruitment", method = RequestMethod.GET)
		public ModelAndView listPlayersByRecruitment(@RequestParam int recruitmentId) {
			ModelAndView result;
			Collection<Player> players;

			players = playerService.findPlayersByRecruitment(recruitmentId);

			result = new ModelAndView("player/list");
			result.addObject("players", players);
			result.addObject("requestURI", "player/listPlayersByRecruitment.do");

			return result;
		}

	// Ver el perfil de un jugador
	@RequestMapping(value = "/verPerfilJugador", method = RequestMethod.GET)
	public ModelAndView verPerfilJugador(@RequestParam int playerId) {
		ModelAndView result;
		Player p = playerService.findOne(playerId);

		// Assert.notNull(player);
		result = createModelAndView1(p);

		result.addObject("requestURI", "player/verPerfilJugador.do?playerId=" + p.getId());
		result.addObject("viewProfileOther", true);
		result.addObject("detailsPlayer", true);

		return result;
	}


	// Ver el perfil de un coach
	@RequestMapping(value = "/verPerfilCoach", method = RequestMethod.GET)
	public ModelAndView verPerfilCoach(@RequestParam int coachId) {
		ModelAndView result;
		Coach p = coachService.findOne(coachId);

//		CoachForm coach = coachService.createForm(p);
		// Assert.notNull(player);
		result = createModelAndView2(p);

		result.addObject("requestURI", "player/verPerfilCoach.do?coachId=" + p.getId());
		result.addObject("viewProfileOther", true);
		result.addObject("detailsPlayer", true);

		return result;
	}
	
	

	// REGISTRAR --------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		PlayerForm playerForm = new PlayerForm();
		result = createModelAndView(playerForm);

		return result;

	}

	// Save
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PlayerForm playerForm, BindingResult bindingResult) {
		ModelAndView result;

		Player player = playerService.reconstruct(playerForm);

		if (playerForm.getAvailable() && playerForm.getVerifyPassword().equals(playerForm.getPassword())) {
			if (bindingResult.hasErrors()) {
				result = createModelAndView(playerForm);

			} else {
				try {
					playerService.save(player);
					result = new ModelAndView("redirect:../principal/index.do");
				} catch (Throwable error) {
					result = createModelAndView(playerForm, "register.commit.error");
				}
			}
		} else {
			if (playerForm.getAvailable()) {
				result = createModelAndView(playerForm, "register.commit.error1");
			} else {
				result = createModelAndView(playerForm, "register.commit.error2");

			}
		}
		return result;
	}
	
	// ----------------------------------------------------------------------
	// VER SU PERFIL
	// --------------------------------------------------------------

	@RequestMapping(value = "/displayA", method = RequestMethod.GET)
	public ModelAndView displayA() {
		ModelAndView result;
		Player p = playerService.findByPrincipal();

//		PlayerForm player = playerService.createForm(p);
		// Assert.notNull(player);
		result = createModelAndView1(p);

		result.addObject("requestURI", "player/displayA.do?playerId=" + p.getId());
		result.addObject("detailsPlayer", true);
		result.addObject("viewProfileOther", false);

		return result;
	}

	// EDITAR SU PERFIL
	// --------------------------------------------------------------
	@RequestMapping(value = "/displayB", method = RequestMethod.GET)
	public ModelAndView displayB() {
		ModelAndView result;
		Player p = playerService.findByPrincipal();

		PlayerForm player = playerService.createForm(p);
		// Assert.notNull(player);
		result = createModelAndView4(player);

		result.addObject("requestURI", "player/displayB.do?playerId=" + p.getId());
		result.addObject("detailsPlayer", false);
		result.addObject("editPhoto", false);
		result.addObject("viewProfileOther", false);

		return result;
	}

	@RequestMapping(value = "/displayB", method = RequestMethod.POST, params = "save1")
	public ModelAndView save1(@Valid PlayerForm player, BindingResult binding) {
		ModelAndView result;
		Player p;
		if (binding.hasErrors()) {
			result = createModelAndView4(player);
			result.addObject("detailsPlayer", false);
			result.addObject("editPhoto", false);
		} else {
			try {
				p = playerService.reconstructor2(player);
				playerService.save(p);
				result = new ModelAndView("redirect:../principal/index.do");

			} catch (Throwable error) {
				result = createModelAndView4(player, "player.commit.error");
				result.addObject("detailsPlayer", false);
				result.addObject("editPhoto", false);
				result.addObject("viewProfileOther", false);
			}
		}

		return result;
	}
	
	//EDITAR FOTO PERFIL
	// --------------------------------------------------------------
	@RequestMapping(value = "/displayC", method = RequestMethod.GET)
	public ModelAndView displayC() {
		ModelAndView result;
		Player p = playerService.findByPrincipal();

		PlayerForm2 player = playerService.createForm2(p);
		result = createModelAndView3(player);

		result.addObject("requestURI", "player/displayC.do?playerId=" + p.getId());
		result.addObject("detailsPlayer", false);
		result.addObject("editPhoto", true);
		result.addObject("viewProfileOther", false);

		return result;
	}

	@RequestMapping(value = "/displayC", method = RequestMethod.POST, params = "save2")
	public ModelAndView save2(@Valid PlayerForm2 playerForm2, BindingResult binding) {
		ModelAndView result;
		Player p;
		if (binding.hasErrors()) {
			result = createModelAndView3(playerForm2);
			result.addObject("editPhoto", true);
		} else {
			try {
				p = playerService.reconstructor3(playerForm2);
				playerService.save2(p);
				result = new ModelAndView("redirect:../principal/index.do");

			} catch (Throwable error) {
				result = createModelAndView3(playerForm2, "player.commit.error");
				result.addObject("detailsPlayer", false);
				result.addObject("editPhoto", true);
				result.addObject("viewProfileOther", false);
			
			}
		}
		return result;
	}
		
	//Subir foto
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception{
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	//recuperar la foto de la BD
	
	@RequestMapping(value ="/showImage")
	public ModelAndView showImage(HttpServletResponse response, @RequestParam int playerId) throws Exception{
		Player player = playerService.findOneToEdit(playerId);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		response.setContentLength(player.getFile().length);
		response.setHeader("Content-Disposition", "attachment; filename=\""+ player.getName()+"\"");
		
		FileCopyUtils.copy(player.getFile(), response.getOutputStream());
		
		return null;
		
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

	protected ModelAndView createModelAndView1(Player player) {
		ModelAndView result;

		result = createModelAndView1(player, null);

		return result;
	}

	protected ModelAndView createModelAndView1(Player player, String message) {
		ModelAndView result;
		
//		Player player1 = playerService.create();

		result = new ModelAndView("player/display");
		result.addObject("player", player);
		result.addObject("message", message);

		return result;
	}
	
	protected ModelAndView createModelAndView2(Coach coach) {
		ModelAndView result;

		result = createModelAndView2(coach, null);

		return result;
	}

	protected ModelAndView createModelAndView2(Coach coach, String message) {
		ModelAndView result;

		result = new ModelAndView("coach/display");
		result.addObject("coach", coach);

		result.addObject("message", message);

		return result;
	}
	
	protected ModelAndView createModelAndView3(PlayerForm2 playerForm2) {
		ModelAndView result;

		result = createModelAndView3(playerForm2, null);

		return result;
	}

	protected ModelAndView createModelAndView3(PlayerForm2 playerForm2, String message) {
		ModelAndView result;
		

		
		result = new ModelAndView("player/display");
		result.addObject("player", playerForm2);
		result.addObject("message", message);

		return result;
	}
	
	protected ModelAndView createModelAndView4(PlayerForm playerForm) {
		ModelAndView result;

		result = createModelAndView4(playerForm, null);

		return result;
	}

	protected ModelAndView createModelAndView4(PlayerForm playerForm, String message) {
		ModelAndView result;
		
//		Player player1 = playerService.create();

		result = new ModelAndView("player/display");
		result.addObject("player", playerForm);
		result.addObject("message", message);

		return result;
	}
	
}