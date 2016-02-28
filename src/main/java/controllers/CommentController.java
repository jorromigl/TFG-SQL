package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Match;
import services.CommentService;
import services.MatchService;

//CLASE ENTERA A LAS 20:21 LUNES 23
@Controller
@RequestMapping("/comment/")
public class CommentController extends ErrorController {

	// Services
	@Autowired
	private CommentService commentService;

	@Autowired
	private MatchService matchService;

	public CommentController() {
		super();
	}

	// List all
	@RequestMapping(value = "/listByMatch", method = RequestMethod.GET)
	public ModelAndView listByMatch(int matchId) {
		ModelAndView result;
		Collection<Comment> comments;

		comments = commentService.findByMatch(matchId);

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/listByMatch.do");

		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int matchId) {
		ModelAndView result;
		Comment c;
		Match m;

		m = matchService.findOne(matchId);
		c = commentService.create(m);

		result = createModelAndView(c);
		result.addObject("match", m);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int commentId) {
		ModelAndView result;
		Comment c;

		c = commentService.findOne(commentId);

		result = createModelAndView(c);
		result.addObject("match", c.getMatch());
		return result;
	}

	// SAVE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Comment c, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors()) {
			result = createModelAndView(c);
		} else {
			try {
				commentService.save(c);
				result = new ModelAndView("redirect:/match/listAll.do");
			} catch (Throwable oops) {
				result = createModelAndView(c, "comment.commit.error");
			}
		}
		return result;
	}

	// Delete

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int commentId) {
		ModelAndView result;
		Comment comment;

		comment = commentService.findOne(commentId);

		try {
			commentService.delete(comment);
			result = new ModelAndView("redirect:../../match/listAll.do");
		} catch (Throwable error) {
			result = createModelAndView(comment, "comment.commit.error");
		}
		return result;
	}

	// VER RESUMEN
	// --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView displayA(int commentId) {
		ModelAndView result;
		Comment c = commentService.findOne(commentId);

		result = createModelAndView1(c);

		result.addObject("requestURI", "comment/display.do?commentId=" + commentId);

		return result;
	}

	protected ModelAndView createModelAndView(Comment c) {
		ModelAndView result;

		result = createModelAndView(c, null);

		return result;
	}

	protected ModelAndView createModelAndView(Comment c, String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", c);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createModelAndView1(Comment comment) {
		ModelAndView result;

		result = createModelAndView1(comment, null);

		return result;
	}

	protected ModelAndView createModelAndView1(Comment comment, String message) {
		ModelAndView result;

		result = new ModelAndView("comment/display");
		result.addObject("comment", comment);

		result.addObject("message", message);

		return result;
	}

}
