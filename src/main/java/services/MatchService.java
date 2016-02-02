package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Match;
import repositories.MatchRepository;

@Service
@Transactional
public class MatchService {
	
	// Managed repository
	@Autowired
	private MatchRepository matchRepository;
	
	// Services
	
	// Constructor
	public MatchService() {
		super();
	}


	public Match create() {
		Match m = new Match();

		return m;
	}
	
	public void save(Match m){
		Assert.notNull(m);
		
		matchRepository.save(m);
	}
	
	public void delete(Match m) {
		Assert.notNull(m);
		matchRepository.delete(m);
	}
	
	public Match findOne(int id) {
		Match m= matchRepository.findOne(id);

		return m;
	}
	
	public Collection<Match> findAll(){
		 return matchRepository.findAll();
	}
	
}
