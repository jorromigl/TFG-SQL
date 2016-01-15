package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Summary;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Integer> {
	
	@Query("select a from Summary a where a.userAccount.id=?1")
	Summary findByUserAccountId(int id);

}
