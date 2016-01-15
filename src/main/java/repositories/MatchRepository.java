package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
	
	@Query("select a from Match a where a.userAccount.id=?1")
	Match findByUserAccountId(int id);

}
