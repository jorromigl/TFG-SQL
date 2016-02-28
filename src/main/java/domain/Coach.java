package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Coach extends User {

	public Coach() {
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
	private Collection<Squadra> squadras;


//	@OneToOne(optional=false)
	@OneToMany(mappedBy = "coach")//para populate
	public Collection<Squadra> getSquadras() {
		return squadras;
	}

	public void setSquadras(Collection<Squadra> squadras) {
		this.squadras = squadras;
	}

	@OneToMany(mappedBy="coach")
	public Collection<Match> getMatches() {
		return matches;
	}

	public void setMatches(Collection<Match> matches) {
		this.matches = matches;
	}

	@OneToMany(mappedBy = "coach")
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
