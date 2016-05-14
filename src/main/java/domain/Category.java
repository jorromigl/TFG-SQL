package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Category {

	private String cname;
	public static final String INICIACION = "Iniciacion";
	public static final String PREBENJAMIN = "Prebenjamin";
	public static final String BENJAMIN = "Benjamin";
	public static final String ALEVIN = "Alevin";
	public static final String INFANTIL = "Infantil";
	public static final String CADETE = "Cadete";
	public static final String JUVENIL = "Juvenil";
	public static final String SENIOR = "Senior";

	@NotBlank
	@Pattern(regexp = "^" + INICIACION + "|" + PREBENJAMIN + "|" + BENJAMIN + "|" + ALEVIN + "|" + INFANTIL + "|"
			+ CADETE + "|" + JUVENIL + "|" + SENIOR + "$")
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
