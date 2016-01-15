package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query("select a from Player a where a.userAccount.id=?1")
	Player findByUserAccountId(int id);

}
