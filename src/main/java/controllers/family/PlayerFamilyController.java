package controllers.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Player;
import forms.PlayerForm;
import services.PlayerService;

@Controller
@RequestMapping("/player/f")
public class PlayerFamilyController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private PlayerService playerService;

	// Constructors -----------------------------------------------------------

	public PlayerFamilyController() {
		super();
	}
	
	//Ver el perfil de un jugador
	@RequestMapping(value = "/verPerfilJugador", method = RequestMethod.GET)
	public ModelAndView verPerfilJugador(@RequestParam int playerId){
		ModelAndView result;
		Player p = playerService.findOne(playerId);
		
		PlayerForm player = playerService.createForm(p);
		result = createModelAndView1(player);
		
		result.addObject("requestURI", "player/verPerfilJugador.do?playerId="+p.getId());
		result.addObject("detailsPlayer",true);
		
		return result;
	}
	
	protected ModelAndView createModelAndView1(PlayerForm player) {
		ModelAndView result;

		result = createModelAndView1(player, null);

		return result;
	}

	protected ModelAndView createModelAndView1(PlayerForm player, String message) {
		ModelAndView result;

		result = new ModelAndView("player/display");
		result.addObject("player", player);
		
		result.addObject("message", message);
	
		return result;
	}

}
