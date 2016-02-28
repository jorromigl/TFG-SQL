package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Summary;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Integer> {
	
	@Query("select s from Summary s where s.match.id=?1")
	Summary findByMatchId(int id);

}
