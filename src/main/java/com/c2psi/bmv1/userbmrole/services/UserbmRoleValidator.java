package com.c2psi.bmv1.userbmrole.services;

import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.UserbmRoleDto;
import com.c2psi.bmv1.role.services.RoleService;
import com.c2psi.bmv1.userbm.models.Userbm;
import com.c2psi.bmv1.userbm.services.UserbmService;
import com.c2psi.bmv1.userbmrole.models.UserbmRole;
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

@Service(value = "UserbmRoleValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class UserbmRoleValidator {
    final AppService appService;
    final UserbmService userbmService;
    final RoleService roleService;

    public List<String> validate(UserbmRoleDto userbmroleDto){
        List<String> errors = new ArrayList<>();
        if(userbmroleDto == null){
            errors.add("The userbmRoleDto to validate can't be null");
        }

        /***********************************************************************
         * On doit se rassurer que l'id du Userbm identifie vraiment un Userbm
         */
        if(userbmroleDto.getUserbmId() != null) {
            if (!userbmService.isUserbmExistWithId(userbmroleDto.getUserbmId())) {
                errors.add("There is no Userbm in the system with the userbmId sent");
            }
        }
        else{
            errors.add("The userbmId in the UserbmRoleDto can't be null");
        }

        /***********************************************************************
         * On doit se rassurer que l'id du Role identifie vraiment un Role en BD
         */
        if(userbmroleDto.getRoleId() != null) {
            if (!roleService.isRoleExistWithId(userbmroleDto.getRoleId())) {
                errors.add("There is no Role in the system with the roleId sent");
            }
        }
        else{
            errors.add("The roleId in the UserbmRoleDto can't be null");
        }
        return errors;
    }
    public List<String> validate(UserbmRole userbmRole){
        List<String> errors = new ArrayList<>();
        if(userbmRole == null){
            errors.add("The userbmRole to validate can't be null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserbmRole>> constraintViolations = validator.validate(userbmRole);

        if (constraintViolations.size() > 0 ) {
            for (ConstraintViolation<UserbmRole> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                errors.add(contraintes.getMessage());
            }
        }

        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> userbmroleFields = Arrays.stream(UserbmRole.class.getDeclaredFields()).toList();
        List<Field> userbmroleInheritFields = Arrays.stream(Userbm.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, userbmroleFields, userbmroleInheritFields);
    }

}
