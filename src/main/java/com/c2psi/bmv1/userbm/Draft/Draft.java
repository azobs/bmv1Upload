package com.c2psi.bmv1.userbm.Draft;


public class Draft {
    /**********************************************
     * Methode de UserbmSpecService
     * @param filterList
     * @param operator
     * @return
     */
//    public Specification<Userbm> getUserbmSearchSpecification(List<Filter> filterList,
//                                                              FilterRequest.LogicOperatorEnum operator) {
//        List<Orderby> sortCriterias = new ArrayList<>();
//        List<String> errors = userbmValidator.validate(filterList, sortCriterias);
//        if(!errors.isEmpty()){
//            log.error("The filterColumn or sortColumn sent don't correspond to the properties of corresponding classes. " +
//                    "Please check the following {}", errors);
//            throw new InvalidColumnNameException("Dans la liste des filtres, certains attributs ne sont pas valides ",
//                    errors);
//        }
//
//        return ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicateList = new ArrayList<>();
//            List<Order> orderList = new ArrayList<>();
//            for(Orderby orderby : sortCriterias){
//                switch (orderby.getSortDirection()){
//                    case ASC:
//                        var orderAsc =criteriaBuilder.asc(root.get(orderby.getSortColumn()));
//                        orderList.add(orderAsc);
//                        break;
//                    case DESC:
//                        var orderDesc =criteriaBuilder.desc(root.get(orderby.getSortColumn()));
//                        orderList.add(orderDesc);
//                        break;
//                    default:
//                        throw new InvalidSortDirectionException("Unexpected direction");
//                }
//            }
//            query.orderBy(orderList);
//            for(Filter filter : filterList){
//                switch (filter.getFilterOperator()){
//                    case EQUAL:
//                        Predicate equal = null;
//                        try {
//                            equal = getEqualPredicate(root, criteriaBuilder, filter);
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
//
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
//
//    }


    /**********************************
     * Methode de la classe UserbmSpecService
     * @param root
     * @param criteriaBuilder
     * @param filter
     * @return
     * @throws NoSuchFieldException
     */
//    Predicate getBetweenPredicate(Root<Userbm> root, CriteriaBuilder criteriaBuilder, Filter filter)
//            throws NoSuchFieldException {
//        Field field = getFieldType(filter.getFilterColumn());
//        var fieldType = field.getType().getName();
//        String[] args = filter.getFilterValue().split("|");
//
//        if(args.length != 2){
//            throw new InvalidFilterOperatorException("The filter value for Between operator need exactly " +
//                    "02 arguments separate by the sign | but here we have "+args.length+" arguments");
//        }
//
//        if(fieldType.equals("java.lang.String")){
//            var between = criteriaBuilder.between(root.get(filter.getFilterColumn()), args[0], args[1]);
//            return between;
//        }
//
//        if(fieldType.equals("java.lang.Integer")){
//            var between = criteriaBuilder.between(root.get(filter.getFilterColumn()), appService.convertToInteger(args[0]),
//                    appService.convertToInteger(args[1]));
//            return between;
//        }
////        java.lang.Long
//        if(fieldType.equals("java.lang.Long")){
//            var between = criteriaBuilder.between(root.get(filter.getFilterColumn()), appService.convertToLong(args[0]),
//                    appService.convertToLong(args[1]));
//            return between;
//        }
////        java.lang.Double
//        if(fieldType.equals("java.lang.Double")){
//            var between = criteriaBuilder.between(root.get(filter.getFilterColumn()), appService.convertToDouble(args[0]),
//                    appService.convertToDouble(args[1]));
//            return between;
//        }
////        java.time.LocalDate
//        if(fieldType.equals("java.time.LocalDate")){
//            var between = criteriaBuilder.between(root.get(filter.getFilterColumn()), appService.convertToLocaldate(args[0]),
//                    appService.convertToLocaldate(args[1]));
//            return between;
//        }
////        java.time.LocalDateTime
//        if(fieldType.equals("java.time.LocalDateTime")){
//            var between = criteriaBuilder.between(root.get(filter.getFilterColumn()),
//                    appService.convertToLocaldatetime(args[0]), appService.convertToLocaldatetime(args[1]));
//            return between;
//        }
//
//        return null;
//    }


    /****************************************
     * Methode la classe UserbmSpecService
     * @param root
     * @param criteriaBuilder
     * @param filter
     * @return
     * @throws NoSuchFieldException
     */
//    Predicate getEqualPredicate(Root<Userbm> root, CriteriaBuilder criteriaBuilder, Filter filter)
//            throws NoSuchFieldException {
//        Field field = getFieldType(filter.getFilterColumn());
//        var fieldType = field.getType().getName();
//        if(fieldType.equals("java.lang.String")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()), filter.getFilterValue());
//            return equal;
//        }
//
//        if(fieldType.equals("java.lang.Boolean")){
//            Boolean boolValue = Boolean.valueOf(filter.getFilterValue());
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()), boolValue);
//            return equal;
//        }
//
//        if(fieldType.equals("java.lang.Integer")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()),
//                    appService.convertToInteger(filter.getFilterValue()));
//            return equal;
//        }
////        java.lang.Long
//        if(fieldType.equals("java.lang.Long")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()),
//                    appService.convertToLong(filter.getFilterValue()));
//            return equal;
//        }
////        java.lang.Double
//        if(fieldType.equals("java.lang.Double")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()),
//                    appService.convertToDouble(filter.getFilterValue()));
//            return equal;
//        }
////        java.time.LocalDate
//        if(fieldType.equals("java.time.LocalDate")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()),
//                    appService.convertToLocaldate(filter.getFilterValue()));
//            return equal;
//        }
////        java.time.LocalDateTime
//        if(fieldType.equals("java.time.LocalDateTime")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()),
//                    appService.convertToLocaldatetime(filter.getFilterValue()));
//            return equal;
//        }
////        com.c2psi.bmv1.bmapp.enumerations.UserStateEnum
//        if(fieldType.equals("com.c2psi.bmv1.bmapp.enumerations.UserStateEnum")){
//            var equal = criteriaBuilder.equal(root.get(filter.getFilterColumn()),
//                    convertToUserStateEnum(filter.getFilterValue()));
//            return equal;
//        }
//        return null;
//    }


}
