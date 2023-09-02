package com.c2psi.bmv1.inventory.inventory.services;

import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.InventoryDto;
import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
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

@Service(value = "InventoryValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class InventoryValidator {
    final AppService appService;
    final PointofsaleService posService;

    public List<String> validate(InventoryDto inventoryDto){
        List<String> errors = new ArrayList<>();
        if(inventoryDto == null){
            errors.add("The inventoryDto to validate can't be null");
        }
        else{
            if(inventoryDto.getInvPosId() == null){
                errors.add("The posId of the Inventory can't be null");
            }
            else {
                if(!posService.isPointofsaleExistWith(inventoryDto.getInvPosId())){
                    errors.add("There is no pointofsale in the DB with the precise Id");
                }
            }
        }
        return errors;
    }

    List<String> validate(Inventory inventory){
        List<String> errors = new ArrayList<>();
        if(inventory == null){
            errors.add("The inventory to validate can't be null");
        }
        else {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Inventory>> constraintViolations = validator.validate(inventory);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Inventory> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(inventory));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> invFields = Arrays.stream(Inventory.class.getDeclaredFields()).toList();
        List<Field> invInheritFields = Arrays.stream(Inventory.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, invFields, invInheritFields);
    }

    private List<String> validateStringofBm(Inventory inventory) {
        List<String> errors = new ArrayList<>();

        for(Method method : inventory.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(inventory);
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
