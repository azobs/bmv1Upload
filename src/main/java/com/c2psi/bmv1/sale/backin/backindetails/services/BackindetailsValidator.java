package com.c2psi.bmv1.sale.backin.backindetails.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.exceptions.NullValueException;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.article.service.ArticleService;
import com.c2psi.bmv1.sale.backin.backin.services.BackinService;
import com.c2psi.bmv1.sale.backin.backindetails.models.Backindetails;
import com.c2psi.bmv1.sale.command.services.CommandService;
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

@Service(value = "BackindetailsValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class BackindetailsValidator {
    final AppService appService;
    final BackinService backinService;
    final ArticleService articleService;
    final CommandService commandService;

    public List<String> validate(BackindetailsDto backindetailsDto){
        List<String> errors = new ArrayList<>();
        ArticleDto articleDto = null;
        BackinDto backinDto = null;
        CommandDto commandDto = null;
        if(backindetailsDto == null){
            errors.add("The backindetailsDto to validate can't be null");
        }
        else{
            if(backindetailsDto.getBidArticleId() == null){
                errors.add("Id of the article associate to the backindetails can't be null");
            }
            else{
                if(!articleService.isArticleExistWith(backindetailsDto.getBidArticleId())){
                    errors.add("There is no Article in the system with the id sent");
                }
                else{
                    articleDto = articleService.getArticleById(backindetailsDto.getBidArticleId());
                }
            }

            if(backindetailsDto.getBidBackinId() == null){
                errors.add("Id of the Backin associate to the backindetails can't be null");
            }
            else{
                if(!backinService.isBackinExistWith(backindetailsDto.getBidBackinId())){
                    errors.add("There is no Backin in the system with the id sent");
                }
                else{
                    backinDto = backinService.getBackinById(backindetailsDto.getBidBackinId());
                    if(backinDto != null) {
                        commandDto = commandService.getCommandById(backinDto.getBiCommandId());
                    }
                }
            }
            if(articleDto != null && commandDto != null){
                if(!articleDto.getArtPosId().equals(commandDto.getCmdPosId())){
                    errors.add("The poinofsale of article must be the same as the one of Backin");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Backindetails backindetails){
        List<String> errors = new ArrayList<>();
        if(backindetails == null){
            throw new NullValueException("The backindetails sent can't be null");
        }
        else {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Backindetails>> constraintViolations = validator.validate(backindetails);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Backindetails> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(backindetails));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> backindetailsFields = Arrays.stream(Backindetails.class.getDeclaredFields()).toList();
        List<Field> backindetailsInheritFields = Arrays.stream(Backindetails.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, backindetailsFields, backindetailsInheritFields);
    }

    private List<String> validateStringofBm(Backindetails backindetails) {
        List<String> errors = new ArrayList<>();

        for(Method method : backindetails.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(backindetails);
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
