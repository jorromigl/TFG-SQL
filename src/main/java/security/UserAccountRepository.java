package security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	UserAccount findByUsername(String username);
	
	@Query("select u.userAccount from User a where u.id = ?1")
	UserAccount findByUserId(int userId);


}
