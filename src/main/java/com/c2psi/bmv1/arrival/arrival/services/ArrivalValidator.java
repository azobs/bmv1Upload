package com.c2psi.bmv1.arrival.arrival.services;

import com.c2psi.bmv1.arrival.arrival.models.Arrival;
import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import com.c2psi.bmv1.arrival.supplyinvoice.services.SupplyinvoiceService;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
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

@Service(value = "ArrivalValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ArrivalValidator {
    final AppService appService;
    final ArticleService articleService;
    final SupplyinvoiceService siService;

    public List<String> validate(ArrivalDto arrivalDto){
        List<String> errors = new ArrayList<>();
        ArticleDto artDto = null;
        SupplyinvoiceDto siDto = null;
        if(arrivalDto == null){
            errors.add("The arrivalDto sent can't be null");
        }
        else{
            if(arrivalDto.getArrivalType() != null){
                switch (arrivalDto.getArrivalType()){
                    case DIVERS:
                        break;
                    case STANDARD:
                        break;
                    default:
                        errors.add("The arrival type value is not recognized. It must be standard or divers");
                        break;
                }
            }

            if(arrivalDto.getArrivalNature() != null){
                switch (arrivalDto.getArrivalNature()){
                    case COVER:
                        break;
                    case CASH:
                        break;
                    case DAMAGE:
                        break;
                    default:
                        errors.add("The arrival nature value is not recognized. It must be cash, cover or damage");
                }
            }

            if(arrivalDto.getArrivalArticleId() != null){
                if(!articleService.isArticleExistWith(arrivalDto.getArrivalArticleId())){
                    errors.add("There is no article in the system with the precise articleId");
                }
                else{
                    artDto = articleService.getArticleById(arrivalDto.getArrivalArticleId());
                }
            }

            if(arrivalDto.getArrivalInvoiceId() != null){
                if(!siService.isSupplyinvoiceExistWith(arrivalDto.getArrivalInvoiceId())){
                    errors.add("There is no supplyinvoice in the system with the precise supplyinvoiceId");
                }
                else{
                    siDto = siService.getSupplyinvoiceById(arrivalDto.getArrivalInvoiceId());
                }
            }

            if(artDto != null && siDto != null){
                if(artDto.getArtPosId() != null && siDto.getSiPosId() != null) {
                    if (!artDto.getArtPosId().equals(siDto.getSiPosId())) {
                        errors.add("The article and the supplyinvoice indicated in the arrival must belong to the " +
                                "same pointofsale ");
                    }
                }
            }

        }
        return errors;
    }

    public List<String> validate(Arrival arrival){
        List<String> errors = new ArrayList<>();
        if(arrival == null){
            errors.add("The arrival to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Arrival>> constraintViolations = validator.validate(arrival);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Arrival> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(arrival));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> arrivalFields = Arrays.stream(Arrival.class.getDeclaredFields()).toList();
        List<Field> arrivalInheritFields = Arrays.stream(Arrival.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, arrivalFields, arrivalInheritFields);
    }


    private List<String> validateStringofBm(Arrival arrival) {
        List<String> errors = new ArrayList<>();

        for(Method method : arrival.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(arrival);
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
