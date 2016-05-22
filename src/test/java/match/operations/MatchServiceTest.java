package match.operations;

import java.util.Collection;
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

import domain.Category;
import domain.Coach;
import domain.Match;
import domain.Squadra;
import forms.CoachForm;
import security.LoginService;
import services.MatchService;
import services.SquadraService;
import utilities.PopulateDatabase;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MatchServiceTest {

	@Autowired
	private MatchService matchService;

	@Autowired
	private SquadraService squadraService;

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
	public void testCreateMatch() {

		authenticate("coach1");

		Match match = matchService.create();
		Date date = new Date("12/12/2020");
		Collection<Squadra> s = squadraService.getMySquadra();
		Squadra squadra = new Squadra();
		for (Squadra i : s) {
			squadra = i;
		}
		match.setLocation("tomares");
		match.setMoment(date);
		match.setSquadra(squadra);
		matchService.save(match);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatchBad() {

		desauthenticate();
		authenticate("coach1");

		Match match = matchService.create();
		Date date = new Date("12/12/2020");
		Collection<Squadra> s = squadraService.getMySquadra();
		Squadra squadra = new Squadra();
		for (Squadra i : s) {
			squadra = i;
		}
		match.setLocation("tomares");
		match.setMoment(date);
		match.setSquadra(squadra);
		matchService.save(match);
	}
}
