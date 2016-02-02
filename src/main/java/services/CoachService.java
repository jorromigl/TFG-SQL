package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Admin;
import domain.Coach;
import domain.Comment;
import domain.Folder;
import forms.CoachRegistrationForm;
import repositories.CoachRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CoachService {

	// Managed repository
	
	@Autowired
	private CoachRepository coachRepository;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdminService adminService;

	// Services	
	
	// Constructor
	public CoachService() {
		super();
	}	
	
	// Simple CRUD Methods ---------------------------
	
	public Coach create(){
		Coach res = new Coach();			
			
		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();
	
		
		authority.setAuthority(Authority.COACH);
		userAccount.addAuthority(authority);
		
		res.setUserAccount(userAccount);
			
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Comment> comments = new ArrayList<Comment>();
		
		res.setComments(comments);					
		res.setFolders(folders);
				
		return res;
	}
	
	public void delete(Coach c) {
		Assert.notNull(c);
		
		coachRepository.delete(c);
	}
	
	public Coach findOneToEdit(int coachId) {
		Coach result;
			
		result = coachRepository.findOne(coachId);		
		
		
		return result;
	}
	
		
	public void save(Coach coach){
		Assert.notNull(coach);
					
		String password = coach.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		coach.getUserAccount().setPassword(password);
		Admin a = adminService.findByPrincipal();
		coach.setAdmin(a);
		Coach saved = coachRepository.save(coach);
			
		folderService.createDefaultFolders(saved);
			
					
	}

	// Other business methods ------------------------
		
	public Coach reconstruct(CoachRegistrationForm registrationForm){
		
		Coach result = create();
			
		result.setEmail(registrationForm.getRegistrationForm().getEmail());
		result.setName(registrationForm.getRegistrationForm().getName());
		result.setPhone(registrationForm.getRegistrationForm().getPhone());
		result.setSurname(registrationForm.getRegistrationForm().getSurname());
		result.setAddress(registrationForm.getRegistrationForm().getAddress());
		result.setCategory(registrationForm.getCategory());
//		result.setPhoto(registrationForm.getPhoto());
		result.getUserAccount().setUsername(registrationForm.getRegistrationForm().getUsername());
		result.getUserAccount().setPassword(registrationForm.getRegistrationForm().getPassword());
			
		return result;	
	}
	


	public Coach findByPrincipal() {
		UserAccount coachAccount = LoginService.getPrincipal();
		return findByCoachAccount(coachAccount);
	}

	public Coach findByCoachAccount(UserAccount userAccount) {
		Coach result;
		result = coachRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
//	public void addImageToPhoto(int coachId, byte[] bytes) {
//		Coach c= findOne(coachId);
//		if(bytes.length==0){
//		
//			c.setPhoto(null);
//		}else{
//			c.setPhoto(bytes);
//		}
//		
//		save(c);
//	}
	

	
	public Collection<Coach> findAll(){
		return coachRepository.findAll();
	}	
	
	public Coach findOne(int id){
		return coachRepository.findOne(id);
	}
	

	public void save2(Coach coach){
		Assert.notNull(coach);
		
		coachRepository.save(coach);
					
	}
	
}
