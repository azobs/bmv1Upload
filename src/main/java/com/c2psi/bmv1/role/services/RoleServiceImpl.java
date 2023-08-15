package com.c2psi.bmv1.role.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.RoleTypeEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.enterprise.mappers.EnterpriseMapper;
import com.c2psi.bmv1.pos.enterprise.services.EnterpriseService;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.role.dao.RoleDao;
import com.c2psi.bmv1.role.mappers.RoleMapper;
import com.c2psi.bmv1.role.errorCode.ErrorCode;
import com.c2psi.bmv1.role.models.Role;
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

@Service(value = "RoleServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    final RoleDao roleDao;
    final RoleMapper roleMapper;
    final RoleValidator roleValidator;
    final AppService appService;
    final RoleSpecService roleSpecService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final EnterpriseService enterpriseService;
    final EnterpriseMapper enterpriseMapper;


    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        if(roleDto == null){
            throw new NullValueException("Le role envoye est null");
        }
        /********************************************************
         * On valide les parametres saisis grace au validateur
         */
        List<String> errors = roleValidator.validate(roleMapper.dtoToEntity(roleDto));
        if(!errors.isEmpty()){
            log.error("Entity Role not valid because of {}", errors);
            throw new InvalidEntityException("Le role a enregistrer n'est pas valide ", errors,
                    ErrorCode.ROLE_NOT_VALID.name());
        }
        /**********************************************************
         * On se rassure que les contraintes d'unicite ne seront
         * pas violees
         */
        if(!isRoleNameUsable(roleDto.getRoleName(), convert(roleDto.getRoleType()))){
            throw new DuplicateEntityException("Un autre role existe deja avec le meme nom et le meme RoleType en BD ",
                    ErrorCode.ROLE_DUPLICATED.name());
        }

        /***********************************************************************
         * On se rassure que l'id du pointofsale du role existe et que cet id
         * identifie vraiment un Pointofsale en BD
         */
        boolean posPrecise = false;
        if(roleDto.getRolePos() != null){
            if(roleDto.getRolePos().getId() == null){
                throw new NullValueException("L'id du pos du role ne peut etre null");
            }
            if(!posService.isPointofsaleExistWith(roleDto.getRolePos().getId())){
                throw new ModelNotFoundException("Aucun Pointofsale n'existe avec l'id envoye. Le role n'est donc pas " +
                        "valide ", ErrorCode.ROLE_NOT_VALID.name());
            }
            posPrecise = true;
        }

        /***********************************************************************
         * On se rassure que l'id de l'entreprise du role existe et que cet id
         * identifie vraiment une entreprise en BD
         */

        if(roleDto.getRoleEnt() != null){
            if(roleDto.getRoleEnt().getId() == null) {
                throw new NullValueException("L'id de l'entreprise precise ne peut etre null");
            }
            if (posPrecise) {
                boolean posValid = false;
                /**********************************************************************************************
                * Alors il faut se rassurer que le pos precise est bel et bien un pos de l'entreprise indique
                */
                List<PointofsaleDto> posListofEnterprise = posService.getPointofsaleList(roleDto.getRoleEnt().getId());
                for(PointofsaleDto posDto : posListofEnterprise){
                    if(posDto.getId().longValue() == roleDto.getRolePos().getId().longValue()){
                        posValid = true;
                    }
                }

                if(!posValid){
                    throw new InvalidEntityException("Le pointofsale indique dans le role n'est pas un pointofsale de " +
                            "l'entreprise indique.", ErrorCode.ROLE_NOT_VALID.name());
                }
            } else {
                /**********************************************************************
                 * Alors on se rassure juste que l'entreprise indique existe en BD
                 */
                if(!enterpriseService.isEnterpriseExistWith(roleDto.getRoleEnt().getId())){
                    throw new ModelNotFoundException("Aucune entreprise n'existe avec l'id envoye. Le role n'est donc pas " +
                            "valide ", ErrorCode.ROLE_NOT_VALID.name());
                }
            }
        }


        /**********************************************************
         * Si tout est bon on effectue l'enregistrement
         */
        log.info("After all verification, the role can be saved safely");
        Role roleSaved = roleDao.save(roleMapper.dtoToEntity(roleDto));
        return roleMapper.entityToDto(roleSaved);
    }

    Boolean isRoleNameUsable(String roleName, RoleTypeEnum roleType){
        Optional<Role> optionalRole = roleDao.findRoleByRoleNameAndRoleType(roleName, roleType);
        return optionalRole.isEmpty();
    }

    RoleTypeEnum convert(RoleDto.RoleTypeEnum roleTypeEnum){
        switch (roleTypeEnum){
            case ADMINBM -> {
                return RoleTypeEnum.ADMINBM;
            }
            case EMPLOYE -> {
                return RoleTypeEnum.EMPLOYE;
            }
            case ADMINENTERPRISE -> {
                return RoleTypeEnum.ADMINENTERPRISE;
            }
        }
        throw new EnumNonConvertibleException("La valeur envoye ne peut etre convertit en RoleType");
    }

    RoleDto.RoleTypeEnum  convert(RoleTypeEnum roleType){
        switch (roleType){
            case ADMINBM -> {
                return RoleDto.RoleTypeEnum.ADMINBM;
            }
            case EMPLOYE -> {
                return RoleDto.RoleTypeEnum.EMPLOYE;
            }
            case ADMINENTERPRISE -> {
                return RoleDto.RoleTypeEnum.ADMINENTERPRISE;
            }
        }
        throw new EnumNonConvertibleException("La valeur envoye ne peut etre convertit en RoleType");
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        if(roleDto == null){
            throw new NullValueException("Le roleDto envoye est null");
        }

        /*****************************************************
         * On se rassure que son id n'est pas null
         */
        if(roleDto.getId() == null){
            throw new NullValueException("L'id du RoleDto a update ne peut etre null");
        }

        /**************************************************
         * On fait la validation du roleDto envoye
         */
        List<String> errors = roleValidator.validate(roleMapper.dtoToEntity(roleDto));
        if(!errors.isEmpty()){
            log.error("Entity Role not valid because of {}", errors);
            throw new InvalidEntityException("Le role a enregistrer n'est pas valide ", errors,
                    ErrorCode.ROLE_NOT_VALID.name());
        }

        /***************************************************************
         * On recherche le role a update
         */
        Optional<Role> optionalRole = roleDao.findRoleById(roleDto.getId());
        if(!optionalRole.isPresent()){
            throw new ModelNotFoundException("Aucun role n'existe avec l'id envoye", ErrorCode.ROLE_NOT_FOUND.name());
        }
        Role roleToUpdate = optionalRole.get();

        /*************************************************************
         * Si c'est le type qu'on veut modifier, alors on verifie
         * que le couple name et type ne violera pas la contrainte
         */

        if(!convert(roleToUpdate.getRoleType()).getValue().equals(roleDto.getRoleType().getValue())){
            if(!isRoleNameUsable(roleDto.getRoleName(), convert(roleDto.getRoleType()))){
                throw new DuplicateEntityException("Un role existe deja en BD avec le meme nom et le meme type",
                        ErrorCode.ROLE_DUPLICATED.name());
            }
            roleToUpdate.setRoleType(convert(roleDto.getRoleType()));
        }

        if(!roleToUpdate.getRoleName().equals(roleDto.getRoleName())){
            if(!isRoleNameUsable(roleDto.getRoleName(), convert(roleDto.getRoleType()))){
                throw new DuplicateEntityException("Un role existe deja en BD avec le meme nom et le meme type",
                        ErrorCode.ROLE_DUPLICATED.name());
            }
            roleToUpdate.setRoleName(roleDto.getRoleName());
        }

        /*********************************************************************
         * Si c'est le Pointofsale du role qu'on veut modifier
         */
        if(roleToUpdate.getRolePos() != null && roleDto.getRolePos() != null){
            if(roleToUpdate.getRolePos().getId() != null && roleDto.getRolePos().getId() != null){
                if(roleToUpdate.getRolePos().getId().longValue() != roleDto.getRolePos().getId().longValue()){
                    if(!posService.isPointofsaleExistWith(roleDto.getRolePos().getId())){
                        throw new ModelNotFoundException("Aucun pos n'existe en BD avec l'id envoye. Le role n'est " +
                                "donc pas valide ", ErrorCode.ROLE_NOT_VALID.name());
                    }
                    Pointofsale newPos = posMapper.dtoToEntity(posService.getPointofsaleById(roleDto.getRolePos().getId()));
                    roleToUpdate.setRolePos(newPos);
                }
            }
        }



        if(roleDto.getRoleDescription() != null){
            roleToUpdate.setRoleDescription(roleDto.getRoleDescription());
        }

        log.info("After all verification the role can be updated");
        return roleMapper.entityToDto(roleDao.save(roleToUpdate));
    }

    @Override
    public Boolean deleteRoleById(Long id) {
        if(id == null){
            throw new NullValueException("The id of the Role to delete is null");
        }

        Optional<Role> optionalRole = roleDao.findRoleById(id);
        if(!optionalRole.isPresent()){
            throw new ModelNotFoundException("Aucun role n'existe avec l'id envoye ",
                    ErrorCode.ROLE_NOT_FOUND.name());
        }

        if(!isRoleDeleteable(optionalRole.get())){
            throw new EntityNotDeleatableException("La suppression de ce role va generer des conflits ",
                    ErrorCode.ROLE_NOT_DELETEABLE.name());
        }

        roleDao.delete(optionalRole.get());

        return true;
    }

    Boolean isRoleDeleteable(Role role){
        return true;
    }

    @Override
    public RoleDto getRoleById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du role recherche est null");
        }
        Optional<Role> optionalRole = roleDao.findRoleById(id);
        if(!optionalRole.isPresent()){
            throw new ModelNotFoundException("Aucun role n'existe avec l'id envoye ", ErrorCode.ROLE_NOT_FOUND.name());
        }

        return roleMapper.entityToDto(optionalRole.get());
    }

    @Override
    public List<RoleDto> getListofRole(FilterRequest filterRequest) {
        if(filterRequest == null){
            return roleMapper.entityToDto(roleDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return roleMapper.entityToDto(roleDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return roleMapper.entityToDto(roleDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Role> roleSpecification = roleSpecService.getRoleSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return roleMapper.entityToDto(roleDao.findAll(roleSpecification));
    }

    @Override
    public PageofRoleDto getPageofRole(FilterRequest filterRequest) {
        Pagebm pagebm = new Pagebm();
        Page<Role> rolePage = null;
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(1);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            rolePage = roleDao.findAll(pageable);
            //log.error("dsdsds {}", userbmPage.getTotalElements());
            return getPageofRoleDto(rolePage);
        }
        else{
            if(filterRequest.getPage() == null){
                pagebm.setPagenum(0);
                pagebm.setPagesize(1);
                filterRequest.setPage(pagebm);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                rolePage = roleDao.findAll(pageable);
                return getPageofRoleDto(rolePage);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                rolePage = roleDao.findAll(pageable);
                return getPageofRoleDto(rolePage);
            }

            if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            Specification<Role> roleSpecification = roleSpecService.getRoleSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            rolePage = roleDao.findAll(roleSpecification, pageable);
            return getPageofRoleDto(rolePage);
        }
    }

    PageofRoleDto getPageofRoleDto(Page<Role> rolePage){
        PageofRoleDto pageofRoleDto = new PageofRoleDto();
        pageofRoleDto.setContent(roleMapper.entityToDto(rolePage.getContent()));
        pageofRoleDto.setCurrentPage(rolePage.getNumber());
        pageofRoleDto.setPageSize(rolePage.getSize());
        pageofRoleDto.setTotalElements(rolePage.getTotalElements());
        pageofRoleDto.setTotalPages(rolePage.getTotalPages());

        return pageofRoleDto;
    }
}
