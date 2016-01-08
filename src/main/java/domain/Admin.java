package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends User {

	public Admin() {
		super();

	}
	
private Collection<Trainer> trainers;

@OneToMany(mappedBy="admin", cascade= CascadeType.ALL)
public Collection<Trainer> getTrainers() {
	return trainers;
}
public void setTrainers(Collection<Trainer> trainers) {
	this.trainers = trainers;
}


	
	
	
	
	
}


