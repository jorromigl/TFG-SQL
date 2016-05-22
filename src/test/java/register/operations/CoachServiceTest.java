package register.operations;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Coach;
import domain.Player;
import forms.CoachForm;
import forms.PlayerForm;
import security.LoginService;
import services.CoachService;
import utilities.PopulateDatabase;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CoachServiceTest {

	@Autowired
	private CoachService coachService;

	@Autowired
	private LoginService loginService;

	public void authenticate(String username) {
		UserDetails userDetails;
		TestingAuthenticationToken authenticationToken;
		SecurityContext context;

		userDetails = loginService.loadUserByUsername(username);
		authenticationToken = new TestingAuthenticationToken(userDetails, null);
		context = SecurityContextHolder.getContext();
		context.setAuthentication(authenticationToken);
	}

	public void desauthenticate() {
	        UserDetails userDetails;
	        TestingAuthenticationToken authenticationToken;
	        SecurityContext context;

	        userDetails = loginService.loadUserByUsername(null);
	        authenticationToken = new TestingAuthenticationToken(userDetails, null);
	        context = SecurityContextHolder.getContext();
	        context.setAuthentication(authenticationToken);
	}
	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}

	@Test
	public void testRegister() {
		
		authenticate("admin");
		
		Coach coach;
		Category category = new Category();
		category.setCname("Juvenil");
		CoachForm coachForm = new CoachForm();
		coachForm.setUsername("player1");
		coachForm.setName("player1");
		coachForm.setSurname("player1");
		coachForm.setCategory(category);
		coachForm.setEmail("player1@mail.com");
		coachForm.setAddress("c/Pedro Leon");
		coachForm.setPhone("+34666666666");
		coachForm.setPassword("5d2bbc279b5ce75815849d5e3f0533ec");
		coachForm.setVerifyPassword("5d2bbc279b5ce75815849d5e3f0533ec");
		coachForm.setAvailable(true);
		coach = coachService.reconstruct(coachForm);
		coachService.save(coach);
	}
	
	 @Test(expected = IllegalArgumentException.class)
	    public void testRegisterCategory() {

		 Coach coach;
			Category category = new Category();
			category.setCname("aaaaa");
			CoachForm coachForm = new CoachForm();
			coachForm.setUsername("player1");
			coachForm.setName("player1");
			coachForm.setSurname("player1");
			coachForm.setCategory(category);
			coachForm.setEmail("player1@mail.com");
			coachForm.setAddress("c/Pedro Leon");
			coachForm.setPhone("+34666666666");
			coachForm.setPassword("5d2bbc279b5ce75815849d5e3f0533ec");
			coachForm.setVerifyPassword("5d2bbc279b5ce75815849d5e3f0533ec");
			coachForm.setAvailable(true);
			coach = coachService.reconstruct(coachForm);
			coachService.save(coach);
	    }
}
