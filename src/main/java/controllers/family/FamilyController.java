package controllers.family;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Family;
import domain.Player;
import forms.FamilyRegistrationForm;
import services.FamilyService;
import services.PlayerService;

@Controller
@RequestMapping("/family")
public class FamilyController extends ErrorController {

	// Services -------------------------------------------------------------------
	
		@Autowired
		private FamilyService familyService;
		
		@Autowired
		private PlayerService playerService;
	
	
	// Constructors -----------------------------------------------------------

	public FamilyController() {
		super();
	}

	
	// Create methods --------------------------------------------------------------
	
	
		@RequestMapping(value="/register", method = RequestMethod.GET)
		public ModelAndView create(){
			
			ModelAndView result;
			FamilyRegistrationForm familyForm = new FamilyRegistrationForm();
			result = createModelAndView(familyForm);
			
			return result;
			
		}
		
		// Save
		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid FamilyRegistrationForm familyForm, BindingResult bindingResult) {
			ModelAndView result;
			
			Family family = familyService.reconstruct(familyForm);
			
			if(familyForm.getAvailable() && familyForm.getRegistrationForm().getVerifyPassword().equals(familyForm.getRegistrationForm().getPassword())){
				if (bindingResult.hasErrors()) {
					result = createModelAndView(familyForm);

				}else{
					try{
						familyService.save(family);
						result = new ModelAndView("redirect:../principal/index.do");
					} catch (Throwable error) {
						result = createModelAndView(familyForm, "register.commit.error");
					}
				} 
			}else {
				if(familyForm.getAvailable()){
					result = createModelAndView(familyForm, "register.commit.error1");
				}else{
				
					result = createModelAndView(familyForm, "register.commit.error2");

				}
			}
			return result;
		}
		
		
		
		

		// Ancillary methods ---------------------------------------------------------
		
			protected ModelAndView createModelAndView(FamilyRegistrationForm familyForm){
				
				ModelAndView result;
				
				result = createModelAndView(familyForm, null);
				
				return result;
			}
			
			protected ModelAndView createModelAndView(FamilyRegistrationForm familyForm, String message){
				
				ModelAndView result;
				Collection<Player> players= playerService.findAll();
				
				result = new ModelAndView("register/register");
				result.addObject("familyForm", familyForm);
				result.addObject("message", message);
				result.addObject("players", players);
				result.addObject("isPlayer", false);
				

				return result;
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("player/action-1");

		return result;
	}
	
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("player/action-2");

		return result;
	}
}