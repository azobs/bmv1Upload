package com.c2psi.bmv1.sale.backin.backindetails.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
import com.c2psi.bmv1.product.article.service.ArticleService;
import com.c2psi.bmv1.sale.backin.backin.mappers.BackinMapper;
import com.c2psi.bmv1.sale.backin.backin.services.BackinService;
import com.c2psi.bmv1.sale.backin.backindetails.dao.BackindetailsDao;
import com.c2psi.bmv1.sale.backin.backindetails.errorCode.ErrorCode;
import com.c2psi.bmv1.sale.backin.backindetails.mappers.BackindetailsMapper;
import com.c2psi.bmv1.sale.backin.backindetails.models.Backindetails;
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

@Service(value = "BackindetailsServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BackindetailsServiceImpl implements BackindetailsService{
    final AppService appService;
    final BackindetailsValidator backindetailsValidator;
    final BackindetailsMapper backindetailsMapper;
    final BackindetailsDao backindetailsDao;
    final BackindetailsSpecService backindetailsSpecService;
    final ArticleService articleService;
    final ArticleMapper articleMapper;
    final BackinService backinService;
    final BackinMapper backinMapper;


    @Override
    public BackindetailsDto saveBackindetails(BackindetailsDto backindetailsDto) {
        log.info("We start saving the backindetails by checking if it is not null");
        if(backindetailsDto == null){
            throw new NullValueException("Le backindetailsDto envoye ne saurait etre null");
        }
        log.info("We continue saving the backindetails by validate the backindetailsDto");
        List<String> errorsDto = backindetailsValidator.validate(backindetailsDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le backindetailsDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.BACKINDETAILS_NOT_VALID.name());
        }
        log.info("We continue saving the backindetails by validate the backindetails associate to the backindetailsDto");
        List<String> errors = backindetailsValidator.validate(backindetailsMapper.dtoToEntity(backindetailsDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le backindetailsDto envoye n'est pas valide ", errors,
                    ErrorCode.BACKINDETAILS_NOT_VALID.name());
        }
        log.info("We continue saving the backindetails by ensure that the backindetails will be unique");
        if(!isBackindetailsExitforArticleInBackin(backindetailsDto.getBidArticleId(), backindetailsDto.getBidBackinId())){
            throw new DuplicateEntityException("Il existe deja un backindetails pour le meme article dans le backin ",
                    ErrorCode.BACKINDETAILS_DUPLICATED.name());
        }
        log.info("We continue saving the backindetails by preparing the backindetails to save");
        //Backindetails backindetailsToSave = backindetailsMapper.dtoToEntity(backindetailsDto);
        /****
         * backindetailsToUpdate.setBidArticle(articleMapper.dtoToEntity(articleService.getArticleById(
         *                 backindetailsDto.getBidArticleId())));
         *         backindetailsToUpdate.setBidBackin(backinMapper.dtoToEntity(backinService.getBackinById(
         *                 backindetailsDto.getBidBackinId())));
         *         backindetailsToUpdate.setBidComment(backindetailsDto.getBidComment());
         *         backindetailsToUpdate.setBidQuantity(backindetailsDto.getBidQuantity());
         */
        Backindetails backindetailsToSave = Backindetails.builder()
                .bidArticle(articleMapper.dtoToEntity(articleService.getArticleById(backindetailsDto.getBidArticleId())))
                .bidBackin(backinMapper.dtoToEntity(backinService.getBackinById(backindetailsDto.getBidBackinId())))
                .bidComment(backindetailsDto.getBidComment())
                .bidQuantity(backindetailsDto.getBidQuantity())
                .build();
        return backindetailsMapper.entityToDto(backindetailsDao.save(backindetailsToSave));
    }

    Boolean isBackindetailsExitforArticleInBackin(Long articleId, Long backinId){
        if(articleId == null || backinId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Backindetails> optionalBackindetails = backindetailsDao.findBackindetailsByAttributes(articleId, backinId);
        return optionalBackindetails.isEmpty();
    }

    @Override
    public BackindetailsDto updateBackindetails(BackindetailsDto backindetailsDto) {
        log.info("We start updating the backindetails by checking if it is not null");
        if(backindetailsDto == null){
            throw new NullValueException("Le backindetailsDto ne saurait etre null");
        }
        log.info("We continue updating the backindetails by checking if it the id of the backindetails is not null");
        if(backindetailsDto.getId() == null){
            throw new NullValueException("L'id du backindetails a update ne saurait etre null");
        }
        log.info("We continue updating the backindetails by validate the backindetailsDto");
        List<String> errorsDto = backindetailsValidator.validate(backindetailsDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le backindetailsDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.BACKINDETAILS_NOT_VALID.name());
        }
        log.info("We continue updating the backindetails by validate the backindetails associate");
        List<String> errors = backindetailsValidator.validate(backindetailsMapper.dtoToEntity(backindetailsDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le backindetailsDto envoye n'est pas valide ", errors,
                    ErrorCode.BACKINDETAILS_NOT_VALID.name());
        }
        log.info("We continue updating the backindetails by retrieve the backindetails to update");
        Optional<Backindetails> optionalBackindetails = backindetailsDao.findBackindetailsById(backindetailsDto.getId());
        if(!optionalBackindetails.isPresent()){
            throw new ModelNotFoundException("Aucun backindetails n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.BACKINDETAILS_NOT_FOUND.name());
        }
        Backindetails backindetailsToUpdate = optionalBackindetails.get();
        log.info("We continue updating the backindetails by ensure the resulting backindetails will be unique");
        boolean articleUpdated = !backindetailsToUpdate.getBidArticle().getId().equals(backindetailsDto.getBidArticleId());
        boolean backinUpdated = !backindetailsToUpdate.getBidBackin().getId().equals(backindetailsDto.getBidBackinId());
        if(articleUpdated || backinUpdated){
            if(!isBackindetailsExitforArticleInBackin(backindetailsDto.getBidArticleId(), backindetailsDto.getBidBackinId())){
                throw new DuplicateEntityException("Il existe deja un backindetails pour le meme article dans le backin ",
                        ErrorCode.BACKINDETAILS_DUPLICATED.name());
            }
        }
        log.info("We continue updating the backindetails by preparing the updated version");
        backindetailsToUpdate.setBidArticle(articleMapper.dtoToEntity(articleService.getArticleById(
                backindetailsDto.getBidArticleId())));
        backindetailsToUpdate.setBidBackin(backinMapper.dtoToEntity(backinService.getBackinById(
                backindetailsDto.getBidBackinId())));
        backindetailsToUpdate.setBidComment(backindetailsDto.getBidComment());
        backindetailsToUpdate.setBidQuantity(backindetailsDto.getBidQuantity());


        return backindetailsMapper.entityToDto(backindetailsDao.save(backindetailsToUpdate));
    }

    @Override
    public Boolean deleteBackindetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du backindetails a delete ne saurait etre null");
        }
        Optional<Backindetails> optionalBackindetails = backindetailsDao.findBackindetailsById(id);
        if(!optionalBackindetails.isPresent()){
            throw new ModelNotFoundException("Aucun backindetails n'existe avec l'id envoye",
                    ErrorCode.BACKINDETAILS_NOT_FOUND.name());
        }
        if(!isBackindetailsDeleteable(optionalBackindetails.get())){
            throw new EntityNotDeleatableException("Il n'est pas possible de supprimer ce backindetails sans causer " +
                    "de conflits ", ErrorCode.BACKINDETAILS_NOT_DELETEABLE.name());
        }
        backindetailsDao.delete(optionalBackindetails.get());
        return true;
    }

    Boolean isBackindetailsDeleteable(Backindetails backindetails){
        return true;
    }

    @Override
    public BackindetailsDto getBackindetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du backindetails a delete ne saurait etre null");
        }
        Optional<Backindetails> optionalBackindetails = backindetailsDao.findBackindetailsById(id);
        if(!optionalBackindetails.isPresent()){
            throw new ModelNotFoundException("Aucun backindetails n'existe avec l'id envoye",
                    ErrorCode.BACKINDETAILS_NOT_FOUND.name());
        }
        return backindetailsMapper.entityToDto(optionalBackindetails.get());
    }

    @Override
    public List<BackindetailsDto> getListofBackindetails(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return backindetailsMapper.entityToDto(backindetailsDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return backindetailsMapper.entityToDto(backindetailsDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return backindetailsMapper.entityToDto(backindetailsDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Backindetails> backindetailsSpecification = backindetailsSpecService.
                getBackindetailsSpecification(filterRequest.getFilters(), filterRequest.getLogicOperator(),
                        filterRequest.getOrderby());
        return backindetailsMapper.entityToDto(backindetailsDao.findAll(backindetailsSpecification));
    }

    @Override
    public PageofBackindetailsDto getPageofBackindetails(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Backindetails> backindetailsPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            backindetailsPage = backindetailsDao.findAll(pageable);
            return getPageofBackindetailsDto(backindetailsPage);
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
                backindetailsPage = backindetailsDao.findAll(pageable);
                return getPageofBackindetailsDto(backindetailsPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                backindetailsPage = backindetailsDao.findAll(pageable);
                return getPageofBackindetailsDto(backindetailsPage);
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
            Specification<Backindetails> backindetailsSpecification = backindetailsSpecService.getBackindetailsSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            backindetailsPage = backindetailsDao.findAll(backindetailsSpecification, pageable);
            return getPageofBackindetailsDto(backindetailsPage);
        }
    }

    @Override
    public Boolean isBackindetailsExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du backindetails a delete ne saurait etre null");
        }
        Optional<Backindetails> optionalBackindetails = backindetailsDao.findBackindetailsById(id);
        return optionalBackindetails.isPresent();
    }

    PageofBackindetailsDto getPageofBackindetailsDto(Page<Backindetails> backindetailsPage){
        PageofBackindetailsDto pageofBackindetailsDto = new PageofBackindetailsDto();
        pageofBackindetailsDto.setContent(backindetailsMapper.entityToDto(backindetailsPage.getContent()));
        pageofBackindetailsDto.setCurrentPage(backindetailsPage.getNumber());
        pageofBackindetailsDto.setPageSize(backindetailsPage.getSize());
        pageofBackindetailsDto.setTotalElements(backindetailsPage.getTotalElements());
        pageofBackindetailsDto.setTotalPages(backindetailsPage.getTotalPages());

        return pageofBackindetailsDto;

    }
}
