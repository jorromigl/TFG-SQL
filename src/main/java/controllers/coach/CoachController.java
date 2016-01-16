package controllers.coach;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import forms.CoachRegistrationForm;
import services.CoachService;

@Controller
@RequestMapping("/coach")
public class CoachController extends ErrorController {

	// Services -------------------------------------------------------------------
	
		@Autowired
		private CoachService coachService;
	
	
	// Constructors -----------------------------------------------------------

	public CoachController() {
		super();
	}

	
	// Create methods --------------------------------------------------------------
	
	
		@RequestMapping(value="/register", method = RequestMethod.GET)
		public ModelAndView create(){
			
			ModelAndView result;
			CoachRegistrationForm form = new CoachRegistrationForm();
			result = createEditModelAndView(form);
			
			return result;
			
		}
		
		@RequestMapping(value="/register", method=RequestMethod.POST, params="register")
		public ModelAndView save(@ModelAttribute("form") @Valid CoachRegistrationForm form, BindingResult binding){
			
			ModelAndView result;
			Coach coach;
			
			if(binding.hasErrors()){
				
					result = createEditModelAndView(form);
			}else{
				try{
					coach = coachService.reconstruct(form);
					coachService.save(coach);
					result = new ModelAndView("redirect:/welcome/index.do");
					
				}catch(DataIntegrityViolationException exc){
					result = createEditModelAndView(form, "register.duplicated.user");
				}catch(Throwable error){
					result = createEditModelAndView(form, "register.commit.error");
				}
			}
			
			return result;
		}
		
		
		
		
		

		// Ancillary methods ---------------------------------------------------------
		
			protected ModelAndView createEditModelAndView(CoachRegistrationForm registrationForm){
				
				ModelAndView result;
				
				result = createEditModelAndView(registrationForm, null);
				
				return result;
			}
			
			protected ModelAndView createEditModelAndView(CoachRegistrationForm registrationForm, String message){
				
				ModelAndView result;
				
				result = new ModelAndView("coach/register");
				result.addObject("form", registrationForm);
				result.addObject("message", message);
				result.addObject("updateProfile", false);
				result.addObject("actionURI", "coach/register.do");

				return result;
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("coach/action-1");

		return result;
	}
	
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("coach/action-2");

		return result;
	}
}