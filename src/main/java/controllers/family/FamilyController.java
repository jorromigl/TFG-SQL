package controllers.family;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import domain.Family;
import domain.Player;
import domain.Recruitment;
import forms.CoachForm;
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
		
		// List players same category family's player
		@RequestMapping(value = "/listPlayerSameCategory", method = RequestMethod.GET)
		public ModelAndView listPlayerSameCategory() {
			ModelAndView result;
			Collection<Player> players;
			
			players = familyService.playerSameCategory();
									
			result = new ModelAndView("player/list");
			result.addObject("players", players);
			result.addObject("requestURI", "family/listPlayerSameCategory.do");

			return result;
		}
		
		//Subir foto
		@InitBinder
		protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception{
			binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
		
		//recuperar la foto de la BD
		
		@RequestMapping(value ="/showImage")
		public ModelAndView showImage(HttpServletResponse response, @RequestParam int familyId) throws Exception{
			Family family = familyService.findOneToEdit(familyId);
			
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			response.setContentLength(family.getFile().length);
			response.setHeader("Content-Disposition", "attachment; filename=\""+ family.getName()+"\"");
			
			FileCopyUtils.copy(family.getFile(), response.getOutputStream());
			
			return null;
			
		}
		
		// VER PERFIL --------------------------------------------------------------
		
					@RequestMapping(value = "/displayA", method = RequestMethod.GET)
					public ModelAndView displayA(){
						ModelAndView result;
						Family c = familyService.findByPrincipal();
						
						FamilyForm family = familyService.createForm(c);
//						Assert.notNull(player);
						result = createModelAndView1(family);
						
						result.addObject("requestURI", "family/displayA.do?familyId="+c.getId());
						result.addObject("detailsFamily",true);
						
						return result;
					}
					
					// EDITAR PERFIL --------------------------------------------------------------
					@RequestMapping(value = "/displayB", method = RequestMethod.GET)
					public ModelAndView displayB(){
						ModelAndView result;
						Family c = familyService.findByPrincipal();
						
						FamilyForm family = familyService.createForm(c);
//						Assert.notNull(player);
						result = createModelAndView1(family);
						
						result.addObject("requestURI", "family/displayB.do?familyId="+c.getId());
						result.addObject("detailsFamily",false);
						
						return result;
					}
					@RequestMapping(value = "/displayB", method = RequestMethod.POST, params = "save1")
					public ModelAndView save1(@Valid FamilyForm family, BindingResult binding) {
						ModelAndView result;
						Family c;
						if (binding.hasErrors()) {
							result = createModelAndView1(family);
							result.addObject("detailsFamily",false);
						} else {
							try {
								c = familyService.reconstructor2(family);
								familyService.save(c);
								result = new ModelAndView("redirect:../principal/index.do");
				
							} catch (Throwable error) {
								result = createModelAndView1(family, "family.commit.error");	
								result.addObject("detailsFamily",false);
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
			
			protected ModelAndView createModelAndView1(FamilyForm family) {
				ModelAndView result;

				result = createModelAndView1(family, null);

				return result;
			}

			protected ModelAndView createModelAndView1(FamilyForm family, String message) {
				ModelAndView result;

				result = new ModelAndView("family/display");
				result.addObject("family", family);
				
				result.addObject("message", message);
			
				return result;
			}

}