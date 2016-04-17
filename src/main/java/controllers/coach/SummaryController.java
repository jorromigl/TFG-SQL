package controllers.coach;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Comment;
import domain.Match;
import domain.Summary;
import services.MatchService;
import services.SummaryService;

@Controller
@RequestMapping("/summary")
public class SummaryController extends ErrorController {
	// Services
	// -------------------------------------------------------------------

	@Autowired
	private SummaryService summaryService;
	
	@Autowired
	private MatchService matchService;

	// Constructors -----------------------------------------------------------

	public SummaryController() {
		super();
	}
	// VER RESUMEN
	// --------------------------------------------------------------

	@RequestMapping(value = "/coach/displayA", method = RequestMethod.GET)
	public ModelAndView displayA(int matchId) {
		ModelAndView result;
		Summary s = summaryService.findByMatchId(matchId);

		result = createModelAndView1(s);

		result.addObject("requestURI", "summary/coach/displayA.do?matchId=" + matchId);
		result.addObject("detailsSummary", true);

		return result;
	}

	// Create
	@RequestMapping(value = "/coach/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int matchId) {
		ModelAndView result;
		Summary s;
		Match m;

		m = matchService.findOne(matchId);
		s = summaryService.create(m);

		result = createModelAndView(s);
		result.addObject("match", m);

		return result;
	}

	@RequestMapping(value = "coach/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int summaryId) {
		ModelAndView result;
		Summary s;

		s = summaryService.findOne(summaryId);

		result = createModelAndView(s);
		result.addObject("match", s.getMatch());
		return result;
	}

	// SAVE
	@RequestMapping(value = "coach/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Summary s, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors()) {
			result = createModelAndView(s);
		} else {
			try {
				summaryService.save(s);
				result = new ModelAndView("redirect:/match/listAll.do");
			} catch (Throwable error) {
				result = createModelAndView(s, "comment.commit.error");
			}
		}
		return result;
	}

	// //Delete
	//
	// @RequestMapping(value = "/coach/delete", method = RequestMethod.GET)
	// public ModelAndView delete(@RequestParam int matchId) {
	// ModelAndView result;
	// Match match;
	//
	// match= matchService.findOne(matchId);
	//
	// try {
	// matchService.delete(match);
	// result = new ModelAndView("redirect:../coach/listFuture.do");
	// } catch (Throwable error) {
	// result = createModelAndView(match, "coach.commit.error");
	// }
	// return result;
	// }
	//

	protected ModelAndView createModelAndView(Summary summary) {
		ModelAndView result;

		result = createModelAndView(summary, null);

		return result;
	}

	protected ModelAndView createModelAndView(Summary summary, String message) {
		ModelAndView result;

		result = new ModelAndView("summary/edit");

		result.addObject("summary", summary);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createModelAndView1(Summary summary) {
		ModelAndView result;

		result = createModelAndView1(summary, null);

		return result;
	}

	protected ModelAndView createModelAndView1(Summary summary, String message) {
		ModelAndView result;

		result = new ModelAndView("summary/display");
		result.addObject("summary", summary);

		result.addObject("message", message);

		return result;
	}

}