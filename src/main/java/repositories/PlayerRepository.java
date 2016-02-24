package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Player;
import domain.Recruitment;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query("select a from Player a where a.userAccount.id=?1")
	Player findByUserAccountId(int id);
	
	 
    //Devuelve una collection de los jugarodes que tienen la misma Categoria que el Coach logueado
    @Query("select p from Player p where p.category.cname = ?1")
    Collection<Player> findAllPlayersSameCategoryCoach(String categoryUser);
    
    @Query("select p from Player p where p.squadra.id = ?1")
    Collection<Player> findAllPlayersSquadra(int id);
}
