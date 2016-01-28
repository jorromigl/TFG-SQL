package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.Category;
import utilities.PasswordMatches;

public class CoachRegistrationForm {
	
	private int id;
	private int version;
	private RegistrationForm registrationForm;
	private Category category;
	private boolean available;
//	private byte[] photo;
	
	
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
	
//	public byte[] getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(byte[] photo) {
//		this.photo= photo;
//	}
	
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

	@NotNull
	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	


}
