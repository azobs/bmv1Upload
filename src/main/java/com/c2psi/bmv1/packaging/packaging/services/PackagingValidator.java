package com.c2psi.bmv1.packaging.packaging.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.provider.provider.service.ProviderService;
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

@Service(value = "PackagingValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class PackagingValidator {
    final AppService appService;
    final PointofsaleService posService;
    final ProviderService provService;

    public List<String> validate(PackagingDto packagingDto){
        List<String> errors = new ArrayList<>();
        PointofsaleDto posDto = null;
        ProviderDto provDto = null;
        if(packagingDto == null){
            errors.add("the packagingdto sent can't be null");
        }
        else{
            if(packagingDto.getPackagingPosId() == null){
                errors.add("The id of the pointofsale of the packaging can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(packagingDto.getPackagingPosId())){
                    errors.add("There is no pointofsale in the DB with the id sent");
                }
                else{
                    posDto = posService.getPointofsaleById(packagingDto.getPackagingPosId());
                }
            }

            if(packagingDto.getPackagingProviderId() == null){
                errors.add("The id of the Provider of the packaging can't be null");
            }
            else{
                if(!provService.isProviderExistWith(packagingDto.getPackagingProviderId())){
                    errors.add("There is no provider in the DB with the id sent");
                }
                else{
                    provDto = provService.getProviderById(packagingDto.getPackagingProviderId());
                }
            }

            if(posDto != null && provDto != null){
                if(!posDto.getId().equals(provDto.getProviderPosId())){
                    errors.add("The indicated provider must belong to the same pointofsale as indicated in the packaging");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Packaging packaging){
        List<String> errors = new ArrayList<>();
        if(packaging == null){
            errors.add("The packaging to validate cn't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Packaging>> constraintViolations = validator.validate(packaging);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Packaging> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(packaging));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> packFields = Arrays.stream(Packaging.class.getDeclaredFields()).toList();
        List<Field> packInheritFields = Arrays.stream(Packaging.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, packFields, packInheritFields);
    }

    private List<String> validateStringofBm(Packaging packaging) {
        List<String> errors = new ArrayList<>();

        for(Method method : packaging.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(packaging);
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
