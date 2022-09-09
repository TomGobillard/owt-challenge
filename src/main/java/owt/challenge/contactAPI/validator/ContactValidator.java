package owt.challenge.contactAPI.validator;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import owt.challenge.contactAPI.input.ContactInput;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Service
public class ContactValidator {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void validate(ContactInput contactInput) throws ConstraintViolationException {
        Set<ConstraintViolation<ContactInput>> violations = validator.validate(contactInput);
         if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
