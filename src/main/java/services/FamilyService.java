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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Category;
import domain.Coach;
import domain.Comment;
import domain.Family;
import domain.Folder;
import domain.Player;
import domain.Squadra;
import forms.CoachForm;
import forms.CoachForm2;
import forms.FamilyForm;
import forms.FamilyForm2;
import repositories.FamilyRepository;
import repositories.PlayerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class FamilyService {

	// Managed repository
	
	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private PlayerService playerService;

	// Services	
	
	// Constructor
	public FamilyService() {
		super();
	}	
	
	// Simple CRUD Methods ---------------------------
	
	public Family create(){
		Family res = new Family();			
			
		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();
	
		
		authority.setAuthority(Authority.FAMILY);
		userAccount.addAuthority(authority);
		
		res.setUserAccount(userAccount);
			
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Comment> comments = new ArrayList<Comment>();

		
		res.setComments(comments);			
		
		res.setFolders(folders);
				
		return res;
	}
		
	public Family findOneToEdit(int expertId) {
		Family result;
			
		result = familyRepository.findOne(expertId);		
		
		
		return result;
	}
	
		
	public void save(Family family){
		Assert.notNull(family);
					
		String password = family.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		family.getUserAccount().setPassword(password);
			
		Family saved = familyRepository.save(family);
			
		folderService.createDefaultFolders(saved);
			
					
	}

	// Other business methods ------------------------
		
	public Family reconstruct(FamilyForm familyForm){
		
		Family result = create();
			
		result.setEmail(familyForm.getEmail());
		result.setName(familyForm.getName());
		result.setPhone(familyForm.getPhone());
		result.setSurname(familyForm.getSurname());
		result.setAddress(familyForm.getAddress());
		result.setPlayer(familyForm.getPlayer());
		result.getUserAccount().setUsername(familyForm.getUsername());
		result.getUserAccount().setPassword(familyForm.getPassword());
			
		return result;	
	}
	


	public Family findByPrincipal() {
		UserAccount familyAccount = LoginService.getPrincipal();
		return findByFamilyAccount(familyAccount);
	}

	public Family findByFamilyAccount(UserAccount userAccount) {
		Family result;
		result = familyRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Collection<Family> findAll(){
		return familyRepository.findAll();
	}	
	
	public Collection<Player> playerSameCategory(){
		Family f= findByPrincipal();
		String category= f.getPlayer().getCategory().getCname();
		Collection<Player> players= playerRepository.findAllPlayersSameCategory(category);
		players.remove(f.getPlayer());
		return players;
	}	
	
	public Family findOne(int id){
		return familyRepository.findOne(id);
	}
	

	public void save2(Family family){
		Assert.notNull(family);
		family.getPlayer();
		
		familyRepository.save(family);
					
	}

	public Collection<Family> findMyFamily() {
		Player playerConnect = playerService.findByPrincipal();
		Collection<Family> families = familyRepository.findMyFamily(playerConnect.getId());
		return families;
	}
	

		public Family reconstructor2(FamilyForm familyForm) throws SerialException, SQLException {
			Family result;

			result = familyRepository.findOne(familyForm.getId());

			result.setId(familyForm.getId());
			result.setVersion(familyForm.getVersion());

			result.setEmail(familyForm.getEmail());
			result.setPlayer(familyForm.getPlayer());
			result.setName(familyForm.getName());
			result.setPhone(familyForm.getPhone());
			result.setSurname(familyForm.getSurname());
			result.setAddress(familyForm.getAddress());
			result.getUserAccount().setUsername(familyForm.getUsername());
			result.getUserAccount().setPassword(familyForm.getPassword());
			return result;
		}
		
		public Family reconstructor3(FamilyForm2 familyForm2) throws SerialException, SQLException {
			Family result;

			result = findByPrincipal();
			
			result.setId(familyForm2.getId());
			result.setVersion(familyForm2.getVersion());
			long i = familyForm2.getFile().length;	
			
			//Controlamos el tamaño de la foto que no sobrepase los 3MB
			if(i <3145728){
				result.setFile(familyForm2.getFile());
			}
			return result;
		}

		public FamilyForm createForm(Family family) {

			FamilyForm familyForm = new FamilyForm();

			familyForm.setId(family.getId());
			familyForm.setVersion(family.getVersion());
			familyForm.setEmail(family.getEmail());
			familyForm.setPlayer(family.getPlayer());
			familyForm.setName(family.getName());
			familyForm.setPhone(family.getPhone());
			familyForm.setSurname(family.getSurname());
			familyForm.setAddress(family.getAddress());
			familyForm.setAvailable(true);
			
			familyForm.setUsername(family.getUserAccount().getUsername());
			familyForm.setPassword(family.getUserAccount().getPassword());

			return familyForm;
		}
		
		public FamilyForm2 createForm2(Family family) {

			FamilyForm2 familyForm2 = new FamilyForm2();

			familyForm2.setId(family.getId());
			familyForm2.setVersion(family.getVersion());		
			familyForm2.setFile(family.getFile());
			return familyForm2;
		}
		
	

		
		
	
}
