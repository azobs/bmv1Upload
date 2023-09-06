package com.c2psi.bmv1.sale.saleinvoice.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.SaleinvoiceDto;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.sale.saleinvoice.models.Saleinvoice;
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

@Service(value = "SaleinvoiceValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class SaleinvoiceValidator {
    final AppService appService;
    final PointofsaleService posService;

    public List<String> validate(SaleinvoiceDto saleinvoiceDto){
        List<String> errors = new ArrayList<>();
        if(saleinvoiceDto == null){
            errors.add("The saleinvoiceDto can't be null");
        }
        else{
            if(saleinvoiceDto.getSiPosId() == null){
                errors.add("The pointofsaleId of the saleinvoice can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(saleinvoiceDto.getSiPosId())){
                    errors.add("The id of the pointofsale indicated don't identified a pointofsale in the DB");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Saleinvoice saleinvoice){
        List<String> errors = new ArrayList<>();
        if(saleinvoice == null){
            errors.add("The saleinvoice to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Saleinvoice>> constraintViolations = validator.validate(saleinvoice);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Saleinvoice> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(saleinvoice));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> siFields = Arrays.stream(Saleinvoice.class.getDeclaredFields()).toList();
        List<Field> siInheritFields = Arrays.stream(Saleinvoice.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, siFields, siInheritFields);
    }

    private List<String> validateStringofBm(Saleinvoice si) {
        List<String> errors = new ArrayList<>();

        for(Method method : si.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(si);
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
