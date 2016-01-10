package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Macth")
public class Match extends DomainEntity {

	
	private String rival;
	private String location;
	private Date moment;

	public Match() {
		super();
	}


	@NotBlank
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@NotBlank
	public String getRival() {
		return rival;
	}

	public void setRival(String rival) {
		this.rival= rival;
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
	
	private Coach coach;
	private Summary summary;
	private Recruitment recruitment;

	@ManyToOne(optional=false)
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Summary getSummary() {
		return summary;
	}


	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Recruitment getRecruitment() {
		return recruitment;
	}


	public void setRecruitment(Recruitment recruitment) {
		this.recruitment = recruitment;
	}
	
	public String toString(){
		return "rival = " + getRival() + "location= "+ getLocation()  + ", moment= " + getMoment();
	}
	
	
	
}