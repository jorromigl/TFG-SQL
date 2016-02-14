package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Coach;
import domain.Match;
import domain.Summary;
import repositories.SummaryRepository;

@Service
@Transactional
public class SummaryService {
	
	// Managed repository
	@Autowired
	private SummaryRepository summaryRepository;
	
	// Services
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private CoachService coachService;
	
	// Constructor
	public SummaryService() {
		super();
	}


	public Summary create(int id) {
		
		Summary s = new Summary();
		s.setMatch(matchService.findOne(id));
		
		return s;
	}
	
	public void save(Summary s){
		Assert.notNull(s);

		Coach c= coachService.findOne(coachService.findByPrincipal().getId());
		s.setCoach(c);
		
		summaryRepository.save(s);
	}
		
	public void delete(Summary s) {
		Assert.notNull(s);
		summaryRepository.delete(s);
	}
	
	public Summary findOne(int id) {
		Summary s= summaryRepository.findOne(id);

		return s;
	}
	
	public Summary findByMatchId(int id){
		
		Summary s = summaryRepository.findByMatchId(id);
		
		return s;
	}
	
	public Collection<Summary> findAll(){
		 return summaryRepository.findAll();
	}
	
}
