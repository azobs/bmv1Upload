package com.c2psi.bmv1.product.unit.unitconversion.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.UnitconversionDto;
import com.c2psi.bmv1.product.unit.unit.service.UnitService;
import com.c2psi.bmv1.product.unit.unitconversion.models.Unitconversion;
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

@Service(value = "UnitconversionValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class UnitconversionValidator {
    final AppService appService;
    final UnitService unitService;

    public List<String> validate(UnitconversionDto unitconvDto){
        List<String> errors = new ArrayList<>();
        if(unitconvDto == null){
            errors.add("The unitconvDto to validate can't be null");
        } else{
            if (unitconvDto.getUnitSourceId() == null) {
                errors.add("The unitSource id of the conversion rule can't be null");
            }
            if(unitconvDto.getUnitDestinationId() == null){
                errors.add("The unitDestination id of the conversion rule can't be null");
            }
            if(unitconvDto.getUnitSourceId() != null && unitconvDto.getUnitDestinationId() != null){
                if(!unitService.isUnitExistWith(unitconvDto.getUnitSourceId())){
                    errors.add("There is no Unit in the DB with the source Id sent");
                }
                if(!unitService.isUnitExistWith(unitconvDto.getUnitDestinationId())){
                    errors.add("There is no Unit in the DB with the destination Id sent");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Unitconversion unitconv) {
        List<String> errors = new ArrayList<>();
        if(unitconv == null){
            errors.add("The Unitconversion to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Unitconversion>> constraintViolations = validator.validate(unitconv);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Unitconversion> contraintes : constraintViolations) {
                errors.add(contraintes.getMessage());
            }
        }
        errors.addAll(this.validateStringofBm(unitconv));
        return errors;
    }

    private List<String> validateStringofBm(Unitconversion unitconv) {
        List<String> errors = new ArrayList<>();

        for(Method method : unitconv.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(unitconv);
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
        List<Field> unitconvFields = Arrays.stream(Unitconversion.class.getDeclaredFields()).toList();
        List<Field> unitconvInheritFields = Arrays.stream(Unitconversion.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, unitconvFields, unitconvInheritFields);
    }

}
