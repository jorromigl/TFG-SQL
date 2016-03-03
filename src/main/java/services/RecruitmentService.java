package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Coach;
import domain.Family;
import domain.Player;
import domain.Recruitment;
import repositories.RecruitmentRepository;

@Service
@Transactional
public class RecruitmentService {
	

	// Managed repository
	@Autowired
	private RecruitmentRepository recruitmentRepository;
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private FamilyService familyService;
	
	@Autowired
	private MatchService matchService;
	
	// Constructor
		public RecruitmentService() {
			super();
		}
		
		
		public Recruitment create(int id){	
			Recruitment c = new Recruitment();
			c.setMatch(matchService.findOne(id));
			return c;
		}
		
	
		public void save(Recruitment c) {   
			Assert.notNull(c);
			recruitmentRepository.save(c);
		}
		
		public void delete(Recruitment r) {
			Assert.notNull(r);
			recruitmentRepository.delete(r);
		}
		
		public Recruitment findOne(int id) {
			Recruitment r= recruitmentRepository.findOne(id);

			return r;
		}
		

	    //Devuelve una collection de las convocatorias de un coach que han finalizado
	    public Collection<Recruitment> getMyRecruitmentsFinish() {
	    	Coach coachConnect = coachService.findByPrincipal();
			Date currentMoment = new Date();
			Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
			recruitments = recruitmentRepository.getMyRecruitmentsFinish(
					coachConnect.getId(), currentMoment);
			
			return recruitments;
		}
	      
	    //Me devuelve todas las convocatorias de un Coach
	    public Collection<Recruitment> getMyRecruitments(){
	    	Coach coachConnect = coachService.findByPrincipal();
	    	Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
	    		recruitments = recruitmentRepository.getMyRecruitments(
					coachConnect.getId());
	    		
	    		return recruitments;
	    	
	    }
 	    
	    //Me devuelve las convocatorias del coach que no han finalizado (Futuras)
	    public Collection<Recruitment> getMyRecruitmentsNotFinish() {
			Coach coachConnect = coachService.findByPrincipal();
			Date currentMoment = new Date();
			Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
			recruitments = recruitmentRepository.getMyRecruitmentsNotFinish(
					coachConnect.getId(), currentMoment);
			
			return recruitments;
		}
	    
	    //Me devuelve las convocatorias del player que no han finalizado (Futuras)
	    public Collection<Recruitment> getMyRecruitmentsNotFinishPlayer() {
			Player playerConnect = playerService.findByPrincipal();
			Date currentMoment = new Date();
			Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
			recruitments = recruitmentRepository.getMyRecruitmentsNotFinishPlayer(
					playerConnect.getId(), currentMoment);
			
			return recruitments;
		}
	    
	    //Me devuelve las convocatorias del family que no han finalizado (Futuras)
	    public Collection<Recruitment> getMyRecruitmentsNotFinishFamily() {
			Family familyConnect = familyService.findByPrincipal();
			Date currentMoment = new Date();
			Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
			recruitments = recruitmentRepository.getMyRecruitmentsNotFinishFamily(
					familyConnect.getId(), currentMoment);
			
			return recruitments;
		}
	    
	    //Devuelve una collection de las convocatorias de un player que han finalizado
	    public Collection<Recruitment> getMyRecruitmentsFinishPlayer() {
	    	Player playerConnect = playerService.findByPrincipal();
			Date currentMoment = new Date();
			Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
			recruitments = recruitmentRepository.getMyRecruitmentsFinishPlayer(
					playerConnect.getId(), currentMoment);
			
			return recruitments;
		}
	    
	    //Devuelve una collection de las convocatorias de un family que han finalizado
	    public Collection<Recruitment> getMyRecruitmentsFinishFamily() {
	    	Family familyConnect = familyService.findByPrincipal();
			Date currentMoment = new Date();
			Collection<Recruitment> recruitments = new ArrayList<Recruitment>();
			recruitments = recruitmentRepository.getMyRecruitmentsFinishFamily(
					familyConnect.getId(), currentMoment);
			
			return recruitments;
		}
		
		public Collection<Recruitment> findAll(){
			 return recruitmentRepository.findAll();
		}
		
		public void addPlayerRecruitment(int recruitmentId, int playerId){
			
			Recruitment r= findOne(recruitmentId);
			Player p= playerService.findOne(playerId);
			r.addPlayerRecruitment(p);
			
		}
		

		

}


