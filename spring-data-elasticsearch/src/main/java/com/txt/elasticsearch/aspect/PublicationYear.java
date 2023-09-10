package com.txt.elasticsearch.aspect;

import com.txt.elasticsearch.utils.PublicationYearValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = PublicationYearValidator.class)
public @interface PublicationYear {

    String message() default "Publication year cannot be future year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}