package controllers.player;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Player;
import forms.PlayerRegistrationForm;
import services.PlayerService;

@Controller
@RequestMapping("/player")
public class PlayerController extends ErrorController {

	// Services -------------------------------------------------------------------
	
		@Autowired
		private PlayerService playerService;
	
	
	// Constructors -----------------------------------------------------------

	public PlayerController() {
		super();
	}

	
	// Create methods --------------------------------------------------------------
	
	
		@RequestMapping(value="/register", method = RequestMethod.GET)
		public ModelAndView create(){
			
			ModelAndView result;
			PlayerRegistrationForm playerForm = new PlayerRegistrationForm();
			result = createModelAndView(playerForm);
			
			return result;
			
		}
		
		// Save
		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid PlayerRegistrationForm playerForm, BindingResult bindingResult) {
			ModelAndView result;
			
			Player player = playerService.reconstruct(playerForm);
			
			if(playerForm.getAvailable() && playerForm.getRegistrationForm().getVerifyPassword().equals(playerForm.getRegistrationForm().getPassword())){
				if (bindingResult.hasErrors()) {
					result = createModelAndView(playerForm);

				}else{
					try{
						playerService.save(player);
						result = new ModelAndView("redirect:../principal/index.do");
					} catch (Throwable error) {
						result = createModelAndView(playerForm, "register.commit.error");
					}
				} 
			}else {
				if(playerForm.getAvailable()){
					result = createModelAndView(playerForm, "register.commit.error1");
				}else{	
					result = createModelAndView(playerForm, "register.commit.error2");

				}
			}
			return result;
		}
		
		
		
		

		// Ancillary methods ---------------------------------------------------------
		
			protected ModelAndView createModelAndView(PlayerRegistrationForm playerForm){
				
				ModelAndView result;
				
				result = createModelAndView(playerForm, null);
				
				return result;
			}
			
			protected ModelAndView createModelAndView(PlayerRegistrationForm playerForm, String message){
				
				ModelAndView result;
				
				result = new ModelAndView("register/register");
				result.addObject("playerForm", playerForm);
				result.addObject("message", message);
				result.addObject("isPlayer", true);
				result.addObject("isCoach", false);
				
				return result;
			}
}