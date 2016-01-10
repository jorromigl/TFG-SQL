package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Player extends User {

	public Player() {
		super();

	}
	
	private Date moment;
	private Category category;
	
	@Past
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	private Collection<Family> families;
	private Collection<Comment> comments;
	private Squadra squadra;
	private Collection<Recruitment> recruitments;

	
	
	@OneToMany(mappedBy="player", cascade=CascadeType.ALL)
	public Collection<Family> getFamilies() {
		return families;
	}
	public void setFamilies(Collection<Family> families) {
		this.families = families;
	}
	
	@OneToMany(mappedBy="player")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@ManyToOne(optional=false)
	public Squadra getSquadra() {
		return squadra;
	}
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}
	
	@ManyToMany
	public Collection<Recruitment> getRecruitments() {
		return recruitments;
	}
	public void setRecruitments(Collection<Recruitment> recruitments) {
		this.recruitments = recruitments;
	}
	

	

}
