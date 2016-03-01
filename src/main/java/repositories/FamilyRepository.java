package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {
	
	@Query("select a from Family a where a.userAccount.id=?1")
	Family findByUserAccountId(int id);
	
	//Devuelve el familiar de un player
	@Query("select a from Family a where a.player.id=?1")
	Collection<Family> findMyFamily(int id);

}
