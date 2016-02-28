package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Match;
import repositories.CommentRepository;


@Service
@Transactional
public class CommentService {

	// Managed repository
	@Autowired
	private CommentRepository commentRepository;
	
	// Services
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MatchService matchService;
	
	// Constructor
	public CommentService() {
		
		super();
	}
	
	

	public Comment create(Match m){
		
		Comment c = new Comment();
		
		c.setMoment(new Date());
		c.setMatch(m);
		c.setUser(userService.findByPrincipal());
		
		return c;
	}
		
	
	@SuppressWarnings("deprecation")
	public void save(Comment c) {   
		Assert.notNull(c);
		c.setUser(userService.findByPrincipal());
		
		Date date = new Date();
		date.setSeconds(date.getSeconds()+1);
		Assert.isTrue(c.getMoment().before(date));
		
		date.setSeconds(date.getSeconds()-2);
		c.setMoment(date);		
		
		commentRepository.save(c);
	}
	
	public void delete(Comment c){
		
		adminService.checkPrincipal();
		Assert.notNull(c);
		
		commentRepository.delete(c);
	}

	public Comment findOne(int id) {
		Comment c = commentRepository.findOne(id);

		return c;
	}
	
	public Collection<Comment> findAll(){
		return commentRepository.findAll();
	}
	
	public Collection<Comment> findByMatch(int id) {
		Match m = matchService.findOne(id);
		Collection<Comment> c = m.getComments();
		return c;
		
	}

}
