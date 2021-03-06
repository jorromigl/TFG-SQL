package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Coach extends User {

	public Coach() {
		super();

	}

	private Category category;
	private byte[] file;

	@Lob
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private Admin admin;
	private Collection<Summary> summaries;
	private Collection<Squadra> squadras;

	@OneToMany(mappedBy = "coach")
	public Collection<Squadra> getSquadras() {
		return squadras;
	}

	public void setSquadras(Collection<Squadra> squadras) {
		this.squadras = squadras;
	}

	@OneToMany(mappedBy = "coach")
	public Collection<Summary> getSummaries() {
		return summaries;
	}

	public void setSummaries(Collection<Summary> summaries) {
		this.summaries = summaries;
	}

	@ManyToOne(optional = false)
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
