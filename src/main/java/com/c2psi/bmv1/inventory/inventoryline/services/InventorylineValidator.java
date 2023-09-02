package com.c2psi.bmv1.inventory.inventoryline.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.inventory.inventory.services.InventoryService;
import com.c2psi.bmv1.inventory.inventoryline.models.Inventoryline;
import com.c2psi.bmv1.product.article.service.ArticleService;
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

@Service(value = "InventorylineValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class InventorylineValidator {
    final AppService appService;
    final InventoryService invService;
    final ArticleService artService;

    public List<String> validate(InventorylineDto invlineDto){
        List<String> errorsDto = new ArrayList<>();
        if(invlineDto == null){
            errorsDto.add("The invlineDto sent to be validated can't be null");
        }
        else{
            ArticleDto articleDto = null;
            InventoryDto inventoryDto = null;
           if(invlineDto.getInvlineArticleId() == null){
               errorsDto.add("The articleId of the inventoryline can't be null");
           }
           else{
               if(!artService.isArticleExistWith(invlineDto.getInvlineArticleId())){
                   errorsDto.add("There is no article in the DB with the id sent");
               }
               else{
                   articleDto = artService.getArticleById(invlineDto.getInvlineArticleId());
               }
           }

           if(invlineDto.getInventoryId() == null){
               errorsDto.add("The inventory of the inventoryline can't be null");
           }
           else {
               if(!invService.isInventoryExistWith(invlineDto.getInventoryId())){
                   errorsDto.add("There is no inventory in the system with the id sent");
               }
               else{
                   inventoryDto = invService.getInventoryById(invlineDto.getInventoryId());
               }
           }

           if(articleDto != null && inventoryDto != null){
               if(!articleDto.getArtPosId().equals(inventoryDto.getInvPosId())){
                   errorsDto.add("The article and the inventory must belong to the same pointofsale");
               }
           }
        }
        return errorsDto;
    }

    public List<String> validate(Inventoryline invline){
        List<String> errors = new ArrayList<>();
        if(invline == null){
            errors.add("The inventoryline sent can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Inventoryline>> constraintViolations = validator.validate(invline);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Inventoryline> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(invline));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> invlineFields = Arrays.stream(Inventoryline.class.getDeclaredFields()).toList();
        List<Field> invlineInheritFields = Arrays.stream(Inventoryline.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, invlineFields, invlineInheritFields);
    }

    private List<String> validateStringofBm(Inventoryline invline) {
        List<String> errors = new ArrayList<>();

        for(Method method : invline.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(invline);
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
