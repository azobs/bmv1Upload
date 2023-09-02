package com.c2psi.bmv1.product.format.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.FormatDto;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.format.models.Format;
import com.c2psi.bmv1.product.format.dao.FormatDao;
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
import java.util.*;

@Service(value = "FormatValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class FormatValidator {
    final AppService appService;
    final PointofsaleService posService;
    final FormatDao formatDao;

    public List<String> validate(FormatDto formatDto){
        List<String> errors = new ArrayList<>();
        if(formatDto == null){
            errors.add("The formatDto to validate can't be null");
        }
        else if(formatDto.getFormatPosId() == null){
            errors.add("The posId of the pointofsale can't be null");
        }
        else{
            if(!posService.isPointofsaleExistWith(formatDto.getFormatPosId())){
                errors.add("There is no Pointofsale in DB with the id sent");
            }
        }
        return errors;
    }

    public List<String> validate(Format format){
        List<String> errors = new ArrayList<>();

        if(format == null){
            errors.add("The format to validate can't be null");
        }
        else {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Format>> constraintViolations = validator.validate(format);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Format> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(format));
        }
        return errors;
    }

    private List<String> validateStringofBm(Format format) {
        List<String> errors = new ArrayList<>();

        for(Method method : format.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(format);
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
        List<Field> formatFields = Arrays.stream(Format.class.getDeclaredFields()).toList();
        List<Field> formatInheritFields = Arrays.stream(Format.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, formatFields, formatInheritFields);
    }

}
