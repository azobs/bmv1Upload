package com.c2psi.bmv1.auth.services;

import com.c2psi.bmv1.auth.models.ExtendedUser;
import com.c2psi.bmv1.auth.permission.services.PermissionService;
import com.c2psi.bmv1.bmapp.enumerations.RoleTypeEnum;
import com.c2psi.bmv1.bmapp.exceptions.EnumNonConvertibleException;
import com.c2psi.bmv1.bmapp.exceptions.NullValueException;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.role.services.RoleService;
import com.c2psi.bmv1.userbm.mappers.UserbmMapper;
import com.c2psi.bmv1.userbm.services.UserbmService;
import com.c2psi.bmv1.userbmrole.services.UserbmRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "LoadUserbmServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class LoadUserbmService implements UserDetailsService {
    final UserbmService userbmService;
    final UserbmRoleService userbmRoleService;
    final UserbmRolePermissionService ubmRolePermService;
    final RoleService roleService;
    final PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("LoadUserbmbyUsername: start execution");
        UserbmDto userbmDto = userbmService.loadUserbmByUsername(username);

        List<String> roleAndPermissionListofUserString = new ArrayList<>();

        /********************************************************************
                  * Avec le UserDto il faut faire la liste de ses roles
                  */
        List<UserbmRoleDto> userbmRoleDtoList = userbmRoleService.getRoleListofUserbm(userbmDto.getId());

        /**********************************************************************************************
         * Pour chaque role dans la liste des role du User, il faut recuperer ses permissions. Mais
         * avant il faut ajouter a la liste des roles a la liste des rolesString .
         */
        for(UserbmRoleDto userbmRoleDto : userbmRoleDtoList){
            RoleDto roleDto = roleService.getRoleById(userbmRoleDto.getRoleId());
            roleAndPermissionListofUserString.add("ROLE_"+convert(roleDto.getRoleType()));
            /***************************************************************
             * IL faut maintenant les permissions associees a ce UserbmRole
             */
            List<UserbmRolePermissionDto> ubmRolePermDtoList = ubmRolePermService.getPermissionofUserbmRole(
                    userbmRoleDto.getId());
            /************************************************************************
             * Chacune de ces permissions doit etre ajoute a la liste des permissions
             */
            for(UserbmRolePermissionDto ubmRolePerm : ubmRolePermDtoList){
                PermissionDto permDto = permissionService.getPermissionById(ubmRolePerm.getPermissionId());
                roleAndPermissionListofUserString.add("ALLOW_TO_"+permDto.getPermissionName());
            }
        }

        ExtendedUser extendedUser = ExtendedUser.builder()
                .userbmId(userbmDto.getId())
                .password(userbmDto.getUserPassword())
                .username(username)
                .roleAndPermissions(roleAndPermissionListofUserString)
                .build();

        log.info("LoadUserbmbyUsername: end  execution with {}", extendedUser);

        return extendedUser;

    }

    String convert(RoleDto.RoleTypeEnum roleTypeEnum){
        switch (roleTypeEnum){
            case ADMINBM -> {
                return RoleTypeEnum.ADMINBM.name();
            }
            case EMPLOYE -> {
                return RoleTypeEnum.EMPLOYE.name();
            }
            case ADMINENTERPRISE -> {
                return RoleTypeEnum.ADMINENTERPRISE.name();
            }
        }
        throw new EnumNonConvertibleException("La valeur envoye ne peut etre convertit en RoleType");
    }
}
