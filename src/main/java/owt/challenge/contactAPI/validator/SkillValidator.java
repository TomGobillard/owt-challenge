package owt.challenge.contactAPI.validator;

import org.springframework.stereotype.Service;
import owt.challenge.contactAPI.input.SkillInput;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class SkillValidator {

    private Validator validator;

    public void validate(SkillInput skillInput) throws ConstraintViolationException {
        Set<ConstraintViolation<SkillInput>> violations  = validator.validate(skillInput);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
