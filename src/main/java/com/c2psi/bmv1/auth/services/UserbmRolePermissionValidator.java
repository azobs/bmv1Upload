package com.c2psi.bmv1.auth.services;

import com.c2psi.bmv1.auth.permission.services.PermissionService;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.UserbmRolePermissionDto;
import com.c2psi.bmv1.auth.models.UserbmRolePermission;
import com.c2psi.bmv1.userbmrole.services.UserbmRoleService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service(value = "UserbmRolePermissionValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class UserbmRolePermissionValidator {
    final AppService appService;
    final UserbmRoleService ubmRoleService;
    final PermissionService permService;


    public List<String> validate(UserbmRolePermissionDto ubmRolePermDto){
        List<String> errors = new ArrayList<>();
        if(ubmRolePermDto == null){
            errors.add("The ubmRolePermDto to validate can't be null");
        }

        /***********************************************************************
         * On doit se rassurer que l'id du UserbmRole identifie vraiment un UserbmRole
         */

        if(ubmRolePermDto.getUserbmroleId()!= null) {
            if (!ubmRoleService.isUserbmRoleExistWithId(ubmRolePermDto.getUserbmroleId())) {
                errors.add("There is no UserbmRole in the system with the userbmroleId sent");
            }
        }
        else{
            errors.add("The userbmRoleId in the UserbmRolePermissionDto can't be null");
        }

        /******************************************************************************
         * On doit se rassurer que l'id du Permission identifie vraiment un Permission
         */
        if(ubmRolePermDto.getPermissionId()!= null) {
            if (!permService.isPermissionExistWithId(ubmRolePermDto.getPermissionId())) {
                errors.add("There is no Permission in the system with the permissionId sent");
            }
        }
        else{
            errors.add("The permissionId in the UserbmRolePermissionDto can't be null");
        }


        return errors;
    }

    public List<String> validate(UserbmRolePermission ubmRolePerm){
        List<String> errors = new ArrayList<>();
        if(ubmRolePerm == null){
            errors.add("The userbmRolePermission to validate can't be null");
        }
        else {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<UserbmRolePermission>> constraintViolations = validator.validate(ubmRolePerm);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<UserbmRolePermission> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> ubmRolePermFields = Arrays.stream(UserbmRolePermission.class.getDeclaredFields()).toList();
        List<Field> ubmRolePermInheritFields = Arrays.stream(UserbmRolePermission.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, ubmRolePermFields, ubmRolePermInheritFields);
    }


}
