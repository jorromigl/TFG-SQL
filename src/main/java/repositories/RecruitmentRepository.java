package repositories;



import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
	
//	//Devuelve una collection de recruitment no finalizadas dado el id del coach
//    @Query("select r from Recruitment r where r.coach.id=?1 and r.match.moment < date")
//    Collection<Recruitment> getRecruitmentsNotFinish(Date date);
//
//    //Devuelve una collection de recruitment finalizadas dado el id del coach
//    @Query("select r from Recruitment r where r.coach.id=?1 and r.match.moment > date")
//    Collection<Recruitment> getRecruitmentsFinish(Date date);
	

	@Query("select a from Recruitment a where a.match.coach.id =?1")
	Collection<Recruitment> getMyRecruitments(int id);
	
	@Query("select a from Recruitment a where a.match.coach.id =?1 and a.match.moment >= ?2")
	Collection<Recruitment> getMyRecruitmentsNotFinish(int id, Date date);
	
	@Query("select a from Recruitment a where a.match.coach.id =?1 and a.match.moment < ?2")
	Collection<Recruitment> getMyRecruitmentsFinish(int id, Date date);
	
	
	

}
