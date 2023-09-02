package com.c2psi.bmv1.loading.loading.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.dao.LoadingDao;
import com.c2psi.bmv1.loading.loading.errorCode.ErrorCode;
import com.c2psi.bmv1.loading.loading.mappers.LoadingMapper;
import com.c2psi.bmv1.loading.loading.models.Loading;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.userbm.mappers.UserbmMapper;
import com.c2psi.bmv1.userbm.services.UserbmService;
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

@Service(value = "LoadingServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoadingServiceImpl implements LoadingService{
    final AppService appService;
    final LoadingDao loadingDao;
    final LoadingValidator loadingValidator;
    final LoadingSpecService loadingSpecService;
    final LoadingMapper loadingMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final UserbmService userbmService;
    final UserbmMapper userbmMapper;


    @Override
    public LoadingDto saveLoading(LoadingDto loadingDto) {
        log.info("We start saving the loadingDto by checking if the loadingDto is not null");
        if(loadingDto == null){
            throw new NullValueException("Le loadingDto envoye ne saurait etre null");
        }
        log.info("We continue the saving process by validate the loadingDto");
        List<String> errorsDto = loadingValidator.validate(loadingDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le loadingDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.LOADING_NOT_VALID.name());
        }
        log.info("We continue the saving process by validate the loading associate to the loadingDto");
        List<String> errors = loadingValidator.validate(loadingMapper.dtoToEntity(loadingDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le loading envoye n'est pas valide ", errors,
                    ErrorCode.LOADING_NOT_VALID.name());
        }
        log.info("We continue the saving process by ensure that constraint will not be violated");
        if(loadingDto.getLoadCode() != null){
            if(!isLoadingCodeUsableInPos(loadingDto.getLoadCode(), loadingDto.getLoadPosId())){
                throw new DuplicateEntityException("Il existe deja un Loading avec le code envoye dans le pointofsale " +
                        "indique ", ErrorCode.LOADING_DUPLICATED.name());
            }
        }
        log.info("We continue the saving process by preparing the Loading to save");
        Loading loadingToSave = loadingMapper.dtoToEntity(loadingDto);
        loadingToSave.setLoadreturnDate(null);//le returnDate ne peut etre connu au moment de l'enregistrement du loading
        return loadingMapper.entityToDto(loadingDao.save(loadingToSave));
    }

    Boolean isLoadingCodeUsableInPos(String loadCode, Long posId){
        if(loadCode == null || posId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<Loading> optionalLoading = loadingDao.findLoadingByCodeInPos(loadCode, posId);
        return optionalLoading.isEmpty();
    }

    @Override
    public LoadingDto updateLoading(LoadingDto loadingDto) {
        log.info("We start the updating process by ensure that the loadingDto is not null");
        if(loadingDto == null){
            throw new NullValueException("Le loadingDto envoye pour update ne saurait etre null");
        }
        log.info("We continue the updating process by ensure that the loadingDto Id is not null");
        if(loadingDto.getId() == null){
            throw new NullValueException("L'id du loadingDto a update ne saurait etre null");
        }
        log.info("We continue the updating process by ensure that the loadingDto is valid");
        List<String> errorsDto = loadingValidator.validate(loadingDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le loadingDto envoye pour update n'est pas valide ",
                    ErrorCode.LOADING_NOT_VALID.name());
        }
        List<String> errors = loadingValidator.validate(loadingMapper.dtoToEntity(loadingDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le loading envoye pour update n'est pas valide ",
                    ErrorCode.LOADING_NOT_VALID.name());
        }
        log.info("We continue the updating process by retrieve the loadingDto to update");
        Optional<Loading> optionalLoading = loadingDao.findLoadingById(loadingDto.getId());
        if(!optionalLoading.isPresent()){
            throw new ModelNotFoundException("Aucun loading n'existe dans la BD avec l'id envoye ",
                    ErrorCode.LOADING_NOT_FOUND.name());
        }
        Loading loadingToUpdate = optionalLoading.get();
        log.info("We continue the updating process by ensure that the constraint will not be violated");
        if (loadingDto.getLoadCode() != null && loadingToUpdate.getLoadCode() != null){
            if(!loadingDto.getLoadCode().equals(loadingToUpdate.getLoadCode())){
                if(!isLoadingCodeUsableInPos(loadingDto.getLoadCode(), loadingDto.getLoadPosId())){
                    throw new DuplicateEntityException("Il existe deja un Loading pour le meme pointofsale avec le " +
                            "meme code ", ErrorCode.LOADING_DUPLICATED.name());
                }
            }
        }
        log.info("We continue the updating process by ensure that the pointofsale is not different");
        if(!loadingDto.getLoadPosId().equals(loadingToUpdate.getLoadPos().getId())){
            throw new InvalidEntityException("On ne peut changer le pointofsale d'un loading",
                    ErrorCode.LOADING_NOT_VALID.name());
        }
        log.info("We continue the updating process by preparing the update");

        loadingToUpdate.setLoadCode(loadingDto.getLoadCode());
        loadingToUpdate.setLoadDate(loadingDto.getLoadDate());
        loadingToUpdate.setLoadreturnDate(loadingDto.getLoadreturnDate());
        loadingToUpdate.setLoadExpectedamount(loadingDto.getLoadExpectedamount());
        loadingToUpdate.setLoadPaidamount(loadingDto.getLoadPaidamount());
        loadingToUpdate.setLoadRemise(loadingDto.getLoadRemise());
        loadingToUpdate.setLoadReport(loadingDto.getLoadReport());
        loadingToUpdate.setLoadManager(userbmMapper.dtoToEntity(userbmService.getUserbmById(loadingDto.getLoadManagerId())));
        loadingToUpdate.setLoadSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(loadingDto.getLoadSalerId())));
        loadingToUpdate.setLoadPos(posMapper.dtoToEntity(posService.getPointofsaleById(loadingDto.getLoadPosId())));
        log.info("We continue the updating process by lauching the update");
        Loading loadingUpdated = loadingDao.save(loadingToUpdate);
        return loadingMapper.entityToDto(loadingUpdated);
    }

    @Override
    public Boolean deleteLoadingById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loading a supprimer ne peut etre null");
        }
        Optional<Loading> optionalLoading = loadingDao.findLoadingById(id);
        if(!optionalLoading.isPresent()){
            throw new ModelNotFoundException("Aucun loading n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.LOADING_NOT_FOUND.name());
        }
        if(!isLoadingDeleteable(optionalLoading.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer un loading deja clos");
        }
        loadingDao.delete(optionalLoading.get());

        return true;
    }

    Boolean isLoadingDeleteable(Loading loading){
        return loading.getLoadOpen();
    }

    @Override
    public LoadingDto getLoadingById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loading a supprimer ne peut etre null");
        }
        Optional<Loading> optionalLoading = loadingDao.findLoadingById(id);
        if(!optionalLoading.isPresent()){
            throw new ModelNotFoundException("Aucun loading n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.LOADING_NOT_FOUND.name());
        }

        return loadingMapper.entityToDto(optionalLoading.get());
    }

    @Override
    public List<LoadingDto> getListofLoading(FilterRequest filterRequest) {

        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return loadingMapper.entityToDto(loadingDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return loadingMapper.entityToDto(loadingDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return loadingMapper.entityToDto(loadingDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Loading> loadSpecification = loadingSpecService.getLoadingSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return loadingMapper.entityToDto(loadingDao.findAll(loadSpecification));
    }

    @Override
    public PageofLoadingDto getPageofLoading(FilterRequest filterRequest) {

        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Loading> loadingPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            loadingPage = loadingDao.findAll(pageable);
            return getPageofLoadingDto(loadingPage);
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
                loadingPage = loadingDao.findAll(pageable);
                return getPageofLoadingDto(loadingPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                loadingPage = loadingDao.findAll(pageable);
                return getPageofLoadingDto(loadingPage);
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
            Specification<Loading> loadingSpecification = loadingSpecService.getLoadingSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            loadingPage = loadingDao.findAll(loadingSpecification, pageable);
            return getPageofLoadingDto(loadingPage);

        }
    }

    @Override
    public Boolean isLoadingExistWith(Long id) {
        return null;
    }

    @Override
    public Boolean openLoading(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loading a open ne peut etre null");
        }
        Optional<Loading> optionalLoading = loadingDao.findLoadingById(id);
        if(!optionalLoading.isPresent()){
            throw new ModelNotFoundException("Aucun loading n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.LOADING_NOT_FOUND.name());
        }
        optionalLoading.get().setLoadOpen(true);
        return optionalLoading.get().getLoadOpen();
    }

    @Override
    public Boolean closeLoading(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loading a close ne peut etre null");
        }
        Optional<Loading> optionalLoading = loadingDao.findLoadingById(id);
        if(!optionalLoading.isPresent()){
            throw new ModelNotFoundException("Aucun loading n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.LOADING_NOT_FOUND.name());
        }
        optionalLoading.get().setLoadOpen(false);
        return optionalLoading.get().getLoadOpen();
    }

    PageofLoadingDto getPageofLoadingDto(Page<Loading> loadingPage){
        PageofLoadingDto pageofLoadingDto = new PageofLoadingDto();
        pageofLoadingDto.setContent(loadingMapper.entityToDto(loadingPage.getContent()));
        pageofLoadingDto.setCurrentPage(loadingPage.getNumber());
        pageofLoadingDto.setPageSize(loadingPage.getSize());
        pageofLoadingDto.setTotalElements(loadingPage.getTotalElements());
        pageofLoadingDto.setTotalPages(loadingPage.getTotalPages());

        return pageofLoadingDto;

    }

}
