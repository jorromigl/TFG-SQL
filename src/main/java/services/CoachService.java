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
import forms.CoachForm2;
import forms.PlayerForm;
import forms.PlayerForm2;
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
		result.setCategory(coachForm.getCategory());
		result.setAdmin(adminService.findByPrincipal());
		result.getUserAccount().setUsername(coachForm.getUsername());
		result.getUserAccount().setPassword(coachForm.getPassword());

		return result;
	}

	// MIO CONCHI
	public Coach reconstructor2(CoachForm coachForm) throws SerialException, SQLException {
		Coach result;

		result = findByPrincipal();

		result.setId(coachForm.getId());
		result.setVersion(coachForm.getVersion());

		result.setEmail(coachForm.getEmail());
		result.setName(coachForm.getName());
		result.setPhone(coachForm.getPhone());
		result.setSurname(coachForm.getSurname());
		result.setAddress(coachForm.getAddress());
		result.getCategory().setCname(coachForm.getCategory().getCname());
		result.getUserAccount().setUsername(coachForm.getUsername());
		result.getUserAccount().setPassword(coachForm.getPassword());
		
		
		return result;
	}
	
	public Coach reconstructor3(CoachForm2 coachForm2) throws SerialException, SQLException {
		Coach result;

		result = findByPrincipal();
		
		result.setId(coachForm2.getId());
		result.setVersion(coachForm2.getVersion());
		result.setFile(coachForm2.getFile());
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
		coachForm.setAvailable(true);
		Category c = new Category();
		c.setCname(coach.getCategory().getCname());
		coachForm.setCategory(c);
		coachForm.setUsername(coach.getUserAccount().getUsername());
		coachForm.setPassword(coach.getUserAccount().getPassword());

		return coachForm;
	}
	
	public CoachForm2 createForm2(Coach coach) {

		CoachForm2 coachForm2 = new CoachForm2();

		coachForm2.setId(coach.getId());
		coachForm2.setVersion(coach.getVersion());		
		
		coachForm2.setFile(coach.getFile());

		return coachForm2;
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

	

	public Collection<Coach> findAll() {
		return coachRepository.findAll();
	}

	public Coach findOne(int id) {
		return coachRepository.findOne(id);
	}

	public void save2(Coach coach) {
		Assert.notNull(coach);
		
		String password = coach.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		coach.getUserAccount().setPassword(password);
		
		coachRepository.save(coach);

	}
	
	public void save3(Coach coach) {
		Assert.notNull(coach);
//		
//		String password = coach.getUserAccount().getPassword();
//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//		password = encoder.encodePassword(password, null);
//		coach.getUserAccount().setPassword(password);
		
		
		coach.getAdmin();
		
		coachRepository.save(coach);

	}

	// Ver el entrenador de su equipo
	public Coach findCoachSquadra(int squadraid) {

		return coachRepository.findCoachSquadra(squadraid);
	}
	
}
