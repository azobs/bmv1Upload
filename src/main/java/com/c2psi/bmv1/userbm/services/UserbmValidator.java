package com.c2psi.bmv1.userbm.services;

import com.c2psi.bmv1.address.services.AddressValidator;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.userbm.models.Userbm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service(value = "UserbmValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class UserbmValidator {
    final AppService appService;
    final AddressValidator addressValidator;
    public List<String> validate(Userbm userbm){
        List<String> errors = new ArrayList<>();

        if(userbm.getUserAddress() == null){
            errors.add("Address of User can't be null");
        }

        errors.addAll(addressValidator.validate(userbm.getUserAddress()));

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

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){

        //log.error("list of field {}", Userbm.class.getFields());
        //List<Field> userbmFields = Arrays.stream(Userbm.class.getFields()).toList();
        /**************
         * On charge la liste des noms des champs declares
         */
        List<Field> userbmFields = Arrays.stream(Userbm.class.getDeclaredFields()).toList();
        //log.error("other list of field {}", Userbm.class.getSuperclass().getDeclaredFields());
        /************************
         * On charge la liste des noms des champs de la classes herites
         */
        List<Field> userbmInheritFields = Arrays.stream(Userbm.class.getSuperclass().getDeclaredFields()).toList();

        /******
         * On invoque le service general qui verifie les champs
         */
        return appService.checkColumnList(filterList, sortCriterias, userbmFields, userbmInheritFields);

    }

}
