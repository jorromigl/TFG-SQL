package controllers.coach;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Player;
import domain.Squadra;
import services.PlayerService;
import services.SquadraService;

@Controller
@RequestMapping("/squadra/coach")
public class SquadraCoachController extends ErrorController {

	// Services

	@Autowired
	private SquadraService squadraService;

	@Autowired
	private PlayerService playerService;
	// Constructors -----------------------------------------------------------

	public SquadraCoachController() {
		super();
	}

	

	// Ver mi equipo
	@RequestMapping(value = "/mysquadra", method = RequestMethod.GET)
	public ModelAndView mysquadra() {
		ModelAndView result;
		Collection<Squadra> squadras;

		squadras = squadraService.getMySquadra();

		result = new ModelAndView("squadra/list");
		result.addObject("squadras", squadras);
		result.addObject("requestURI", "squadra/coach/mysquadra.do");

		return result;
	}

	// Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Squadra squadra;

		squadra = squadraService.create();

		result = createModelAndView(squadra);
		result.addObject("details", false);
		return result;
	}

	// Details
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int squadraId) {
		ModelAndView result;
		Squadra squadra;

		squadra = squadraService.findOne(squadraId);

		result = new ModelAndView("squadra/edit");
		result.addObject("squadra", squadra);
		result.addObject("details", true);
		return result;
	}


	// Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Squadra squadra, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors()) {
			result = createModelAndView(squadra);
		} else {
			try {
				squadraService.save(squadra);
				result = new ModelAndView("redirect:../../squadra/coach/mysquadra.do");
			} catch (Throwable error) {
				result = createModelAndView(squadra, "squadra.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createModelAndView(Squadra squadra) {
		assert squadra != null;
		ModelAndView result;

		result = createModelAndView(squadra, null);

		return result;
	}

	protected ModelAndView createModelAndView(Squadra squadra, String message) {

		Assert.notNull(squadra);
		ModelAndView result;
		Collection<Player> players = playerService.findInItsCategoryAndNotHaveSquadra(squadra);
		result = new ModelAndView("squadra/edit");
		result.addObject("players", players);
		result.addObject("squadra", squadra);
		result.addObject("message", message);

		return result;
	}

}
