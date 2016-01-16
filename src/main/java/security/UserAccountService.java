package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import domain.User;

@Service
public class UserAccountService {

	// Managed Repository ---------------------------------------------------------
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	// Supporting services --------------------------------------------------------
	
	// Constructor
	public UserAccountService(){
		super();
	}
	
	// Simple CRUD methods --------------------------------------------------------
	
	public UserAccount findByUser(User user){
		Assert.notNull(user);
		
		UserAccount result;
		
		result = this.userAccountRepository.findByUserId(user.getId());
		
		return result;
	}
	
	// Other business methods -----------------------------------------------------
}
