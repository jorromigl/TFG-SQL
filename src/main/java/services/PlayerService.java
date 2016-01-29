package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Comment;
import domain.Family;
import domain.Folder;
import domain.Player;
import forms.PlayerRegistrationForm;
import forms.RegistrationForm;
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
	
	public Player reconstructor2(PlayerRegistrationForm playerForm) throws SerialException, SQLException{
		Player result;

		
		result = playerRepository.findOne(playerForm.getId());		

		result.setId(playerForm.getId());
		result.setVersion(playerForm.getVersion());
		
		result.setEmail(playerForm.getRegistrationForm().getEmail());
		result.setName(playerForm.getRegistrationForm().getName());
		result.setPhone(playerForm.getRegistrationForm().getPhone());
		result.setSurname(playerForm.getRegistrationForm().getSurname());
		result.setAddress(playerForm.getRegistrationForm().getAddress());
		result.getCategory().setCname(playerForm.getCategory().getCname());
		result.setDate(playerForm.getDate());
		result.getUserAccount().setUsername(playerForm.getRegistrationForm().getUsername());
		result.getUserAccount().setPassword(playerForm.getRegistrationForm().getPassword());
		return result;
	}
	
	public PlayerRegistrationForm createForm(Player player){
		
		PlayerRegistrationForm playerForm = new PlayerRegistrationForm();
		
		
		playerForm.setId(player.getId());
		playerForm.setVersion(player.getVersion());
		RegistrationForm form= new RegistrationForm();
		form.setEmail(player.getEmail());
//		playerForm.setRegistrationForm().setEmail(player.getEmail());
		
		playerForm.setRegistrationForm(form);
		playerForm.getRegistrationForm().setName(player.getName());
		playerForm.getRegistrationForm().setPhone(player.getPhone());
		playerForm.getRegistrationForm().setSurname(player.getSurname());
		playerForm.getRegistrationForm().setAddress(player.getAddress());
		playerForm.setAvailable(true);
		Category c = new Category();
		c.setCname(player.getCategory().getCname());
		playerForm.setCategory(c);
		playerForm.setDate(player.getDate());
		playerForm.getRegistrationForm().setUsername(player.getUserAccount().getUsername());
		playerForm.getRegistrationForm().setPassword(player.getUserAccount().getPassword());			
			
		return playerForm;
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
