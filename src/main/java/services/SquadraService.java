package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Coach;
import domain.Family;
import domain.Player;
import domain.Squadra;
import repositories.SquadraRepository;

@Service
@Transactional
public class SquadraService {
	// Managed repository

	@Autowired
	private SquadraRepository squadraRepository;

	@Autowired
	private CoachService coachService;

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private FamilyService familyService;

	// Constructor
	public SquadraService() {
		super();
	}

	public Squadra create() {
		Squadra m = new Squadra();

		return m;
	}

	public void save(Squadra s) {
		Assert.notNull(s);

		Coach c = coachService.findOne(coachService.findByPrincipal().getId());

		s.setCoach(c);
		squadraRepository.save(s);
	}

	public Squadra findOne(int id) {
		Squadra m = squadraRepository.findOne(id);

		return m;
	}

	public Collection<Squadra> getMySquadra() {
		Coach coachConnect = coachService.findByPrincipal();
		Collection<Squadra> squadras = new ArrayList<Squadra>();
		squadras = squadraRepository.getMySquadra(coachConnect.getId());
		return squadras;
	}

	public Collection<Squadra> findAll() {
		return squadraRepository.findAll();
	}
		
	public void addPlayer(int squadraId, int playerId){
		
		Squadra s = findOne(squadraId);
		Player p= playerService.findOne(playerId);
		s.addPlayer(p);
		
	}

	public Squadra getMySquadraPlayer() {
		Player playerConnect = playerService.findByPrincipal();
		Squadra squadra = squadraRepository.getMySquadraPlayer(playerConnect.getId());
		return squadra;
	}
	
	//El equipo de su hijo
	public Squadra getMySquadraPlayerP() {
		Family familyConnect = familyService.findByPrincipal();
		Squadra squadra = squadraRepository.getMySquadraPlayerP(familyConnect.getId());
		return squadra;
	}

}
