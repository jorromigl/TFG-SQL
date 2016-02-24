package controllers.coach;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;
import domain.Coach;
import domain.Folder;
import services.CoachService;
import services.FolderService;

@Controller
@RequestMapping("/folder/coach")
public class FolderCoachController extends ErrorController {

	// Services

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CoachService coachService;

	// Constructors -----------------------------------------------------------

	public FolderCoachController() {
		super();
	}
	
		
	
	// Listing methods -----------------------------------------------------------
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Folder> folders;
		Coach coach = coachService.findByPrincipal();
		
		folders = folderService.findFoldersByUser(coach);
					
		result = new ModelAndView("folder/list");
		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/coach/listall.do");
		
		return result;
    	
		
	}
	

	// Ancillary methods ---------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Folder folder, String selectView){
		
		ModelAndView result;
		
		result = createEditModelAndView(folder, selectView, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Folder folder, String selectView, String message){
		
		ModelAndView result;
		
		Coach coach = coachService.findByPrincipal();
		Collection<Folder> folders = folderService.findFoldersByUser(coach);
		folders.remove(folder);
					
		result = new ModelAndView("folder/coach/" + selectView);
		result.addObject("folder", folder);
		result.addObject("folders", folders);
		result.addObject("message", message);

		return result;
	}

}

