package com.c2psi.bmv1.sale.delivery.delivery.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.DeliveryDto;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
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

@Service(value = "DeliveryValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class DeliveryValidator {
    final AppService appService;
    final PointofsaleService posService;
    final UserbmService userbmService;

    public List<String> validate(DeliveryDto deliveryDto){
        List<String> errors = new ArrayList<>();
        boolean posExist = true;
        if(deliveryDto == null){
            errors.add("The deliveryDto can't be null");
        }
        else{
            if(deliveryDto.getDeliveryPosId() == null){
                errors.add("The pointofsaleId of the delivery can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(deliveryDto.getDeliveryPosId())){
                    errors.add("The id of the pointofsale indicated don't identified a pointofsale in the DB");
                }
            }

            if(deliveryDto.getDeliveryDeliverId() != null){
                if(!userbmService.isUserbmExistWithId(deliveryDto.getDeliveryDeliverId())){
                    errors.add("The id of the deliver indicated don't identified a Userbm in the DB");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Delivery delivery){
        List<String> errors = new ArrayList<>();
        if(delivery == null){
            errors.add("The delivery to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Delivery>> constraintViolations = validator.validate(delivery);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Delivery> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(delivery));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> deliveryFields = Arrays.stream(Delivery.class.getDeclaredFields()).toList();
        List<Field> deliveryInheritFields = Arrays.stream(Delivery.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, deliveryFields, deliveryInheritFields);
    }

    private List<String> validateStringofBm(Delivery delivery) {
        List<String> errors = new ArrayList<>();

        for(Method method : delivery.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(delivery);
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
