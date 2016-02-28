package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Admin;
import domain.Folder;
import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdminService {

	// Managed repository ----------------------------
	
	@Autowired
	private AdminRepository administratorRepository;
	
	@Autowired
	private FolderService folderService;
	
	// Supporting services ---------------------------

	// Constructor -----------------------------------
	
	public AdminService(){
		super();
	}
	
	// Simple CRUD Methods ---------------------------
	
	public Admin create(){
		Admin res = new Admin();
		
		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();
		
						
		authority.setAuthority(Authority.ADMIN);
		userAccount.addAuthority(authority);
	
		res.setUserAccount(userAccount);
		
		Collection<Folder> folders = new ArrayList<Folder>();
			
		
		res.setFolders(folders);
			
		return res;
	}
	
	
	public void save(Admin admin){
		checkPrincipal();
		Assert.notNull(admin);
				
		
		String password = admin.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		admin.getUserAccount().setPassword(password);
		
		Admin saved = administratorRepository.save(admin);
		folderService.createDefaultFolders(saved);
		
		
	}
	
	// Other business methods ------------------------
	
//	public Admin reconstruct(AdminRegistrationForm registrationForm){
//		
//		Admin result = create();
//		
//		result.setEmail(registrationForm.getRegistrationForm().getEmail());
//		result.setName(registrationForm.getRegistrationForm().getName());
//		result.setPhone(registrationForm.getRegistrationForm().getPhone());
//		result.setSurname(registrationForm.getRegistrationForm().getSurname());
//		
//		result.getUserAccount().setUsername(registrationForm.getRegistrationForm().getUsername());
//		result.getUserAccount().setPassword(registrationForm.getRegistrationForm().getPassword());
//		
//		return result;	
//		
//	}
//
//	public AdminForm createForm(Admin admin) {
//		AdminForm adminForm = new AdminForm();
//		adminForm.setId(admin.getId());
//		adminForm.setVersion(admin.getVersion());
//		adminForm.setUsername(admin.getUserAccount().getUsername());
//		adminForm.setPassword(admin.getUserAccount().getPassword());
//		adminForm.setName(admin.getName());
//		adminForm.setSurname(admin.getSurname());
//		adminForm.setEmail(admin.getEmail());
//		adminForm.setPhone(admin.getPhone());
//		
//		return adminForm;
//	}
//	// Other business methods ------------------------
//		
//	
//	public Admin reconstruct2(AdminForm adminForm){
//		
//		Admin result;
//		result = administratorRepository.findOne(adminForm.getId());	
//		result.setId(adminForm.getId());
//		result.setVersion(adminForm.getVersion());
//		result.setEmail(adminForm.getEmail());
//		result.setName(adminForm.getName());
//		result.setPhone(adminForm.getPhone());
//		result.setSurname(adminForm.getSurname());
//		result.getUserAccount().setUsername(adminForm.getUsername());
//		result.getUserAccount().setPassword(adminForm.getPassword());
//			
//		return result;	
//	}
//	

	public Admin findByUserAccount(UserAccount userAccount) {
		Admin result;
		result = administratorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Admin findOne2(int adminId) {
		Admin result;
			
		result = administratorRepository.findOne(adminId);		
		
		checkPrincipal(result);
		return result;
	}
	
	public Admin findOne(int adminId) {
		Admin result;
			
		result = administratorRepository.findOne(adminId);		

		return result;
	}
	
	public void save2(Admin admin) {
		Assert.notNull(admin);
		
		checkPrincipal(admin);
		administratorRepository.save(admin);
		
	}
	
	public void savePassword(Admin admin) {
		Assert.notNull(admin);
		
		String password = admin.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		admin.getUserAccount().setPassword(password);
		checkPrincipal(admin);
		administratorRepository.save(admin);
		
	}
	

	public void checkPrincipal(){
		
		UserAccount userAccount = LoginService.getPrincipal();
		
		Collection<Authority> authorities = userAccount.getAuthorities();
		Authority auth = new Authority();
		auth.setAuthority("ADMIN");
		
		Assert.isTrue(authorities.contains(auth));
	}
	
	public void checkPrincipal(Admin administrator){
		
		Assert.notNull(administrator);
		UserAccount userAccount = LoginService.getPrincipal();
				
		Assert.isTrue(administrator.getUserAccount().equals(userAccount));
	}
	
	public Admin findByPrincipal(){
		Admin res;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		res = administratorRepository.findByUserAccountId(userAccount.getId());
		
		return res;
	}
}
