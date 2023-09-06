package com.c2psi.bmv1.inventory.inventoryline.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.inventory.inventory.mappers.InventoryMapper;
import com.c2psi.bmv1.inventory.inventory.services.InventoryService;
import com.c2psi.bmv1.inventory.inventoryline.dao.InventorylineDao;
import com.c2psi.bmv1.inventory.inventoryline.errorCode.ErrorCode;
import com.c2psi.bmv1.inventory.inventoryline.mappers.InventorylineMapper;
import com.c2psi.bmv1.inventory.inventoryline.models.Inventoryline;
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

@Service(value = "InventorylineServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventorylineServiceImpl implements InventorylineService{

    final AppService appService;
    final InventorylineDao invlineDao;
    final InventorylineValidator invlineValidator;
    final InventorylineMapper invlineMapper;
    final InventorylineSpecService invlineSpecService;
    final ArticleService artService;
    final ArticleMapper artMapper;
    final InventoryService invService;
    final InventoryMapper invMapper;

    @Override
    public InventorylineDto saveInventoryline(InventorylineDto invlineDto) {
        log.info("We start saving the inventoryline by checking if the invlineDto is not null");
        if(invlineDto == null){
            throw new NullValueException("Le inventoryline envoye ne saurait etre null");
        }
        log.info("We continue saving the inventoryline by validating the invlineDto ");
        List<String> errorsDto = invlineValidator.validate(invlineDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'inventorylineDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.INVENTORYLINE_NOT_VALID.name());
        }
        log.info("We continue saving the inventoryline by validating the invline ");
        List<String> errors = invlineValidator.validate(invlineMapper.dtoToEntity(invlineDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'inventoryline envoye n'est pas valide ", errors,
                    ErrorCode.INVENTORYLINE_NOT_VALID.name());
        }
        log.info("We continue the saving process by ensure that the constraint will not be violated");
        if(!isInventorylineAttributesUsable(invlineDto.getInventoryId(), invlineDto.getInvlineArticleId())){
            throw new DuplicateEntityException("Il existe deja une ligne d'inventaire (inventoryline) pour ce article " +
                    "dans l'inventaire indique ", ErrorCode.INVENTORYLINE_DUPLICATED.name());
        }
        log.info("We continue the saving process by preparing the inventoryline to save");
        //Inventoryline invlineToSave = invlineMapper.dtoToEntity(invlineDto);
        /****
         * invlineToUpdate.setInvlineComment(invlineDto.getInvlineComment());
         *         invlineToUpdate.setRealqteinStock(invlineDto.getRealqteinStock());
         *         invlineToUpdate.setLogicqteinStock(invlineDto.getLogicqteinStock());
         *         invlineToUpdate.setInvlineArticle(artMapper.dtoToEntity(artService.getArticleById(invlineDto.getInvlineArticleId())));
         *         invlineToUpdate.setInventory(invMapper.dtoToEntity(invService.getInventoryById(invlineDto.getInventoryId())));
         */
        Inventoryline invlineToSave = Inventoryline.builder()
                .invlineComment(invlineDto.getInvlineComment())
                .realqteinStock(invlineDto.getRealqteinStock())
                .logicqteinStock(invlineDto.getLogicqteinStock())
                .invlineArticle(artMapper.dtoToEntity(artService.getArticleById(invlineDto.getInvlineArticleId())))
                .inventory(invMapper.dtoToEntity(invService.getInventoryById(invlineDto.getInventoryId())))
                .build();
        log.info("We can now save the inventoryline");
        Inventoryline invlineSaved = invlineDao.save(invlineToSave);
        return invlineMapper.entityToDto(invlineSaved);
    }

    Boolean isInventorylineAttributesUsable(Long invId, Long articleId){
        if(invId == null || articleId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<Inventoryline> optionalInventoryline = invlineDao.findInventorylineOfArticleInInventory(articleId, invId);
        return optionalInventoryline.isEmpty();
    }

    @Override
    public InventorylineDto updateInventoryline(InventorylineDto invlineDto) {
        log.info("We start updating the inventoryline by checking if the invlineDto is not null");
        if(invlineDto == null){
            throw new NullValueException("Le inventoryline envoye ne saurait etre null");
        }
        log.info("We continue updating the inventoryline by checking if the invlinedto id is not null");
        if(invlineDto.getId() == null){
            throw new NullValueException("L'id du inventoryline a update ne saurait etre null");
        }
        log.info("We continue updating the inventoryline by validating the invlineDto ");
        List<String> errorsDto = invlineValidator.validate(invlineDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'inventorylineDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.INVENTORYLINE_NOT_VALID.name());
        }
        log.info("We continue updating the inventoryline by validating the invline ");
        List<String> errors = invlineValidator.validate(invlineMapper.dtoToEntity(invlineDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'inventoryline envoye n'est pas valide ", errors,
                    ErrorCode.INVENTORYLINE_NOT_VALID.name());
        }
        log.info("We continue the updating process by retrieve the inventoryline to update");
        Optional<Inventoryline> optionalInventoryline = invlineDao.findInventorylineById(invlineDto.getId());
        if(!optionalInventoryline.isPresent()){
            throw new ModelNotFoundException("Aucun inventoryline n'existe avec l'id envoye ",
                    ErrorCode.INVENTORYLINE_NOT_FOUND.name());
        }
        Inventoryline invlineToUpdate = optionalInventoryline.get();
        log.info("We continue the updating process by ensure that the constraint will not be violated");
        if(!invlineToUpdate.getInvlineArticle().getId().equals(invlineDto.getInvlineArticleId()) ||
                !invlineToUpdate.getInventory().getId().equals(invlineDto.getInventoryId())){
            if(!isInventorylineAttributesUsable(invlineDto.getInventoryId(), invlineDto.getInvlineArticleId())){
                throw new DuplicateEntityException("Il existe deja une ligne d'inventaire (inventoryline) pour ce article " +
                        "dans l'inventaire indique ", ErrorCode.INVENTORYLINE_DUPLICATED.name());
            }
        }
        log.info("We continue the updating process by prepare it");
        invlineToUpdate.setInvlineComment(invlineDto.getInvlineComment());
        invlineToUpdate.setRealqteinStock(invlineDto.getRealqteinStock());
        invlineToUpdate.setLogicqteinStock(invlineDto.getLogicqteinStock());
        invlineToUpdate.setInvlineArticle(artMapper.dtoToEntity(artService.getArticleById(invlineDto.getInvlineArticleId())));
        invlineToUpdate.setInventory(invMapper.dtoToEntity(invService.getInventoryById(invlineDto.getInventoryId())));

        Inventoryline inventorylineUpdated = invlineDao.save(invlineToUpdate);

        return invlineMapper.entityToDto(inventorylineUpdated);
    }

    @Override
    public Boolean deleteInventorylineById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'inventoryline a delete ne saurait etre null");
        }
        Optional<Inventoryline> optionalInventoryline = invlineDao.findInventorylineById(id);
        if(!optionalInventoryline.isPresent()){
            throw new ModelNotFoundException("Aucun inventoryline n'existe en BD avec l'id envoye",
                    ErrorCode.INVENTORYLINE_NOT_FOUND.name());
        }
        if(!isInventorylineDeleteable(optionalInventoryline.get())){
            throw new EntityNotDeleatableException("Il n'est pas possible de supprimer cet inventoryline sans " +
                    "causer de conflit ", ErrorCode.INVENTORYLINE_NOT_DELETEABLE.name());
        }
        invlineDao.delete(optionalInventoryline.get());
        return true;
    }

    Boolean isInventorylineDeleteable(Inventoryline invline){
        return true;
    }

    @Override
    public InventorylineDto getInventorylineById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'inventoryline a delete ne saurait etre null");
        }
        Optional<Inventoryline> optionalInventoryline = invlineDao.findInventorylineById(id);
        if(!optionalInventoryline.isPresent()){
            throw new ModelNotFoundException("Aucun inventoryline n'existe en BD avec l'id envoye",
                    ErrorCode.INVENTORYLINE_NOT_FOUND.name());
        }

        return invlineMapper.entityToDto(optionalInventoryline.get());
    }

    @Override
    public List<InventorylineDto> getListofInventoryline(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return invlineMapper.entityToDto(invlineDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return invlineMapper.entityToDto(invlineDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return invlineMapper.entityToDto(invlineDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Inventoryline> invlineSpecification = invlineSpecService.getInventorylineSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return invlineMapper.entityToDto(invlineDao.findAll(invlineSpecification));
    }

    @Override
    public PageofInventorylineDto getPageofInventoryline(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Inventoryline> invlinePage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            invlinePage = invlineDao.findAll(pageable);
            return getPageofInventorylineDto(invlinePage);
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
                invlinePage = invlineDao.findAll(pageable);
                return getPageofInventorylineDto(invlinePage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                invlinePage = invlineDao.findAll(pageable);
                return getPageofInventorylineDto(invlinePage);
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
            Specification<Inventoryline> invlineSpecification = invlineSpecService.getInventorylineSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            invlinePage = invlineDao.findAll(invlineSpecification, pageable);
            return getPageofInventorylineDto(invlinePage);

        }
    }

    @Override
    public Boolean isInventorylineExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'inventoryline a delete ne saurait etre null");
        }
        Optional<Inventoryline> optionalInventoryline = invlineDao.findInventorylineById(id);
        return optionalInventoryline.isPresent();
    }

    PageofInventorylineDto getPageofInventorylineDto(Page<Inventoryline> invlinePage){
        PageofInventorylineDto pageofInventorylineDto = new PageofInventorylineDto();
        pageofInventorylineDto.setContent(invlineMapper.entityToDto(invlinePage.getContent()));
        pageofInventorylineDto.setCurrentPage(invlinePage.getNumber());
        pageofInventorylineDto.setPageSize(invlinePage.getSize());
        pageofInventorylineDto.setTotalElements(invlinePage.getTotalElements());
        pageofInventorylineDto.setTotalPages(invlinePage.getTotalPages());

        return pageofInventorylineDto;
    }

}
