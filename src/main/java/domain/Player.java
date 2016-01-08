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
	
	private Date age;
	private Category category;
	
	@Past
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	private Collection<Family> families;
	private Collection<Comment> comments;
	private Team team;
	private Collection<Call> calls;

	
	
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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@ManyToMany
	public Collection<Call> getCalls() {
		return calls;
	}
	public void setCalls(Collection<Call> calls) {
		this.calls = calls;
	}
	

	

}
