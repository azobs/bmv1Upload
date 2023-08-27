package com.c2psi.bmv1.product.unit.unit.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.unit.unit.errorCode.ErrorCode;
import com.c2psi.bmv1.product.unit.unit.dao.UnitDao;
import com.c2psi.bmv1.product.unit.unit.mapper.UnitMapper;
import com.c2psi.bmv1.product.unit.unit.models.Unit;
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

@Service(value = "UnitServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UnitServiceImpl implements UnitService{
    final AppService appService;
    final UnitDao unitDao;
    final UnitMapper unitMapper;
    final UnitValidator unitValidator;
    final UnitSpecService unitSpecService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;

    @Override
    public UnitDto saveUnit(UnitDto unitDto) {
        /************************************************
         * On se rassure que l'argument n'est pas null
         */
        if(unitDto == null){
            throw new NullValueException("Le UnitDto a enregistre ne peut etre null");
        }
        /******************************
         * Il faut valider le UnitDto
         */
        List<String> errorsDto = unitValidator.validate(unitDto);
        if(!errorsDto.isEmpty()){
            log.error("The UnitDto to save is not valid. The errors encountered are {}", errorsDto);
            throw new InvalidEntityException("Le UnitDto envoye pour enregistrement n'est pas valide ", errorsDto,
                    ErrorCode.UNIT_NOT_VALID.name());
        }
        /*************************************************
         * On valide le Unit
         */
        List<String> errors = unitValidator.validate(unitMapper.dtoToEntity(unitDto));
        if(!errors.isEmpty()){
            log.error("The Unit to save is not valid. The errors encountered are {}", errors);
            throw new InvalidEntityException("Le Unit envoye pour enregistrement n'est pas valide ", errors,
                    ErrorCode.UNIT_NOT_VALID.name());
        }
        /*****************************************************
         * On se rassure de l'unicite du Unit apres insertion
         */
        if(!isUnitFullnameUsable(unitDto.getUnitName(), unitDto.getUnitAbbreviation(), unitDto.getUnitPosId())){
            log.error("The fullname of unit {} {} {} is already used", unitDto.getUnitName(),
                    unitDto.getUnitAbbreviation(), unitDto.getUnitPosId());
            throw new DuplicateEntityException("Les attributs de l'unite envoye correspondent a une unite deja enregistre",
                    ErrorCode.UNIT_DUPLICATED.name());
        }
        /*******************************************************
         * Si tout est bon jusque la on insere la donnees en BD
         */
        Unit unitToSaved = Unit.builder()
                .unitPos(posMapper.dtoToEntity(posService.getPointofsaleById(unitDto.getUnitPosId())))
                .unitAbbreviation(unitDto.getUnitAbbreviation())
                .unitName(unitDto.getUnitName())
                .build();
        Unit unitSaved = unitDao.save(unitToSaved);

        return unitMapper.entityToDto(unitSaved);
    }

    public Boolean isUnitFullnameUsable(String unitName, String unitAbbre, Long posId){
        Optional<Unit> optionalUnit = unitDao.findUnitByAttributes(unitName, unitAbbre, posId);
        return optionalUnit.isEmpty();
    }

    @Override
    public UnitDto updateUnit(UnitDto unitDto) {
        /**********************************************
         * On se rassure que le UnitDto n'est pas null
         */
        if(unitDto == null){
            throw new NullValueException("Le unitDto envoye pour update ne peut etre null");
        }
        /***************************************************
         * On se rassure que l'Id du UnitDto n'est pas null
         */
        if(unitDto.getId() == null){
            throw new NullValueException("L'Id du unitDto a update ne peut etre null");
        }
        /****************************************************
         * On fait la recherche du Unit a update
         */
        Optional<Unit> optionalUnit = unitDao.findUnitById(unitDto.getId());
        if(!optionalUnit.isPresent()){
            throw new ModelNotFoundException("Le Unit envoye pour Update n'est pas trouve dans la BD. ",
                    ErrorCode.UNIT_NOT_FOUND.name());
        }
        Unit unitToUpdate = optionalUnit.get();
        /****************************************************
         * On valide le UnitDto envoye
         */
        List<String> errorsDto = unitValidator.validate(unitDto);
        if(!errorsDto.isEmpty()){
            log.error("The UnitDto to save is not valid. The errors encountered are {}", errorsDto);
            throw new InvalidEntityException("Le UnitDto envoye pour enregistrement n'est pas valide ", errorsDto,
                    ErrorCode.UNIT_NOT_VALID.name());
        }

        /****************************************************
         * On valide le Unit associe au UnitDto
         */
        List<String> errors = unitValidator.validate(unitMapper.dtoToEntity(unitDto));
        if(!errors.isEmpty()){
            log.error("The Unit to save is not valid. The errors encountered are {}", errors);
            throw new InvalidEntityException("Le Unit envoye pour enregistrement n'est pas valide ", errors,
                    ErrorCode.UNIT_NOT_VALID.name());
        }

        /**********************************************************
         * Si un des attributs de la condition d'unicite est
         * modifie alors on se rassure que la contrainte d'unicite
         * reste inviole
         */
        if(unitToUpdate.getUnitPos().getId().equals(unitDto.getUnitPosId()) ||
                unitToUpdate.getUnitName().equalsIgnoreCase(unitDto.getUnitName()) ||
                unitToUpdate.getUnitAbbreviation().equalsIgnoreCase(unitDto.getUnitAbbreviation())){

            if(!isUnitFullnameUsable(unitDto.getUnitName(), unitDto.getUnitAbbreviation(), unitDto.getUnitPosId())){
                log.error("The fullname of unit {} {} {} is already used", unitDto.getUnitName(),
                        unitDto.getUnitAbbreviation(), unitDto.getUnitPosId());
                throw new DuplicateEntityException("Les attributs de l'unite envoye correspondent a une unite deja enregistre",
                        ErrorCode.UNIT_DUPLICATED.name());
            }
            unitToUpdate.setUnitAbbreviation(unitDto.getUnitAbbreviation());
            unitToUpdate.setUnitName(unitDto.getUnitName());
            unitToUpdate.setUnitPos(posMapper.dtoToEntity(posService.getPointofsaleById(unitDto.getUnitPosId())));
        }

        /***********************************************************
         * Si tout se passe bien on realise la mise a jour
         */
        Unit unitUpdated = unitDao.save(unitToUpdate);

        return unitMapper.entityToDto(unitUpdated);
    }

    @Override
    public Boolean deleteUnitById(Long id) {
        if(id == null){
            throw new NullValueException("The id of the Unit to delete is null");
        }
        Optional<Unit> optionalUnit = unitDao.findUnitById(id);
        if(!optionalUnit.isPresent()){
            throw new ModelNotFoundException("Aucun Unit n'existe en BD avec l'id envoye ",
                    ErrorCode.UNIT_NOT_FOUND.name());
        }
        Unit unitToDelete = optionalUnit.get();
        if(!isUnitDeleteable(unitToDelete)){
            throw new EntityNotDeleatableException("Le Unit indique ne peut etre supprime sans cause des conflits ",
                    ErrorCode.UNIT_NOT_DELETEABLE.name());
        }
        log.info("The unit can be safely deleted");
        unitDao.delete(unitToDelete);
        return true;
    }

    Boolean isUnitDeleteable(Unit unit){return true;}

    @Override
    public UnitDto getUnitById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Unit envoye ne peut etre null");
        }
        Optional<Unit> optionalUnit = unitDao.findUnitById(id);
        if(!optionalUnit.isPresent()){
            throw new ModelNotFoundException("Aucun Unit n'existe en BD avec l'id envoye ",
                    ErrorCode.UNIT_NOT_FOUND.name());
        }
        Unit unitFound = optionalUnit.get();
        return unitMapper.entityToDto(unitFound);
    }

    @Override
    public Boolean isUnitExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Unit envoye ne peut etre null");
        }
        Optional<Unit> optionalUnit = unitDao.findUnitById(id);
        return optionalUnit.isPresent();
    }

    @Override
    public List<UnitDto> getListofUnit(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return unitMapper.entityToDto(unitDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return unitMapper.entityToDto(unitDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return unitMapper.entityToDto(unitDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Unit> unitSpecification = unitSpecService.getUnitSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());

        return unitMapper.entityToDto(unitDao.findAll(unitSpecification));
    }

    @Override
    public PageofUnitDto getPageofUnit(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Unit> unitPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            unitPage = unitDao.findAll(pageable);
            return getPageofUnitDto(unitPage);
        }
        else{
            /*************************************************************************************
             * Si le filterRequest envoye n'est pas null mais que l'element pas indiquant le numero
             * et la taille de page voulu est null alors on assigne des valeurs par defaut soit
             * page numero 0 et taille de page 10
             */
            if(filterRequest.getPage() == null){
                pagebm.setPagenum(0);
                pagebm.setPagesize(10);
                filterRequest.setPage(pagebm);
            }

            /**************************************************************************************
             * Si dans le filterRequest envoye les filtres et les elements de tri sont null alors
             * on retourne le findAll page par page.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                unitPage = unitDao.findAll(pageable);
                return getPageofUnitDto(unitPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                unitPage = unitDao.findAll(pageable);
                return getPageofUnitDto(unitPage);
            }

            /*********************************************************************************************
             * Si l'operateur logique permettant de lier les filtres est null et que la liste des filtres
             * contient plus d'un filtre alors il ya un probleme dans les parametres
             */
            if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            /****************************************************************************************************
             * On peut ici lancer une recherche selon les filtres envoyes, les classer selon les elements de tri
             * et ensuite la page demande
             */
            Specification<Unit> unitSpecification = unitSpecService.getUnitSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            unitPage = unitDao.findAll(unitSpecification, pageable);
            return getPageofUnitDto(unitPage);
        }
    }


    PageofUnitDto getPageofUnitDto(Page<Unit> unitPage){
        PageofUnitDto pageofUnitDto = new PageofUnitDto();
        pageofUnitDto.setContent(unitMapper.entityToDto(unitPage.getContent()));
        pageofUnitDto.setCurrentPage(unitPage.getNumber());
        pageofUnitDto.setPageSize(unitPage.getSize());
        pageofUnitDto.setTotalElements(unitPage.getTotalElements());
        pageofUnitDto.setTotalPages(unitPage.getTotalPages());

        return pageofUnitDto;
    }

}
