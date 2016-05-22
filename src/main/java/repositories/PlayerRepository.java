package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	@Query("select a from Player a where a.userAccount.id=?1")
	Player findByUserAccountId(int id);
	
	 
    //Devuelve una collection de los jugarodes que tienen la misma Categoria que el Coach logueado
    @Query("select p from Player p where p.category.cname = ?1")
    Collection<Player> findAllPlayersSameCategoryCoach(String categoryUser);
    
    @Query("select p from Player p where p.squadra.id = ?1")
    Collection<Player> findAllPlayersSquadra(int id);
    
    @Query("select p from Player p where (select r from Recruitment r where r.id = ?1) member of p.recruitments")
    Collection<Player> findAllPlayersReqcruitment(int id);

    @Query("select p from Player p where p.category.cname = ?1")
	Collection<Player> findAllPlayersSameCategory(String categoryUser);

  //Devuelve el player de un familiar
    @Query("select p from Player p where (select f from Family f where f.id = ?1) member of p.families")
	Player findMyPlayer(int id);
    
  //Devuelve un paciente dado su username
    @Query("select p from Player p where p.userAccount.username=?1")
    Player findForUsername(String username);


    
    
    
//    @Query("select concat p from Player p where p.squadra.id = ?1 and (select r from Recruitment r where r.id = ?2) member of p.recruitments")
//    Collection<Player> findAllPlayersNotReqcruitment(int idSquadra, int idRecruitment);
}
