package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Trainer extends User {

	public Trainer() {
		super();

	}
	
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	private Admin admin;
	private Collection<Summary> summaries;
	private Collection<Match> matches;
	private Team team;
	private Collection<Comment> comments;
	
	@OneToMany(mappedBy="trainer")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@OneToOne(optional=false)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@OneToMany(mappedBy="trainer")
	public Collection<Match> getMatches() {
		return matches;
	}

	public void setMatches(Collection<Match> matches) {
		this.matches = matches;
	}

	@ManyToMany
	public Collection<Summary> getSummaries() {
		return summaries;
	}

	public void setSummaries(Collection<Summary> summaries) {
		this.summaries = summaries;
	}

	@ManyToOne(optional=false)
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	

}
