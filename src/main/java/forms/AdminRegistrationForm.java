package forms;

import javax.validation.Valid;



import javax.validation.constraints.NotNull;

import utilities.PasswordMatches;


public class AdminRegistrationForm {
	
	private RegistrationForm registrationForm;

	
	@NotNull
	@Valid
	@PasswordMatches
	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}


}
