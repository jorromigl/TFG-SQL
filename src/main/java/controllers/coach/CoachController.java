package controllers.coach;

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
		
		
			@RequestMapping(value="/register", method = RequestMethod.GET)
			public ModelAndView create(){
				
				ModelAndView result;
				CoachRegistrationForm coachForm = new CoachRegistrationForm();
				
				result = createModelAndView(coachForm);
				
				
				return result;
				
			}
			
			// Save
			@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid CoachRegistrationForm coachForm, BindingResult bindingResult) {
				ModelAndView result;
				
				Coach coach = coachService.reconstruct(coachForm);
				
				
				
				if(coachForm.getAvailable() && coachForm.getRegistrationForm().getVerifyPassword().equals(coachForm.getRegistrationForm().getPassword())){
					
					if (bindingResult.hasErrors()) {
						result = createModelAndView(coachForm);

					}else{
						try{
							
							coachService.save(coach);
							result = new ModelAndView("redirect:../principal/index.do");
						} catch (Throwable error) {
							result = createModelAndView(coachForm, "register.commit.error");
						}
					} 
				}else {
					if(coachForm.getAvailable()){
						result = createModelAndView(coachForm, "register.commit.error1");
					}else{	
						result = createModelAndView(coachForm, "register.commit.error2");

					}
				}
				return result;
			}
			
			//Delete
			
			@RequestMapping(value = "/delete", method = RequestMethod.GET)
			public ModelAndView delete(@RequestParam int coachId) {
				ModelAndView result;
				Coach coach;
				
				coach = coachService.findOne(coachId);
				
				try {
					coachService.delete(coach);
					result = new ModelAndView("redirect:../coach/listAll.do");
				} catch (Throwable error) {
					result = createModelAndView(coach, "coach.commit.error");
				}
				return result;
			}
			

			
			

			// Ancillary methods ---------------------------------------------------------
			
				protected ModelAndView createModelAndView(CoachRegistrationForm coachForm){
					
					ModelAndView result;
					
					result = createModelAndView(coachForm, null);
					
					return result;
				}
				
				protected ModelAndView createModelAndView(CoachRegistrationForm coachForm, String message){
					
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
	}