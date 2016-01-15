package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Squadra;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Integer> {
	
	@Query("select a from Squadra a where a.userAccount.id=?1")
	Squadra findByUserAccountId(int id);

}
