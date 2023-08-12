package com.c2psi.bmv1.pos.pos.service;

import com.c2psi.bmv1.address.services.AddressValidator;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
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

@Service(value = "PointofsaleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class PointofsaleValidator {
    final AppService appService;
    final AddressValidator addressValidator;
    public List<String> validate(Pointofsale pos){
        List<String> errors = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Pointofsale>> constraintViolations = validator.validate(pos);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Pointofsale> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }

        /*************************************************
         * L'adresse du Pointofsale doit aussi etre valide
         */
        log.info("Pos is  {}", pos);
        log.info("Address of pos {}", pos.getPosAddress());
        errors.addAll(addressValidator.validate(pos.getPosAddress()));

        /***************************************************************
         * Le currency par defaut ne doit pas etre null et
         * L'id du Currency par defaut du Pointofsale ne peut etre null
         */
        if(pos.getPosCurrency() == null){
            errors.add("A default currency of a pointofsale can't be null");
        } else if (pos.getPosCurrency().getId() == null) {
            errors.add("Id of the default currency of the pointofsale can't be null");
        }

        /*************************************************************************
         * L'Entreprise proprietaire du Pointofsale et son id ne peuvent etre null
         */
        if(pos.getPosEnterprise() == null){
            errors.add("The enterprise owner of the Pointofsale can't be null");
        } else if (pos.getPosEnterprise().getId() == null) {
            errors.add("The id of the enterprise owner of the pointofsale can't be null");
        }

        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> posFields = Arrays.stream(Pointofsale.class.getDeclaredFields()).toList();
        List<Field> posInheritFields = Arrays.stream(Pointofsale.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, posFields, posInheritFields);
    }

}
