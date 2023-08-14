package com.c2psi.bmv1.userbm.services;

import com.c2psi.bmv1.address.services.AddressValidator;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.controllers.userbm.models.Userbm;
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

        errors.addAll(this.validateStringofBm(userbm));

        return errors;
    }

    private List<String> validateStringofBm(Userbm userbm){
        List<String> errors = new ArrayList<>();
        /****************************************************************
         * On va regarder tous les champs qui ont l'annotation que nous
         * avons definit et les valider aussi selon la logique que nous
         * avons defini
         */
//        for(Field field : userbm.getClass().getDeclaredFields()){
//            if(field.isAnnotationPresent(BmNotBlank.class)){
//                BmNotBlank bmNotBlank = field.getAnnotation(BmNotBlank.class);
//                String message = bmNotBlank.message();
//                try {
//                    /*String fc = String.valueOf(field.getName().charAt(0));
//                    String getMethodName = "get"+fc.toUpperCase()+field.getName().substring(1)+"()";
//                    log.warn("The get method name associate to the field is {}", getMethodName);
//                    Method method = userbm.getClass().getDeclaredMethod(getMethodName);*/
//                    Object objectValueSent = field.get(userbm);
//                    //Object objectValueSent = method.invoke(userbm);
//                    if(objectValueSent instanceof String stringValueSent){
//                        if(appService.isBlankValueIfNotNull(stringValueSent)) {
//                            errors.add(message);
//                        }
//                    }
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException("Access denied to this validation classe");
//                }
//            }
//        }


        /******************************************************************
         * On va regarder toutes les methodes qui ont l'annotation que nous
         * avons definit et valider la valeur que renvoit ces methodes apres
         * invocation selon nos regles
         */
        for(Method method : userbm.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(userbm);
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
