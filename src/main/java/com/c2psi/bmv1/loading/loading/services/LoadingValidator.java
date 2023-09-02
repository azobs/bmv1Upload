package com.c2psi.bmv1.loading.loading.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.LoadingDto;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.UserbmDto;
import com.c2psi.bmv1.loading.loading.models.Loading;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
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

@Service(value = "LoadingValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class LoadingValidator {
    final AppService appService;
    final PointofsaleService posService;
    final UserbmService userbmService;

    public List<String> validate(LoadingDto loadingDto){
        List<String> errors = new ArrayList<>();
        if(loadingDto == null){
            errors.add("The loadingDto to validate can't be null");
        }
        else{
            if(loadingDto.getLoadManagerId() == null){
                errors.add("the managerId of the Loading can't be null");
            }
            else{
                if(!userbmService.isUserbmExistWithId(loadingDto.getLoadManagerId())){
                    errors.add("There is no Userbm in the system with the managerId sent");
                }
                else{
                    if(userbmService.getUserbmById(loadingDto.getLoadManagerId()).getUserState().name().
                            equalsIgnoreCase(UserbmDto.UserStateEnum.CONNECTED.name())){
                        errors.add("The Userbm manager must be connected before register a loading");
                    }
                }
            }

            if(loadingDto.getLoadSalerId() == null){
                errors.add("The salerId of the loading can't be null");
            }
            else{
                if(!userbmService.isUserbmExistWithId(loadingDto.getLoadSalerId())){
                    errors.add("There is no Userbm in the system with the salerId sent");
                }
            }

            if(loadingDto.getLoadPosId() == null){
                errors.add("the posId of the loading can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(loadingDto.getLoadPosId())){
                    errors.add("There is no Pointofsale in the system with the posId sent");
                }
            }

            //if(loadingDto.getLoadDate().isAfter(loadingDto.get))
            if(loadingDto.getLoadDate() == null){
                errors.add("The loadDate can't be null");
            }
            else{
                if(loadingDto.getLoadreturnDate() != null){
                    if(loadingDto.getLoadDate().isAfter(loadingDto.getLoadreturnDate())){
                        errors.add("The loadDate can't be after the return date");
                    }
                }
            }
        }
        return errors;
    }

    public List<String> validate(Loading loading){
        List<String> errors = new ArrayList<>();
        if(loading == null){
            errors.add("The loading to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Loading>> constraintViolations = validator.validate(loading);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Loading> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(loading));

            if(loading.getLoadDate() != null && loading.getLoadreturnDate() != null){
                if(loading.getLoadDate().isAfter(loading.getLoadreturnDate())){
                    errors.add("The loadDate can't be after his corresponding return date");
                }
            }

        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> loadFields = Arrays.stream(Loading.class.getDeclaredFields()).toList();
        List<Field> loadInheritFields = Arrays.stream(Loading.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, loadFields, loadInheritFields);
    }

    private List<String> validateStringofBm(Loading loading) {
        List<String> errors = new ArrayList<>();

        for(Method method : loading.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(loading);
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
