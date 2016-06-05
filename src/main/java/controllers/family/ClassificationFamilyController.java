package controllers.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controllers.ErrorController;
import services.ClassificationService;

@Controller
@RequestMapping("/classification/family")
public class ClassificationFamilyController extends ErrorController {

	// Services
	// -------------------------------------------------------------------

	@Autowired
	private ClassificationService classificationService;

	// Constructors -----------------------------------------------------------

	public ClassificationFamilyController() {
		super();
	}

}
