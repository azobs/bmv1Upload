package com.c2psi.bmv1.sale.sale.services;

import com.c2psi.bmv1.sale.sale.models.Sale;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.article.service.ArticleService;
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

@Service(value = "SaleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class SaleValidator {
    final AppService appService;
    final CommandService commandService;
    final ArticleService articleService;

    public List<String> validate(SaleDto saleDto){
        List<String> errors = new ArrayList<>();
        ArticleDto articleDto = null;
        CommandDto commandDto = null;
        if(saleDto == null){
            errors.add("");
        }
        else{
            if(saleDto.getSaleArticleId() == null){
                errors.add("The articleId concerned by the sale can't be null");
            }
            else {
                if(!articleService.isArticleExistWith(saleDto.getSaleArticleId())){
                    errors.add("There is no article in the system with the articleId sent");
                }
                else{
                    articleDto = articleService.getArticleById(saleDto.getSaleArticleId());
                }
            }
            if(saleDto.getSaleCommandId() == null){
                errors.add("The commandId concerned by the sale can't be null");
            }
            else {
                if(!commandService.isCommandExistWith(saleDto.getSaleCommandId())){
                    errors.add("There is no command in the system with the commandId sent");
                }
                else{
                    commandDto = commandService.getCommandById(saleDto.getSaleCommandId());
                }
            }

            if(articleDto != null && commandDto != null){
                if(!articleDto.getArtPosId().equals(commandDto.getCmdPosId())){
                    errors.add("The pointofsale of the articleDto must be the same as the one of command");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Sale sale){
        List<String> errors = new ArrayList<>();
        if(sale == null){
            errors.add("The sale sent to be validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Sale>> constraintViolations = validator.validate(sale);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Sale> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(sale));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> saleFields = Arrays.stream(Sale.class.getDeclaredFields()).toList();
        List<Field> saleInheritFields = Arrays.stream(Sale.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, saleFields, saleInheritFields);
    }

    private List<String> validateStringofBm(Sale sale) {
        List<String> errors = new ArrayList<>();

        for(Method method : sale.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(sale);
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
