package com.c2psi.bmv1.loading.loadingdetails.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.loading.loadingdetails.models.Loadingdetails;
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

@Service(value = "LoadingdetailsValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class LoadingdetailsValidator {
    final AppService appService;
    final ArticleService articleService;
    final LoadingService loadingService;

    public List<String> validate(LoadingdetailsDto loadingdetailsDto){
        List<String> errors = new ArrayList<>();
        ArticleDto articleDto = null;
        LoadingDto loadingDto = null;
        if(loadingdetailsDto == null){
            errors.add("The loadingdetails to validate can't be null");
        }
        else{
            if(loadingdetailsDto.getLdArticleId() == null){
                errors.add("The articleId of the loadingdetails can't be null");
            }
            else{
                if(!articleService.isArticleExistWith(loadingdetailsDto.getLdArticleId())){
                    errors.add("There is no article in the DB with the articleId sent in loadingdetailsDto");
                }
                else{
                    articleDto = articleService.getArticleById(loadingdetailsDto.getLdArticleId());
                }
            }

            if(loadingdetailsDto.getLdLoadingId() == null){
                errors.add("The loadingId of the loadingdetails can't be null");
            }
            else{
                if(!loadingService.isLoadingExistWith(loadingdetailsDto.getLdLoadingId())){
                    errors.add("There is no loading in the DB with the loadingId sent in loadingdetailsDto ");
                }
                else {
                    loadingDto = loadingService.getLoadingById(loadingdetailsDto.getLdLoadingId());
                }
            }

            if(articleDto != null && loadingDto != null){
                if(!articleDto.getArtPosId().equals(loadingDto.getLoadPosId())){
                    errors.add("The pointofsale of the article and the one of the loading must be the same in " +
                            "the loadingdetails");
                }
            }

        }

        return errors;
    }

    public List<String> validate(Loadingdetails loadingdetails){
        List<String> errors = new ArrayList<>();
        if(loadingdetails == null){
            errors.add("The loadingdetails to validate can't be null");
        }
        else {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Loadingdetails>> constraintViolations = validator.validate(loadingdetails);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Loadingdetails> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(loadingdetails));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> ldFields = Arrays.stream(Loadingdetails.class.getDeclaredFields()).toList();
        List<Field> ldInheritFields = Arrays.stream(Loadingdetails.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, ldFields, ldInheritFields);
    }

    private List<String> validateStringofBm(Loadingdetails loadingdetails) {
        List<String> errors = new ArrayList<>();

        for(Method method : loadingdetails.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(loadingdetails);
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
