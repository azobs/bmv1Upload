package com.c2psi.bmv1.product.pf.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.category.service.CategoryService;
import com.c2psi.bmv1.product.format.service.FormatService;
import com.c2psi.bmv1.product.pf.dao.ProductformatedDao;
import com.c2psi.bmv1.product.pf.models.Productformated;
import com.c2psi.bmv1.product.product.service.ProductService;
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

@Service(value = "ProductformatedValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ProductformatedValidator {
    final AppService appService;
    final ProductService productService;
    final FormatService formatService;
    final CategoryService categoryService;
    final ProductformatedDao productformatedDao;

    public List<String> validate(ProductformatedDto pfDto){
        List<String> errors = new ArrayList<>();
        if(pfDto == null){
            errors.add("The formatDto to validate can't be null");
        }
        else{
            if(pfDto.getPfFormatId() == null) {
                errors.add("The formatId of the PF can't be null");
            }
            if(pfDto.getPfProductId() == null){
                errors.add("The productId of the PF can't be null");
            }
        }

        FormatDto formatDto = null;
        ProductDto productDto = null;
        CategoryDto categoryDto = null;

        if(pfDto.getPfFormatId() != null){
            if(!formatService.isFormatExistWith(pfDto.getPfFormatId())){
                errors.add("There is no Format in the DB with the id sent");
            }
            else{
                formatDto = formatService.getFormatById(pfDto.getPfFormatId());
            }
        }

        if(pfDto.getPfProductId() != null){
            if(!productService.isProductExistWith(pfDto.getPfProductId())){
                errors.add("There is no Product in the DB with the id sent");
            }
            else{
                productDto = productService.getProductById(pfDto.getPfProductId());
                categoryDto = categoryService.getCategoryById(productDto.getProdCatId());
            }
        }

        if(formatDto != null && categoryDto != null){
            if(!formatDto.getFormatPosId().equals(categoryDto.getCatPosId())){
                errors.add("The format and the product must belong to the same pointofsale");
            }
        }
        return errors;
    }

    public List<String> validate(Productformated pf){
        List<String> errors = new ArrayList<>();

        if(pf == null){
            errors.add("The productformated to validate can't be null");
        }
        else {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Productformated>> constraintViolations = validator.validate(pf);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Productformated> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(pf));
        }
        return errors;
    }

    private List<String> validateStringofBm(Productformated pf) {
        List<String> errors = new ArrayList<>();

        for(Method method : pf.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(pf);
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

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> pfFields = Arrays.stream(Productformated.class.getDeclaredFields()).toList();
        List<Field> pfInheritFields = Arrays.stream(Productformated.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, pfFields, pfInheritFields);
    }

}
