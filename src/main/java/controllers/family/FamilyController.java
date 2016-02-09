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
import forms.FamilyForm;
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
			FamilyForm familyForm = new FamilyForm();
			result = createModelAndView(familyForm);
			
			return result;
			
		}
		
		// Save
		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid FamilyForm familyForm, BindingResult bindingResult) {
			ModelAndView result;
			
			Family family = familyService.reconstruct(familyForm);
			
			if(familyForm.getAvailable() && familyForm.getVerifyPassword().equals(familyForm.getPassword())){
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
		
			protected ModelAndView createModelAndView(FamilyForm familyForm){
				
				ModelAndView result;
				
				result = createModelAndView(familyForm, null);
				
				return result;
			}
			
			protected ModelAndView createModelAndView(FamilyForm familyForm, String message){
				
				ModelAndView result;
				Collection<Player> players= playerService.findAll();
				
				result = new ModelAndView("register/register");
				result.addObject("familyForm", familyForm);
				result.addObject("message", message);
				result.addObject("players", players);
				result.addObject("isPlayer", false);
				result.addObject("isCoach", false);
				

				return result;
			}

}