package com.c2psi.bmv1.price.specialprice.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.SpecialpriceDto;
import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import com.c2psi.bmv1.price.baseprice.services.BasepriceService;
import com.c2psi.bmv1.price.specialprice.models.Specialprice;
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

@Service(value = "SpecialpriceValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class SpecialpriceValidator {
    final AppService appService;
    final BasepriceService basepriceService;

    public List<String> validate(SpecialpriceDto specialpriceDto){
        List<String> errors = new ArrayList<>();
        if(specialpriceDto == null){
            errors.add("The specialpriceDto to validate can't be null");
        }
        else{
            if(specialpriceDto.getSpBasepriceId() == null){
                errors.add("The id of the baseprice associate can't be null");
            }
            else{
                if(!basepriceService.isBasepriceExistWith(specialpriceDto.getSpBasepriceId())){
                    errors.add("There is no Baseprice in the DB with the id sent");
                }
            }
        }
        return errors;
    }


    public List<String> validate(Specialprice specialprice){
       List<String> errors = new ArrayList<>();

        if(specialprice == null){
            errors.add("The specialprice to validate can't be null");
        }
        else {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Specialprice>> constraintViolations = validator.validate(specialprice);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Specialprice> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(specialprice));
        }
       return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> spFields = Arrays.stream(Specialprice.class.getDeclaredFields()).toList();
        List<Field> spInheritFields = Arrays.stream(Specialprice.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, spFields, spInheritFields);
    }

    private List<String> validateStringofBm(Specialprice sp) {
        List<String> errors = new ArrayList<>();

        for(Method method : sp.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(sp);
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
