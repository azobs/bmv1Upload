package com.c2psi.bmv1.userbmrole.services;

import com.c2psi.bmv1.auth.permission.dao.PermissionDao;
import com.c2psi.bmv1.bmapp.exceptions.DuplicateEntityException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidEntityException;
import com.c2psi.bmv1.bmapp.exceptions.NullValueException;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.UserbmRolePermissionDto;
import com.c2psi.bmv1.userbmrole.dao.UserbmRoleDao;
import com.c2psi.bmv1.userbmrole.dao.UserbmRolePermissionDao;
import com.c2psi.bmv1.userbmrole.errorCode.ErrorCode;
import com.c2psi.bmv1.userbmrole.mappers.UserbmRolePermissionMapper;
import com.c2psi.bmv1.userbmrole.models.UserbmRole;
import com.c2psi.bmv1.userbmrole.models.UserbmRolePermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "UserbmRolePermissionServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserbmRolePermissionServiceImpl implements UserbmRolePermissionService{

    final UserbmRolePermissionDao ubmRolePermDao;
    final UserbmRolePermissionMapper ubmRolePermMapper;
    final UserbmRolePermissionValidator ubmRolePermValidator;
    final UserbmRoleDao userbmRoleDao;
    final PermissionDao permissionDao;

    @Override
    public UserbmRolePermissionDto saveUserbmRolePermission(UserbmRolePermissionDto ubmRolePermDto) {
        /*************************************************
         * Le parametre dto ne devrait pas etre null
         */
        if(ubmRolePermDto == null){
            throw new NullValueException("Le ubmRolePermDto envoye est null");
        }

        /*************************************************
         * Le parametre dto doit etre valide
         */
        List<String> errorsDto = ubmRolePermValidator.validate(ubmRolePermDto);
        if(!errorsDto.isEmpty()){
            log.error("Dto UserbmRolePermission not valid because of {}", errorsDto);
            throw new InvalidEntityException("Les entites lies sont inexistantes en BD ", errorsDto,
                    ErrorCode.USERBMROLE_PERMISSION_NOT_VALID.name());
        }

        /**************************************
         * On valide l'entite associe au dto
         */
        List<String> errors = ubmRolePermValidator.validate(ubmRolePermMapper.dtoToEntity(ubmRolePermDto));
        if(!errors.isEmpty()){
            log.error("Entity UserbmRolePermission not valid because of {}", errors);
            throw new InvalidEntityException("Le userbmrole a enregistrer n'est pas valide ", errors,
                    ErrorCode.USERBMROLE_PERMISSION_NOT_VALID.name());
        }

        /******************************************************************
         * On se rassure que les contraintes d'unicite ne seront pas viole
         */

        if(!isUserbmRolePermissionUsable(ubmRolePermDto.getUserbmroleId(), ubmRolePermDto.getPermissionId())){
            throw new DuplicateEntityException("La permission indique a deja ete attribue au UserbmRole ",
                    ErrorCode.USERBMROLE_DUPLICATED.name());
        }


        /**********************************************************
         * Si tout est bon on effectue l'enregistrement
         */
        log.info("After all verification, the userbmrolepermission can be saved safely");
        UserbmRolePermission ubmRolePermToSaved = UserbmRolePermission.builder()
                .userbmRole(userbmRoleDao.findUserbmRoleById(ubmRolePermDto.getUserbmroleId()).get())
                .permission(permissionDao.findPermissionById(ubmRolePermDto.getPermissionId()).get())
                .build();

        UserbmRolePermission userbmRolePermSaved = ubmRolePermDao.save(ubmRolePermToSaved);
        return ubmRolePermMapper.entityToDto(userbmRolePermSaved);
    }

    Boolean isUserbmRolePermissionUsable(Long userbmRoleId, Long permId){
        Optional<UserbmRolePermission> optionalUserbmRolePermission = ubmRolePermDao.
                findByUserbmroleAndPermission(userbmRoleId, permId);
        return optionalUserbmRolePermission.isEmpty();
    }

    @Override
    public Boolean deleteUserbmRolePermissionById(Long id) {
        return null;
    }

    @Override
    public UserbmRolePermissionDto getUserbmRolePermissionById(Long id) {
        return null;
    }

    @Override
    public List<UserbmRolePermissionDto> getListofUserbmRolePermission(FilterRequest filterRequest) {
        return null;
    }
}
