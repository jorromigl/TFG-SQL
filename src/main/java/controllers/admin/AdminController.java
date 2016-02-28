package controllers.admin;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.ErrorController;


@Controller
@RequestMapping("/admin")
public class AdminController extends ErrorController {

	// Constructors -----------------------------------------------------------
	
	public AdminController() {
		super();
	}
		
}