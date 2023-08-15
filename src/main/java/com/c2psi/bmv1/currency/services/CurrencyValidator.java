package com.c2psi.bmv1.currency.services;

import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
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

@Service(value = "CurrencyValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class CurrencyValidator {
    final AppService appService;
    public List<String> validate(Currency currency) {
        List<String> errors = new ArrayList<>();

        if(currency == null){
            errors.add("The currency to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Currency>> constraintViolations = validator.validate(currency);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Currency> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        //log.info("We are in validate of filterList and sortCriterias ");
        List<Field> currencyFields = Arrays.stream(Currency.class.getDeclaredFields()).toList();
        List<Field> currencyInheritFields = Arrays.stream(Currency.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, currencyFields, currencyInheritFields);
    }

}
