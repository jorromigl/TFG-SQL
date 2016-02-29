package repositories;



import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {

//	COACH
	@Query("select a from Recruitment a where a.match.coach.id =?1")
	Collection<Recruitment> getMyRecruitments(int id);
	
	@Query("select a from Recruitment a where a.match.coach.id =?1 and a.match.moment >= ?2")
	Collection<Recruitment> getMyRecruitmentsNotFinish(int id, Date date);
	
	@Query("select a from Recruitment a where a.match.coach.id =?1 and a.match.moment < ?2")
	Collection<Recruitment> getMyRecruitmentsFinish(int id, Date date);
	
//	PLAYER
	@Query("select r from Recruitment r where (select p from Player p where p.id = ?1) member of r.players and r.match.moment >= ?2")
	Collection<Recruitment> getMyRecruitmentsNotFinishPlayer(int id, Date date);
	
	@Query("select r from Recruitment r where (select p from Player p where p.id = ?1) member of r.players and r.match.moment < ?2")
	Collection<Recruitment> getMyRecruitmentsFinishPlayer(int id, Date date);
	
//	FAMILY
	@Query("select r from Recruitment r where (select p from Player p where (select f from Family f where f.id = ?1) member of p.families) member of r.players and r.match.moment >= ?2")
	Collection<Recruitment> getMyRecruitmentsNotFinishFamily(int id, Date currentMoment);
	
	@Query("select r from Recruitment r where (select p from Player p where (select f from Family f where f.id = ?1) member of p.families) member of r.players and r.match.moment < ?2")
	Collection<Recruitment> getMyRecruitmentsFinishFamily(int id, Date currentMoment);

 

}
