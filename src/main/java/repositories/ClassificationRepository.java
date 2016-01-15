package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Classification;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Integer> {
	
	@Query("select a from Classification a where a.userAccount.id=?1")
	Classification findByUserAccountId(int id);

}
