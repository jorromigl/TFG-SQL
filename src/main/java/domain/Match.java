package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Match extends DomainEntity {

	
	private String adversary;
	private String place;
	private Date moment;
	

	public Match() {
		super();
	}


	@NotBlank
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	@NotBlank
	public String getAdversary() {
		return adversary;
	}

	public void setAdversary(String adversary) {
		this.adversary = adversary;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	private Trainer trainer;
	private Summary summary;
	private Call call;

	@ManyToOne(optional=false)
	public Trainer getTrainer() {
		return trainer;
	}


	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	@OneToOne(optional=false, cascade=CascadeType.ALL)
	public Summary getSummary() {
		return summary;
	}


	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	@OneToOne(optional=false, cascade=CascadeType.ALL)
	public Call getCall() {
		return call;
	}


	public void setCall(Call call) {
		this.call = call;
	}
	
	
	
}