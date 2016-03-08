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

import domain.Admin;
import domain.Category;
import domain.Coach;
import domain.Comment;
import domain.Folder;
import domain.Player;
import forms.CoachForm;
import forms.PlayerForm;
import repositories.CoachRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CoachService {

	// Managed repository

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private FolderService folderService;

	@Autowired
	private AdminService adminService;

	// Services

	// Constructor
	public CoachService() {
		super();
	}

	// Simple CRUD Methods ---------------------------

	public Coach create() {
		Coach res = new Coach();

		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		authority.setAuthority(Authority.COACH);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);

		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Comment> comments = new ArrayList<Comment>();

		res.setComments(comments);
		res.setFolders(folders);

		return res;
	}

	public void delete(Coach c) {
		Assert.notNull(c);

		coachRepository.delete(c);
	}

	public Coach findOneToEdit(int coachId) {
		Coach result;

		result = coachRepository.findOne(coachId);

		return result;
	}

	public void save(Coach coach) {
		Assert.notNull(coach);

		String password = coach.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		coach.getUserAccount().setPassword(password);
		Admin a = adminService.findByPrincipal();
		coach.setAdmin(a);
		Coach saved = coachRepository.save(coach);

		folderService.createDefaultFolders(saved);

	}

	// Other business methods ------------------------

	public Coach reconstruct(CoachForm coachForm) {

		Coach result = create();

		result.setEmail(coachForm.getEmail());
		result.setName(coachForm.getName());
		result.setPhone(coachForm.getPhone());
		result.setSurname(coachForm.getSurname());
		result.setAddress(coachForm.getAddress());
		result.setFile(coachForm.getFile());
		result.setCategory(coachForm.getCategory());
		result.setAdmin(coachForm.getAdmin());
		result.getUserAccount().setUsername(coachForm.getUsername());
		result.getUserAccount().setPassword(coachForm.getPassword());

		return result;
	}

	// MIO CONCHI
	public Coach reconstructor2(CoachForm coachForm) throws SerialException, SQLException {
		Coach result;

		result = coachRepository.findOne(coachForm.getId());

		result.setId(coachForm.getId());
		result.setVersion(coachForm.getVersion());
		result.setAdmin(coachForm.getAdmin());

		result.setEmail(coachForm.getEmail());
		result.setName(coachForm.getName());
		result.setPhone(coachForm.getPhone());
		result.setSurname(coachForm.getSurname());
		result.setFile(coachForm.getFile());
		result.setAddress(coachForm.getAddress());
		result.setAdmin(coachForm.getAdmin());
		result.getCategory().setCname(coachForm.getCategory().getCname());
		result.getUserAccount().setUsername(coachForm.getUsername());
		result.getUserAccount().setPassword(coachForm.getPassword());
		
		
		return result;
	}

	// MIO CONCHI
	public CoachForm createForm(Coach coach) {

		CoachForm coachForm = new CoachForm();

		coachForm.setId(coach.getId());
		coachForm.setVersion(coach.getVersion());
		coachForm.setEmail(coach.getEmail());
		coachForm.setName(coach.getName());
		coachForm.setPhone(coach.getPhone());
		coachForm.setSurname(coach.getSurname());
		coachForm.setAddress(coach.getAddress());
		coachForm.setFile(coach.getFile());
		coachForm.setAvailable(true);
		
		coachForm.setAdmin(coach.getAdmin());
		Category c = new Category();
		c.setCname(coach.getCategory().getCname());
		coachForm.setCategory(c);
		coachForm.setUsername(coach.getUserAccount().getUsername());
		coachForm.setPassword(coach.getUserAccount().getPassword());

		return coachForm;
	}

	public Coach findByPrincipal() {
		UserAccount coachAccount = LoginService.getPrincipal();
		return findByCoachAccount(coachAccount);
	}

	public Coach findByCoachAccount(UserAccount userAccount) {
		Coach result;
		result = coachRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	// public void addImageToPhoto(int coachId, byte[] bytes) {
	// Coach c= findOne(coachId);
	// if(bytes.length==0){
	//
	// c.setPhoto(null);
	// }else{
	// c.setPhoto(bytes);
	// }
	//
	// save(c);
	// }

	public Collection<Coach> findAll() {
		return coachRepository.findAll();
	}

	public Coach findOne(int id) {
		return coachRepository.findOne(id);
	}

	public void save2(Coach coach) {
		Assert.notNull(coach);

		coachRepository.save(coach);

	}

	// Ver el entrenador de su equipo
	public Coach findCoachSquadra(int squadraid) {

		return coachRepository.findCoachSquadra(squadraid);
	}
	
}
