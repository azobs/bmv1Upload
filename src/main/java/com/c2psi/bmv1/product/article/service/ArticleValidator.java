package com.c2psi.bmv1.product.article.service;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.price.baseprice.mappers.BasepriceMapper;
import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import com.c2psi.bmv1.price.baseprice.services.BasepriceService;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.product.pf.mapper.ProductformatedMapper;
import com.c2psi.bmv1.product.pf.models.Productformated;
import com.c2psi.bmv1.product.pf.service.ProductformatedService;
import com.c2psi.bmv1.product.unit.unit.mapper.UnitMapper;
import com.c2psi.bmv1.product.unit.unit.models.Unit;
import com.c2psi.bmv1.product.unit.unit.service.UnitService;
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

@Service(value = "ArticleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ArticleValidator {
    final AppService appService;
    final BasepriceService basepriceService;
    final BasepriceMapper basepriceMapper;
    final ProductformatedService pfService;
    final ProductformatedMapper pfMapper;
    final UnitService unitService;
    final UnitMapper unitMapper;
    final PointofsaleService posService;

    public List<String> validate(ArticleDto artDto) {
        List<String> errors = new ArrayList<>();
        if(artDto == null){
            errors.add("The artDto sent is null");
        }
        else if(artDto.getArtBasepriceId() == null){
            errors.add("The basepriceId of the article can't be null");
        }

        if(artDto.getArtBasepriceId() != null){
            if (!basepriceService.isBasepriceExistWith(artDto.getArtBasepriceId())) {
                errors.add("The baseprice precise by the id don't exist in the DB");
            }
        }

        Productformated pf = null;
        Unit unit = null;
        Baseprice baseprice = null;
        if(artDto.getArtPfId() != null){
            if(!pfService.isProductformatedExistWith(artDto.getArtPfId())){
                errors.add("The product formated precise by its id don't exist in the DB");
            }
            pf = pfMapper.dtoToEntity(pfService.getProductformatedById(artDto.getArtPfId()));
        }

        if(artDto.getArtUnitId() == null){
            errors.add("The selling unit of the article can't be null");
        }
        else {
            if(!unitService.isUnitExistWith(artDto.getArtUnitId())){
                errors.add("The Unit precise by its id don't exist in the DB");
            }
            unit = unitMapper.dtoToEntity(unitService.getUnitById(artDto.getArtUnitId()));
        }

        if(artDto.getArtBasepriceId() == null){
            errors.add("The baseprice of the article can't be null");
        }
        else {
            if(!basepriceService.isBasepriceExistWith(artDto.getArtBasepriceId())){
                errors.add("The Baseprice precise by its id don't exist in the DB");
            }
            baseprice = basepriceMapper.dtoToEntity(basepriceService.getBasepriceById(artDto.getArtBasepriceId()));
        }

        if(artDto.getArtPosId() == null){
            errors.add("The Pointofsale of the article can't be null");
        }
        else{
            if(!posService.isPointofsaleExistWith(artDto.getArtPosId())){
                errors.add("The Pointofsale precise by its id don't exist en the DB");
            }

            if(pf != null){
                if(!pf.getPfProduct().getProdCat().getCatPos().getId().equals(artDto.getArtPosId())){
                    errors.add("The productformated must belong to the same pointofsale precise in the articleDto");
                }
            }

            if(unit != null){
                if(!unit.getUnitPos().getId().equals(artDto.getArtPosId())){
                    errors.add("The unit must belong to the same pointofsale precise in the articleDto");
                }
            }

            if(pf != null && unit != null){
                if(!pf.getPfProduct().getProdCat().getCatPos().getId().equals(unit.getUnitPos().getId())){
                    errors.add("The productformated and the Unit must belong to the same pointofsale");
                }
            }

            if(baseprice != null){
                if(!baseprice.getBpPos().getId().equals(artDto.getArtPosId())){
                    errors.add("The baseprice must belong to the same pointofsale precise in the articleDto");
                }
            }

        }

        return errors;
    }

    public List<String> validate(Article art) {

        List<String> errors = new ArrayList<>();

        if(art == null){
            errors.add("The article to validate can't be null");
        }
        else {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Article>> constraintViolations = validator.validate(art);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Article> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }

            errors.addAll(this.validateStringofBm(art));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> artFields = Arrays.stream(Article.class.getDeclaredFields()).toList();
        List<Field> artInheritFields = Arrays.stream(Article.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, artFields, artInheritFields);
    }

    private List<String> validateStringofBm(Article art) {
        List<String> errors = new ArrayList<>();

        for(Method method : art.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(art);
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
