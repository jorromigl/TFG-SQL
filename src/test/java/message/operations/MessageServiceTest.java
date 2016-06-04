package message.operations;

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
import org.springframework.util.Assert;

import domain.Comment;
import domain.Folder;
import domain.Match;
import domain.Message;
import domain.Player;
import domain.User;
import security.LoginService;
import services.CommentService;
import services.FolderService;
import services.MatchService;
import services.MessageService;
import services.PlayerService;
import services.UserService;
import utilities.PopulateDatabase;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageServiceTest {

		@Autowired
	    private MessageService messageService;
		
		@Autowired
	    private FolderService folderService;
		
		@Autowired
	    private UserService userService;
		
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

	    //crear comentario para un partido.
	    @Test()
	    public void testSendMessage() {
	        authenticate("coach1");

			Message message = messageService.create();
			
			message.setSubject("probando");
			message.setBody("cuerpo mensaje");
			User u = userService.findOne(24);
			message.setRecipient(u);
			
			messageService.save(message);

	    }


	    @Test(expected = IllegalArgumentException.class)
	    public void testSendMessageNotAuthenticated() {
	        desauthenticate();

			Message message = messageService.create();
			
			message.setSubject("probando");
			message.setBody("cuerpo mensaje");
			User u = userService.findOne(24);
			message.setRecipient(u);
			
			messageService.save(message);

	    }
	}
