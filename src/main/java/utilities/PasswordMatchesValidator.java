package utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import forms.UserForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserForm> {   
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {       
    }
    @Override
    public boolean isValid(UserForm form, ConstraintValidatorContext context){   
        return form.getPassword().equals(form.getVerifyPassword());    
    }     
}