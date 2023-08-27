package com.c2psi.bmv1.auth.token.services;

import com.c2psi.bmv1.auth.permission.models.Permission;
import com.c2psi.bmv1.auth.token.models.Token;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.TokenDto;
import com.c2psi.bmv1.userbm.services.UserbmService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "TokenValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class TokenValidator {
    final AppService appService;
    final UserbmService userbmService;

    public List<String> validate(TokenDto tokenDto){
        List<String> errors = new ArrayList<>();
        if(tokenDto == null){
            errors.add("The tokenDto to validate can't be null");
        }
        if(tokenDto.getUserbmId() == null){
            errors.add("The UserbmId of a token can't be null");
        }
        if(!userbmService.isUserbmExistWithId(tokenDto.getUserbmId())){
            errors.add("There is no Userbm in the system with the id sent");
        }
        return errors;
    }

    public List<String> validate(Token token){
        List<String> errors = new ArrayList<>();
        if(token == null){
            errors.add("The token to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Token>> constraintViolations = validator.validate(token);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Token> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }

        errors.addAll(this.validateStringofBm(token));

        return errors;

    }

    private List<String> validateStringofBm(Token token) {
        List<String> errors = new ArrayList<>();
        /******************************************************************
         * On va regarder toutes les methodes qui ont l'annotation que nous
         * avons definit et valider la valeur que renvoit ces methodes apres
         * invocation selon nos regles
         */

        for(Method method : token.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(token);
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

        return  errors;
    }


}
