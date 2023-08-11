package com.c2psi.bmv1.pos.enterprise.draft;


public class Draft {
    /***********************************************************************
     * Methode getEnterpriseSearchSpecification of EnterpriseSpecService
     */
//    public Specification<Enterprise> getEnterpriseSearchSpecification(List<Filter> filterList,
//                                                                      FilterRequest.LogicOperatorEnum operator) {
//
//        List<Orderby> sortCriterias = new ArrayList<>();
//        List<String> errors = enterpriseValidator.validate(filterList, sortCriterias);
//        if(!errors.isEmpty()){
//            log.error("The filterColumn or sortColumn sent don't correspond to the properties of corresponding classes. " +
//                    "Please check the following {}", errors);
//            throw new InvalidColumnNameException("Dans la liste des filtres, certains attributs ne sont pas valides ",
//                    errors);
//        }
//
//        return ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicateList = new ArrayList<>();
//            List<Order> orderList = appService.getOrderList(root, criteriaBuilder, sortCriterias);
//            query.orderBy(orderList);
//            for(Filter filter : filterList){
//                switch (filter.getFilterOperator()){
//                    case EQUAL:
//                        Predicate equal = null;
//                        try {
//                            equal = getEnterpriseEqualPredicate(root, criteriaBuilder, filter);
//                        } catch (NoSuchFieldException e) {
//                            throw new InvalidColumnNameException("l'objet Userbm n'a pas d'attribut "+filter.getFilterColumn());
//                        }
//                        predicateList.add(equal);
//                        break;
//                    case LIKE:
//                        var like = criteriaBuilder.like(root.get(filter.getFilterColumn()), "%"+filter.getFilterValue()+"%");
//                        predicateList.add(like);
//                        break;
//                    case BETWEEN:
//                        Predicate between = null;
//                        try {
//                            between = getBetweenPredicate(root, criteriaBuilder, filter);
//                        } catch (NoSuchFieldException e) {
//                            throw new InvalidColumnNameException("l'objet Userbm n'a pas d'attribut "+filter.getFilterColumn());
//                        }
//                        predicateList.add(between);
//                        break;
//                    default:
//                        throw new InvalidFilterOperatorException("Unexpected operator "+filter.getFilterOperator());
//                }
//            }
//            if(operator == null){
//                return predicateList.get(0);
//            }
//
//            if(operator.equals(FilterRequest.LogicOperatorEnum.AND)) {
//                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
//            } else if(operator.equals(FilterRequest.LogicOperatorEnum.OR)) {
//                return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
//            } else {
//                throw new InvalidFilterOperatorException("Unknow Operator "+operator);
//            }
//        });
//    }
}
