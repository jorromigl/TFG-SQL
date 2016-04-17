package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Summary extends DomainEntity {

	private String text;
	private String subject;

	public Summary() {
		super();
	}

	@NotBlank
	// @SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NotBlank
	// @SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	private Coach coach;
	private Match match;

	@ManyToOne(optional = true)
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	@OneToOne(optional = false)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}