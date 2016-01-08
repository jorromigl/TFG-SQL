//package domain;
//
//import java.util.Date;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.Entity;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//
//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.SafeHtml;
//import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
//import org.springframework.format.annotation.DateTimeFormat;
//
//@Entity
//@Access(AccessType.PROPERTY)
//public class Classification extends DomainEntity {
//
//	
//	private String division;
//	private String name;
//	private Integer point;
//	private Integer played;
//	private Integer won;
//	private Integer lost;
//	private Integer tied;
//	
//	public Classification() {
//		super();
//	}
//	
//	@NotBlank
//	public String getDivision() {
//		return division;
//	}
//
//	public void setDivision(String division) {
//		this.division = division;
//	}
//
//	@NotBlank
//	public String getName() {
//		return name;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	@Min(0)
//	public Integer getPoint() {
//		return point;
//	}
//
//	public void setPoint(Integer point) {
//		this.point = point;
//	}
//	
//	@Min(0)
//	public Integer getPlayed() {
//		return played;
//	}
//
//	public void setPlayed(Integer played) {
//		this.played = played;
//	}
//	
//	@Min(0)
//	public Integer getWon() {
//		return won;
//	}
//
//	public void setWon(Integer won) {
//		this.won = won;
//	}
//	
//	@Min(0)
//	public Integer getLost() {
//		return lost;
//	}
//
//	public void setLost(Integer lost) {
//		this.lost = lost;
//	}
//	
//	@Min(0)
//	public Integer getTied() {
//		return tied;
//	}
//
//	public void setTied(Integer tied) {
//		this.tied = tied;
//	}
//
//
//}