package com.c2psi.bmv1.bmapp.services;

import com.c2psi.bmv1.bmapp.exceptions.InvalidArgumentException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidFilterOperatorException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidSortDirectionException;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.bmapp.enumerations.EntRegimeEnum;
import com.c2psi.bmv1.bmapp.enumerations.UserStateEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service(value = "ConvertServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AppService {

    public Boolean isBlankValueIfNotNull(String value){
        if(value != null){
            if (!StringUtils.hasLength(value)) {
                //log.warn("String {} is empty", value);
                return true;
            } else if (!StringUtils.hasLength(value.trim())) {
                return true;
            }
        }
        return false;
    }

    public Integer convertToInteger(String filterValue){
        try{
            return Integer.valueOf(filterValue);
        }
        catch (NumberFormatException e){
            throw new InvalidArgumentException("L'argument "+filterValue+" ne peut etre convertit en Integer");
        }
    }

    public Double convertToDouble(String filterValue){
        try{
            return Double.valueOf(filterValue);
        }
        catch (NumberFormatException e){
            throw new InvalidArgumentException("L'argument "+filterValue+" ne peut etre convertit en Double");
        }
    }

    public Long convertToLong(String filterValue){
        try{
            return Long.valueOf(filterValue);
        }
        catch (NumberFormatException e){
            throw new InvalidArgumentException("L'argument "+filterValue+" ne peut etre convertit en Long");
        }
    }

    public LocalDate convertToLocaldate(String filterValue){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(filterValue, formatter);
        }
        catch (DateTimeException e){
            throw new InvalidArgumentException("L'argument "+filterValue+" ne peut etre convertit en LocalDate(yyyy-MM-dd)");
        }
    }

    public LocalDateTime convertToLocaldatetime(String filterValue){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try{
            return LocalDateTime.parse(filterValue, formatter);
        }
        catch (DateTimeException e){
            throw new InvalidArgumentException("L'argument "+filterValue+" ne peut etre convertit en LocalDateTime" +
                    "(yyyy-MM-dd HH:mm:ss)");
        }
    }

    public Sort getSortOrders(List<Orderby> sortCriterias){
        List<Sort.Order> orders = new ArrayList<>();
        for(Orderby orderby : sortCriterias){
            switch (orderby.getSortDirection()){
                case ASC:
                    Sort.Order orderAsc = new Sort.Order(Sort.Direction.ASC, orderby.getSortColumn());
                    orders.add(orderAsc);
                    break;
                case DESC:
                    Sort.Order orderDesc = new Sort.Order(Sort.Direction.DESC, orderby.getSortColumn());
                    orders.add(orderDesc);
                    break;
                default:
                    throw new InvalidSortDirectionException("Unexpected direction");
            }
        }
        return Sort.by(orders);
    }

    public List<String> checkColumnList(List<Filter> filterList, List<Orderby> orderbyList, List<Field> classFieldList,
                                        List<Field> inheritClassFieldList){
        List<String> errors = new ArrayList<>();
        /****
         * On va fabriquer la liste des noms des champs correspondants au field passe en argument
          */
        List<String> fieldNameList = new ArrayList<>();

        for(Field field : classFieldList){
            fieldNameList.add(field.getName());
        }

        for(Field field : inheritClassFieldList){
            fieldNameList.add(field.getName());
        }

        //log.info("list of different possible field {}", fieldNameList);

        /***************
         * On verifie que chaque nom de champ present dans la liste filterList existe dans fieldNameList
         * Si c'est le cas la liste errors va rester vide
         */
        if(filterList != null) {
            //log.info("filterList is not null");
            for (Filter filter : filterList) {
                if (!fieldNameList.contains(filter.getFilterColumn())) {
                    errors.add(filter.getFilterColumn());
                }
            }
        }
        //log.info("Error list {}", errors);
        /***************
         * On verifie que chaque nom de champ present dans la liste orderList existe dans fieldNameList
         * Si c'est le cas la liste errors va rester vide
         */
        if(orderbyList != null) {
            //log.info("orderbyList is not null");
            for (Orderby orderby : orderbyList) {
                if (!fieldNameList.contains(orderby.getSortColumn())) {
                    errors.add(orderby.getSortColumn());
                }
            }
        }
        //log.info("Error list {}", errors);
        return errors;
    }

    public List<Order> getOrderList(Root root, CriteriaBuilder criteriaBuilder, List<Orderby> sortCriterias){
        //log.info("We are in getOrderList");
        List<Order> orderList = new ArrayList<>();
        for(Orderby orderby : sortCriterias){
            switch (orderby.getSortDirection()){
                case ASC:
                    var orderAsc =criteriaBuilder.asc(root.get(orderby.getSortColumn()));
                    orderList.add(orderAsc);
                    break;
                case DESC:
                    var orderDesc =criteriaBuilder.desc(root.get(orderby.getSortColumn()));
                    orderList.add(orderDesc);
                    break;
                default:
                    throw new InvalidSortDirectionException("Unexpected direction");
            }
        }
        return orderList;
    }

    public Predicate getEqualPredicate(Root root, CriteriaBuilder criteriaBuilder, String fieldType,
                                       String fieldColumnName, String fieldColumnValue){
        //log.info("We are in getEqualPredicate");
        if(fieldType.equals("java.lang.String")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName), fieldColumnValue);
            return equal;
        }

        if(fieldType.equals("java.lang.Boolean")){
            Boolean boolValue = Boolean.valueOf(fieldColumnValue);
            var equal = criteriaBuilder.equal(root.get(fieldColumnName), boolValue);
            return equal;
        }

        if(fieldType.equals("java.lang.Integer")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToInteger(fieldColumnValue));
            return equal;
        }
//        java.lang.Long
        if(fieldType.equals("java.lang.Long")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToLong(fieldColumnValue));
            return equal;
        }
//        java.lang.Double
        if(fieldType.equals("java.lang.Double")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToDouble(fieldColumnValue));
            return equal;
        }
//        java.time.LocalDate
        if(fieldType.equals("java.time.LocalDate")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToLocaldate(fieldColumnValue));
            return equal;
        }
//        java.time.LocalDateTime
        if(fieldType.equals("java.time.LocalDateTime")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToLocaldatetime(fieldColumnValue));
            return equal;
        }
//        com.c2psi.bmv1.bmapp.enumerations.EntRegimeEnum
        if(fieldType.equals("com.c2psi.bmv1.bmapp.enumerations.EntRegimeEnum")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToEntRegimeEnum(fieldColumnValue));
            return equal;
        }
//        com.c2psi.bmv1.bmapp.enumerations.UserStateEnum
        if(fieldType.equals("com.c2psi.bmv1.bmapp.enumerations.UserStateEnum")){
            var equal = criteriaBuilder.equal(root.get(fieldColumnName),
                    convertToUserStateEnum(fieldColumnValue));
            return equal;
        }
        return null;
    }

    public Predicate getBetweenPredicate(Root root, CriteriaBuilder criteriaBuilder, String fieldType,
                                  String fieldColumnName, String fieldColumnValue){

        String[] args = fieldColumnValue.split("|");

        if(args.length != 2){
            throw new InvalidFilterOperatorException("The filter value for Between operator need exactly " +
                    "02 arguments separate by the sign | but here we have "+args.length+" arguments");
        }

        if(fieldType.equals("java.lang.String")){
            var between = criteriaBuilder.between(root.get(fieldColumnName), args[0], args[1]);
            return between;
        }

        if(fieldType.equals("java.lang.Integer")){
            var between = criteriaBuilder.between(root.get(fieldColumnName), convertToInteger(args[0]),
                    convertToInteger(args[1]));
            return between;
        }
//        java.lang.Long
        if(fieldType.equals("java.lang.Long")){
            var between = criteriaBuilder.between(root.get(fieldColumnName), convertToLong(args[0]),
                    convertToLong(args[1]));
            return between;
        }
//        java.lang.Double
        if(fieldType.equals("java.lang.Double")){
            var between = criteriaBuilder.between(root.get(fieldColumnName), convertToDouble(args[0]),
                    convertToDouble(args[1]));
            return between;
        }
//        java.time.LocalDate
        if(fieldType.equals("java.time.LocalDate")){
            var between = criteriaBuilder.between(root.get(fieldColumnName), convertToLocaldate(args[0]),
                    convertToLocaldate(args[1]));
            return between;
        }
//        java.time.LocalDateTime
        if(fieldType.equals("java.time.LocalDateTime")){
            var between = criteriaBuilder.between(root.get(fieldColumnName),
                    convertToLocaldatetime(args[0]), convertToLocaldatetime(args[1]));
            return between;
        }

        return null;
    }

    EntRegimeEnum convertToEntRegimeEnum(String filterValue){
        return switch (EntRegimeEnum.valueOf(filterValue)) {
            case GRP -> EntRegimeEnum.GRP;
            case SARL -> EntRegimeEnum.SARL;
            case SI -> EntRegimeEnum.SI;
            case SA -> EntRegimeEnum.SA;
            case IL -> EntRegimeEnum.IL;
        };
    }

    UserStateEnum convertToUserStateEnum(String filterValue){
        return switch (UserStateEnum.valueOf(filterValue)) {
            case Activated -> UserStateEnum.Activated;
            case Deactivated -> UserStateEnum.Deactivated;
            case Connected -> UserStateEnum.Connected;
            case Disconnected -> UserStateEnum.Disconnected;
        };
    }


}
