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
import domain.Comment;
import domain.Match;
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

		return result;
	}

	// CREATE
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
	
			Squadra s = squadraService.create();

			result = createModelAndView1(s);
			result.addObject("squadra", s);
			result.addObject("details", false);

			return result;
		}

		// SAVE
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid @ModelAttribute Squadra s, BindingResult bindingResult) {
			ModelAndView result;

			if (bindingResult.hasErrors()) {
				result = createModelAndView1(s);
			} else {
				try {
					squadraService.save(s);
					result = new ModelAndView("redirect:/match/listAll.do");
				} catch (Throwable error) {
					result = createModelAndView1(s, "squadra.commit.error");
				}
			}
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


		
		protected ModelAndView createModelAndView1(Squadra squadra) {
			ModelAndView result;

			result = createModelAndView1(squadra, null);

			return result;
		}

		protected ModelAndView createModelAndView1(Squadra squadra, String message) {
			ModelAndView result;
			result = new ModelAndView("squadra/edit");

			result.addObject("squadra", squadra);
			result.addObject("message", message);
			result.addObject("details", false);
			
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
