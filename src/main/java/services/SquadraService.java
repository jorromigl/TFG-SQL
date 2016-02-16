package services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
		
	
}
