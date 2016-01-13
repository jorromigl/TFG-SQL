package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Classification extends DomainEntity {

	
	private String division;
	private String info;
	private Integer point;
	private Integer played;
	private Integer won;
	private Integer lost;
	private Integer tied;
	
	public Classification() {
		super();
	}
	
	@NotBlank
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@NotBlank
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Min(0)
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	@Min(0)
	public Integer getPlayed() {
		return played;
	}

	public void setPlayed(Integer played) {
		this.played = played;
	}
	
	@Min(0)
	public Integer getWon() {
		return won;
	}

	public void setWon(Integer won) {
		this.won = won;
	}
	
	@Min(0)
	public Integer getLost() {
		return lost;
	}

	public void setLost(Integer lost) {
		this.lost = lost;
	}
	
	@Min(0)
	public Integer getTied() {
		return tied;
	}

	public void setTied(Integer tied) {
		this.tied = tied;
	}

	private Squadra squadra;
	
	@OneToOne(optional=true)
	public Squadra getSquadra() {
		return squadra;
	}

	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}
	
	
	
	
}