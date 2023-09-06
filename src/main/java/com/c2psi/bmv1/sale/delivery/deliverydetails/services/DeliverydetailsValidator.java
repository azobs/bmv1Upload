package com.c2psi.bmv1.sale.delivery.deliverydetails.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
import com.c2psi.bmv1.sale.delivery.delivery.services.DeliveryService;
import com.c2psi.bmv1.sale.delivery.deliverydetails.models.Deliverydetails;
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

@Service(value = "DeliverydetailsValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class DeliverydetailsValidator {
    final AppService appService;
    final DeliveryService deliveryService;
    final PackagingService packagingService;

    public List<String> validate(DeliverydetailsDto deliverydetailsDto){
        List<String> errors = new ArrayList<>();
        DeliveryDto deliveryDto = null;
        PackagingDto packagingDto = null;
        if(deliverydetailsDto == null){
            errors.add("The deliverydetailsDto to validate can't be null");
        }
        else{
            if(deliverydetailsDto.getDdDeliveryId() == null){
                errors.add("The deliveryId of the deliverydetails can't be null");
            }
            else{
                if(!deliveryService.isDeliveryExistWith(deliverydetailsDto.getDdDeliveryId())){
                    errors.add("There is no delivery in the system with the id sent in the deliverydetails");
                }
                else{
                    deliveryDto = deliveryService.getDeliveryById(deliverydetailsDto.getDdDeliveryId());
                }
            }

            if(deliverydetailsDto.getDdPackagingId() == null){
                errors.add("The packagingId of the deliverydetails can't be null");
            }
            else{
                if(!packagingService.isPackagingExistWith(deliverydetailsDto.getDdPackagingId())){
                    errors.add("There is no packaging in the systen with the id sent in the deliverydetails");
                }
                else {
                    packagingDto = packagingService.getPackagingById(deliverydetailsDto.getDdPackagingId());
                }
            }

            if(deliveryDto != null && packagingDto != null){
                if(!deliveryDto.getDeliveryPosId().equals(packagingDto.getPackagingPosId())){
                    errors.add("The pointofsale of the delivery must be the same with the one of the packaging");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Deliverydetails deliverydetails){
        List<String> errors = new ArrayList<>();
        if(deliverydetails == null){
            errors.add("The deliverydetails to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Deliverydetails>> constraintViolations = validator.validate(deliverydetails);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Deliverydetails> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(deliverydetails));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> deliverydetailsFields = Arrays.stream(Deliverydetails.class.getDeclaredFields()).toList();
        List<Field> deliverydetailsInheritFields = Arrays.stream(Deliverydetails.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, deliverydetailsFields, deliverydetailsInheritFields);
    }

    private List<String> validateStringofBm(Deliverydetails deliverydetails) {
        List<String> errors = new ArrayList<>();

        for(Method method : deliverydetails.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(deliverydetails);
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
