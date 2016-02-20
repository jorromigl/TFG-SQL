package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Squadra;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Integer> {
	
	@Query("select a from Squadra a where a.coach.id =?1")
	Collection<Squadra> getMySquadra(int id);

}
