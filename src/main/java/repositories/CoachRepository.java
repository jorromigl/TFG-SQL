package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
	
	@Query("select a from Coach a where a.userAccount.id=?1")
	Coach findByUserAccountId(int id);
	
	//Devuelve el entrenador del equipo
    @Query("select c from Coach c where (select s from Squadra s where s.id = ?1) member of c.squadras")
	Coach findCoachSquadra(int squadraid);
}
