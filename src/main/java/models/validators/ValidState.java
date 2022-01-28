package models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorState.class)
@Documented
public @interface ValidState {
    String message() default "{models.Address.state}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
