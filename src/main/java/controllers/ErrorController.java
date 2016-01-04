package controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	@ExceptionHandler(Throwable.class)//Throwable is the superclass of all errors and exceptions in the Java language
	public ModelAndView error(Throwable error) {
		ModelAndView result;

		result = new ModelAndView("error/error");
		result.addObject("name", ClassUtils.getShortName(error.getClass()));
		result.addObject("exception", error.getMessage());
		result.addObject("stackTrace", ExceptionUtils.getStackTrace(error));

		return result;
	}	

}
