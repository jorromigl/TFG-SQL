package utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import forms.RegistrationForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationForm> {   
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {       
    }
    @Override
    public boolean isValid(RegistrationForm form, ConstraintValidatorContext context){   
        return form.getPassword().equals(form.getVerifyPassword());    
    }     
}