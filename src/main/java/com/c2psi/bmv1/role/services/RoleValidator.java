package com.c2psi.bmv1.role.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.role.models.Role;
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

@Service(value = "RoleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class RoleValidator {
    final AppService appService;

    public List<String> validate(Role role){
        List<String> errors = new ArrayList<>();
        if(role == null){
            errors.add("The role to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Role> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }

        errors.addAll(this.validateStringofBm(role));

        /*****************************************************
         * Le pointofsale du role et son entreprise ne peuvent pas
         * etre null en meme temps
         */
        if(role.getRolePos() == null && role.getRoleEnt() == null){
            errors.add("The pointofsale and enterprise owner of the role can't be null at the same time");
        }

        return errors;
    }

    private List<String> validateStringofBm(Role role) {
        List<String> errors = new ArrayList<>();
        /******************************************************************
         * On va regarder toutes les methodes qui ont l'annotation que nous
         * avons definit et valider la valeur que renvoit ces methodes apres
         * invocation selon nos regles
         */
        for(Method method : role.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(role);
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
        List<Field> roleFields = Arrays.stream(Role.class.getDeclaredFields()).toList();
        List<Field> roleInheritFields = Arrays.stream(Role.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, roleFields, roleInheritFields);
    }

}
