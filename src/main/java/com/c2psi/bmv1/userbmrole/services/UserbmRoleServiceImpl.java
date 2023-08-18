package com.c2psi.bmv1.userbmrole.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.role.dao.RoleDao;
import com.c2psi.bmv1.role.models.Role;
import com.c2psi.bmv1.userbm.dao.UserbmDao;
import com.c2psi.bmv1.userbmrole.dao.UserbmRoleDao;
import com.c2psi.bmv1.userbmrole.errorCode.ErrorCode;
import com.c2psi.bmv1.userbmrole.mappers.UserbmRoleMapper;
import com.c2psi.bmv1.userbmrole.models.UserbmRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    final UserbmRoleSpecService userbmRoleSpecService;


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

        Optional<UserbmRole>  optionalUserbmRole = userbmRoleDao.findUserbmRoleById(id);
        if(!optionalUserbmRole.isPresent()){
            throw new ModelNotFoundException("Aucun UserbmRole n'existe avec l'id envoye ",
                    ErrorCode.USERBMROLE_NOT_FOUND.name());
        }

        if(!isUserbmRoleDeleteable(optionalUserbmRole.get())){
            throw new EntityNotDeleatableException("La suppression de ce UserbmRole va provoquer des conflits ",
                    ErrorCode.USERBMROLE_NOT_DELETEABLE.name());
        }

        userbmRoleDao.delete(optionalUserbmRole.get());
        return true;
    }

    Boolean isUserbmRoleDeleteable(UserbmRole userbmRole){
        return true;
    }

    @Override
    public UserbmRoleDto getUserbmRoleById(Long id) {
        if(id == null) {
            throw new NullValueException("The id of the UserbmRole to delete can be null");
        }

        Optional<UserbmRole>  optionalUserbmRole = userbmRoleDao.findUserbmRoleById(id);
        if(!optionalUserbmRole.isPresent()){
            throw new ModelNotFoundException("Aucun UserbmRole n'existe avec l'id envoye ",
                    ErrorCode.USERBMROLE_NOT_FOUND.name());
        }
        return userbmRoleMapper.entityToDto(optionalUserbmRole.get());
    }

    @Override
    public List<UserbmRoleDto> getListofUserbmRole(FilterRequest filterRequest) {
        if(filterRequest == null){
            return userbmRoleMapper.entityToDto(userbmRoleDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return userbmRoleMapper.entityToDto(userbmRoleDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return userbmRoleMapper.entityToDto(userbmRoleDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<UserbmRole> userbmRoleSpecification = userbmRoleSpecService.getUserbmRoleSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return userbmRoleMapper.entityToDto(userbmRoleDao.findAll(userbmRoleSpecification));
    }

    @Override
    public PageofUserbmRoleDto getPageofUserbmRole(FilterRequest filterRequest) {
        Pagebm pagebm = new Pagebm();
        Page<UserbmRole> userbmRolePage = null;
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(1);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            userbmRolePage = userbmRoleDao.findAll(pageable);
            //log.error("dsdsds {}", userbmPage.getTotalElements());
            return getPageofUserbmRoleDto(userbmRolePage);
        }
        else{
            if(filterRequest.getPage() == null){
                pagebm.setPagenum(0);
                pagebm.setPagesize(1);
                filterRequest.setPage(pagebm);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                userbmRolePage = userbmRoleDao.findAll(pageable);
                return getPageofUserbmRoleDto(userbmRolePage);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                userbmRolePage = userbmRoleDao.findAll(pageable);
                return getPageofUserbmRoleDto(userbmRolePage);
            }

            if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            Specification<UserbmRole> userbmRoleSpecification = userbmRoleSpecService.getUserbmRoleSpecification(
                    filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            userbmRolePage = userbmRoleDao.findAll(userbmRoleSpecification, pageable);
            return getPageofUserbmRoleDto(userbmRolePage);

        }
    }

    PageofUserbmRoleDto getPageofUserbmRoleDto(Page<UserbmRole> userbmRolePage){
        PageofUserbmRoleDto pageofUserbmRoleDto = new PageofUserbmRoleDto();
        pageofUserbmRoleDto.setContent(userbmRoleMapper.entityToDto(userbmRolePage.getContent()));
        pageofUserbmRoleDto.setCurrentPage(userbmRolePage.getNumber());
        pageofUserbmRoleDto.setPageSize(userbmRolePage.getSize());
        pageofUserbmRoleDto.setTotalElements(userbmRolePage.getTotalElements());
        pageofUserbmRoleDto.setTotalPages(userbmRolePage.getTotalPages());

        return pageofUserbmRoleDto;
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
