package models.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValidatorState implements ConstraintValidator<ValidState, String> {

    private List<String> states;

    @Override
    public void initialize(ValidState constraintAnnotation) {
        this.states = new ArrayList<>();
        this.states.add("AC");
        this.states.add("AL");
        this.states.add("AP");
        this.states.add("AM");
        this.states.add("BA");
        this.states.add("CE");
        this.states.add("DF");
        this.states.add("ES");
        this.states.add("GO");
        this.states.add("MA");
        this.states.add("MT");
        this.states.add("MS");
        this.states.add("MG");
        this.states.add("PA");
        this.states.add("PB");
        this.states.add("PR");
        this.states.add("PE");
        this.states.add("PI");
        this.states.add("RJ");
        this.states.add("RN");
        this.states.add("RS");
        this.states.add("RO");
        this.states.add("RR");
        this.states.add("SC");
        this.states.add("SP");
        this.states.add("SE");
        this.states.add("TO");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null ? false : states.contains(value);
    }
}
