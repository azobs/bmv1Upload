package com.c2psi.bmv1.provider.provider.service;

import com.c2psi.bmv1.address.services.AddressValidator;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.ProviderDto;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.provider.provider.models.Provider;
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

@Service(value = "ProviderValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ProviderValidator {
    final AppService appService;
    final AddressValidator addressValidator;
    final PointofsaleService pointofsaleService;

    public List<String> validate(ProviderDto providerDto){
        List<String> errors = new ArrayList<>();
        if(providerDto == null){
            errors.add("The providerDto to validate can't be null");
        }
        else{
            if(providerDto.getProviderPosId() == null){
                errors.add("The providerPosId of the provider can't be null");
            }
            else{
                if(!pointofsaleService.isPointofsaleExistWith(providerDto.getProviderPosId())){
                    errors.add("There is no Pointofsale in DB with the id sent");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Provider provider){
        List<String> errors = new ArrayList<>();
        if(provider == null){
            errors.add("The provider to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Provider>> constraintViolations = validator.validate(provider);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Provider> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(provider));

            /*************************************************
             * L'adresse du Pointofsale doit aussi etre valide
             */
            if (provider.getProviderAddress() == null) {
                errors.add("The address of provider to validate can't be null");
            }
            else {
                errors.addAll(addressValidator.validate(provider.getProviderAddress()));
            }
        }


        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> providerFields = Arrays.stream(Provider.class.getDeclaredFields()).toList();
        List<Field> providerInheritFields = Arrays.stream(Provider.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, providerFields, providerInheritFields);
    }

    private List<String> validateStringofBm(Provider provider) {
        List<String> errors = new ArrayList<>();

        for(Method method : provider.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(provider);
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
