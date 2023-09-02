package com.c2psi.bmv1.loading.packagingdetails.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.loading.loadingdetails.models.Loadingdetails;
import com.c2psi.bmv1.loading.packagingdetails.models.Packagingdetails;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
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

@Service(value = "PackagingdetailsValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class PackagingdetailsValidator {
    final AppService appService;
    final LoadingService loadingService;
    final PackagingService packagingService;

    public List<String> validate(PackagingdetailsDto packagingdetailsDto){
        List<String> errors = new ArrayList<>();
        LoadingDto loadingDto = null;
        PackagingDto packagingDto = null;
        if(packagingdetailsDto == null){
            errors.add("The packagingdetails to validate can't be null");
        }
        else{
            if(packagingdetailsDto.getPdLoadingId() == null){
                errors.add("The loadingId of the loadingdetails can't be null");
            }
            else{
                if(!loadingService.isLoadingExistWith(packagingdetailsDto.getPdLoadingId())){
                    errors.add("There is no loading in the DB with the loadingId sent in packagingdetailsDto ");
                }
                else {
                    loadingDto = loadingService.getLoadingById(packagingdetailsDto.getPdLoadingId());
                }
            }

            if(packagingdetailsDto.getPdPackagingId() == null){
                errors.add("The packagingId of the packagingdetails can't be null");
            }
            else{
                if(!packagingService.isPackagingExistWith(packagingdetailsDto.getPdPackagingId())){
                    errors.add("There is no Packaging in the DB with the packagingId sent in packagingdetailsDto");
                }
                else{
                    packagingDto = packagingService.getPackagingById(packagingdetailsDto.getPdPackagingId());
                }
            }

            if(loadingDto != null && packagingDto != null){
                if(!loadingDto.getLoadPosId().equals(packagingDto.getPackagingPosId())){
                    errors.add("The pointofsale of the packaging must be the same with the one of the loading");
                }
            }

        }
        return errors;
    }

    public List<String> validate(Packagingdetails packagingdetails){
        List<String> errors = new ArrayList<>();
        if(packagingdetails == null){
            errors.add("The packagingdetails to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Packagingdetails>> constraintViolations = validator.validate(packagingdetails);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Packagingdetails> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(packagingdetails));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> pdFields = Arrays.stream(Packagingdetails.class.getDeclaredFields()).toList();
        List<Field> pdInheritFields = Arrays.stream(Packagingdetails.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, pdFields, pdInheritFields);
    }

    private List<String> validateStringofBm(Packagingdetails packagingdetails) {
        List<String> errors = new ArrayList<>();

        for(Method method : packagingdetails.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(packagingdetails);
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
