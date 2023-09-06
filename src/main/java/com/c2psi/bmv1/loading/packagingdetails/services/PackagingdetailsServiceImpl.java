package com.c2psi.bmv1.loading.packagingdetails.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.mappers.LoadingMapper;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.loading.loadingdetails.models.Loadingdetails;
import com.c2psi.bmv1.loading.packagingdetails.dao.PackagingdetailsDao;
import com.c2psi.bmv1.loading.packagingdetails.errorCode.ErrorCode;
import com.c2psi.bmv1.loading.packagingdetails.mappers.PackagingdetailsMapper;
import com.c2psi.bmv1.loading.packagingdetails.models.Packagingdetails;
import com.c2psi.bmv1.packaging.packaging.mappers.PackagingMapper;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
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

@Service(value = "PackagingdetailsServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PackagingdetailsServiceImpl implements PackagingdetailsService{
    final AppService appService;
    final PackagingdetailsDao packagingdetailsDao;
    final PackagingdetailsMapper packagingdetailsMapper;
    final PackagingdetailsValidator packagingdetailsValidator;
    final PackagingdetailsSpecService packagingdetailsSpecService;
    final PackagingService packagingService;
    final PackagingMapper packagingMapper;
    final LoadingService loadingService;
    final LoadingMapper loadingMapper;


    @Override
    public PackagingdetailsDto savePackagingdetails(PackagingdetailsDto packagingdetailsDto) {
        log.info("We start saving the packagingdetails by checking if the packagingDto is not null");
        if(packagingdetailsDto == null){
            throw new NullValueException("Le packagingdetails a save ne saurait etre null");
        }
        log.info("We continue saving the packagingdetails by validate the packagingdetailsDto");
        List<String> errorsDto = packagingdetailsValidator.validate(packagingdetailsDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le packagingdetailsdto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PACKAGINGDETAILS_NOT_VALID.name());
        }
        List<String> errors = packagingdetailsValidator.validate(packagingdetailsMapper.dtoToEntity(packagingdetailsDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le packagingdetails envoye n'est pas valide ", errors,
                    ErrorCode.PACKAGINGDETAILS_NOT_VALID.name());
        }
        log.info("We continue the saving process by ensure that the unique constraint will not be violated");
        if(!isPackagingdetailsUnique(packagingdetailsDto.getPdPackagingId(), packagingdetailsDto.getPdLoadingId())){
            throw new DuplicateEntityException("Il existe deja dans le loading un packagingdetails pour " +
                    "le packaging indique ", ErrorCode.PACKAGINGDETAILS_DUPLICATED.name());
        }
        log.info("We continue the saving process by preparing the packagingdetails to save");
        //Packagingdetails packagingdetailsToSave = packagingdetailsMapper.dtoToEntity(packagingdetailsDto);
        /****
         * packagingdetailsToUpdate.setPdLoading(loadingMapper.dtoToEntity(loadingService.getLoadingById(
         *                 packagingdetailsDto.getPdLoadingId())));
         *         packagingdetailsToUpdate.setPdPackaging(packagingMapper.dtoToEntity(packagingService.getPackagingById(
         *                 packagingdetailsDto.getPdPackagingId())));
         *         packagingdetailsToUpdate.setPackagenumberReturn(packagingdetailsDto.getPackagenumberReturn());
         *         packagingdetailsToUpdate.setPackagenumberUsed(packagingdetailsDto.getPackagenumberUsed());
         */
        Packagingdetails packagingdetailsToSave = Packagingdetails.builder()
                .pdLoading(loadingMapper.dtoToEntity(loadingService.getLoadingById(packagingdetailsDto.getPdLoadingId())))
                .pdPackaging(packagingMapper.dtoToEntity(packagingService.getPackagingById(packagingdetailsDto.
                        getPdPackagingId())))
                .packagenumberReturn(packagingdetailsDto.getPackagenumberReturn())
                .packagenumberUsed(packagingdetailsDto.getPackagenumberUsed())
                .build();

        Packagingdetails packagingdetailsSaved = packagingdetailsDao.save(packagingdetailsToSave);


        return packagingdetailsMapper.entityToDto(packagingdetailsSaved);
    }

    Boolean isPackagingdetailsUnique(Long packagingId, Long loadingId){
        if(packagingId == null || loadingId == null){
            throw new NullValueException("les arguments envoye est null");
        }
        Optional<Packagingdetails> optionalPackagingdetails = packagingdetailsDao.
                findPackagingdetailsByPackagingInLoading(packagingId, loadingId);
        return optionalPackagingdetails.isEmpty();
    }

    @Override
    public PackagingdetailsDto updatePackagingdetails(PackagingdetailsDto packagingdetailsDto) {
        log.info("We start updating the packagingdetails by checking if the packagingDto is not null");
        if(packagingdetailsDto == null){
            throw new NullValueException("Le packagingdetailsDto a update ne saurait etre null");
        }
        log.info("We start updating the packagingdetails by checking if the packagingDtoId is not null");
        if(packagingdetailsDto.getId() == null){
            throw new NullValueException("L'Id du packagingdetailsDto a update ne saurait etre null");
        }
        log.info("We continue updating the packagingdetails by validate the packagingdetailsDto");
        List<String> errorsDto = packagingdetailsValidator.validate(packagingdetailsDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le packagingdetailsdto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PACKAGINGDETAILS_NOT_VALID.name());
        }
        List<String> errors = packagingdetailsValidator.validate(packagingdetailsMapper.dtoToEntity(packagingdetailsDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le packagingdetails envoye n'est pas valide ", errors,
                    ErrorCode.PACKAGINGDETAILS_NOT_VALID.name());
        }
        log.info("We continue the updating process by retrieve the packagingdetails to update");
        Optional<Packagingdetails> optionalPackagingdetails = packagingdetailsDao.findPackagingdetailsById(
                packagingdetailsDto.getId());
        if(!optionalPackagingdetails.isPresent()){
            throw new ModelNotFoundException("Aucun packagingdetails n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.PACKAGINGDETAILS_NOT_FOUND.name());
        }
        Packagingdetails packagingdetailsToUpdate = optionalPackagingdetails.get();
        log.info("We continue the updating process by ensure that the unique constraint will not be violated");
        if(!packagingdetailsToUpdate.getPdLoading().getId().equals(packagingdetailsDto.getPdLoadingId()) ||
                !packagingdetailsToUpdate.getPdPackaging().getId().equals(packagingdetailsDto.getPdPackagingId())){
            if(!isPackagingdetailsUnique(packagingdetailsDto.getPdPackagingId(), packagingdetailsDto.getPdLoadingId())){
                throw new DuplicateEntityException("Il existe deja un packagingdetails avec les memes attributs en BD",
                        ErrorCode.PACKAGINGDETAILS_DUPLICATED.name());
            }
        }
        log.info("We continue the updating process by preparing the packagingdetails to update");
        packagingdetailsToUpdate.setPdLoading(loadingMapper.dtoToEntity(loadingService.getLoadingById(
                packagingdetailsDto.getPdLoadingId())));
        packagingdetailsToUpdate.setPdPackaging(packagingMapper.dtoToEntity(packagingService.getPackagingById(
                packagingdetailsDto.getPdPackagingId())));
        packagingdetailsToUpdate.setPackagenumberReturn(packagingdetailsDto.getPackagenumberReturn());
        packagingdetailsToUpdate.setPackagenumberUsed(packagingdetailsDto.getPackagenumberUsed());
        Packagingdetails packagingdetailsUpdated = packagingdetailsDao.save(packagingdetailsToUpdate);

        return packagingdetailsMapper.entityToDto(packagingdetailsUpdated);
    }

    @Override
    public Boolean deletePackagingdetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du packagingdetails a delete est null");
        }
        Optional<Packagingdetails> optionalPackagingdetails = packagingdetailsDao.findPackagingdetailsById(id);
        if(!optionalPackagingdetails.isPresent()){
            throw new ModelNotFoundException("Aucun packagingdetails n'existe avec l'id envoye",
                    ErrorCode.PACKAGINGDETAILS_NOT_FOUND.name());
        }
        if(!isPackagingdetailsDeleteable(optionalPackagingdetails.get())){
            throw new EntityNotDeleatableException("Il n'est pas possible de supprimer ce packagingdetails sans causer " +
                    "de conflits ", ErrorCode.PACKAGINGDETAILS_NOT_DELETEABLE.name());
        }
        packagingdetailsDao.delete(optionalPackagingdetails.get());
        return true;
    }

    Boolean isPackagingdetailsDeleteable(Packagingdetails packagingdetails){
        return packagingdetails.getPdLoading().getLoadOpen();
    }

    @Override
    public PackagingdetailsDto getPackagingdetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du packagingdetails a delete est null");
        }
        Optional<Packagingdetails> optionalPackagingdetails = packagingdetailsDao.findPackagingdetailsById(id);
        if(!optionalPackagingdetails.isPresent()){
            throw new ModelNotFoundException("Aucun packagingdetails n'existe avec l'id envoye",
                    ErrorCode.PACKAGINGDETAILS_NOT_FOUND.name());
        }

        return packagingdetailsMapper.entityToDto(optionalPackagingdetails.get());
    }

    @Override
    public List<PackagingdetailsDto> getListofPackagingdetails(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return packagingdetailsMapper.entityToDto(packagingdetailsDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return packagingdetailsMapper.entityToDto(packagingdetailsDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return packagingdetailsMapper.entityToDto(packagingdetailsDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Packagingdetails> packagingdetailsSpecification = packagingdetailsSpecService.getPackagingdetailsSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return packagingdetailsMapper.entityToDto(packagingdetailsDao.findAll(packagingdetailsSpecification));
    }

    @Override
    public PageofPackagingdetailsDto getPageofPackagingdetails(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Packagingdetails> packagingdetailsPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            packagingdetailsPage = packagingdetailsDao.findAll(pageable);
            return getPageofPackagingdetailsDto(packagingdetailsPage);
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
                packagingdetailsPage = packagingdetailsDao.findAll(pageable);
                return getPageofPackagingdetailsDto(packagingdetailsPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                packagingdetailsPage = packagingdetailsDao.findAll(pageable);
                return getPageofPackagingdetailsDto(packagingdetailsPage);
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
            Specification<Packagingdetails> loadingdetailsSpecification = packagingdetailsSpecService.
                    getPackagingdetailsSpecification(filterRequest.getFilters(),
                            filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            packagingdetailsPage = packagingdetailsDao.findAll(loadingdetailsSpecification, pageable);
            return getPageofPackagingdetailsDto(packagingdetailsPage);

        }
    }

    @Override
    public Boolean isPackagingdetailsExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du packagingdetails a delete est null");
        }
        Optional<Packagingdetails> optionalPackagingdetails = packagingdetailsDao.findPackagingdetailsById(id);
        return optionalPackagingdetails.isPresent();
    }

    PageofPackagingdetailsDto getPageofPackagingdetailsDto(Page<Packagingdetails> packagingdetailsPage){
        PageofPackagingdetailsDto pageofPackagingdetailsDto = new PageofPackagingdetailsDto();
        pageofPackagingdetailsDto.setContent(packagingdetailsMapper.entityToDto(packagingdetailsPage.getContent()));
        pageofPackagingdetailsDto.setCurrentPage(packagingdetailsPage.getNumber());
        pageofPackagingdetailsDto.setPageSize(packagingdetailsPage.getSize());
        pageofPackagingdetailsDto.setTotalElements(packagingdetailsPage.getTotalElements());
        pageofPackagingdetailsDto.setTotalPages(packagingdetailsPage.getTotalPages());

        return pageofPackagingdetailsDto;

    }
}
