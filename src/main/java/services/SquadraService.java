package services;





import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Coach;
import domain.Match;
import domain.Squadra;
import repositories.SquadraRepository;

@Service
@Transactional
public class SquadraService {
	// Managed repository
	
		@Autowired
		private SquadraRepository  squadraRepository;
		
		@Autowired
		private CoachService coachService;
		
		// Constructor
		public SquadraService() {
			super();
		}	
		
		public Squadra create() {
			Squadra m = new Squadra();

			return m;
		}
		
		public void save(Squadra s){
			Assert.notNull(s);
			
			Coach c= coachService.findOne(coachService.findByPrincipal().getId());
			
			s.setCoach(c);
			squadraRepository.save(s);
		}
		public Squadra findOne(int id) {
			Squadra m= squadraRepository.findOne(id);

			return m;
		}

		public Collection<Squadra> getMySquadra() {
			Coach coachConnect = coachService.findByPrincipal();
			Collection<Squadra> squadras = new ArrayList<Squadra>();
			squadras = squadraRepository.getMySquadra(
				coachConnect.getId());
			return squadras;
		}
		
		public Collection<Squadra> findAll(){
			 return squadraRepository.findAll();
		}
		
		
		
}