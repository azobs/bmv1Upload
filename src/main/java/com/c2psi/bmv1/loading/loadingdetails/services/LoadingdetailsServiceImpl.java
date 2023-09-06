package com.c2psi.bmv1.loading.loadingdetails.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.mappers.LoadingMapper;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.loading.loadingdetails.dao.LoadingdetailsDao;
import com.c2psi.bmv1.loading.loadingdetails.errorCode.ErrorCode;
import com.c2psi.bmv1.loading.loadingdetails.mappers.LoadingdetailsMapper;
import com.c2psi.bmv1.loading.loadingdetails.models.Loadingdetails;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
import com.c2psi.bmv1.product.article.service.ArticleService;
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

@Service(value = "LoadingdetailsServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoadingdetailsServiceImpl implements LoadingdetailsService{

    final AppService appService;
    final LoadingdetailsDao loaddetailsDao;
    final LoadingdetailsValidator loaddetailsValidator;
    final LoadingdetailsMapper loaddetailsMapper;
    final LoadingdetailsSpecService loaddetailsSpecService;
    final LoadingService loadingService;
    final LoadingMapper loadingMapper;
    final ArticleService articleService;
    final ArticleMapper articleMapper;


    @Override
    public LoadingdetailsDto saveLoadingdetails(LoadingdetailsDto ldDto) {
        log.info("We start the loadingdetails saving process by checking if the ldDto is not null");
        if(ldDto == null){
            throw new NullValueException("Le loadingdetails envoye ne saurait etre null");
        }
        log.info("We continue the saving process by validate the ldDto");
        List<String> errorsDto = loaddetailsValidator.validate(ldDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le loadingdetailsDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.LOADINGDETAILS_NOT_VALID.name());
        }
        log.info("We continue the saving process by validate the ld associate to the ldDto");
        List<String> errors = loaddetailsValidator.validate(loaddetailsMapper.dtoToEntity(ldDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le loadingdetails envoye n'est pas valide ", errors,
                    ErrorCode.LOADINGDETAILS_NOT_VALID.name());
        }
        log.info("We continue the saving process by ensure that the loadingdetails will be unique");
        if(!isLoadingdetailsAttributeUsable(ldDto.getLdArticleId(), ldDto.getLdLoadingId())){
            throw new DuplicateEntityException("Il existe deja un loadingdetails pour cet article dans ce loading ",
                    ErrorCode.LOADINGDETAILS_DUPLICATED.name());
        }
        log.info("We continue the saving process by preparing the loadingdetails to save");
        //Loadingdetails loadingdetailsToSave = loaddetailsMapper.dtoToEntity(ldDto);

        /*******
         * loadingdetailsToUpdate.setLdArticle(articleMapper.dtoToEntity(articleService.getArticleById(
         *                 ldDto.getLdArticleId())));
         *         loadingdetailsToUpdate.setLdLoading(loadingMapper.dtoToEntity(loadingService.getLoadingById(
         *                 ldDto.getLdLoadingId())));
         *         loadingdetailsToUpdate.setQuantityReturn(ldDto.getQuantityReturn());
         *         loadingdetailsToUpdate.setQuantityTaken(ldDto.getQuantityTaken());
         */
        Loadingdetails loadingdetailsToSave = Loadingdetails.builder()
                .ldArticle(articleMapper.dtoToEntity(articleService.getArticleById(ldDto.getLdArticleId())))
                .ldLoading(loadingMapper.dtoToEntity(loadingService.getLoadingById(ldDto.getLdLoadingId())))
                .quantityReturn(ldDto.getQuantityReturn())
                .quantityTaken(ldDto.getQuantityTaken())
                .build();

        return loaddetailsMapper.entityToDto(loaddetailsDao.save(loadingdetailsToSave));
    }

    Boolean isLoadingdetailsAttributeUsable(Long articleId, Long loadingId){
        if(articleId == null || loadingId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<Loadingdetails> optionalLoadingdetails = loaddetailsDao.findLoadingdetailsByArticleInLoading(articleId,
                loadingId);
        return optionalLoadingdetails.isEmpty();
    }

    @Override
    public LoadingdetailsDto updateLoadingdetails(LoadingdetailsDto ldDto) {
        log.info("We start the loadingdetails updating process by checking if the ldDto is not null");
        if(ldDto == null){
            throw new NullValueException("Le loadingdetails envoye pour update ne saurait etre null");
        }
        log.info("We continue the loadingdetails updating process by checking if the id if the ldDto is not null");
        if(ldDto.getId() == null){
            throw new NullValueException("L'id du loadingdetails envoye pour update ne saurait etre null");
        }
        log.info("We continue the loadingdetails updating process by validate the ldDto");
        List<String> errorsDto = loaddetailsValidator.validate(ldDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le loadingdetails envoye n'est pas valide ", errorsDto,
                    ErrorCode.LOADINGDETAILS_NOT_VALID.name());
        }
        List<String> errors = loaddetailsValidator.validate(loaddetailsMapper.dtoToEntity(ldDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le loadingdetails envoye n'est pas valide ", errors,
                    ErrorCode.LOADINGDETAILS_NOT_VALID.name());
        }
        log.info("We continue the updating process by retrieve the loadingdetails to update");
        Optional<Loadingdetails> optionalLoadingdetails = loaddetailsDao.findLoadingdetailsById(ldDto.getId());
        if(!optionalLoadingdetails.isPresent()){
            throw new ModelNotFoundException("Aucun loadingdetails n'existe dans le systeme avec l'id envoye",
                    ErrorCode.LOADINGDETAILS_NOT_FOUND.name());
        }
        Loadingdetails loadingdetailsToUpdate = optionalLoadingdetails.get();
        log.info("We continue the updating process by ensure that if an element of the constraint is different, " +
                "the constraint will not be violated");

        if(!loadingdetailsToUpdate.getLdArticle().getId().equals(ldDto.getLdArticleId()) ||
                !loadingdetailsToUpdate.getLdLoading().getId().equals(ldDto.getLdLoadingId())){
            if(!isLoadingdetailsAttributeUsable(ldDto.getLdArticleId(), ldDto.getLdLoadingId())){
                throw new DuplicateEntityException("Il existe deja dans le loading indique un loading details de " +
                        "l'article indique ", ErrorCode.LOADINGDETAILS_DUPLICATED.name());
            }
        }

        log.info("WE continue the updating process by preparing the update");
        loadingdetailsToUpdate.setLdArticle(articleMapper.dtoToEntity(articleService.getArticleById(
                ldDto.getLdArticleId())));
        loadingdetailsToUpdate.setLdLoading(loadingMapper.dtoToEntity(loadingService.getLoadingById(
                ldDto.getLdLoadingId())));
        loadingdetailsToUpdate.setQuantityReturn(ldDto.getQuantityReturn());
        loadingdetailsToUpdate.setQuantityTaken(ldDto.getQuantityTaken());

        return loaddetailsMapper.entityToDto(loaddetailsDao.save(loadingdetailsToUpdate));
    }

    @Override
    public Boolean deleteLoadingdetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loadingdetails a delete ne saurait etre null");
        }
        Optional<Loadingdetails> optionalLoadingdetails = loaddetailsDao.findLoadingdetailsById(id);
        if(!optionalLoadingdetails.isPresent()){
            throw new ModelNotFoundException("Aucun loadingdetails n'existe avec l'id envoye",
                    ErrorCode.LOADINGDETAILS_NOT_FOUND.name());
        }
        if(!isLoadingdetailsDeleteable(optionalLoadingdetails.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer le laodingdetails sans causer de conflit",
                    ErrorCode.LOADINGDETAILS_NOT_DELETEABLE.name());
        }
        loaddetailsDao.delete(optionalLoadingdetails.get());
        return true;
    }

    Boolean isLoadingdetailsDeleteable(Loadingdetails loadingdetails){
        return loadingdetails.getLdLoading().getLoadOpen();
    }

    @Override
    public LoadingdetailsDto getLoadingdetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loadingdetails a delete ne saurait etre null");
        }
        Optional<Loadingdetails> optionalLoadingdetails = loaddetailsDao.findLoadingdetailsById(id);
        if(!optionalLoadingdetails.isPresent()){
            throw new ModelNotFoundException("Aucun loadingdetails n'existe avec l'id envoye",
                    ErrorCode.LOADINGDETAILS_NOT_FOUND.name());
        }
        return loaddetailsMapper.entityToDto(optionalLoadingdetails.get());
    }

    @Override
    public List<LoadingdetailsDto> getListofLoadingdetails(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return loaddetailsMapper.entityToDto(loaddetailsDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return loaddetailsMapper.entityToDto(loaddetailsDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return loaddetailsMapper.entityToDto(loaddetailsDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Loadingdetails> loaddetailsSpecification = loaddetailsSpecService.getLoadingdetailsSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return loaddetailsMapper.entityToDto(loaddetailsDao.findAll(loaddetailsSpecification));
    }

    @Override
    public PageofLoadingdetailsDto getPageofLoadingdetails(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Loadingdetails> loaddetailsPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            loaddetailsPage = loaddetailsDao.findAll(pageable);
            return getPageofLoadingdetailsDto(loaddetailsPage);
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
                loaddetailsPage = loaddetailsDao.findAll(pageable);
                return getPageofLoadingdetailsDto(loaddetailsPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                loaddetailsPage = loaddetailsDao.findAll(pageable);
                return getPageofLoadingdetailsDto(loaddetailsPage);
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
            Specification<Loadingdetails> loadingdetailsSpecification = loaddetailsSpecService.
                    getLoadingdetailsSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            loaddetailsPage = loaddetailsDao.findAll(loadingdetailsSpecification, pageable);
            return getPageofLoadingdetailsDto(loaddetailsPage);

        }
    }

    @Override
    public Boolean isLoadingdetailsExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du loadingdetails a delete ne saurait etre null");
        }
        Optional<Loadingdetails> optionalLoadingdetails = loaddetailsDao.findLoadingdetailsById(id);
        return optionalLoadingdetails.isPresent();
    }

    PageofLoadingdetailsDto getPageofLoadingdetailsDto(Page<Loadingdetails> loadingdetailsPage){
        PageofLoadingdetailsDto pageofLoadingdetailsDto = new PageofLoadingdetailsDto();
        pageofLoadingdetailsDto.setContent(loaddetailsMapper.entityToDto(loadingdetailsPage.getContent()));
        pageofLoadingdetailsDto.setCurrentPage(loadingdetailsPage.getNumber());
        pageofLoadingdetailsDto.setPageSize(loadingdetailsPage.getSize());
        pageofLoadingdetailsDto.setTotalElements(loadingdetailsPage.getTotalElements());
        pageofLoadingdetailsDto.setTotalPages(loadingdetailsPage.getTotalPages());

        return pageofLoadingdetailsDto;

    }


}
