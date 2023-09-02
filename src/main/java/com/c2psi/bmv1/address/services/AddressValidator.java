package com.c2psi.bmv1.address.services;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service(value = "AddressValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class AddressValidator {
    final AppService appService;
    public List<String> validate(Address address) {

        List<String> errors = new ArrayList<>();
        if(address == null){
            errors.add("The address sent is null");
        }
        else {
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
            if (address.getEmail() != null) {
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
            if (address.getEmail() == null && address.getNumtel1() == null && address.getNumtel2() == null
                    && address.getNumtel3() == null) {
                errors.add("At least one of those information email, numtel1, numtel2 and numtel3 must be precised in an address");
            }

            errors.addAll(this.validateStringofBm(address));
        }
        return errors;
    }

    private List<String> validateStringofBm(Address address) {
        List<String> errors = new ArrayList<>();

        for(Method method : address.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(address);
                    if(objectValueSent instanceof String stringValueSent){
                        /***
                         * On se rassure que la chaine saisi une fois non null n'est ni empty ni blank
                         * et ensuite que sa taille est comprise entre le min et le max defini
                         * */
                        if(appService.isBlankValueIfNotNull(stringValueSent)) {
                            errors.add(message);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Access denied to this method due to encapsulation");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("InvocationTargetException");
                }
            }
        }
        return errors;
    }


}
