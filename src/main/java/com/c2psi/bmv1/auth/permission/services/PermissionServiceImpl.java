package com.c2psi.bmv1.auth.permission.services;

import com.c2psi.bmv1.auth.permission.dao.PermissionDao;
import com.c2psi.bmv1.auth.permission.mappers.PermissionMapper;
import com.c2psi.bmv1.auth.permission.models.Permission;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.auth.permission.errorCode.ErrorCode;
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

@Service(value = "PermissionServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService{

    final PermissionDao permissionDao;
    final PermissionMapper permissionMapper;
    final PermissionValidator permissionValidator;
    final AppService appService;
    final PermissionSpecService permissionSpecService;

    Boolean isPermissionNameUsable(String permName){
        Optional<Permission> optionalPermission = permissionDao.findPermissionByPermissionName(permName);
        return optionalPermission.isEmpty();
    }

    @Override
    public PermissionDto savePermission(PermissionDto permissionDto) {
        if(permissionDto == null){
            throw new NullValueException("Le permission envoye est null");
        }
        /********************************************************
         * On valide les parametres saisis grace au validateur
         */
        List<String> errors = permissionValidator.validate(permissionMapper.dtoToEntity(permissionDto));
        if(!errors.isEmpty()){
            log.error("Entity Permission not valid because of {}", errors);
            throw new InvalidEntityException("Le permission a enregistrer n'est pas valide ", errors,
                    ErrorCode.PERMISSION_NOT_VALID.name());
        }
        /**********************************************************
         * On se rassure que les contraintes d'unicite ne seront
         * pas violees
         */
        if(!isPermissionNameUsable(permissionDto.getPermissionName())){
            throw new DuplicateEntityException("Une autre permission existe deja avec le meme nom en BD ",
                    ErrorCode.PERMISSION_DUPLICATED.name());
        }
        /**********************************************************
         * Si tout est bon on effectue l'enregistrement
         */
        log.info("After all verification, the permission can be saved safely");
        Permission permissionSaved = permissionDao.save(permissionMapper.dtoToEntity(permissionDto));
        return permissionMapper.entityToDto(permissionSaved);
    }

    @Override
    public PermissionDto updatePermission(PermissionDto permissionDto) {
        if(permissionDto == null){
            throw new NullValueException("Le permissionDto envoye est null");
        }

        /*****************************************************
         * On se rassure que son id n'est pas null
         */
        if(permissionDto.getId() == null){
            throw new NullValueException("L'id du PermissionDto a update ne peut etre null");
        }

        /**************************************************
         * On fait la validation du permissionDto envoye
         */
        List<String> errors = permissionValidator.validate(permissionMapper.dtoToEntity(permissionDto));
        if(!errors.isEmpty()){
            log.error("Entity Permission not valid because of {}", errors);
            throw new InvalidEntityException("Le permission a enregistrer n'est pas valide ", errors,
                    ErrorCode.PERMISSION_NOT_VALID.name());
        }

        /***************************************************************
         * On recherche le permission a update
         */
        Optional<Permission> optionalPermission = permissionDao.findPermissionById(permissionDto.getId());
        if(!optionalPermission.isPresent()){
            throw new ModelNotFoundException("Aucun permission n'existe avec l'id envoye",
                    ErrorCode.PERMISSION_NOT_FOUND.name());
        }
        Permission permissionToUpdate = optionalPermission.get();

        /*************************************************************
         * Si c'est le name qu'on veut modifier, alors on verifie
         * que permissionName ne violera pas la contrainte
         */

        if(!permissionToUpdate.getPermissionName().equals(permissionDto.getPermissionName())){
            if(!isPermissionNameUsable(permissionDto.getPermissionName())){
                throw new DuplicateEntityException("Une permission existe deja en BD avec le meme nom",
                        ErrorCode.PERMISSION_DUPLICATED.name());
            }
            permissionToUpdate.setPermissionName(permissionDto.getPermissionName());
        }

        if(permissionDto.getPermissionDescription() != null){
            permissionToUpdate.setPermissionDescription(permissionDto.getPermissionDescription());
        }

        log.info("After all verification the permission can be updated");
        return permissionMapper.entityToDto(permissionDao.save(permissionToUpdate));
    }

    @Override
    public Boolean deletePermissionById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de la permission a supprimer est null");
        }
        Optional<Permission> optionalPermission = permissionDao.findPermissionById(id);
        if(!optionalPermission.isPresent()){
            throw new ModelNotFoundException("Aucune permission n'existe en BD avec l'id envoye ",
                    ErrorCode.PERMISSION_NOT_FOUND.name());
        }
        if(!isPermissionDeleteable(optionalPermission.get())){
            throw new EntityNotDeleatableException("La permission dont l'id est envoye ne peut etre supprimer sans causer des conflits",
                    ErrorCode.PERMISSION_NOT_DELETEABLE.name());
        }

        permissionDao.delete(optionalPermission.get());

        return true;
    }

    Boolean isPermissionDeleteable(Permission permission){
        return true;
    }

    @Override
    public PermissionDto getPermissionById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de la permission a supprimer est null");
        }
        Optional<Permission> optionalPermission = permissionDao.findPermissionById(id);
        if(!optionalPermission.isPresent()){
            throw new ModelNotFoundException("Aucune permission n'existe en BD avec l'id envoye ",
                    ErrorCode.PERMISSION_NOT_FOUND.name());
        }
        return permissionMapper.entityToDto(optionalPermission.get());
    }

    @Override
    public PermissionDto getPermissionByName(String permName) {
        if(permName == null){
            throw new NullValueException("Le perName envoye est null");
        }
        Optional<Permission> optionalPermission = permissionDao.findPermissionByPermissionName(permName);
        if(!optionalPermission.isPresent()){
            throw new ModelNotFoundException("Il n'existe pas de permission avec le nom envoye ",
                    ErrorCode.PERMISSION_NOT_FOUND.name());
        }
        return permissionMapper.entityToDto(optionalPermission.get());
    }

    @Override
    public List<PermissionDto> getListofPermission(FilterRequest filterRequest) {

        if(filterRequest == null){
            return permissionMapper.entityToDto(permissionDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return permissionMapper.entityToDto(permissionDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return permissionMapper.entityToDto(permissionDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Permission> permissionSpecification = permissionSpecService.getPermissionSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return permissionMapper.entityToDto(permissionDao.findAll(permissionSpecification));
    }

    @Override
    public PageofPermissionDto getPageofPermission(FilterRequest filterRequest) {
        Pagebm pagebm = new Pagebm();
        Page<Permission> permissionPage = null;
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(1);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            permissionPage = permissionDao.findAll(pageable);
            //log.error("dsdsds {}", userbmPage.getTotalElements());
            return getPageofPermissionDto(permissionPage);
        }
        else{
            if(filterRequest.getPage() == null){
                pagebm.setPagenum(0);
                pagebm.setPagesize(1);
                filterRequest.setPage(pagebm);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                permissionPage = permissionDao.findAll(pageable);
                return getPageofPermissionDto(permissionPage);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                permissionPage = permissionDao.findAll(pageable);
                return getPageofPermissionDto(permissionPage);
            }

            if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            Specification<Permission> permissionSpecification = permissionSpecService.getPermissionSpecification(
                    filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            permissionPage = permissionDao.findAll(permissionSpecification, pageable);
            return getPageofPermissionDto(permissionPage);
        }
    }

    @Override
    public Boolean isPermissionExistWithId(Long permId) {
        if(permId == null){
            throw new NullValueException("L'id permId envoye est null");
        }
        Optional<Permission> optionalPermission = permissionDao.findPermissionById(permId);
        return optionalPermission.isPresent();
    }

    PageofPermissionDto getPageofPermissionDto(Page<Permission> permissionPage){
        PageofPermissionDto pageofPermissionDto = new PageofPermissionDto();
        pageofPermissionDto.setContent(permissionMapper.entityToDto(permissionPage.getContent()));
        pageofPermissionDto.setCurrentPage(permissionPage.getNumber());
        pageofPermissionDto.setPageSize(permissionPage.getSize());
        pageofPermissionDto.setTotalElements(permissionPage.getTotalElements());
        pageofPermissionDto.setTotalPages(permissionPage.getTotalPages());

        return pageofPermissionDto;
    }
}
