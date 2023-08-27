package com.c2psi.bmv1.role.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.RoleDto;
import com.c2psi.bmv1.pos.enterprise.services.EnterpriseService;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.role.models.Role;
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

@Service(value = "RoleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class RoleValidator {
    final AppService appService;
    final PointofsaleService posService;
    final EnterpriseService entService;

    public List<String> validate(RoleDto roleDto){
        List<String> errors = new ArrayList<>();
        if(roleDto == null){
            errors.add("The roleDto to validate can't be null");
        }

        if(roleDto.getRoleType() == null){
            errors.add("The roleType can't be null");
        } else if (roleDto.getRoleType() != RoleDto.RoleTypeEnum.ADMINBM) {
            if(roleDto.getRoleType() == RoleDto.RoleTypeEnum.ADMINENTERPRISE){
                if(roleDto.getRoleEntId() == null){
                    errors.add("A role of that type must belong to an enterprise.");
                }
            }
            else{
                if(roleDto.getRolePosId() == null || roleDto.getRoleEntId() == null){
                    errors.add("A role of that type must belong either to a pointofsale or to an enterprise. Both can't be null");
                }
            }
        }


        if(roleDto.getRolePosId() == null || roleDto.getRoleEntId() == null){
            boolean posValid = true;
            if(roleDto.getRolePosId() != null) {
                if(!posService.isPointofsaleExistWith(roleDto.getRolePosId())){
                    errors.add("There is no Pointofsale in the DB with the rolePosId sent");
                    posValid = false;
                }
            }

            boolean entValid = true;
            if(roleDto.getRoleEntId() != null){
                if(!entService.isEnterpriseExistWith(roleDto.getRoleEntId())){
                    errors.add("There is no Enterprise in the DB with the roleEntId sent");
                    entValid = false;
                }
            }

            if(roleDto.getRolePosId() != null && roleDto.getRoleEntId() != null) {
//                if (entValid && posValid) {
//                    /*****************************************
//                     * On doit se rassurer que le pos indique par son id est bel et bien un pos de l'entreprise indique
//                     */
//                    PointofsaleDto posDto = posService.getPointofsaleById(roleDto.getRolePosId());
//                    if (posDto.getPosEnterpriseId().longValue() != roleDto.getRoleEntId().longValue()) {
//                        errors.add("The posId sent don't identify a Pointofsale that belong to the enterprise " +
//                                "indicated by the entId");
//                    }
//                }
                errors.add("A role must belong either to a pointofsale or to an enterprise. Not to both");
            }
        }
        return errors;
    }

    public List<String> validate(Role role){
        List<String> errors = new ArrayList<>();
        if(role == null){
            errors.add("The role to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<Role> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }

        errors.addAll(this.validateStringofBm(role));

        return errors;
    }

    private List<String> validateStringofBm(Role role) {
        List<String> errors = new ArrayList<>();
        /******************************************************************
         * On va regarder toutes les methodes qui ont l'annotation que nous
         * avons definit et valider la valeur que renvoit ces methodes apres
         * invocation selon nos regles
         */
        for(Method method : role.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(role);
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
        List<Field> roleFields = Arrays.stream(Role.class.getDeclaredFields()).toList();
        List<Field> roleInheritFields = Arrays.stream(Role.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, roleFields, roleInheritFields);
    }

}
