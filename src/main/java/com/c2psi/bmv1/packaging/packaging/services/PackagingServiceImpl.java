package com.c2psi.bmv1.packaging.packaging.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.packaging.packaging.dao.PackagingDao;
import com.c2psi.bmv1.packaging.packaging.errorCode.ErrorCode;
import com.c2psi.bmv1.packaging.packaging.mappers.PackagingMapper;
import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.provider.provider.mappers.ProviderMapper;
import com.c2psi.bmv1.provider.provider.service.ProviderService;
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

@Service(value = "PackagingServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PackagingServiceImpl implements PackagingService{

    final PackagingDao packagingDao;
    final PackagingMapper packagingMapper;
    final AppService appService;
    final PackagingValidator packagingValidator;
    final PackagingSpecService packagingSpecService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final ProviderService provService;
    final ProviderMapper provMapper;

    @Override
    public PackagingDto savePackaging(PackagingDto packagingDto) {
        log.info("We start saving a packaging by checking if the packagingDto is not null");
        if(packagingDto == null){
            throw new NullValueException("Le packagingDto sent ne saurait etre null");
        }
        log.info("We continue saving a packaging by validate the packagingDto");
        List<String> errorsDto = packagingValidator.validate(packagingDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le packagingDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PACKAGING_NOT_VALID.name());
        }
        List<String> errors = packagingValidator.validate(packagingMapper.dtoToEntity(packagingDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le packaging envoye n'est pas valide ", errorsDto,
                    ErrorCode.PACKAGING_NOT_VALID.name());
        }
        log.info("We continue the saving process by ensure that the unicity constraint will not be violated");
        if(!isPackagingAttributesUsable(packagingDto.getPackLabel(), packagingDto.getPackFirstcolor(),
                packagingDto.getPackagingProviderId(), packagingDto.getPackagingPosId())){
            throw new DuplicateEntityException("Il existe deja en BD un packaging avec les attributs saisis dans le " +
                    "pointofsale ", ErrorCode.PACKAGING_DUPLICATED.name());
        }
        log.info("We continue the saving process by preparing the Packaging to save");
        Packaging packagingToSave = packagingMapper.dtoToEntity(packagingDto);
        return packagingMapper.entityToDto(packagingDao.save(packagingToSave));
    }

    Boolean isPackagingAttributesUsable(String packLabel, String packFirstColor, Long providerId, Long posId){
        if(packLabel == null || packFirstColor == null || providerId == null || posId == null){
            throw new NullValueException("The argument sent is null");
        }
        Optional<Packaging> optionalPackaging = packagingDao.findPackagingByAttributes(packLabel, packFirstColor,
                providerId, posId);
        return optionalPackaging.isEmpty();
    }

    @Override
    public PackagingDto updatePackaging(PackagingDto packagingDto) {
        log.info("The updating process start by checking if the packagingDto is not null");
        if(packagingDto == null){
            throw new NullValueException("Le packagingDto a update ne saurait etre null");
        }
        log.info("The updating process continue by checking that the id of the packagingDto is not null");
        if(packagingDto.getId() == null){
            throw new NullValueException("L'id du PackagingDto a update ne saurait etre null");
        }
        log.info("The updating process continue by validate the packagingDto");
        List<String> errorsDto = packagingValidator.validate(packagingDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le packagingDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PACKAGING_NOT_VALID.name());
        }
        log.info("The updating process continue by validate the packaging associate to the packagingDto");
        List<String> errors = packagingValidator.validate(packagingMapper.dtoToEntity(packagingDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le packaging envoye n'est pas valide ", errorsDto,
                    ErrorCode.PACKAGING_NOT_VALID.name());
        }
        log.info("The updating process continue by retrieve the packaging to update");
        Optional<Packaging> optionalPackaging = packagingDao.findPackagingById(packagingDto.getId());
        if(!optionalPackaging.isPresent()){
            throw new ModelNotFoundException("Aucun packaging n'existe dans le systeme avec l'id envoye",
                    ErrorCode.PACKAGING_NOT_FOUND.name());
        }
        Packaging packagingToUpdate = optionalPackaging.get();
        log.info("The updating process continue by ensure that the constraint is not violated if the attributes " +
                "are different");
        boolean providerDifferent = packagingToUpdate.getPackagingProvider().getId().
                equals(packagingDto.getPackagingProviderId());
        boolean posDifferent = packagingToUpdate.getPackagingPos().getId().equals(packagingDto.getPackagingPosId());
        boolean packLabelDifferent = packagingToUpdate.getPackLabel().equalsIgnoreCase(packagingDto.getPackLabel());
        boolean packFirstColorDifferent = packagingToUpdate.getPackFirstcolor().
                equalsIgnoreCase(packagingDto.getPackFirstcolor());
        if(!providerDifferent || !posDifferent || !packLabelDifferent || !packFirstColorDifferent){
            if(!isPackagingAttributesUsable(packagingDto.getPackLabel(), packagingDto.getPackFirstcolor(),
                    packagingDto.getPackagingProviderId(), packagingDto.getPackagingPosId())){
                throw new DuplicateEntityException("Il existe deja dans la BD un packaging avec les memes attributs",
                        ErrorCode.PACKAGING_DUPLICATED.name());
            }
        }
        log.info("The updating process continue by preparing the update");
        packagingToUpdate.setPackLabel(packagingDto.getPackLabel());
        packagingToUpdate.setPackDescription(packagingDto.getPackDescription());
        packagingToUpdate.setPackFirstcolor(packagingDto.getPackFirstcolor());
        packagingToUpdate.setPackPrice(packagingDto.getPackPrice());
        packagingToUpdate.setPackagingProvider(provMapper.dtoToEntity(provService.getProviderById(
                packagingDto.getPackagingProviderId())));
        packagingToUpdate.setPackagingPos(posMapper.dtoToEntity(posService.getPointofsaleById(
                packagingDto.getPackagingPosId())));

        Packaging packagingUpdated = packagingDao.save(packagingToUpdate);

        return packagingMapper.entityToDto(packagingUpdated);
    }

    @Override
    public Boolean deletePackagingById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du packaging a delete ne saurait etre null");
        }
        Optional<Packaging> optionalPackaging = packagingDao.findPackagingById(id);
        if(!optionalPackaging.isPresent()){
            throw new ModelNotFoundException("Aucun packaging n'existe avec l'id envoye",
                    ErrorCode.PACKAGING_NOT_FOUND.name());
        }
        if(!isPackagingDeleteable(optionalPackaging.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce packaging sans causer de conflits ",
                    ErrorCode.PACKAGING_NOT_DELETEABLE.name());
        }

        packagingDao.delete(optionalPackaging.get());
        return true;
    }

    Boolean isPackagingDeleteable(Packaging packaging){
        return true;
    }

    @Override
    public PackagingDto getPackagingById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du packaging a delete ne saurait etre null");
        }
        Optional<Packaging> optionalPackaging = packagingDao.findPackagingById(id);
        if(!optionalPackaging.isPresent()){
            throw new ModelNotFoundException("Aucun packaging n'existe avec l'id envoye",
                    ErrorCode.PACKAGING_NOT_FOUND.name());
        }
        return packagingMapper.entityToDto(optionalPackaging.get());
    }

    @Override
    public List<PackagingDto> getListofPackaging(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return packagingMapper.entityToDto(packagingDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return packagingMapper.entityToDto(packagingDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return packagingMapper.entityToDto(packagingDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Packaging> packagingSpecification = packagingSpecService.getPackagingSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return packagingMapper.entityToDto(packagingDao.findAll(packagingSpecification));
    }

    @Override
    public PageofPackagingDto getPageofPackaging(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Packaging> packagingPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            packagingPage = packagingDao.findAll(pageable);
            return getPageofPackagingDto(packagingPage);
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
                packagingPage = packagingDao.findAll(pageable);
                return getPageofPackagingDto(packagingPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                packagingPage = packagingDao.findAll(pageable);
                return getPageofPackagingDto(packagingPage);
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
            Specification<Packaging> packagingSpecification = packagingSpecService.getPackagingSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            packagingPage = packagingDao.findAll(packagingSpecification, pageable);
            return getPageofPackagingDto(packagingPage);
        }
    }

    @Override
    public Boolean isPackagingExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du packaging a delete ne saurait etre null");
        }
        Optional<Packaging> optionalPackaging = packagingDao.findPackagingById(id);
        return optionalPackaging.isPresent();
    }

    PageofPackagingDto getPageofPackagingDto(Page<Packaging> packagingPage){
        PageofPackagingDto pageofPackagingDto = new PageofPackagingDto();
        pageofPackagingDto.setContent(packagingMapper.entityToDto(packagingPage.getContent()));
        pageofPackagingDto.setCurrentPage(packagingPage.getNumber());
        pageofPackagingDto.setPageSize(packagingPage.getSize());
        pageofPackagingDto.setTotalElements(packagingPage.getTotalElements());
        pageofPackagingDto.setTotalPages(packagingPage.getTotalPages());

        return pageofPackagingDto;
    }

}
