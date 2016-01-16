package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.User;
import forms.UserForm;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class UserService {

	// Managed Repository -----------------------------------------------
	@Autowired
	private UserRepository userRepository;
	
	// Supporting services ----------------------------------------------
	@Autowired
	private UserAccountService userAccountService;
	
	// Constructor ------------------------------------------------------
	public UserService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------
	
	// Other business methods -------------------------------------------------
	public UserAccount findUserAccount(User user) {
		Assert.notNull(user);
		
		UserAccount result;
		
		result = userAccountService.findByUser(user);
		
		return result;
	}
	
	public User findByUserAccount(int id){
		Assert.isTrue(id != 0);
		User res;
		
		res = this.userRepository.findByUserAccountId(id);
		Assert.notNull(res);
		
		return res;
	}
	
	public Collection<User> findAll() {
		Collection<User> result;
		
		result = userRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}

	public User findOne(int userId) {
		Assert.isTrue(userId != 0);
		
		User result;
		result = userRepository.findOne(userId);
		Assert.notNull(result);

		return result;
	}
	
	public User findByPrincipal() {
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		return userRepository.findByUserAccountId(userAccount.getId());
		
	}
		
	public UserForm createForm(User user) {
		UserForm userForm = new UserForm();
		userForm.setId(user.getId());
		userForm.setVersion(user.getVersion());
		userForm.setUsername(user.getUserAccount().getUsername());
		userForm.setPassword(user.getUserAccount().getPassword());
		userForm.setAddress(user.getAddress());
		userForm.setName(user.getName());
		userForm.setSurname(user.getSurname());
		userForm.setEmail(user.getEmail());
		userForm.setPhone(user.getPhone());
		
		return userForm;
	}
	
	
	
	
	// Other business methods ------------------------
		
	
	public User reconstruct2(UserForm userForm){
		
		User result;
		result = userRepository.findOne(userForm.getId());	
		result.setId(userForm.getId());
		result.setVersion(userForm.getVersion());
		result.setEmail(userForm.getEmail());
		result.setName(userForm.getName());
		result.setPhone(userForm.getPhone());
		result.setSurname(userForm.getSurname());
		result.setAddress(userForm.getAddress());
		result.getUserAccount().setUsername(userForm.getUsername());
		result.getUserAccount().setPassword(userForm.getPassword());
			
		return result;	
	}
	
	public User findByUserAccount(UserAccount userAccount) {
		User result;
		result = userRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public User findOne2(int userId) {
		User result;
			
		result = userRepository.findOne(userId);		
		
		checkPrincipal(result);
		return result;
	}
	
	public void save2(User user) {
		Assert.notNull(user);
		
	
		checkPrincipal(user);
		userRepository.save(user);
		
	}
	
	
	
	public void savePassword(User user) {
		Assert.notNull(user);
		
		String password = user.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		user.getUserAccount().setPassword(password);
		checkPrincipal(user);
		userRepository.save(user);
		
	}
	
	
	public void checkPrincipal(User u){
		Assert.notNull(u);
		
		Assert.isTrue(findByPrincipal().equals(u));
		
		
	}
	
	

}

