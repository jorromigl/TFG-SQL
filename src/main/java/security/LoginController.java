package security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Match;
import domain.Player;
import forms.PassForm;
import forms.PlayerForm;
import services.UserService;

@Controller
@Transactional
@RequestMapping("/security")
public class LoginController extends ErrorController {

	// Supporting services ----------------------------------------------------
	
	@Autowired
	LoginService service;
	
	@Autowired
	UserService userservice;
	
	// Constructors -----------------------------------------------------------
	
	public LoginController() {
		super();
	}
	
	// Login ------------------------------------------------------------------

	@RequestMapping("/login")
	public ModelAndView login(
			@Valid @ModelAttribute Credentials credentials,
			BindingResult bindingResult,
			@RequestParam(required = false) boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);
		
		ModelAndView result;

		result = new ModelAndView("security/login");
		result.addObject("credentials", credentials);
		result.addObject("showError", showError);

		return result;
	}
	
	// LoginFailure -----------------------------------------------------------

	@RequestMapping("/loginFailure")
	public ModelAndView failure() {
		ModelAndView result;

		result = new ModelAndView("redirect:login.do?showError=true");

		return result;
	}
	
	// Miss PASS------------------------------------------------------------------

				@RequestMapping(value ="/missPass", method = RequestMethod.GET)
				public ModelAndView missPass() {
					
					ModelAndView result;
					PassForm passForm= new PassForm();
					result = createModelAndView(passForm);
					
					return result;
				}
				
				// Send
				@RequestMapping(value = "/missPass", method = RequestMethod.POST, params = "send")
				public ModelAndView missPass(@Valid PassForm passForm, BindingResult bindingResult) {
					ModelAndView result;

					if (bindingResult.hasErrors()) {
						result = createModelAndView(passForm);
					} else {
						try {
							userservice.sendPass(passForm);
							result = new ModelAndView("redirect:/security/login.do");
						} catch (Throwable error) {
							result = createModelAndView(passForm, "login.commit.error");
						}
					}
					return result;
				}
				
				
				
				protected ModelAndView createModelAndView(PassForm passForm) {

					ModelAndView result;

					result = createModelAndView(passForm, null);

					return result;
				}
				
				protected ModelAndView createModelAndView(PassForm passForm, String message) {

					ModelAndView result;
					result = new ModelAndView("security/missPass");
					result.addObject("passForm", passForm);
					result.addObject("message", message);

					return result;
				}
	
}