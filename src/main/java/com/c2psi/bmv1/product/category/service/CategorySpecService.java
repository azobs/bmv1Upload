package com.c2psi.bmv1.product.category.service;

import com.c2psi.bmv1.bmapp.exceptions.InvalidColumnNameException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidFilterOperatorException;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.product.category.models.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service(value = "CategorySpecServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategorySpecService {
    final AppService appService;
    final CategoryValidator catValidator;

    Field getFieldType(String fieldName){
        try {
            return Category.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new InvalidColumnNameException("Category Class don't have an attributes with the name "+fieldName);
        }
    }

    Predicate getCategoryBetweenPredicate(Root<Category> root, CriteriaBuilder criteriaBuilder, Filter filter) {
        Field field = getFieldType(filter.getFilterColumn());
        var fieldType = field.getType().getName();

        return appService.getBetweenPredicate(root, criteriaBuilder, fieldType, filter.getFilterColumn(),
                filter.getFilterValue());
    }

    Predicate getCategoryEqualPredicate(Root<Category> root, CriteriaBuilder criteriaBuilder, Filter filter) {
        Field field = getFieldType(filter.getFilterColumn());
        var fieldType = field.getType().getName();

        return appService.getEqualPredicate(root, criteriaBuilder, fieldType, filter.getFilterColumn(),
                filter.getFilterValue());
    }

    public Predicate getCategoryPredicate(Root root, CriteriaBuilder criteriaBuilder,
                                          List<Filter> filterList, FilterRequest.LogicOperatorEnum operator){
        List<Predicate> predicateList = new ArrayList<>();
        for(Filter filter : filterList){
            switch (filter.getFilterOperator()){
                case EQUAL:
                    Predicate equal = getCategoryEqualPredicate(root, criteriaBuilder, filter);;
                    predicateList.add(equal);
                    break;
                case LIKE:
                    var like = criteriaBuilder.like(root.get(filter.getFilterColumn()), "%"+filter.getFilterValue()+"%");
                    predicateList.add(like);
                    break;
                case BETWEEN:
                    Predicate between = getCategoryBetweenPredicate(root, criteriaBuilder, filter);
                    predicateList.add(between);
                    break;
                default:
                    throw new InvalidFilterOperatorException("Unexpected operator "+filter.getFilterOperator());
            }
        }
        if(operator == null){
            return predicateList.get(0);
        }

        if(operator.equals(FilterRequest.LogicOperatorEnum.AND)) {
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        } else if(operator.equals(FilterRequest.LogicOperatorEnum.OR)) {
            return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
        } else {
            throw new InvalidFilterOperatorException("Unknow Operator "+operator);
        }
    }

    public Specification<Category> getCategorySpecification(List<Filter> filterList,
                                                          FilterRequest.LogicOperatorEnum operator,
                                                          List<Orderby> orderbyList) {
        List<Orderby> sortCriterias;

        sortCriterias = orderbyList != null ? orderbyList: new ArrayList<>();

        List<String> errors = catValidator.validate(filterList, sortCriterias);
        if(!errors.isEmpty()){
            log.error("The filterColumn or sortColumn sent don't correspond to the properties of corresponding classes. " +
                    "Please check the following {}", errors);
            throw new InvalidColumnNameException("Dans la liste des filtres, certains attributs ne sont pas valides ",
                    errors);
        }

        return ((root, query, criteriaBuilder) -> {
            List<Order> orderList = appService.getOrderList(root, criteriaBuilder, sortCriterias);
            query.orderBy(orderList);
            Predicate predicate = getCategoryPredicate(root, criteriaBuilder, filterList, operator);
            return predicate;
        });

    }

}
