package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Folder;
import domain.Message;
import domain.Player;
import domain.User;
import repositories.MessageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class MessageService {

	// Managed repository ---------------------------------------------
	@Autowired
	private MessageRepository messageRepository;

	// Supporting services --------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private FolderService folderService;

	// Constructor -----------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD Methods --------------------------------------------
	public Message create() {

		Message message = new Message();

		User sender = userService.findByPrincipal();

		message.setSender(sender);

		message.setMoment(new Date());

		Folder folder = folderService.findOutboxUser(sender);

		message.setFolder(folder);

		return message;
	}

	public void save(Message message) {
		checkPrincipal(message);

		Assert.isTrue(!message.getRecipient().equals(message.getSender()));

		Date d = new Date(System.currentTimeMillis() - 1000);

		message.setMoment(d);

		messageRepository.save(message);

		if (message.getId() == 0) {

			Message messageToSend = message;
			User recipient = message.getRecipient();
			Folder folder = folderService.findInboxUser(recipient);
			messageToSend.setFolder(folder);
			messageRepository.save(messageToSend);

		}

	}

	public void delete(Message message) {
		checkPrincipal(message);
		messageRepository.delete(message);
	}

	// Other business Methods -----------------------------------------

	public Message findOne(int messageId) {
		Message message = messageRepository.findOne(messageId);
		checkPrincipal(message);

		return message;
	}

	public Collection<Message> getByFolder(int folderId) {
		Folder result = folderService.findOne(folderId);
		folderService.checkPrincipal(result);

		return result.getMessages();
	}

	public Collection<Message> findListMessagesByUser() {
		User user = userService.findByPrincipal();

		Collection<Message> res = messageRepository.findMessagesByUserId(user.getId());
		Assert.notNull(res);

		return res;
	}

	public void changeFolder(Message message) {
		checkPrincipal(message);

		messageRepository.save(message);
	}

	public void checkPrincipal(Message message) {
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(message.getSender().getUserAccount().equals(userAccount)
				|| message.getRecipient().getUserAccount().equals(userAccount)
				|| userAccount.getAuthorities().contains(au));

	}

public void createAndSave(Player p, Boolean b){
		Message m = create();
		m.setSubject("Convocatoria Partido");
		if(b==true){
			m.setBody("Le doy mi enhorabuena " + p.getFullName()+ " ,ha sido usted convocado para un partido. Consulte la página oficial de TeamSchool para más información. "
					+ "Un saludo, gracias");
		}else{
			m.setBody(p.getFullName()+ ", siento comunicarle que ha sido eliminado de una convocatoria para un partido. Consulte la página oficial de TeamSchool para más información. "
					+ "Un saludo, gracias");
		}		
		m.setRecipient(p);
		save(m);
		
	}

}
