package com.c2psi.bmv1.product.unit.unitconversion.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.unit.unit.mapper.UnitMapper;
import com.c2psi.bmv1.product.unit.unit.service.UnitService;
import com.c2psi.bmv1.product.unit.unitconversion.dao.UnitconversionDao;
import com.c2psi.bmv1.product.unit.unitconversion.errorCode.ErrorCode;
import com.c2psi.bmv1.product.unit.unitconversion.mapper.UnitconversionMapper;
import com.c2psi.bmv1.product.unit.unitconversion.models.Unitconversion;
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

@Service(value = "UnitconversionServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UnitconversionServiceImpl implements UnitconversionService{
    final AppService appService;
    final UnitconversionDao unitconversionDao;
    final UnitconversionMapper unitconversionMapper;
    final UnitconversionValidator unitconversionValidator;
    final UnitconversionSpecService unitconversionSpecService;
    final UnitService unitService;
    final UnitMapper unitMapper;

    @Override
    public UnitconversionDto saveUnitconversion(UnitconversionDto unitconversionDto) {
        /*****************************************************************************
         * On verifie que unitconversionDto n'est pas null
         */
        if(unitconversionDto == null){
            throw new NullValueException("The unitconversion to save can't be null");
        }
        /******************************************************************************
         * On valide le unitconversionDto
         */
        List<String> errorsDto = unitconversionValidator.validate(unitconversionDto);
        if(!errorsDto.isEmpty()){
            log.error("The unitconversionDto to save is not valid. The errors encounter are {}", errorsDto);
            throw new InvalidEntityException("Le Unitconversion a enregistre n'est pas valide ", errorsDto,
                    ErrorCode.UNITCONVERSION_NOT_VALID.name());
        }
        /*****************************************************************************
         * On valide le unitconversion associe a ce Dto
         */
        List<String> errors = unitconversionValidator.validate(unitconversionMapper.dtoToEntity(unitconversionDto));
        if(!errors.isEmpty()){
            log.error("The unitcomversion to save is not valid. The errors encounter are {}", errors);
            throw new InvalidEntityException("Le Unitconversion a enregistre n'est pas valide ", errors,
                    ErrorCode.UNITCONVERSION_NOT_VALID.name());
        }
        /*****************************************************************************
         * On se rassure que la contrainte d'unicite ne sera pas viole
         */
        if(!isUnitconversionAttributeUsable(unitconversionDto.getUnitSourceId(),
                unitconversionDto.getUnitDestinationId())){
            log.error("A conversion rule already exist between the two unit sent ");
            throw new DuplicateEntityException("Une regle de conversion existe deja entre le UnitSource et le " +
                    "UnitDestination envoye ", ErrorCode.UNITCONVERSION_DUPLICATED.name());
        }
        /****************************************************************************
         * Si tout est bon on effectue l'enregistrement
         */
        Unitconversion unitconversion = Unitconversion.builder()
                .conversionFactor(unitconversionDto.getConversionFactor())
                .unitSource(unitMapper.dtoToEntity(unitService.getUnitById(unitconversionDto.getUnitSourceId())))
                .unitDestination(unitMapper.dtoToEntity(unitService.getUnitById(unitconversionDto.getUnitDestinationId())))
                .build();

        Unitconversion unitconversionSaved = unitconversionDao.save(unitconversion);

        return unitconversionMapper.entityToDto(unitconversionSaved);
    }

    Boolean isUnitconversionAttributeUsable(Long unitSourceId, Long unitDestinationId){
        Optional<Unitconversion> optionalUnitconversion = unitconversionDao.
                findUnitconversionByAttributes(unitSourceId, unitDestinationId);

        Optional<Unitconversion> optionalUnitconversion1 = unitconversionDao.
                findUnitconversionByAttributes(unitDestinationId, unitSourceId);

        return optionalUnitconversion.isEmpty() && optionalUnitconversion1.isEmpty();
    }

    @Override
    public UnitconversionDto updateUnitconversion(UnitconversionDto unitconversionDto) {
        /***********************************************************************************
         * On se rassure que le unitconversionDto n'est pas null
         */
        if(unitconversionDto == null){
            throw new NullValueException("Le unitconversionDto envoye ne peut etre null");
        }
        /*************************************************************************************
         * On se rassure que l'id du unitconversionDto n'est pas null
         */
        if(unitconversionDto.getId() == null){
            throw new NullValueException("L'id du UnitconversionDto a update ne peut etre null");
        }
        /*************************************************************************************
         * On recupere donc en BD le unitconversion a update
         */
        Optional<Unitconversion> optionalUnitconversion = unitconversionDao.
                findUnitconversionById(unitconversionDto.getId());
        if(!optionalUnitconversion.isPresent()){
            throw new ModelNotFoundException("L'id envoye ne correspond a aucun Unitconversion en BD ",
                    ErrorCode.UNITCONVERSION_NOT_FOUND.name());
        }
        Unitconversion unitconversionToUpdate = optionalUnitconversion.get();
        /*************************************************************************************
         * On valide le UnitconversionDto
         */
        List<String> errorsDto = unitconversionValidator.validate(unitconversionDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le Unitconversion envoye n'est pas valide ", errorsDto,
                    ErrorCode.UNITCONVERSION_NOT_VALID.name());
        }
        /*****************************************************************************************
         * On valide le Unitconversion associe
         */
        List<String> errors = unitconversionValidator.validate(unitconversionMapper.dtoToEntity(unitconversionDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le Unitconversion envoye n'est pas valide ", errors,
                    ErrorCode.UNITCONVERSION_NOT_VALID.name());
        }
        /****************************************************************************************
         * Si les attributs du UnitconversionDto sont different de ceux du Unitconversion
         * on se rassure de l'unicite de la regle en BD et on prepare les update
         */
        if(!unitconversionToUpdate.getUnitSource().getId().equals(unitconversionDto.getUnitSourceId()) ||
            !unitconversionToUpdate.getUnitDestination().getId().equals(unitconversionDto.getUnitDestinationId())){
            if(!isUnitconversionAttributeUsable(unitconversionDto.getUnitSourceId(),
                    unitconversionDto.getUnitDestinationId())){
                log.error("A conversion rule already exist between the two unit sent ");
                throw new DuplicateEntityException("Une regle de conversion existe deja entre le UnitSource et le " +
                        "UnitDestination envoye ", ErrorCode.UNITCONVERSION_DUPLICATED.name());
            }
            unitconversionToUpdate.setUnitSource(unitMapper.dtoToEntity(unitService.getUnitById(
                    unitconversionDto.getUnitSourceId()
            )));
            unitconversionToUpdate.setUnitDestination(unitMapper.dtoToEntity(unitService.getUnitById(
                    unitconversionDto.getUnitDestinationId()
            )));
        }
        unitconversionToUpdate.setConversionFactor(unitconversionDto.getConversionFactor());
        /****************************************************************************************
         * Si tout est bon on lance le Update
         */
        Unitconversion unitconversionUpdated = unitconversionDao.save(unitconversionToUpdate);

        return unitconversionMapper.entityToDto(unitconversionUpdated);
    }

    @Override
    public Boolean deleteUnitconversionById(Long id) {
        if(id == null){
            throw new NullValueException("The id of the Unitconversion sent is null");
        }
        Optional<Unitconversion> optionalUnitconversion = unitconversionDao.findUnitconversionById(id);
        if(!optionalUnitconversion.isPresent()){
            throw new ModelNotFoundException("Aucun Unitconversion n'existe en BD avec l'id envoye ",
                    ErrorCode.UNITCONVERSION_NOT_FOUND.name());
        }

        if(!isUnitconversionDeleteable(optionalUnitconversion.get())){
            throw new EntityNotDeleatableException("Il n'est pas possible de supprimer cette regle sans cause de confilt ",
                    ErrorCode.UNITCONVERSION_NOT_DELETEABLE.name());
        }
        unitconversionDao.delete(optionalUnitconversion.get());
        return true;
    }

    Boolean isUnitconversionDeleteable(Unitconversion unitconversion){
        return true;
    }

    @Override
    public UnitconversionDto getUnitconversionById(Long id) {
        if(id == null){
            throw new NullValueException("The id of the Unitconversion sent is null");
        }
        Optional<Unitconversion> optionalUnitconversion = unitconversionDao.findUnitconversionById(id);
        if(!optionalUnitconversion.isPresent()){
            throw new ModelNotFoundException("Aucun Unitconversion n'existe en BD avec l'id envoye ",
                    ErrorCode.UNITCONVERSION_NOT_FOUND.name());
        }

        return unitconversionMapper.entityToDto(optionalUnitconversion.get());
    }

    @Override
    public Boolean isUnitconversionExistWith(Long id) {
        if(id == null){
            throw new NullValueException("The id of the Unitconversion sent is null");
        }
        Optional<Unitconversion> optionalUnitconversion = unitconversionDao.findUnitconversionById(id);

        return optionalUnitconversion.isPresent();
    }

    @Override
    public List<UnitconversionDto> getListofUnitconversion(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return unitconversionMapper.entityToDto(unitconversionDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return unitconversionMapper.entityToDto(unitconversionDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return unitconversionMapper.entityToDto(unitconversionDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Unitconversion> uniconvSpecification = unitconversionSpecService.getUnitconversionSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());

        return unitconversionMapper.entityToDto(unitconversionDao.findAll(uniconvSpecification));
    }

    @Override
    public PageofUnitconversionDto getPageofUnitconversion(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Unitconversion> unitconvPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            unitconvPage = unitconversionDao.findAll(pageable);
            return getPageofUnitconversionDto(unitconvPage);
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
                unitconvPage = unitconversionDao.findAll(pageable);
                return getPageofUnitconversionDto(unitconvPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                unitconvPage = unitconversionDao.findAll(pageable);
                return getPageofUnitconversionDto(unitconvPage);
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
            Specification<Unitconversion> unitconvSpecification = unitconversionSpecService.
                    getUnitconversionSpecification(filterRequest.getFilters(), filterRequest.getLogicOperator(),
                            filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            unitconvPage = unitconversionDao.findAll(unitconvSpecification, pageable);
            return getPageofUnitconversionDto(unitconvPage);
        }
    }

    PageofUnitconversionDto getPageofUnitconversionDto(Page<Unitconversion> unitconvPage){
        PageofUnitconversionDto pageofUnitconvDto = new PageofUnitconversionDto();
        pageofUnitconvDto.setContent(unitconversionMapper.entityToDto(unitconvPage.getContent()));
        pageofUnitconvDto.setCurrentPage(unitconvPage.getNumber());
        pageofUnitconvDto.setPageSize(unitconvPage.getSize());
        pageofUnitconvDto.setTotalElements(unitconvPage.getTotalElements());
        pageofUnitconvDto.setTotalPages(unitconvPage.getTotalPages());

        return pageofUnitconvDto;
    }
}
