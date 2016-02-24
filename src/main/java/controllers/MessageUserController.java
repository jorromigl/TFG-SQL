package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import domain.Folder;
import domain.Message;
import domain.User;
import services.FolderService;
import services.MessageService;
import services.UserService;

@Controller
@RequestMapping("/message/user")
public class MessageUserController extends ErrorController {

	// Services

	@Autowired
	private MessageService messageService;

	// Constructors -----------------------------------------------------------

	public MessageUserController() {
		super();
	}

	@Autowired
	private UserService userService;

	@Autowired
	private FolderService folderService;

	// Listing methods
	// -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId) {

		ModelAndView result;

		Folder folder = folderService.findOne(folderId);

		result = new ModelAndView("message/user/list");
		result.addObject("folder", folder);

		return result;

	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)

	public ModelAndView display(@RequestParam int messageId) {

		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);
		result = new ModelAndView("message/user/display");
		result.addObject("messageDisplay", message);

		return result;
	}

	// Creation and edition methods ------------------------------------

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Message message;

		message = messageService.create();

		result = createNewModelAndView(message);

		return result;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "save")
	public ModelAndView send(@ModelAttribute("m") @Valid Message m, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(m);
		} else {
			try {
				messageService.save(m);
				result = new ModelAndView("redirect:../../folder/user/list.do");
			} catch (Throwable oops) {

				result = createNewModelAndView(m, "message.commit.error");

			}
		}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageId) {

		ModelAndView result;

		Message message;
		message = messageService.findOne(messageId);
		Assert.notNull(message);

		messageService.delete(message);
		result = new ModelAndView("redirect:../../folder/user/list.do");

		return result;

	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam int messageId) {

		ModelAndView result;

		Message message, aux;
		aux = messageService.findOne(messageId);
		message = messageService.create();
		Assert.notNull(aux);

		message.setRecipient(aux.getSender());
		message.setSubject("Reply to:\"" + aux.getSubject() + "\"");
		message.setBody("\n-----------------\nSender: " + aux.getSender().getName() + "\n Recipient: "
				+ aux.getRecipient().getName() + "\n Moment: " + aux.getMoment() + "\n Subject: " + aux.getSubject()
				+ "\n Body: " + aux.getBody() + "\"\"");

		result = createReplyModelAndView(message);

		return result;

	}

	protected ModelAndView createNewModelAndView(Message m) {
		ModelAndView result;
		result = createNewModelAndView(m, null);
		return result;
	}

	protected ModelAndView createNewModelAndView(Message m, String message) {
		ModelAndView result;

		result = new ModelAndView("message/user/send");

		User user = userService.findByPrincipal();

		Collection<User> users = userService.findAll();
		users.remove(user);

		result.addObject("users", users);
		result.addObject("message", message);
		result.addObject("m", m);
		return result;
	}

	protected ModelAndView createReplyModelAndView(Message m) {
		ModelAndView result;
		result = createReplyModelAndView(m, null);
		return result;
	}

	protected ModelAndView createReplyModelAndView(Message m, String message) {
		ModelAndView result;
		result = new ModelAndView("message/user/reply");

		result.addObject("message", message);
		result.addObject("m", m);
		return result;
	}

}