package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import domain.Folder;
import domain.User;
import services.FolderService;
import services.UserService;

@Controller
@RequestMapping("/folder/user")
public class FolderUserController extends ErrorController {

	// Services

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public FolderUserController() {
		super();
	}
	
		
	
	// Listing methods -----------------------------------------------------------
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Folder> folders;
		User user = userService.findByPrincipal();
		
		folders = folderService.findFoldersByUser(user);
					
		result = new ModelAndView("folder/user/list");
		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/user/listall.do");
		
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
		
		User user = userService.findByPrincipal();
		Collection<Folder> folders = folderService.findFoldersByUser(user);
		folders.remove(folder);
					
		result = new ModelAndView("folder/user/" + selectView);
		result.addObject("folder", folder);
		result.addObject("folders", folders);
		result.addObject("message", message);

		return result;
	}

}