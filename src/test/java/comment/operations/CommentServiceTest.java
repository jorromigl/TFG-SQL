package comment.operations;

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
import domain.Match;
import security.LoginService;
import services.CommentService;
import services.MatchService;
import utilities.PopulateDatabase;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceTest {

		@Autowired
	    private CommentService commentService;
		
		@Autowired
	    private MatchService matchService;
	    

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
	    public void testCreateComment() {
	        authenticate("coach1");
	        Comment comment = new Comment();
	        Match match = matchService.findOne(0);
	        
	        Date moment = new Date();
	        comment.setMatch(match);
	        comment.setMoment(moment);
	        comment.setSubject("revision");
	        comment.setText("El partido no debio llevarselo el equipo contrario.");

	        commentService.save(comment);
	        Collection<Comment> comments = commentService.findByMatch(match.getId());

	        boolean res = false;
	        for (Comment c : comments) {
	            if (c.getSubject().equals(comment.getSubject())) {
	                res = true;
	                break;
	            }
	        }
	        Assert.isTrue(res);

	    }


	    @Test(expected = IllegalArgumentException.class)
	    public void testCreateCommentNotAuthenticated() {
	        desauthenticate();
	        Comment comment = new Comment();
	        Match match = matchService.findOne(43);
	        comment.setMatch(match);
	        comment.setText("El partido no debio llevarselo el equipo contrario.");
	        commentService.save(comment);

	    }
	}
