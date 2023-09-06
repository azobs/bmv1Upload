package com.c2psi.bmv1.sale.backin.backin.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.BackinDto;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.sale.backin.backin.models.Backin;
import com.c2psi.bmv1.sale.command.services.CommandService;
import com.c2psi.bmv1.userbm.services.UserbmService;
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

@Service(value = "BackinValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class BackinValidator {
    final AppService appService;
    final UserbmService userbmService;
    final CommandService commandService;


    public List<String> validate(BackinDto backinDto){
        List<String> errors = new ArrayList<>();
        if(backinDto == null){
            errors.add("The backinDto to validate can't be null");
        }
        else {
            if(backinDto.getBiSalerId() == null){
                errors.add("The salerId of the backin can't be null");
            }
            else{
                if(!userbmService.isUserbmExistWithId(backinDto.getBiSalerId())){
                    errors.add("There is no Userbm in the DB with the id sent");
                }
            }

            if(backinDto.getBiCommandId() == null){
                errors.add("The command of the backin can't be null");
            }
            else{
                if(!commandService.isCommandExistWith(backinDto.getBiCommandId())){
                    errors.add("There is no Command in the DB with the id sent");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Backin backin){
        List<String> errors = new ArrayList<>();
        if(backin == null){
            errors.add("The backin sent can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Backin>> constraintViolations = validator.validate(backin);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Backin> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(backin));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> backinFields = Arrays.stream(Backin.class.getDeclaredFields()).toList();
        List<Field> backinInheritFields = Arrays.stream(Backin.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, backinFields, backinInheritFields);
    }

    private List<String> validateStringofBm(Backin backin) {
        List<String> errors = new ArrayList<>();

        for(Method method : backin.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(backin);
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
