package com.c2psi.bmv1.pos.pos.service;

import com.c2psi.bmv1.address.services.AddressValidator;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.services.CurrencyService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.pos.enterprise.services.EnterpriseService;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
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

@Service(value = "PointofsaleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class PointofsaleValidator {
    final AppService appService;
    final AddressValidator addressValidator;
    final EnterpriseService enterpriseService;
    final CurrencyService currencyService;

    public List<String> validate(PointofsaleDto posDto){
        List<String> errors = new ArrayList<>();
        if(posDto == null){
            errors.add("The posDto to validate can't be null");
        }
        else if(posDto.getPosEnterpriseId() == null){
            errors.add("The posEnterpriseId of the pointofsale can't be null");
        }
        else{
            if(!enterpriseService.isEnterpriseExistWith(posDto.getPosEnterpriseId())){
                errors.add("There is no Enterprise in DB with the id sent");
            }
        }

        /***************************************************************
         * Le currency par defaut ne doit pas etre null et
         * L'id du Currency par defaut du Pointofsale ne peut etre null
         */
        if(posDto.getPosCurrency() == null){
            errors.add("A default currency of a pointofsale can't be null");
        } else if (posDto.getPosCurrency().getId() == null) {
            errors.add("Id of the default currency of the pointofsale can't be null");
        } else {
            if(!currencyService.isCurrencyExistWith(posDto.getPosCurrency().getId())){
                errors.add("There is no currency in the DB with the id sent");
            }
        }

        return errors;
    }
    public List<String> validate(Pointofsale pos){
        List<String> errors = new ArrayList<>();

        if(pos == null){
            errors.add("The pos to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Pointofsale>> constraintViolations = validator.validate(pos);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Pointofsale> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }

        /*************************************************
         * L'adresse du Pointofsale doit aussi etre valide
         */
        if(pos.getPosAddress() == null){
            errors.add("The address of pos to validate can't be null");
        }
        errors.addAll(addressValidator.validate(pos.getPosAddress()));


        /*************************************************************************
         * L'Entreprise proprietaire du Pointofsale et son id ne peuvent etre null
         */
//        if(pos.getPosEnterprise() == null){
//            errors.add("The enterprise owner of the Pointofsale can't be null");
//        } else if (pos.getPosEnterprise().getId() == null) {
//            errors.add("The id of the enterprise owner of the pointofsale can't be null");
//        }

        errors.addAll(this.validateStringofBm(pos));

        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> posFields = Arrays.stream(Pointofsale.class.getDeclaredFields()).toList();
        List<Field> posInheritFields = Arrays.stream(Pointofsale.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, posFields, posInheritFields);
    }

    private List<String> validateStringofBm(Pointofsale pos) {
        List<String> errors = new ArrayList<>();

        for(Method method : pos.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(pos);
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
