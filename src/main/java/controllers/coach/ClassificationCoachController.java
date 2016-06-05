package controllers.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controllers.ErrorController;
import services.ClassificationService;

@Controller
@RequestMapping("/classification/coach")
public class ClassificationCoachController extends ErrorController{
	
	// Services -------------------------------------------------------------------
	
	@Autowired
	private ClassificationService classificationService;
	
	

// Constructors -----------------------------------------------------------

public ClassificationCoachController() {
	super();
}


}
