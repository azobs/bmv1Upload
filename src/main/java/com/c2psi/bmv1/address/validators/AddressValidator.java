package com.c2psi.bmv1.address.validators;

import com.c2psi.bmv1.address.models.Address;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddressValidator {
    public static List<String> validate(Address address){
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Address>> constraintViolations = validator.validate(address);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Address> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }
        return errors;
    }
}
