package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Collection<Comment> comments;

//	@ManyToOne(optional=false)
	@ManyToOne //para populate
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
	
	@OneToMany(mappedBy = "match")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comment) {
		this.comments = comment;
	}
	
	public String toString(){
		return "rival = " + getRival() + "location= "+ getLocation()  + ", moment= " + getMoment();
	}
	
	
	
}