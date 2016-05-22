package controllers.coach;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
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
import forms.CoachForm;
import forms.CoachForm2;
import services.AdminService;
import services.CoachService;

@Controller
@RequestMapping("/coach")
public class CoachController extends ErrorController {
	// Services
	// -------------------------------------------------------------------

	@Autowired
	private CoachService coachService;

	@Autowired
	private AdminService adminService;

	// Constructors -----------------------------------------------------------

	public CoachController() {
		super();
	}

	// Create methods
	// --------------------------------------------------------------

	// List all coach
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Coach> coachs;

		coachs = coachService.findAll();

		result = new ModelAndView("coach/list");
		result.addObject("coachs", coachs);
		result.addObject("requestURI", "coach/listAll.do");

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		CoachForm coachForm = new CoachForm();
		result = createModelAndView5(coachForm);

		return result;

	}

	// Save
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CoachForm coachForm, BindingResult bindingResult) {
		ModelAndView result;

		Coach coach = coachService.reconstruct(coachForm);

		if (coachForm.getAvailable() && coachForm.getVerifyPassword().equals(coachForm.getPassword())) {

			if (bindingResult.hasErrors()) {
				result = createModelAndView5(coachForm);

			} else {
				try {

					coachService.save(coach);
					result = new ModelAndView("redirect:../principal/index.do");
				} catch (Throwable error) {
					result = createModelAndView5(coachForm, "register.commit.error");
				}
			}
		} else {
			if (coachForm.getAvailable()) {
				result = createModelAndView5(coachForm, "register.commit.error1");
			} else {
				result = createModelAndView5(coachForm, "register.commit.error2");

			}
		}
		return result;
	}

	// Delete

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int coachId) {
		ModelAndView result;
		Coach c = coachService.findOne(coachId);
		try {

			coachService.deleteFolder(coachId);
			coachService.deleteUser(coachId);
			result = new ModelAndView("redirect:../coach/listAll.do");
		} catch (Throwable error) {
			result = createModelAndView(c, "register.commit.error1");
		}
		return result;
	}

	// Subir foto
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	// recuperar la foto de la BD

	@RequestMapping(value = "/showImage")
	public ModelAndView showImage(HttpServletResponse response, @RequestParam int coachId) throws Exception {
		Coach coach = coachService.findOneToEdit(coachId);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		response.setContentLength(coach.getFile().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + coach.getName() + "\"");

		FileCopyUtils.copy(coach.getFile(), response.getOutputStream());

		return null;

	}

	// VER PERFIL --------------------------------------------------------------
	// Ver el perfil de un coach
	@RequestMapping(value = "/verPerfilCoach", method = RequestMethod.GET)
	public ModelAndView verPerfilCoach(@RequestParam int coachId) {
		ModelAndView result;
		Coach p = coachService.findOne(coachId);
		result = createModelAndView4(p);

		result.addObject("requestURI", "player/verPerfilCoach.do?coachId=" + p.getId());
		result.addObject("viewProfileOther", true);
		result.addObject("detailsPlayer", true);

		return result;
	}

	@RequestMapping(value = "/displayA", method = RequestMethod.GET)
	public ModelAndView displayA() {
		ModelAndView result;
		Coach c = coachService.findByPrincipal();

		// CoachForm coach = coachService.createForm(c);
		result = createModelAndView2(c);

		result.addObject("requestURI", "coach/displayA.do?coachId=" + c.getId());
		result.addObject("detailsCoach", true);

		return result;
	}

	// EDITAR PERFIL
	// --------------------------------------------------------------
	@RequestMapping(value = "/displayB", method = RequestMethod.GET)
	public ModelAndView displayB() {
		ModelAndView result;
		Coach c = coachService.findByPrincipal();

		CoachForm coachForm = coachService.createForm(c);
		// Assert.notNull(player);
		result = createModelAndView1(coachForm);

		result.addObject("requestURI", "coach/displayB.do?coachId=" + c.getId());
		result.addObject("detailsCoach", false);
		result.addObject("editPhoto", false);
		return result;
	}

	@RequestMapping(value = "/displayB", method = RequestMethod.POST, params = "save1")
	public ModelAndView save1(@Valid CoachForm coach, BindingResult binding) {
		ModelAndView result;
		Coach c;
		if (binding.hasErrors()) {
			result = createModelAndView1(coach);
			result.addObject("detailsCoach", false);
			result.addObject("editPhoto", false);
		} else {
			try {
				c = coachService.reconstructor2(coach);
				coachService.save2(c);
				result = new ModelAndView("redirect:../principal/index.do");

			} catch (Throwable error) {
				result = createModelAndView1(coach, "coach.commit.error");
				result.addObject("detailsCoach", false);
				result.addObject("editPhoto", false);
			}
		}

		return result;
	}

	// EDITAR FOTO PERFIL
	// --------------------------------------------------------------
	@RequestMapping(value = "/displayC", method = RequestMethod.GET)
	public ModelAndView displayC() {
		ModelAndView result;
		Coach c = coachService.findByPrincipal();

		CoachForm2 coach = coachService.createForm2(c);
		result = createModelAndView3(coach);

		result.addObject("requestURI", "coach/displayC.do?coachId=" + c.getId());
		result.addObject("detailsCoach", false);
		result.addObject("editPhoto", true);

		return result;
	}

	@RequestMapping(value = "/displayC", method = RequestMethod.POST, params = "save2")
	public ModelAndView save2(@Valid CoachForm2 coachForm2, BindingResult binding) {
		ModelAndView result;
		Coach c;
		if (binding.hasErrors()) {
			result = createModelAndView3(coachForm2);
			result.addObject("editPhoto", true);
		} else {
			try {
				c = coachService.reconstructor3(coachForm2);
				coachService.save3(c);
				result = new ModelAndView("redirect:../principal/index.do");

			} catch (Throwable error) {
				result = createModelAndView3(coachForm2, "coach.commit.error");
				result.addObject("detailsCoach", false);
				result.addObject("editPhoto", true);
			}
		}

		return result;
	}

	// Ancillary methods
	// ---------------------------------------------------------

	protected ModelAndView createModelAndView5(CoachForm coachForm) {

		ModelAndView result;

		result = createModelAndView5(coachForm, null);

		return result;
	}

	protected ModelAndView createModelAndView5(CoachForm coachForm, String message) {

		ModelAndView result;

		result = new ModelAndView("register/register");
		result.addObject("coachForm", coachForm);
		result.addObject("message", message);
		result.addObject("isPlayer", false);
		result.addObject("isCoach", true);

		return result;
	}

	protected ModelAndView createModelAndView(Coach coach) {
		ModelAndView result;

		result = createModelAndView(coach, null);

		return result;
	}

	protected ModelAndView createModelAndView(Coach coach, String message) {
		ModelAndView result;

		result = new ModelAndView("coach/delete");
		result.addObject("coach", coach);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createModelAndView1(CoachForm coach) {
		ModelAndView result;

		result = createModelAndView1(coach, null);

		return result;
	}

	protected ModelAndView createModelAndView1(CoachForm coach, String message) {
		ModelAndView result;

		result = new ModelAndView("coach/display");
		result.addObject("coach", coach);

		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createModelAndView3(CoachForm2 coachForm2) {
		ModelAndView result;

		result = createModelAndView3(coachForm2, null);

		return result;
	}

	protected ModelAndView createModelAndView3(CoachForm2 coachForm2, String message) {
		ModelAndView result;

		result = new ModelAndView("coach/display");
		result.addObject("coach", coachForm2);
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

	protected ModelAndView createModelAndView4(Coach coach) {
		ModelAndView result;

		result = createModelAndView4(coach, null);

		return result;
	}

	protected ModelAndView createModelAndView4(Coach coach, String message) {
		ModelAndView result;

		result = new ModelAndView("coach/display");
		result.addObject("coach", coach);

		result.addObject("message", message);

		return result;
	}

}