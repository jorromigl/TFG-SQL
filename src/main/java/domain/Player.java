package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Player extends User {

	public Player() {
		super();

	}
	
	private Date date;
	private Category category;
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	@Valid
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

	
	@NotNull
//	@OneToMany(mappedBy="player", cascade=CascadeType.ALL)
	@OneToMany(mappedBy="player")
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
	
	@ManyToOne(optional=true)
	public Squadra getSquadra() {
		return squadra;
	}
	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}
	
	@ManyToMany(mappedBy = "players")
	public Collection<Recruitment> getRecruitments() {
		return recruitments;
	}
	public void setRecruitments(Collection<Recruitment> recruitments) {
		this.recruitments = recruitments;
	}
	
	public String toString() {
		return "Player  [date=" + date + ", category="
				+ category +"]";
	}
	

}
