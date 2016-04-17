package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends User {

	public Admin() {
		super();

	}

	private Collection<Coach> coachs;

	@OneToMany(mappedBy = "admin")
	public Collection<Coach> getCoachs() {
		return coachs;
	}

	public void setCoachs(Collection<Coach> coachs) {
		this.coachs = coachs;
	}

}
