package controllers.coach;

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
import forms.CoachForm;
import forms.PlayerForm;
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
				CoachForm coachForm = new CoachForm();
				
				result = createModelAndView(coachForm);
				
				
				return result;
				
			}
			
			// Save
			@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid CoachForm coachForm, BindingResult bindingResult) {
				ModelAndView result;
				
				Coach coach = coachService.reconstruct(coachForm);
				
				
				
				if(coachForm.getAvailable() && coachForm.getVerifyPassword().equals(coachForm.getPassword())){
					
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
			
			//Subir foto
			@InitBinder
			protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception{
				binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
			}
			
			//recuperar la foto de la BD
			
			@RequestMapping(value ="/showImage")
			public ModelAndView showImage(HttpServletResponse response, @RequestParam int coachId) throws Exception{
				Coach coach = coachService.findOneToEdit(coachId);
				
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				response.setContentLength(coach.getFile().length);
				response.setHeader("Content-Disposition", "attachment; filename=\""+ coach.getName()+"\"");
				
				FileCopyUtils.copy(coach.getFile(), response.getOutputStream());
				
				return null;
				
			}
			
			// VER PERFIL --------------------------------------------------------------
			
			@RequestMapping(value = "/displayA", method = RequestMethod.GET)
			public ModelAndView displayA(){
				ModelAndView result;
				Coach c = coachService.findByPrincipal();
				
				CoachForm coach = coachService.createForm(c);
//				Assert.notNull(player);
				result = createModelAndView1(coach);
				
				result.addObject("requestURI", "coach/displayA.do?coachId="+c.getId());
				result.addObject("detailsCoach",true);
				
				return result;
			}
			
			// EDITAR PERFIL --------------------------------------------------------------
			@RequestMapping(value = "/displayB", method = RequestMethod.GET)
			public ModelAndView displayB(){
				ModelAndView result;
				Coach c = coachService.findByPrincipal();
				
				CoachForm coach = coachService.createForm(c);
//				Assert.notNull(player);
				result = createModelAndView1(coach);
				
				result.addObject("requestURI", "coach/displayB.do?coachId="+c.getId());
				result.addObject("detailsCoach",false);
				
				return result;
			}
			@RequestMapping(value = "/displayB", method = RequestMethod.POST, params = "save1")
			public ModelAndView save1(@Valid CoachForm coach, BindingResult binding) {
				ModelAndView result;
				Coach c;
				if (binding.hasErrors()) {
					result = createModelAndView1(coach);
					result.addObject("detailsCoach",false);
				} else {
					try {
						c = coachService.reconstructor2(coach);
						coachService.save(c);
						result = new ModelAndView("redirect:../principal/index.do");
		
					} catch (Throwable error) {
						result = createModelAndView1(coach, "coach.commit.error");	
						result.addObject("detailsCoach",false);
					}
				}

				return result;
			}
			

			
			

			// Ancillary methods ---------------------------------------------------------
			
				protected ModelAndView createModelAndView(CoachForm coachForm){
					
					ModelAndView result;
					
					result = createModelAndView(coachForm, null);
					
					return result;
				}
				
				protected ModelAndView createModelAndView(CoachForm coachForm, String message){
					
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
	}