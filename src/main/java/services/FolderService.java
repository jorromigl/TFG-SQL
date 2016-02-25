package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Folder;
import domain.Message;
import domain.User;
import repositories.FolderRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class FolderService {
	
	// Managed Repositories -------------------------------------------------------------
	@Autowired
	private FolderRepository folderRepository;
	
	// Supporting services ------------------------------------------------
	
	
	// Constructor ----------------------------------------------------------------------
	public FolderService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------
	public Folder create(){
		Folder folder = new Folder();

		folder.setMessages(new ArrayList<Message>());
		return folder;
	}
	
	public void save(Folder folder){

		folderRepository.save(folder);
	}

	
	// Other business methods -----------------------------------------------------------
	
	public void createDefaultFolders(User user) {
		Folder inbox = create();
		Folder outbox = create();
				
		inbox.setName("Inbox");
		inbox.setUser(user);
		
		save(inbox);
		
		outbox.setName("Outbox");
		outbox.setUser(user);

		save(outbox);		
		
		
	}
	
	
	public void checkPrincipal(Folder folder) {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(folder.getUser().getUserAccount()
				.equals(userAccount));

	}

		
	public Folder findInboxUser(User user){
		Assert.notNull(user);
		Folder folder = folderRepository.inboxFolder(user.getId());
		Assert.notNull(folder);
		return folder;
		
	}
	
	public Folder findOutboxUser(User user){
		Assert.notNull(user);
		Folder folder = folderRepository.outboxFolder(user.getId());
		Assert.notNull(folder);
		return folder;
		
	}
	
	public Collection<Folder> findFoldersByUser(User user) {
		Assert.notNull(user);
		Collection<Folder> folders = folderRepository.findFoldersByUser(user.getId());
		Assert.notNull(folders);
		return folders;
	}
	
	public Folder findOne(int folderId){
		Folder folder = folderRepository.findOne(folderId);
		checkPrincipal(folder);
		
		return folder;
	}

}


