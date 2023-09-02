package com.c2psi.bmv1.arrival.supplyinvoice.services;

import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.ProviderDto;
import com.c2psi.bmv1.dto.SupplyinvoiceDto;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
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

@Service(value = "SupplyinvoiceValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class SupplyinvoiceValidator {
    final AppService appService;
    final PointofsaleService posService;
    final ProviderService providerService;

    public List<String> validate(SupplyinvoiceDto supplyinvoiceDto){
        List<String> errors = new ArrayList<>();
        boolean posExist = false;
        if(supplyinvoiceDto == null){
            errors.add("The supplyinvoiceDto can't be null");
        }
        else{
            if(supplyinvoiceDto.getSiPosId() == null){
                errors.add("The pointofsaleId of the supplyinvoice can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(supplyinvoiceDto.getSiPosId())){
                    errors.add("The id of the pointofsale indicated don't identified a pointofsale in the DB");
                }
                else{
                    posExist = true;
                }


            }

            if(supplyinvoiceDto.getSiProviderId() != null){
                if(!providerService.isProviderExistWith(supplyinvoiceDto.getSiProviderId())){
                    errors.add("The providerId indicated don't identified a provider in the DB");
                }
                else if(posExist){
                    ProviderDto providerDto = providerService.getProviderById(supplyinvoiceDto.getSiProviderId());
                    if(!providerDto.getProviderPosId().equals(supplyinvoiceDto.getSiPosId())){
                        errors.add("The indicated provider is not a provider of the pointofsale indicate in the " +
                                "supplyinvoice");
                    }
                }
            }
        }
        return errors;
    }

    public List<String> validate(Supplyinvoice supplyinvoice){
        List<String> errors = new ArrayList<>();
        if(supplyinvoice == null){
            errors.add("The supplyinvoice to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Supplyinvoice>> constraintViolations = validator.validate(supplyinvoice);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Supplyinvoice> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(supplyinvoice));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> siFields = Arrays.stream(Supplyinvoice.class.getDeclaredFields()).toList();
        List<Field> siInheritFields = Arrays.stream(Supplyinvoice.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, siFields, siInheritFields);
    }

    private List<String> validateStringofBm(Supplyinvoice si) {
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
