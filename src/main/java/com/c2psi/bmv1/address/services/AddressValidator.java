package com.c2psi.bmv1.address.services;

import com.c2psi.bmv1.address.models.Address;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service(value = "AddressValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class AddressValidator {
    public List<String> validate(Address address) {
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Address>> constraintViolations = validator.validate(address);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Address> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }
        /*******************
         * Validation email
         */
        if (address.getEmail() != null){
            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                    + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = Pattern.compile(regexPattern)
                    .matcher(address.getEmail())
                    .matches();
            log.info("adress email {} valid? {}", address.getEmail(), b);
            if (!b) errors.add(address.getEmail() + " is not valid as an email address. Please check its syntax ");
        }

        /******************************************************************************************
         * En plus de tout ca on doit se rassurer qu'au moins l'email et un des numtel est precise
         * Si ils sont non null alors ils sont valide sinon on doit se rassurer qu'au moins 1
         * de tous est non null
         */
        if(address.getEmail() == null && address.getNumtel1() == null && address.getNumtel2() == null
                && address.getNumtel3() == null){
            errors.add("At least one of those data email, numtel1, numtel2 and numtel3 must be precised in an address");
        }

        return errors;
    }
}
