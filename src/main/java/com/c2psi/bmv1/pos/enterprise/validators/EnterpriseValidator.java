package com.c2psi.bmv1.pos.enterprise.validators;

import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
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

@Service(value = "EnterpriseValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class EnterpriseValidator {
    final AppService appService;
    public List<String> validate(Enterprise enterprise){
        List<String> errors = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Enterprise>> constraintViolations = validator.validate(enterprise);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Enterprise> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> enterpriseFields = Arrays.stream(Enterprise.class.getDeclaredFields()).toList();
        List<Field> enterpriseInheritFields = Arrays.stream(Enterprise.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, enterpriseFields, enterpriseInheritFields);
    }

}
