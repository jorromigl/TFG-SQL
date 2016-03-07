package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Coach;
import domain.Comment;
import domain.Family;
import domain.Folder;
import domain.Match;
import domain.Player;
import domain.Recruitment;
import domain.Squadra;
import forms.PlayerForm;
//import forms.PlayerRegistrationForm;
//import forms.RegistrationForm;
import repositories.PlayerRepository;
import repositories.SquadraRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PlayerService {

	// Managed repository

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private FolderService folderService;

	@Autowired
	private CoachService coachService;

	@Autowired
	private SquadraService squadraService;

	@Autowired
	private SquadraRepository squadraRepository;

	@Autowired
	private RecruitmentService recruitmentService;
		
	@Autowired
	private FamilyService familyService;

	// Services

	// Constructor
	public PlayerService() {
		super();
	}

	// Simple CRUD Methods ---------------------------

	public Player create() {
		Player res = new Player();

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		authority.setAuthority(Authority.PLAYER);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);

		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Comment> comments = new ArrayList<Comment>();
		Collection<Family> families = new ArrayList<Family>();

		res.setComments(comments);
		res.setFamilies(families);
		res.setFolders(folders);

		return res;
	}

	public Player findOneToEdit(int playerId) {
		Player result;

		result = playerRepository.findOne(playerId);

		return result;
	}

	public void save(Player player) {
		Assert.notNull(player);

		String password = player.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		player.getUserAccount().setPassword(password);

		Player saved = playerRepository.save(player);

		folderService.createDefaultFolders(saved);

	}

	// Other business methods ------------------------

	// MIO CONCHI
	public Player reconstruct(PlayerForm playerForm) {

		Player result = create();

		result.setEmail(playerForm.getEmail());
		result.setName(playerForm.getName());
		result.setPhone(playerForm.getPhone());
		result.setSurname(playerForm.getSurname());
		result.setAddress(playerForm.getAddress());

		result.setCategory(playerForm.getCategory());
		result.setDate(playerForm.getDate());
		result.getUserAccount().setUsername(playerForm.getUsername());
		result.getUserAccount().setPassword(playerForm.getPassword());
		
		result.setFile(playerForm.getFile());

		return result;
	}

	// MIO CONCHI
	public Player reconstructor2(PlayerForm playerForm) throws SerialException, SQLException {
		Player result;

		result = playerRepository.findOne(playerForm.getId());

		result.setId(playerForm.getId());
		result.setVersion(playerForm.getVersion());

		result.setEmail(playerForm.getEmail());
		result.setName(playerForm.getName());
		result.setPhone(playerForm.getPhone());
		result.setSurname(playerForm.getSurname());
		result.setAddress(playerForm.getAddress());
		result.getCategory().setCname(playerForm.getCategory().getCname());
		result.setDate(playerForm.getDate());
		result.getUserAccount().setUsername(playerForm.getUsername());
		result.getUserAccount().setPassword(playerForm.getPassword());
		
		result.setFile(playerForm.getFile());
		return result;
	}

	// MIO CONCHI
	public PlayerForm createForm(Player player) {

		PlayerForm playerForm = new PlayerForm();

		playerForm.setId(player.getId());
		playerForm.setVersion(player.getVersion());
		playerForm.setEmail(player.getEmail());

		playerForm.setName(player.getName());
		playerForm.setPhone(player.getPhone());
		playerForm.setSurname(player.getSurname());
		playerForm.setAddress(player.getAddress());
		playerForm.setAvailable(true);
		Category c = new Category();
		c.setCname(player.getCategory().getCname());
		playerForm.setCategory(c);
		playerForm.setDate(player.getDate());
		playerForm.setUsername(player.getUserAccount().getUsername());
		playerForm.setPassword(player.getUserAccount().getPassword());
		
		playerForm.setFile(player.getFile());

		return playerForm;
	}

	

	public Player findByPrincipal() {
		UserAccount playerAccount = LoginService.getPrincipal();
		return findByPlayerAccount(playerAccount);
	}

	public Player findByPlayerAccount(UserAccount userAccount) {
		Player result;
		result = playerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Collection<Player> findAll() {
		return playerRepository.findAll();
	}

	public Player findOne(int id) {
		return playerRepository.findOne(id);
	}

	public void delete(Player p) {
		Assert.notNull(p);
		playerRepository.delete(p);
	}

	public void save2(Player player) {
		Assert.notNull(player);

		playerRepository.save(player);

	}

	// Listar los Jugadores cuya categoria sea igual a la categoria del Coach
	// que está autentificado
	public Collection<Player> findPlayerSameCategoryCoach() {
		Coach coachConnect = coachService.findByPrincipal();
		String categoryUser = coachConnect.getCategory().getCname();
		Collection<Player> players;
		players = playerRepository.findAllPlayersSameCategoryCoach(categoryUser);
		return players;
	}

	
	//Listar jugadores de su equipo  
	public Collection<Player> findPlayersSquadra(int squadraid) {

		
		return playerRepository.findAllPlayersSquadra(squadraid);
	}
	

	// Devuelve una colección con los jugadores que no tienen equipo asignado
	// pero que tienen la misma categoria
	public Collection<Player> findInItsCategoryAndNotHaveSquadra(Squadra squadra) {

		Collection<Squadra> squadras = squadraService.getMySquadra();
		Collection<Player> players = findPlayerSameCategoryCoach();
		Collection<Player> playersSquadra = new ArrayList<Player>();

		for (Squadra s : squadras) {
			if (s.getName().equals(squadra.getName())) {
				for (Player p : players) {
					if (p.getSquadra() == null) {
						playersSquadra.add(p);

					}

				}

			}
		}

		return playersSquadra;
	}
		
	//Listar jugadores de su equipo que no estan en el recruitment dado 
	public Collection<Player> findPlayersSquadraToRecruit(int squadraid, int recruitmentId) {
		Collection<Player> players= playerRepository.findAllPlayersSquadra(squadraid);
		Collection<Player> players2= playerRepository.findAllPlayersReqcruitment(recruitmentId);	
		players.removeAll(players2);			
		return players;
	}


	// Devuelve una coleccion de los jugadores de esa convocatoria
	public Collection<Player> findPlayersByRecruitment(int recruitmentId) {



		return playerRepository.findAllPlayersReqcruitment(recruitmentId);
	}
	
	//Listar Jugadores de la misma categoria
	public Collection<Player> findPlayerSameCategory() {
			Player playerConnect = findByPrincipal();
			String categoryUser = playerConnect.getCategory().getCname();
			Collection<Player> players;
			players = playerRepository.findAllPlayersSameCategory(categoryUser);
			return players;
		}
	
	//Ver My Player
	public Player findMyPlayer() {
		Family familyConnect = familyService.findByPrincipal();
		Player player = playerRepository.findMyPlayer(familyConnect.getId());
		return player;
	}
	

	//Delete from Future Recruitment 
	public void deleteFromRecruitment(Player player, int recruitmentId) {
		Recruitment r= recruitmentService.findOne(recruitmentId);
		r.removePlayer(player);
	}

	

	}
	

