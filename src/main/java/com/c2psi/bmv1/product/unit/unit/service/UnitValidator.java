package com.c2psi.bmv1.product.unit.unit.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.UnitDto;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.unit.unit.models.Unit;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service(value = "UnitValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class UnitValidator {
    final AppService appService;
    final PointofsaleService posService;

    public List<String> validate(UnitDto unitDto){
        List<String> errors = new ArrayList<>();
        if(unitDto == null){
            errors.add("The unitDto to validate can't be null");
        } else if (unitDto.getUnitPosId() == null) {
            errors.add("The unitPosId of the pointofsale can't be null");
        }
        else{
            if(!posService.isPointofsaleExistWith(unitDto.getUnitPosId())){
                errors.add("There is no Pointofsale in DB with the id sent");
            }
        }
        return errors;
    }

    public List<String> validate(Unit unit){
        List<String> errors = new ArrayList<>();
        if(unit == null){
            errors.add("The unit to validate can't be null");
        }
        else {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Unit>> constraintViolations = validator.validate(unit);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Unit> contraintes : constraintViolations) {
                    errors.add(contraintes.getMessage());
                }
            }

            if (unit.getUnitPos() == null) {
                errors.add("The Pointofsale of the Unit can't be null");
            }

            errors.addAll(this.validateStringofBm(unit));
        }
        return errors;
    }

    private List<String> validateStringofBm(Unit unit) {
        List<String> errors = new ArrayList<>();

        for(Method method : unit.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(unit);
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

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> unitFields = Arrays.stream(Unit.class.getDeclaredFields()).toList();
        List<Field> unitInheritFields = Arrays.stream(Unit.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, unitFields, unitInheritFields);
    }

}
