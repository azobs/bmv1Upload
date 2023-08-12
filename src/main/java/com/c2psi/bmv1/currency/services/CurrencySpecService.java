package com.c2psi.bmv1.currency.services;

import com.c2psi.bmv1.bmapp.exceptions.InvalidColumnNameException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidFilterOperatorException;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.Orderby;
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

@Service(value = "CurrencySpecServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CurrencySpecService {
    final AppService appService;
    final CurrencyValidator currencyValidator;

    Field getFieldType(String fieldName){
        try {
            return Currency.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new InvalidColumnNameException("Currency Class don't have an attributes with the name "+fieldName);
        }
    }

    Predicate getCurrencyBetweenPredicate(Root<Currency> root, CriteriaBuilder criteriaBuilder, Filter filter) {
        Field field = getFieldType(filter.getFilterColumn());
        var fieldType = field.getType().getName();

        return appService.getBetweenPredicate(root, criteriaBuilder, fieldType, filter.getFilterColumn(),
                filter.getFilterValue());
    }

    Predicate getCurrencyEqualPredicate(Root<Currency> root, CriteriaBuilder criteriaBuilder, Filter filter) {
        //log.info("We are in getCurrencyEqualPredicate");
        Field field = getFieldType(filter.getFilterColumn());
        var fieldType = field.getType().getName();
        //log.info("In getCurrencyEqualPredicate fieldType is {}", fieldType);
        return appService.getEqualPredicate(root, criteriaBuilder, fieldType, filter.getFilterColumn(),
                filter.getFilterValue());
    }

    public Predicate getCurrencyPredicate(Root root, CriteriaBuilder criteriaBuilder,
                                            List<Filter> filterList, FilterRequest.LogicOperatorEnum operator){
        //log.info("We are in getCurrencyPredicate");
        List<Predicate> predicateList = new ArrayList<>();
        for(Filter filter : filterList){
            switch (filter.getFilterOperator()){
                case EQUAL:
                    Predicate equal = getCurrencyEqualPredicate(root, criteriaBuilder, filter);;
                    predicateList.add(equal);
                    break;
                case LIKE:
                    var like = criteriaBuilder.like(root.get(filter.getFilterColumn()), "%"+filter.getFilterValue()+"%");
                    predicateList.add(like);
                    break;
                case BETWEEN:
                    Predicate between = getCurrencyBetweenPredicate(root, criteriaBuilder, filter);
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

    public Specification<Currency> getCurrencySpecification(List<Filter> filterList,
                                                                FilterRequest.LogicOperatorEnum operator,
                                                                List<Orderby> orderbyList) {
        //log.info("We are in getCurrencySpecification ");
        List<Orderby> sortCriterias;

        sortCriterias = orderbyList != null ? orderbyList: new ArrayList<>();

        List<String> errors = currencyValidator.validate(filterList, sortCriterias);

        //log.info("after validate errors are {} ", errors);

        if(!errors.isEmpty()){
            log.error("The filterColumn or sortColumn sent don't correspond to the properties of corresponding classes. " +
                    "Please check the following {}", errors);
            throw new InvalidColumnNameException("Dans la liste des filtres, certains attributs ne sont pas valides ",
                    errors);
        }

        return ((root, query, criteriaBuilder) -> {
            List<Order> orderList = appService.getOrderList(root, criteriaBuilder, sortCriterias);
            //log.info("oderlist is {}", orderList);
            query.orderBy(orderList);
            //log.info("oderlist add to query");
            Predicate predicate = getCurrencyPredicate(root, criteriaBuilder, filterList, operator);
            //log.info("predicate  is {}", predicate);
            return predicate;
        });
    }

}
