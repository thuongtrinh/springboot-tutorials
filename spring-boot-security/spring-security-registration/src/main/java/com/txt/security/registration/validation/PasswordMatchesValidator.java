package com.txt.security.registration.validation;

import com.txt.security.registration.dto.PasswordRequest;
import com.txt.security.registration.dto.RegistrationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        if(obj instanceof RegistrationRequest) {
            final RegistrationRequest user = (RegistrationRequest) obj;
            return user.getPassword().equals(user.getMatchPassword());
        } else if(obj instanceof PasswordRequest) {
            final PasswordRequest user = (PasswordRequest) obj;
            return user.getPassword().equals(user.getMatchPassword());
        }
        return false;
    }

}
