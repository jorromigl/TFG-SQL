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
import forms.FamilyForm;
import repositories.FamilyRepository;
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
	private FolderService folderService;


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
	
	public Family findOne(int id){
		return familyRepository.findOne(id);
	}
	

	public void save2(Family family){
		Assert.notNull(family);
		
		familyRepository.save(family);
					
	}
	
}
