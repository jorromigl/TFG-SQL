package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Squadra;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Integer> {
	
	@Query("select a from Squadra a where a.coach.id =?1")
	Collection<Squadra> getMySquadra(int id);
	
	@Query("select a from Squadra a where (select p from Player p where p.id = ?1) member of a.players ")
	Squadra getMySquadraPlayer(int id);
	
	//Devuelve el equipo de su hijo 
	@Query("select a from Squadra a where (select p from Player p where(select f from Family f where f.id = ?1) member of p.families)member of a.players")
	Squadra getMySquadraPlayerP(int id);
}



