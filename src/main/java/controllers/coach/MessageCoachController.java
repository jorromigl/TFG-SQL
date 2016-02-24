package controllers.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controllers.ErrorController;
import services.MessageService;

@Controller
@RequestMapping("/message/coach")
public class MessageCoachController extends ErrorController{
	
	// Services

		@Autowired
		private MessageService messageService;

		// Constructors -----------------------------------------------------------

		public MessageCoachController() {
			super();
		}

}
