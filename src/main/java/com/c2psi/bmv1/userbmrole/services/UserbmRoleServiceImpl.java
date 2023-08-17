package com.c2psi.bmv1.userbmrole.services;

import com.c2psi.bmv1.bmapp.exceptions.DuplicateEntityException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidEntityException;
import com.c2psi.bmv1.bmapp.exceptions.ModelNotFoundException;
import com.c2psi.bmv1.bmapp.exceptions.NullValueException;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUserbmRoleDto;
import com.c2psi.bmv1.dto.UserbmRoleDto;
import com.c2psi.bmv1.role.dao.RoleDao;
import com.c2psi.bmv1.userbm.dao.UserbmDao;
import com.c2psi.bmv1.userbmrole.dao.UserbmRoleDao;
import com.c2psi.bmv1.userbmrole.errorCode.ErrorCode;
import com.c2psi.bmv1.userbmrole.mappers.UserbmRoleMapper;
import com.c2psi.bmv1.userbmrole.models.UserbmRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "UserbmRoleServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserbmRoleServiceImpl implements UserbmRoleService{

    final UserbmRoleDao userbmRoleDao;
    final UserbmRoleMapper userbmRoleMapper;
    final UserbmRoleValidator userbmRoleValidator;
    final AppService appService;
    final UserbmDao userbmDao;
    final RoleDao roleDao;


    @Override
    public UserbmRoleDto saveUserbmRole(UserbmRoleDto userbmroleDto) {
        if(userbmroleDto == null){
            throw new NullValueException("Le userbmroleDto envoye est null");
        }

        /********************************************************
         * On valide les parametres saisis grace au validateur
         */

        List<String> errorsDto = userbmRoleValidator.validate(userbmroleDto);
        if(!errorsDto.isEmpty()){
            log.error("Dto UserbmRole not valid because of {}", errorsDto);
            throw new InvalidEntityException("Les entites lies sont inexistantes en BD ", errorsDto,
                    ErrorCode.USERBMROLE_NOT_VALID.name());
        }

        List<String> errors = userbmRoleValidator.validate(userbmRoleMapper.dtoToEntity(userbmroleDto));
        if(!errors.isEmpty()){
            log.error("Entity UserbmRole not valid because of {}", errors);
            throw new InvalidEntityException("Le userbmrole a enregistrer n'est pas valide ", errors,
                    ErrorCode.USERBMROLE_NOT_VALID.name());
        }

        /**********************************************************
         * On se rassure que les contraintes d'unicite ne seront
         * pas violees
         */
        if(!isUserbmRoleUsable(userbmroleDto.getUserbmId(), userbmroleDto.getRoleId())){
            throw new DuplicateEntityException("Le role indique a deja ete attribue au userbm indique ",
                    ErrorCode.USERBMROLE_DUPLICATED.name());
        }


        /**********************************************************
         * Si tout est bon on effectue l'enregistrement
         */
        log.info("After all verification, the userbmrole can be saved safely");
        UserbmRole userbmRoleToSaved = UserbmRole.builder()
                .userbm(userbmDao.findUserbmById(userbmroleDto.getUserbmId()).get())
                .role(roleDao.findRoleById(userbmroleDto.getRoleId()).get())
                .build();

        UserbmRole userbmRoleSaved = userbmRoleDao.save(userbmRoleToSaved);
        return userbmRoleMapper.entityToDto(userbmRoleSaved);
    }

    Boolean isUserbmRoleUsable(Long userbmId, Long roleId){
        Optional<UserbmRole> optionalUserbmRole = userbmRoleDao.findUserbmRoleByUserbmAndRole(userbmId, roleId);
        return optionalUserbmRole.isEmpty();
    }

    @Override
    public UserbmRoleDto updateUserbmRole(UserbmRoleDto userbmroleDto) {
        if(userbmroleDto == null){
            throw new NullValueException("Le userbmroleDto envoye est null");
        }

        /********************************************************
         * On valide les parametres saisis grace au validateur
         */
        List<String> errorsDto = userbmRoleValidator.validate(userbmroleDto);
        if(!errorsDto.isEmpty()){
            log.error("Dto UserbmRole not valid because of {}", errorsDto);
            throw new InvalidEntityException("Les entites lies sont inexistantes en BD ", errorsDto,
                    ErrorCode.USERBMROLE_NOT_VALID.name());
        }

        /********************************************************
         * On valide l'entity associe a ce dto grace au validateur
         */
        List<String> errors = userbmRoleValidator.validate(userbmRoleMapper.dtoToEntity(userbmroleDto));
        if(!errors.isEmpty()){
            log.error("Entity UserbmRole not valid because of {}", errors);
            throw new InvalidEntityException("Le userbmrole a enregistrer n'est pas valide ", errors,
                    ErrorCode.USERBMROLE_NOT_VALID.name());
        }

        /********************************************************
         * On recupere l'entity a modifier
         */
        if(userbmroleDto.getId() == null){
            throw new NullValueException("L'id du UserbmRole a update ne peut etre null");
        }

        Optional<UserbmRole> optionalUserbmRole = userbmRoleDao.findUserbmRoleById(userbmroleDto.getId());
        if(!optionalUserbmRole.isPresent()){
            throw new ModelNotFoundException("L'id du UserbmRole envoye n'identifie aucun UserbmRole dans le systeme. " +
                    "Donc le UserbmRole n'est pas valide",
                    ErrorCode.USERBMROLE_NOT_VALID.name());
        }
        UserbmRole userbmRoleToUpdate = optionalUserbmRole.get();

        /**********************************************************
         * On se rassure que les contraintes d'unicite ne seront
         * pas violees
         */
        if(userbmroleDto.getUserbmId() != null && userbmroleDto.getRoleId() != null){
            if(userbmRoleToUpdate.getUserbm().getId().longValue() != userbmroleDto.getUserbmId().longValue() ||
            userbmRoleToUpdate.getRole().getId().longValue() != userbmroleDto.getRoleId().longValue()){
                if(!isUserbmRoleUsable(userbmroleDto.getUserbmId(), userbmroleDto.getRoleId())){
                    throw new DuplicateEntityException("Le role indique a deja ete attribue au userbm indique ",
                            ErrorCode.USERBMROLE_DUPLICATED.name());
                }
            }
        }

        /**********************************************************
         * Si tout est bon on effectue l'enregistrement
         */
        log.info("After all verification, the userbmrole can be updated safely");
        UserbmRole userbmRoleUpdated = userbmRoleDao.save(userbmRoleToUpdate);
        return userbmRoleMapper.entityToDto(userbmRoleUpdated);
    }

    @Override
    public Boolean deleteUserbmRoleById(Long id) {
        if(id == null) {
            throw new NullValueException("The id of the UserbmRole to delete can be null");
        }




        return null;
    }

    @Override
    public UserbmRoleDto getUserbmRoleById(Long id) {
        return null;
    }

    @Override
    public List<UserbmRoleDto> getListofUserbmRole(FilterRequest filterRequest) {
        return null;
    }

    @Override
    public PageofUserbmRoleDto getPageofUserbmRole(FilterRequest filterRequest) {
        return null;
    }

    @Override
    public Boolean isUserbmRoleExistWithId(Long userbmRoleId) {
        if(userbmRoleId == null){
            throw new NullValueException("Le userbmroleId envoye est null");
        }
        Optional<UserbmRole> optionalUserbmRole = userbmRoleDao.findUserbmRoleById(userbmRoleId);
        return optionalUserbmRole.isPresent();
    }
}
