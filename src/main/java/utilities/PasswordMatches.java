package utilities;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;


/**
 * Validate that the annotated password match with its verify.
 *
 * 
 */
@Documented
@Constraint(validatedBy = {PasswordMatchesValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER  })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface PasswordMatches { 
	String message() default "{password.constraints.passwordVerify.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}