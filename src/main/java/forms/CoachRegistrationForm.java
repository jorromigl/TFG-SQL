package forms;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.Category;
import utilities.PasswordMatches;

@Table(uniqueConstraints={@UniqueConstraint(columnNames="referenceNumber")})
public class CoachRegistrationForm {
	
	private int id;
	private int version;
	private RegistrationForm registrationForm;
	private Category category;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@NotNull
	@Valid
	@PasswordMatches
	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}
	
	@NotNull
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	


}
