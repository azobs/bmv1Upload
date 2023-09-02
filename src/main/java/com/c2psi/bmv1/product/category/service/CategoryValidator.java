package com.c2psi.bmv1.product.category.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.CategoryDto;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.category.dao.CategoryDao;
import com.c2psi.bmv1.product.category.models.Category;
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
import java.util.*;

@Service(value = "CategoryValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class CategoryValidator {
    final AppService appService;
    final PointofsaleService posService;
    final CategoryDao categoryDao;


    public List<String> validate(CategoryDto categoryDto){
        List<String> errors = new ArrayList<>();
        if(categoryDto == null){
            errors.add("The categoryDto to validate can't be null");
        }
        else if(categoryDto.getCatPosId() == null){
            errors.add("The posId of the pointofsale can't be null");
        }
        else{
            if(!posService.isPointofsaleExistWith(categoryDto.getCatPosId())){
                errors.add("There is no Pointofsale in DB with the id sent");
            }
            if(categoryDto.getCatParentId() != null){
                Optional<Category> optionalCategory = categoryDao.findCategoryById(categoryDto.getCatParentId());
                if(!optionalCategory.isPresent()){
                    errors.add("There is no Category in the system with the catParentId sent");
                }
                else if(optionalCategory.get().getCatPos().getId() != categoryDto.getCatPosId()){
                    errors.add("Category sent to be saved must belong to the same pointofsale as the parent precise");
                }
            }
        }
        return errors;
    }

    public List<String> validate(Category category){
        List<String> errors = new ArrayList<>();

        if(category == null){
            errors.add("The category to validate can't be null");
        }
        else {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Category>> constraintViolations = validator.validate(category);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Category> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(category));
        }
        return errors;
    }

    private List<String> validateStringofBm(Category category) {
        List<String> errors = new ArrayList<>();

        for(Method method : category.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(category);
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
        List<Field> catFields = Arrays.stream(Category.class.getDeclaredFields()).toList();
        List<Field> catInheritFields = Arrays.stream(Category.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, catFields, catInheritFields);
    }

}
