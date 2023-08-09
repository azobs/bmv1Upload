package com.c2psi.bmv1.userbm.validators;

import com.c2psi.bmv1.userbm.models.Userbm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserbmValidator {
    public static List<String> validate(Userbm userbm){
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Userbm>> constraintViolations = validator.validate(userbm);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Userbm> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }
        return errors;
    }
}
