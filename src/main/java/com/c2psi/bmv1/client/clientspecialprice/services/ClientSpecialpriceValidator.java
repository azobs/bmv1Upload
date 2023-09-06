package com.c2psi.bmv1.client.clientspecialprice.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.client.clientspecialprice.models.ClientSpecialprice;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.price.baseprice.services.BasepriceService;
import com.c2psi.bmv1.price.specialprice.services.SpecialpriceService;
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

@Service(value = "ClientSpecialpriceValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ClientSpecialpriceValidator {
    final AppService appService;
    final ClientService clientService;
    final SpecialpriceService specialpriceService;
    final BasepriceService basepriceService;
    final ArticleService articleService;

    public List<String> validate(ClientSpecialpriceDto clientSpecialpriceDto){
        List<String> errors = new ArrayList<>();
        ClientDto clientDto = null;
        SpecialpriceDto specialpriceDto = null;
        BasepriceDto basepriceDto = null;
        ArticleDto articleDto = null;
        if(clientSpecialpriceDto == null){
            errors.add("The clientspecialpriceDto to validate cna't be null");
        }
        else{
            if(clientSpecialpriceDto.getClientId() == null){
                errors.add("The clientId in the clientspecialpriceDto can't be null");
            }
            else{
                if(!clientService.isClientExistWith(clientSpecialpriceDto.getClientId())){
                    errors.add("There is no client in the DB with the id sent in the clientspecialpriceDto");
                }
                else{
                    clientDto = clientService.getClientById(clientSpecialpriceDto.getClientId());
                }
            }

            if(clientSpecialpriceDto.getSpecialpriceId() == null){
                errors.add("The specialpriceId in the clientspecialpriceDto can't be null");
            }
            else{
                if(!specialpriceService.isSpecialpriceExistWith(clientSpecialpriceDto.getSpecialpriceId())){
                    errors.add("There is no specialprice in the DB with the id sent in the clientspecialpriceDto");
                }
                else{
                    specialpriceDto = specialpriceService.getSpecialpriceById(clientSpecialpriceDto.getSpecialpriceId());
                    if(specialpriceDto != null) {
                        basepriceDto = basepriceService.getBasepriceById(specialpriceDto.getSpBasepriceId());
                    }
                }
            }

            if(clientSpecialpriceDto.getArticleId() == null){
                errors.add("The articleId in the clientspecialpriceDto can't be null");
            }
            else{
                if(!articleService.isArticleExistWith(clientSpecialpriceDto.getArticleId())){
                    errors.add("There is no article in the DB with the id sent in the clientspecialpriceDto");
                }
                else{
                    articleDto = articleService.getArticleById(clientSpecialpriceDto.getArticleId());
                }
            }

            if(clientDto != null && basepriceDto != null && articleDto != null){
                if(!clientDto.getClientPosId().equals(basepriceDto.getBpPosId())){
                    errors.add("The client and the specialprice must belong to the same pointofsale");
                }
                if(!clientDto.getClientPosId().equals(articleDto.getArtPosId())){
                    errors.add("The client and the article must belong to the same pointofsale");
                }
                if(!basepriceDto.getBpPosId().equals(articleDto.getArtPosId())){
                    errors.add("The baseprice and the article must belong to the same pointofsale");
                }
            }

        }
        return errors;
    }

    public List<String> validate(ClientSpecialprice clientSpecialprice){
        List<String> errors = new ArrayList<>();
        if(clientSpecialprice == null){
            errors.add("The specialprice to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<ClientSpecialprice>> constraintViolations = validator.validate(clientSpecialprice);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<ClientSpecialprice> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(clientSpecialprice));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> clientspecialpriceFields = Arrays.stream(ClientSpecialprice.class.getDeclaredFields()).toList();
        List<Field> clientspecialpriceInheritFields = Arrays.stream(ClientSpecialprice.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, clientspecialpriceFields, clientspecialpriceInheritFields);
    }

    private List<String> validateStringofBm(ClientSpecialprice clientspecialprice) {
        List<String> errors = new ArrayList<>();

        for(Method method : clientspecialprice.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(clientspecialprice);
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
