package com.c2psi.bmv1.product.product.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.ProductDto;
import com.c2psi.bmv1.product.category.service.CategoryService;
import com.c2psi.bmv1.product.product.models.Product;
import com.c2psi.bmv1.product.product.dao.ProductDao;
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

@Service(value = "ProductValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ProductValidator {
    final AppService appService;
    final CategoryService categoryService;
    final ProductDao productDao;

    public List<String> validate(ProductDto prodDto){
        List<String> errors = new ArrayList<>();
        if(prodDto == null){
            errors.add("The prodDto to validate can't be null");
        }
        else if(prodDto.getProdCatId() == null){
            errors.add("The id of the Category owner can't be null");
        }
        else{
            if(prodDto.getProdCatId() != null) {
                if (!categoryService.isCategoryExistWith(prodDto.getProdCatId())) {
                    errors.add("There is no Category in DB with the id sent");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Product product){
        List<String> errors = new ArrayList<>();

        if(product == null){
            errors.add("The product to validate can't be null");
        }
        else {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Product> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(product));
        }
        return errors;
    }

    private List<String> validateStringofBm(Product product) {
        List<String> errors = new ArrayList<>();

        for(Method method : product.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(product);
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
        List<Field> productFields = Arrays.stream(Product.class.getDeclaredFields()).toList();
        List<Field> productInheritFields = Arrays.stream(Product.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, productFields, productInheritFields);
    }

}
