package controllers.player;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Player;
import forms.PlayerForm;
//import forms.PlayerRegistrationForm;
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

	
	// REGISTRAR --------------------------------------------------------------
	
	
		@RequestMapping(value="/register", method = RequestMethod.GET)
		public ModelAndView create(){
			
			ModelAndView result;
			PlayerForm playerForm = new PlayerForm();
			result = createModelAndView(playerForm);
			
			return result;
			
		}
		
		// Save
		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid PlayerForm playerForm, BindingResult bindingResult) {
			ModelAndView result;
			
			Player player = playerService.reconstruct(playerForm);
			
			if(playerForm.getAvailable() && playerForm.getVerifyPassword().equals(playerForm.getPassword())){
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
		
//		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
//		public ModelAndView save(@Valid PlayerRegistrationForm playerForm,
//				BindingResult binding) {
//
//			ModelAndView result;
//			Player player;
//			
//
//			if (binding.hasErrors()) {
//				result = createModelAndView(playerForm);
//
//			} else {
//
//				try {
//					player = playerService.reconstruct(playerForm);
//
//					playerService.save(player);
//					result = new ModelAndView("redirect:../../security/login.do");
//
//				} catch (DataIntegrityViolationException oops) {
//					result = createModelAndView(playerForm,
//							"register.commit.error");
//
//				} catch (Throwable oops) {
//					oops.getMessage();
//
//					if (!playerForm.getRegistrationForm().getVerifyPassword().equals(playerForm.getRegistrationForm().getPassword())) {
//						result = createModelAndView(playerForm,
//								"register.commit.error");
//					} else {
//
//						if (playerForm.getAvailable()== false) {
//							result = createModelAndView(playerForm,
//									"register.commit.error1");
//						}else{
//							result = createModelAndView(playerForm,
//									"register.commit.error2");
//						} 
//					}
//
//				}
//			}
//			return result;
//		}
		
		//----------------------------------------------------------------------
		// VER PERFIL --------------------------------------------------------------
		
		@RequestMapping(value = "/displayA", method = RequestMethod.GET)
		public ModelAndView displayA(){
			ModelAndView result;
			Player p = playerService.findByPrincipal();
			
			PlayerForm player = playerService.createForm(p);
//			Assert.notNull(player);
			result = createModelAndView1(player);
			
			result.addObject("requestURI", "player/displayA.do?playerId="+p.getId());
			result.addObject("detailsPlayer",true);
			
			return result;
		}
		
		// EDITAR PERFIL --------------------------------------------------------------
		@RequestMapping(value = "/displayB", method = RequestMethod.GET)
		public ModelAndView displayB(){
			ModelAndView result;
			Player p = playerService.findByPrincipal();
			
			PlayerForm player = playerService.createForm(p);
//			Assert.notNull(player);
			result = createModelAndView1(player);
			
			result.addObject("requestURI", "player/displayB.do?playerId="+p.getId());
			result.addObject("detailsPlayer",false);
			
			return result;
		}
		@RequestMapping(value = "/displayB", method = RequestMethod.POST, params = "save1")
		public ModelAndView save1(@Valid PlayerForm player, BindingResult binding) {
			ModelAndView result;
			Player p;
			if (binding.hasErrors()) {
				result = createModelAndView1(player);
				result.addObject("detailsPlayer",false);
			} else {
				try {
					p = playerService.reconstructor2(player);
					playerService.save(p);
					result = new ModelAndView("redirect:../principal/index.do");
	
				} catch (Throwable error) {
					result = createModelAndView1(player, "player.commit.error");	
					result.addObject("detailsPlayer",false);
				}
			}

			return result;
		}
		
		// Ancillary methods ---------------------------------------------------------
		
			protected ModelAndView createModelAndView(PlayerForm playerForm){
				
				ModelAndView result;
				
				result = createModelAndView(playerForm, null);
				
				return result;
			}
			
			protected ModelAndView createModelAndView(PlayerForm playerForm, String message){
				
				ModelAndView result;
				Player player = playerService.create();
				
				result = new ModelAndView("register/register");
				result.addObject("playerForm", playerForm);
				result.addObject("message", message);
				result.addObject("isPlayer", true);
				result.addObject("isCoach", false);
				result.addObject("player", player);
				result.addObject("user", "playerForm");
				
				return result;
			}
			
			
			protected ModelAndView createModelAndView1(PlayerForm player) {
				ModelAndView result;

				result = createModelAndView1(player, null);

				return result;
			}

			protected ModelAndView createModelAndView1(PlayerForm player, String message) {
				ModelAndView result;

				result = new ModelAndView("player/display");
				result.addObject("player", player);
				
				result.addObject("message", message);
			
				return result;
			}
}