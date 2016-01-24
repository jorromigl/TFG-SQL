package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Family;
import domain.Folder;
import domain.Player;
import forms.PlayerRegistrationForm;
import repositories.PlayerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PlayerService {

	// Managed repository
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private FolderService folderService;


	// Services	
	
	// Constructor
	public PlayerService() {
		super();
	}	
	
	// Simple CRUD Methods ---------------------------
	
	public Player create(){
		Player res = new Player();			
			
		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();
	
		
		authority.setAuthority(Authority.PLAYER);
		userAccount.addAuthority(authority);
		
		res.setUserAccount(userAccount);
			
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Comment> comments = new ArrayList<Comment>();
		Collection<Family> families= new ArrayList<Family>();
		
		res.setComments(comments);			
		res.setFamilies(families);			
		res.setFolders(folders);
				
		return res;
	}
		
	public Player findOneToEdit(int playerId) {
		Player result;
			
		result = playerRepository.findOne(playerId);		
		
		
		return result;
	}
	
		
	public void save(Player player){
		Assert.notNull(player);
					
		String password = player.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		player.getUserAccount().setPassword(password);
			
		Player saved = playerRepository.save(player);
			
		folderService.createDefaultFolders(saved);
			
					
	}

	// Other business methods ------------------------
		
	public Player reconstruct(PlayerRegistrationForm registrationForm){
		
		Player result = create();
			
		result.setEmail(registrationForm.getRegistrationForm().getEmail());
		result.setName(registrationForm.getRegistrationForm().getName());
		result.setPhone(registrationForm.getRegistrationForm().getPhone());
		result.setSurname(registrationForm.getRegistrationForm().getSurname());
		result.setAddress(registrationForm.getRegistrationForm().getAddress());
		
		result.setCategory(registrationForm.getCategory());
		result.setDate(registrationForm.getDate());
		result.getUserAccount().setUsername(registrationForm.getRegistrationForm().getUsername());
		result.getUserAccount().setPassword(registrationForm.getRegistrationForm().getPassword());
			
		return result;	
	}
	


	public Player findByPrincipal() {
		UserAccount playerAccount = LoginService.getPrincipal();
		return findByPlayerAccount(playerAccount);
	}

	public Player findByPlayerAccount(UserAccount userAccount) {
		Player result;
		result = playerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	

	
	public Collection<Player> findAll(){
		return playerRepository.findAll();
	}	
	
	public Player findOne(int id){
		return playerRepository.findOne(id);
	}
	

	public void save2(Player player){
		Assert.notNull(player);
		
		playerRepository.save(player);
					
	}
	
}
